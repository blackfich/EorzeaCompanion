package com.blackfich.eorzeacompanion.activity.vistas;

import com.blackfich.eorzeacompanion.activity.gathering.GatheringNode;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.filter.Filters;
import com.blackfich.eorzeacompanion.util.filter.constraints.Constrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.TimeConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.VersionConstrainable;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class VistaFilterConstraints implements Constrainable<Vista>, TimeConstrainable, VersionConstrainable {

    private boolean showARR = true;
    private boolean showHW = true;
    private int timeFiltering = TimeConstrainable.FILTER_NONE;
    private int time;

    @Override
    public boolean isFiltered(Vista vista) {
        boolean filtered = false;

        filtered |= Filters.isFiltered(filtered, (Versionable) vista, this);
        filtered |= Filters.isFiltered(filtered, (Timable) vista, this);

        return filtered;
    }

    @Override
    public boolean isShowARR() {
        return showARR;
    }

    @Override
    public void setShowARR(boolean showARR) {
        this.showARR = showARR;
    }

    @Override
    public boolean toggleShowARR() {
        showARR = !showARR;
        return showARR;
    }

    @Override
    public boolean isShowHW() {
        return showHW;
    }

    @Override
    public void setShowHW(boolean showHW) {
        this.showHW = showHW;
    }

    @Override
    public boolean toggleShowHW() {
        showHW = !showHW;
        return showHW;
    }

    @Override
    public int getTimeFiltering() {
        return timeFiltering;
    }

    @Override
    public void setTimeFiltering(int timeFiltering) {
        this.timeFiltering = timeFiltering;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

}
