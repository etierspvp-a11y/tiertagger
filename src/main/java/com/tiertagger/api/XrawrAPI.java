package com.tiertagger.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.tiertagger.TierTaggerMod;
import com.tiertagger.model.PlayerTierData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class XrawrAPI {
    private static final String BASE_URL = "https://xrawrtl.netlify.app/api/search/";
    private static final Gson GSON = new Gson();
    private static final int TIMEOUT = 5000; // 5 second timeout

    public static PlayerTierData searchPlayer(String username) {
        try {
            URL url = new URI(BASE_URL + username).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setRequestProperty("User-Agent", "TierTagger-Mod/1.0");

            int responseCode = connection.getResponseCode();
            
            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                    
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    
                    JsonObject json = GSON.fromJson(response.toString(), JsonObject.class);
                    
                    if (json != null && json.get("found").getAsBoolean()) {
                        return parsePlayerData(json);
                    }
                }
            } else if (responseCode == 404) {
                TierTaggerMod.LOGGER.debug("Player not found: " + username);
            } else {
                TierTaggerMod.LOGGER.warn("API returned status code: " + responseCode);
            }
            
            connection.disconnect();
        } catch (JsonSyntaxException e) {
            TierTaggerMod.LOGGER.error("Failed to parse API response for player: " + username, e);
        } catch (Exception e) {
            TierTaggerMod.LOGGER.error("Failed to fetch tier data for player: " + username, e);
        }
        
        return null;
    }

    private static PlayerTierData parsePlayerData(JsonObject json) {
        try {
            PlayerTierData data = new PlayerTierData();
            data.setName(json.get("name").getAsString());
            data.setTitle(json.get("title").getAsString());
            data.setPoints(json.get("points").getAsInt());
            data.setRegion(json.get("region").getAsString());
            data.setUpdatedAt(json.get("updatedAt").getAsString());
            
            // Parse tiers
            JsonObject tiersJson = json.getAsJsonObject("tiers");
            if (tiersJson != null) {
                tiersJson.keySet().forEach(key -> {
                    String tierValue = tiersJson.get(key).getAsString();
                    data.setTier(key, tierValue);
                });
            }
            
            return data;
        } catch (Exception e) {
            TierTaggerMod.LOGGER.error("Failed to parse player data", e);
            return null;
        }
    }
}
