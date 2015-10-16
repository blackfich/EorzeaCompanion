package com.blackfich.eorzeacompanion.util.filter;

import com.blackfich.eorzeacompanion.util.bean.Favoritable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.filter.constraints.FavoriteConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.TimeConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.VersionConstrainable;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class Filters {

    public static boolean isFiltered(boolean prevFilter, Versionable data, VersionConstrainable constraints) {
        boolean filtered = prevFilter;

        filtered |= !constraints.isShowARR() && data.getVersion() == 2;
        filtered |= !constraints.isShowHW() && data.getVersion() == 3;

        return filtered;
    }

    public static boolean isFiltered(boolean prevFilter, Timable data, TimeConstrainable constraints) {
        boolean filtered = prevFilter;

        filtered |= constraints.getTimeFiltering() == TimeConstrainable.FILTER_CURRENT && !isInTime(constraints.getTime(), data);
        filtered |= constraints.getTimeFiltering() == TimeConstrainable.FILTER_CURRENT_PLUS_1 && !(isInTime(constraints.getTime(), data) || isNextTime(constraints.getTime(), data));

        return filtered;
    }

    public static boolean isFiltered(boolean prevFilter, Favoritable data, FavoriteConstrainable constraints) {
        boolean filtered = prevFilter;

        if ( constraints.isShowFavorite() && data.isFavorite() ) {
            filtered = false;
        }

        return filtered;
    }


    public static boolean isInTime(int time, Timable timable) {
        boolean inTime = false;
        int startTime = timable.getStartTime();
        int endTime = timable.getEndTime();
        if ( startTime < endTime ) {
            // ie 13:00 - 19:00
            inTime = time >= startTime && time < endTime;
        } else {
            // ie 18:00 - 04:00
            inTime = time >= startTime || time < endTime;
        }
        return inTime;
    }

    public static boolean isNextTime(int time, Timable timable) {
        int nextHour = ((time + 100) % 2400) / 100;
        return timable.getStartTime() / 100 == nextHour;
    }
}
