package com.blackfich.eorzeacompanion.util.bean;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public interface Timable {

    public int getStartTime();

    public void setStartTime(int startTime);

    public int getEndTime();

    public void setEndTime(int endTime);

    public boolean isTwicePerDay();

    public void setTwicePerDay(boolean twicePerDay);

}
