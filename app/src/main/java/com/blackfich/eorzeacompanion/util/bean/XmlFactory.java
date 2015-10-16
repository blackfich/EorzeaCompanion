package com.blackfich.eorzeacompanion.util.bean;

import com.blackfich.eorzeacompanion.util.XmlUtil;

import org.w3c.dom.Element;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class XmlFactory {


    public static void fromXml(Versionable versionable, Element xml) {
        versionable.setVersion(XmlUtil.getAttribueValue(xml, "version", 2));
    }

    public static void fromXml(Timable timable, Element xml) {
        if ( xml.hasAttribute("time") ) {
            timable.setStartTime(100 * XmlUtil.getAttribueValue(xml, "time", 0));
            timable.setEndTime(-1);
        } else {
            timable.setStartTime(100 * XmlUtil.getAttribueValue(xml, "start-time", 0));
            timable.setEndTime(100 * XmlUtil.getAttribueValue(xml, "end-time", 0));
        }
        timable.setTwicePerDay(XmlUtil.getAttribueValue(xml, "twice-per-day", false));
    }


    public static void fromXml(Mapable mapable, Element xml) {
        mapable.setMap(XmlUtil.getAttribueValue(xml, "map", ""));
        mapable.setX(XmlUtil.getAttribueValue(xml, "x", 0));
        mapable.setY(XmlUtil.getAttribueValue(xml, "y", 0));
    }

}
