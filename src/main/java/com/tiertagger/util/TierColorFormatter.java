package com.tiertagger.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class TierColorFormatter {
    // Map tier abbreviations to their color codes
    private static final Map<String, Integer> TIER_COLORS = new HashMap<>();

    static {
        // HT Tiers (Heated)
        TIER_COLORS.put("HT1", 0xE8B23A); // Yellow
        TIER_COLORS.put("HT2", 0xE8B23A); // Yellow
        TIER_COLORS.put("HT3", 0xE8B23A); // Yellow
        TIER_COLORS.put("HT4", 0xB794F6); // Purple
        TIER_COLORS.put("HT5", 0xB794F6); // Purple

        // LT Tiers (Low Tier)
        TIER_COLORS.put("LT1", 0xE8B23A); // Yellow
        TIER_COLORS.put("LT2", 0xFFFFFF); // White
        TIER_COLORS.put("LT3", 0xFF9654); // Orange
        TIER_COLORS.put("LT4", 0xB794F6); // Purple
        TIER_COLORS.put("LT5", 0xB794F6); // Purple
    }

    public static Text formatTierTag(String tier) {
        if (tier == null || tier.isEmpty() || tier.equals("Unknown")) {
            return Text.literal("[?]").formatted(Formatting.DARK_GRAY);
        }

        Integer color = TIER_COLORS.getOrDefault(tier, 0xAAAAAA);
        MutableText tierText = Text.literal("[" + tier + "]")
                .styled(style -> style.withColor(color));
        return tierText;
    }

    public static Text formatPlayerWithTier(String playerName, String tier) {
        MutableText text = Text.literal(playerName);
        text.append(" ");
        text.append(formatTierTag(tier));
        return text;
    }

    public static String getTierForGamemode(java.util.Map<String, String> tiers) {
        // Priority: uhc > nethpot > diapot > axe > sword > crystal > smp > mace
        String[] priority = {"uhc", "nethpot", "diapot", "axe", "sword", "crystal", "smp", "mace"};
        
        for (String gamemode : priority) {
            String tier = tiers.get(gamemode);
            if (tier != null && !tier.isEmpty()) {
                return tier;
            }
        }
        return null;
    }
}
