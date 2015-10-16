package com.blackfich.eorzeacompanion.util.filter.constraints;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public interface TimeConstrainable {

    public static int FILTER_NONE = 0;

    public static int FILTER_CURRENT = 1;

    public static int FILTER_CURRENT_PLUS_1 = 2;

    public static int FILTER_MOD = 3;

    public int getTimeFiltering();

    public void setTimeFiltering(int timeFiltering);

    public int getTime();

    public void setTime(int time);

}
