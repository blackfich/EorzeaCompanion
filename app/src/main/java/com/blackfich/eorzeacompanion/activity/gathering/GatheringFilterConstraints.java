package com.blackfich.eorzeacompanion.activity.gathering;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;
import com.blackfich.eorzeacompanion.util.JSONUtil;
import com.blackfich.eorzeacompanion.util.bean.JSONable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Versionable;
import com.blackfich.eorzeacompanion.util.filter.Filters;
import com.blackfich.eorzeacompanion.util.filter.constraints.Constrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.FavoriteConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.TimeConstrainable;
import com.blackfich.eorzeacompanion.util.filter.constraints.VersionConstrainable;

import org.json.JSONObject;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class GatheringFilterConstraints implements Constrainable<GatheringNode>, TimeConstrainable, VersionConstrainable, FavoriteConstrainable, JSONable {

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

    public GatheringFilterConstraints() {

    }
    public GatheringFilterConstraints(String constraints) {
        JSONUtil.fromJSON(this, constraints);
    }

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

    public void fromJSON(JSONObject json) {
        showBotanist = JSONUtil.getBoolean(json, "showBotanist", true);
        showMiner = JSONUtil.getBoolean(json, "showMiner", true);
        showFisher = JSONUtil.getBoolean(json, "showFisher", false);
        showFavorite = JSONUtil.getBoolean(json, "showFavorite", false);
        showARR = JSONUtil.getBoolean(json, "showARR", false);
        showHW = JSONUtil.getBoolean(json, "showHW", false);
        timeFiltering = JSONUtil.getInt(json, "timeFiltering", TimeConstrainable.FILTER_NONE);
        showUnspoiledNodes = JSONUtil.getBoolean(json, "showUnspoiledNodes", false);
        showEphemeralNodes = JSONUtil.getBoolean(json, "showEphemeralNodes", false);
        showFolkloreNodes = JSONUtil.getBoolean(json, "showFolkloreNodes", false);
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        JSONUtil.put(jsonObject, "showBotanist", showBotanist);
        JSONUtil.put(jsonObject, "showMiner", showMiner);
        JSONUtil.put(jsonObject, "showFisher", showFisher);
        JSONUtil.put(jsonObject, "showFavorite", showFavorite);
        JSONUtil.put(jsonObject, "showARR", showARR);
        JSONUtil.put(jsonObject, "showHW", showHW);
        JSONUtil.put(jsonObject, "timeFiltering", timeFiltering);
        JSONUtil.put(jsonObject, "showUnspoiledNodes", showUnspoiledNodes);
        JSONUtil.put(jsonObject, "showEphemeralNodes", showEphemeralNodes);
        JSONUtil.put(jsonObject, "showFolkloreNodes", showFolkloreNodes);

        return jsonObject;
    }
}
