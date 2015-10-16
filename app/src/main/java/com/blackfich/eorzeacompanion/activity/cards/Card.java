package com.blackfich.eorzeacompanion.activity.cards;

import com.blackfich.eorzeacompanion.util.bean.Versionable;

import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class Card implements Versionable {

    private String name;
    private int version;
    private int stars;
    private String stats;
    private List<CardProvider> providers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getStats() {
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public List<CardProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<CardProvider> providers) {
        this.providers = providers;
    }
}
