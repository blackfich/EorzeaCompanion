package com.blackfich.eorzeacompanion.util;

import android.graphics.Color;

/**
 * Created by Marc Fichant on 14/10/2015.
 */
public class Config {
    public static final int VISTA_SORT_NUMBER = 0;
    public static final int VISTA_SORT_TIME = 1;
    public static final int FILTER_TIME_NONE = 0;
    public static final int FILTER_TIME_CURRENT = 1;
    public static final int FILTER_TIME_CURRENT_PLUS_1 = 2;
    public static final int FILTER_TIME_MOD = 3;
    private static int COLOR_TINT_EPHEMERAL = Color.parseColor("#800040FF");
    private static int COLOR_TINT_FOLKLORE = Color.parseColor("#80FF40FF");
    private static int COLOR_OPEN = Color.parseColor("#004000");
    private static int COLOR_NEXT_HOUR = Color.parseColor("#404000");
    private static int VISTA_SORT = VISTA_SORT_NUMBER;

    public static int getColorTintEphemeral() {
        return COLOR_TINT_EPHEMERAL;
    }

    public static void setColorTintEphemeral(int colorTintEphemeral) {
        COLOR_TINT_EPHEMERAL = colorTintEphemeral;
    }

    public static int getColorTintFolklore() {
        return COLOR_TINT_FOLKLORE;
    }

    public static void setColorTintFolklore(int colorTintFolklore) {
        COLOR_TINT_FOLKLORE = colorTintFolklore;
    }

    public static int getColorOpen() {
        return COLOR_OPEN;
    }

    public static void setColorOpen(int colorOpen) {
        COLOR_OPEN = colorOpen;
    }

    public static int getColorNextHour() {
        return COLOR_NEXT_HOUR;
    }

    public static void setColorNextHour(int colorNextHour) {
        COLOR_NEXT_HOUR = colorNextHour;
    }

    public static int getVistaSort() {
        return VISTA_SORT;
    }

    public static void setVistaSort(int vistaSort) {
        VISTA_SORT = vistaSort;
    }
}
