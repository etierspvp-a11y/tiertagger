package com.tiertagger.model;

import java.util.HashMap;
import java.util.Map;

public class PlayerTierData {
    private String name;
    private String title;
    private int points;
    private String region;
    private String updatedAt;
    private Map<String, String> tiers = new HashMap<>();

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, String> getTiers() {
        return tiers;
    }

    public String getTier(String gamemode) {
        return tiers.getOrDefault(gamemode, "Unknown");
    }

    public void setTier(String gamemode, String tier) {
        tiers.put(gamemode, tier);
    }

    @Override
    public String toString() {
        return "PlayerTierData{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", points=" + points +
                ", region='" + region + '\'' +
                ", tiers=" + tiers +
                '}';
    }
}
