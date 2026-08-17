package com.tiertagger.mixin;

import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.tiertagger.cache.TierCache;
import com.tiertagger.model.PlayerTierData;
import com.tiertagger.util.TierColorFormatter;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Inject(method = "addMessage", at = @At("HEAD"))
    private void onAddMessage(Text message, CallbackInfo ci) {
        // Process chat messages to add tier tags
        String messageText = message.getString();
        
        // Check if this is a join message
        if (messageText.contains("joined the game") || messageText.contains("left the game")) {
            String[] parts = messageText.split(" ");
            if (parts.length > 0) {
                String playerName = parts[0];
                PlayerTierData tierData = TierCache.get(playerName);
                
                if (tierData != null) {
                    String highestTier = TierColorFormatter.getTierForGamemode(tierData.getTiers());
                    if (highestTier != null) {
                        // Tier data will be shown in the join message from server
                    }
                }
            }
        }
    }
}
