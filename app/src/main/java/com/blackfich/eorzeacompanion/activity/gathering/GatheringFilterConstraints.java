package com.blackfich.eorzeacompanion.activity.gathering;

import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.filter.Filters;
import com.blackfich.eorzeacompanion.util.filter.constraints.Constrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.FavoriteConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.TimeConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.VersionConstrainable;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class GatheringFilterConstraints implements Constrainable<GatheringNode>, TimeConstrainable, VersionConstrainable, FavoriteConstrainable {

    private boolean showBotanist = true;
    private boolean showMiner = true;
    private boolean showFisher = true;
    private boolean showFavorite = true;
    private boolean showARR = true;
    private boolean showHW = true;
    private int timeFiltering = TimeConstrainable.FILTER_NONE;
    private int time;

    private boolean showUnspoiledNodes = true;
    private boolean showEphemeralNodes = true;
    private boolean showFolkloreNodes = true;


    public boolean isFiltered(GatheringNode node) {
        boolean filtered = false;

        filtered |= !showBotanist && "botanist".equals(node.getType());
        filtered |= !showMiner && "miner".equals(node.getType());
        filtered |= !showFisher && "fisher".equals(node.getType());

        filtered |= Filters.isFiltered(filtered, (Versionable) node, this);
        filtered |= Filters.isFiltered(filtered, (Timable) node, this);

        return filtered;
    }

    public boolean isShowBotanist() {
        return showBotanist;
    }

    public void setShowBotanist(boolean showBotanist) {
        this.showBotanist = showBotanist;
    }

    public boolean toggleShowBotanist() {
        showBotanist = !showBotanist;
        return showBotanist;
    }

    public boolean isShowMiner() {
        return showMiner;
    }

    public void setShowMiner(boolean showMiner) {
        this.showMiner = showMiner;
    }

    public boolean toggleShowMiner() {
        showMiner = !showMiner;
        return showMiner;
    }

    public boolean isShowFisher() {
        return showFisher;
    }

    public void setShowFisher(boolean showFisher) {
        this.showFisher = showFisher;
    }

    public boolean toggleShowFisher() {
        showFisher = !showFisher;
        return showFisher;
    }

    @Override
    public boolean isShowFavorite() {
        return showFavorite;
    }

    @Override
    public void setShowFavorite(boolean showFavorite) {
        this.showFavorite = showFavorite;
    }

    @Override
    public boolean toggleShowFavorite() {
        showFavorite = !showFavorite;
        return showFavorite;
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

    public boolean isShowUnspoiledNodes() {
        return showUnspoiledNodes;
    }

    public void setShowUnspoiledNodes(boolean showUnspoiledNodes) {
        this.showUnspoiledNodes = showUnspoiledNodes;
    }

    public boolean toggleShowUnspoiledNodes() {
        showUnspoiledNodes = !showUnspoiledNodes;
        return showUnspoiledNodes;
    }

    public boolean isShowEphemeralNodes() {
        return showEphemeralNodes;
    }

    public void setShowEphemeralNodes(boolean showEphemeralNodes) {
        this.showEphemeralNodes = showEphemeralNodes;
    }

    public boolean toggleShowEphemeralNodes() {
        showEphemeralNodes = !showEphemeralNodes;
        return showEphemeralNodes;
    }

    public boolean isShowFolkloreNodes() {
        return showFolkloreNodes;
    }

    public void setShowFolkloreNodes(boolean showFolkloreNodes) {
        this.showFolkloreNodes = showFolkloreNodes;
    }

    public boolean toggleShowFolkloreNodes() {
        showFolkloreNodes = !showFolkloreNodes;
        return showFolkloreNodes;
    }
}
