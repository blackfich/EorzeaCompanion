package com.blackfich.eorzeacompanion.util;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.blackfich.eorzeacompanion.activity.gathering.GatheringNode;
import com.blackfich.eorzeacompanion.activity.vistas.Vista;
import com.blackfich.eorzeacompanion.util.filter.constraints.TimeConstrainable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Marc Fichant on 28/09/2015.
 */
public class EorzeaUtils {
    private static final double E_TIME = 20.5714285714d;
    private static final Calendar CALENDAR = new GregorianCalendar();
    private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    private static Date eorzeaTime = null;

    public static Date getEorzeaTimeAsDate() {
        long utcTime = new Date().getTime();
        long eo_timestamp = (long) Math.floor(utcTime * E_TIME);
        eorzeaTime = new Date();
        eorzeaTime.setTime(eo_timestamp);
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(eo_timestamp);
        cal.setTimeZone(UTC);
        return cal.getTime();
    }

    public static int getEorzeaTime() {
        long utcTime = new Date().getTime();
        long eo_timestamp = (long) Math.floor(utcTime * E_TIME);
        eorzeaTime = new Date();
        eorzeaTime.setTime(eo_timestamp);
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(eo_timestamp);
        cal.setTimeZone(UTC);
        return 100 * cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE);
    }

    public static String toTimeString(int time) {
        StringBuilder sb = new StringBuilder(5);
        if (time < 1000) {
            sb.append('0');
        }
        sb.append((int) Math.floor(time / 100)).append(':');
        if ((time % 100) < 10) {
            sb.append('0');
        }
        sb.append(time % 100);

        return sb.toString();
    }

    public static int getRowColor(int time, GatheringNode node) {
        if (EorzeaUtils.isInTime(time, node.getStartTime(), node.getEndTime())) {
            return Config.getColorOpen();
        }
        if (EorzeaUtils.isNextTime(time, node.getStartTime())) {
            return Config.getColorNextHour();
        }
        return Color.TRANSPARENT;
    }

    public static int getRowColor(int time, Vista vista) {
        if (EorzeaUtils.isInTime(time, vista.getStartTime(), vista.getEndTime())) {
            return Config.getColorOpen();
        }
        if (EorzeaUtils.isNextTime(time, vista.getStartTime())) {
            return Config.getColorNextHour();
        }
        return Color.TRANSPARENT;
    }

    public static boolean isInTime(int time, int startTime, int endTime) {
        if (startTime < endTime) {
            // ie 15:00 - 16:59
            return time >= startTime && time <= endTime;
        } else {
            // ie 23:00 - 01:59
            return time >= endTime || time <= startTime;
        }
    }


    public static boolean isNextTime(int time, int startTime) {
        int endTime = (startTime + 59) % 2400;
        time = (time + 100) % 2400;
        if (startTime < endTime) {
            // ie 15:00 - 16:59
            return time >= startTime && time <= endTime;
        } else {
            // ie 23:00 - 01:59
            return time <= endTime || time >= startTime;
        }
    }


    public static int dpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }

    public static int timeFilteringFromString(String string) {
        if ( string == null ) {
            return TimeConstrainable.FILTER_NONE;
        }
        if ( string.indexOf('T') != -1 ) {
            return TimeConstrainable.FILTER_CURRENT;
        }
        if ( string.indexOf('t') != -1 ) {
            return TimeConstrainable.FILTER_CURRENT_PLUS_1;
        }
        return TimeConstrainable.FILTER_NONE;
    }

    public static String timeFilteringToString(int timeConstraint) {
        switch (timeConstraint ) {
            case TimeConstrainable.FILTER_CURRENT:
                return "T";
            case TimeConstrainable.FILTER_CURRENT_PLUS_1:
                return "t";
            default:
            case TimeConstrainable.FILTER_NONE:
                return "";
        }
    }
}
