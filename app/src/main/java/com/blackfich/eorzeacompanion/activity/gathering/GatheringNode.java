package com.blackfich.eorzeacompanion.activity.gathering;

import com.blackfich.eorzeacompanion.util.XmlUtil;
import com.blackfich.eorzeacompanion.util.bean.Favoritable;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.bean.XmlFactory;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public abstract class GatheringNode implements Comparable<GatheringNode>, Timable, Versionable, Mapable, Favoritable {

    protected String type;
    protected boolean alternate;
    protected String map;
    protected int x;
    protected int y;
    protected int startTime;
    protected int endTime = -1;
    protected boolean twicePerDay;
    protected int version;
    protected List<GatheringNodeItem> items;
    protected boolean favorite;

    public GatheringNode() {
        this.items = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public boolean isAlternate() {
        return alternate;
    }

    public void setAlternate(boolean alternate) {
        this.alternate = alternate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        if (endTime == -1) {
            Calendar cal = new GregorianCalendar(1970, 1, 1, (int) startTime / 100, startTime % 100);
            cal.add(Calendar.MINUTE, getDuration());
            endTime = 100 * cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE);
        }
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public boolean isTwicePerDay() {
        return twicePerDay;
    }

    public void setTwicePerDay(boolean twicePerDay) {
        this.twicePerDay = twicePerDay;
    }

    /*
         * Returns the node's availablily in Eorzean minutes.
         * This depends on the type of node (unspoilled, legend ...) and version (ARR/HW)
         * @return the node's availablily in Eorzean minutes
         */
    public abstract int getDuration();

    public List<GatheringNodeItem> getItems() {
        return items;
    }

    public void setItems(List<GatheringNodeItem> items) {
        this.items = items;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public abstract GatheringNode duplicate();

    protected void clone(GatheringNode clone) {
        clone.type = type;
        clone.alternate = alternate;
        clone.map = map;
        clone.version = version;
        clone.x = x;
        clone.y = y;
        clone.twicePerDay = twicePerDay;
        clone.favorite = favorite;
        clone.items = new ArrayList<GatheringNodeItem>();
        clone.items.addAll(this.items);
    }

    @Override
    public int compareTo(GatheringNode another) {
        if (this.startTime < another.startTime) {
            return -1;
        } else if (this.startTime > another.startTime) {
            return 1;
        } else {
            return this.map.compareTo(another.map);
        }
    }

    public void fromXml(Element element) {
        XmlFactory.fromXml((Versionable) this, element);
        XmlFactory.fromXml((Mapable) this, element);
        XmlFactory.fromXml((Timable) this, element);

        type = XmlUtil.getAttribueValue(element, "type");
        alternate = XmlUtil.getAttribueValue(element, "alternate", false);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GatheringNode{");
        sb.append("type='").append(type).append('\'');
        sb.append(", alternate=").append(alternate);
        sb.append(", map='").append(map).append('\'');
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", twicePerDay=").append(twicePerDay);
        sb.append(", version=").append(version);
        sb.append(", items=").append(items);
        sb.append(", favorite=").append(favorite);
        sb.append('}');
        return sb.toString();
    }
}
