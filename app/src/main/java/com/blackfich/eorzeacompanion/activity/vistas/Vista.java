package com.blackfich.eorzeacompanion.activity.vistas;

import com.blackfich.eorzeacompanion.util.Config;
import com.blackfich.eorzeacompanion.util.XmlUtil;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.bean.Weatherable;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class Vista implements Comparable<Vista>, Versionable, Timable, Mapable, Weatherable {

    protected int n;
    protected String map;
    protected int x;
    protected int y;
    protected int startTime;
    protected int endTime = -1;
    protected int version;
    protected List<String> weathers;
    protected String emote;

    public Vista() {
        this.weathers = new ArrayList<>();
    }


    @Override
    public int compareTo(Vista another) {
        if (Config.getVistaSort() == Config.VISTA_SORT_TIME) {
            return compareToByTime(another);
        } else {
            return compareToByNumber(another);
        }
    }

    private int compareToByNumber(Vista another) {
        if (this.n < another.n) {
            return -1;
        } else if (this.n > another.n) {
            return 1;
        } else {
            return 0;
        }
    }

    private int compareToByTime(Vista another) {
        if (this.startTime < another.startTime) {
            return -1;
        } else if (this.startTime > another.startTime) {
            return 1;
        } else {
            return compareToByNumber(another);
        }
    }

    public void fromXml(Element element) {
        n = Integer.parseInt(XmlUtil.getAttribueValue(element, "n", "0"), 10);
        map = XmlUtil.getAttribueValue(element, "map");
        version = Integer.parseInt(XmlUtil.getAttribueValue(element, "version", "2"), 10);
        x = Integer.parseInt(XmlUtil.getAttribueValue(element, "x", "0"), 10);
        y = Integer.parseInt(XmlUtil.getAttribueValue(element, "y", "0"), 10);
        startTime = 100 * Integer.parseInt(XmlUtil.getAttribueValue(element, "start-time", "0"), 10);
        endTime = 100 * Integer.parseInt(XmlUtil.getAttribueValue(element, "end-time", "0"), 10);
        emote = XmlUtil.getAttribueValue(element, "emote");
        String weathers = XmlUtil.getAttribueValue(element, "weather");
        this.weathers.clear();
        while (weathers.indexOf(',') >= 0) {
            String weather = weathers.substring(0, weathers.indexOf(',')).trim();
            this.weathers.add(weather);
            weathers = weathers.substring(weathers.indexOf(',') + 1);
        }
        this.weathers.add(weathers);
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String getMap() {
        return map;
    }

    @Override
    public void setMap(String map) {
        this.map = map;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getStartTime() {
        return startTime;
    }

    @Override
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    @Override
    public int getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean isTwicePerDay() {
        return false;
    }

    @Override
    public void setTwicePerDay(boolean twicePerDay) {

    }

    public List<String> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<String> weathers) {
        this.weathers = weathers;
    }

    public String getEmote() {
        return emote;
    }

    public void setEmote(String emote) {
        this.emote = emote;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vista{");
        sb.append("n=").append(n);
        sb.append(", map='").append(map).append('\'');
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", version=").append(version);
        sb.append(", weathers=").append(weathers);
        sb.append(", emote='").append(emote).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
