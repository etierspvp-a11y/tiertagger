package com.tiertagger.cache;

import com.tiertagger.model.PlayerTierData;
import java.util.HashMap;
import java.util.Map;

public class TierCache {
    private static final Map<String, CachedTierData> CACHE = new HashMap<>();
    private static final long CACHE_DURATION = 3600000; // 1 hour in milliseconds

    public static void initialize() {
        // Initialize cache system
    }

    public static void cache(String username, PlayerTierData data) {
        CACHE.put(username.toLowerCase(), new CachedTierData(data, System.currentTimeMillis()));
    }

    public static PlayerTierData get(String username) {
        CachedTierData cached = CACHE.get(username.toLowerCase());
        if (cached != null && !cached.isExpired()) {
            return cached.data;
        } else if (cached != null) {
            CACHE.remove(username.toLowerCase());
        }
        return null;
    }

    public static void clear() {
        CACHE.clear();
    }

    public static boolean isCached(String username) {
        CachedTierData cached = CACHE.get(username.toLowerCase());
        return cached != null && !cached.isExpired();
    }

    private static class CachedTierData {
        final PlayerTierData data;
        final long timestamp;

        CachedTierData(PlayerTierData data, long timestamp) {
            this.data = data;
            this.timestamp = timestamp;
        }

        boolean isExpired() {
            return System.currentTimeMillis() - timestamp > CACHE_DURATION;
        }
    }
}
