package com.blackfich.eorzeacompanion.util.bean;

import org.json.JSONObject;

/**
 * Created by Blackfich on 19/10/2015.
 */
public interface JSONable {

    void fromJSON(JSONObject json);

    JSONObject toJSON();

}
