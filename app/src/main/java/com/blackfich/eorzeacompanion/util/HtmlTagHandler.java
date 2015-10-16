package com.blackfich.eorzeacompanion.util;

import android.text.Editable;
import android.text.Html;
import android.util.Log;

import org.xml.sax.XMLReader;

/**
 * Created by Marc Fichant on 15/10/2015.
 */
public class HtmlTagHandler implements Html.TagHandler {

    @Override
    public void handleTag(boolean opening, String tag, Editable output,
                          XMLReader xmlReader) {
        Log.d(null, "tag=" + tag + ", opening=" + opening);
        if ("br".equalsIgnoreCase(tag) || "newline".equalsIgnoreCase(tag)) {
            output.append("\n");
        }
    }

}