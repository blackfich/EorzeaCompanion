package com.blackfich.eorzeacompanion;

import android.app.Application;
import android.content.Context;

/**
 * Created by Marc Fichant on 08/10/2015.
 */
public class EorzeaCompanion extends Application {
    private static EorzeaCompanion instance;

    public EorzeaCompanion() {

        instance = this;

    }

    public static Context getContext() {
        return instance;
    }

}
