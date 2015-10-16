package com.blackfich.eorzeacompanion.activity.gathering;

import com.blackfich.eorzeacompanion.util.XmlUtil;

import org.w3c.dom.Element;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class GatheringNodeItem {

    private String name;
    private int slot;
    private boolean variableSlot;
    private int stars;
    private boolean hidden;
    private int level;

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isVariableSlot() {
        return variableSlot;
    }

    public void setVariableSlot(boolean variableSlot) {
        this.variableSlot = variableSlot;
    }

    public void fromXml(Element element) {
        name = XmlUtil.getAttribueValue(element, "id");
        hidden = XmlUtil.getAttribueValue(element, "hidden", false);
        slot = XmlUtil.getAttribueValue(element, "slot", 0);
        stars = XmlUtil.getAttribueValue(element, "stars", 0);
        level = XmlUtil.getAttribueValue(element, "level", 1);
        variableSlot = XmlUtil.getAttribueValue(element, "variable", false);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GatheringNodeItem{");
        sb.append("name='").append(name).append('\'');
        sb.append(", slot=").append(slot);
        sb.append(", variableSlot=").append(variableSlot);
        sb.append(", stars=").append(stars);
        sb.append(", hidden=").append(hidden);
        sb.append(", level=").append(level);
        sb.append('}');
        return sb.toString();
    }
}
