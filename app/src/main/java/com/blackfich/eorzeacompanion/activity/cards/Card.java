package com.blackfich.eorzeacompanion.activity.cards;

import com.blackfich.eorzeacompanion.util.XmlUtil;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.bean.XmlFactory;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class Card implements Versionable {

    private String id;
    private String name;
    private int version;
    private int stars;
    private String stats;
    private List<String> providers;

    public String getId() {
        return id;
    }

    public void seId(String id) {
        this.id = id;
    }

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

    public List<String> getProviders() {
        return providers;
    }

    public void setProviders(List<String> providers) {
        this.providers = providers;
    }

    public void fromXml(Element element) {
        XmlFactory.fromXml((Versionable) this, element);

        id = XmlUtil.getAttribueValue(element, "id");
        name = XmlUtil.getAttribueValue(element, "name");
        stars = XmlUtil.getAttribueValue(element, "stars", 1);
        stats =  XmlUtil.getAttribueValue(element, "stats");
        providers = new ArrayList<String>();

    }
}
