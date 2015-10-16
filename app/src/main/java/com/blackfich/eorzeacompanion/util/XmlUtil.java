package com.blackfich.eorzeacompanion.util;

import android.content.res.AssetManager;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class XmlUtil {

    public static Document getDomElement(AssetManager assetManager, String filename) {
        Document doc = null;
        InputStream inputStream = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            inputStream = assetManager.open(filename);
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(inputStream);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e("Error: ", e.getMessage());
                }
            }
        }
        // return DOM
        return doc;
    }

    public static String getAttribueValue(Element element, String attrName) {
        return getAttribueValue(element, attrName, (String) null);
    }

    public static String getAttribueValue(Element element, String attrName, String defaultValue) {
        if (element.hasAttribute(attrName)) {
            return element.getAttribute(attrName);
        } else {
            return defaultValue;
        }
    }

    public static int getAttribueValue(Element element, String attrName, int defaultValue) {
        if (element.hasAttribute(attrName)) {
            return Integer.parseInt(element.getAttribute(attrName), 10);
        } else {
            return defaultValue;
        }
    }

    public static boolean getAttribueValue(Element element, String attrName, boolean defaultValue) {
        if (element.hasAttribute(attrName)) {
            return Boolean.parseBoolean(element.getAttribute(attrName));
        } else {
            return defaultValue;
        }
    }
}
