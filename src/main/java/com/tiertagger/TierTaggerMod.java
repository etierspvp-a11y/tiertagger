package com.tiertagger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketS2CEvents;
import com.tiertagger.cache.TierCache;
import com.tiertagger.listener.PlayerJoinListener;
import com.tiertagger.api.XrawrAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TierTaggerMod implements ModInitializer {
	public static final String MOD_ID = "tiertagger";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing TierTagger Mod");
		
		// Initialize cache
		TierCache.initialize();
		
		// Register player join listener
		PlayerJoinListener.register();
		
		LOGGER.info("TierTagger Mod initialized successfully");
	}
}
