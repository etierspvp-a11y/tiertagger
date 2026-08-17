package com.tiertagger.listener;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import com.tiertagger.TierTaggerMod;
import com.tiertagger.api.XrawrAPI;
import com.tiertagger.cache.TierCache;
import com.tiertagger.model.PlayerTierData;
import com.tiertagger.util.TierColorFormatter;

public class PlayerJoinListener {
    
    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, server, networkManager) -> {
            ServerPlayerEntity player = handler.getPlayer();
            String playerName = player.getName().getString();
            
            // Fetch tier data asynchronously to avoid blocking
            new Thread(() -> {
                try {
                    PlayerTierData tierData = null;
                    
                    // Check cache first
                    if (TierCache.isCached(playerName)) {
                        tierData = TierCache.get(playerName);
                        TierTaggerMod.LOGGER.info("Using cached tier data for: " + playerName);
                    } else {
                        // Fetch from API
                        tierData = XrawrAPI.searchPlayer(playerName);
                        
                        if (tierData != null) {
                            TierCache.cache(playerName, tierData);
                            TierTaggerMod.LOGGER.info("Fetched tier data for: " + playerName);
                        } else {
                            TierTaggerMod.LOGGER.info("No tier data found for: " + playerName);
                        }
                    }
                    
                    // Broadcast to all players if tier data found
                    if (tierData != null) {
                        String highestTier = TierColorFormatter.getTierForGamemode(tierData.getTiers());
                        if (highestTier != null) {
                            Text joinMessage = Text.literal(playerName + " ");
                            joinMessage.append(TierColorFormatter.formatTierTag(highestTier));
                            joinMessage.append(" joined the game");
                            
                            // Send to all players
                            server.getPlayerManager().broadcast(joinMessage, false);
                        }
                    }
                } catch (Exception e) {
                    TierTaggerMod.LOGGER.error("Error fetching tier data for " + playerName, e);
                }
            }).start();
        });
    }
}
