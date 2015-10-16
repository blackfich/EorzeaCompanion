package com.blackfich.eorzeacompanion.activity.vistas;

import android.content.res.AssetManager;
import android.util.Log;

import com.blackfich.eorzeacompanion.util.XmlUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marc Fichant on 12/10/2015.
 */
public class VistasFactory {

    public static List<Vista> loadVistas(AssetManager assetManager) {
        List<Vista> vistas = new ArrayList<Vista>();
        Document doc = XmlUtil.getDomElement(assetManager, "data/vistas.xml");

        NodeList nl = doc.getChildNodes();
        nl = nl.item(0).getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node xmlNode = nl.item(i);
            if (!(xmlNode instanceof Element)) {
                continue;
            }
            Element element = (Element) xmlNode;
            Vista vista = null;
            if ("vista".equals(element.getTagName())) {
                vista = newVista(element);
            } else {
                Log.w("loadVistas", "Unknown vista type <" + element.getTagName() + ">");
            }
            if (vista != null) {
                vistas.add(vista);
            }
        }

        Collections.sort(vistas);

        return vistas;
    }

    private static Vista newVista(Element element) {
        Vista vista = new Vista();
        vista.fromXml(element);
        return vista;
    }

}
