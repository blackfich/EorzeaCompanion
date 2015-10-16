package com.blackfich.eorzeacompanion.activity.gathering;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class UnspoiledNode extends GatheringNode {

    @Override
    public int getDuration() {
        return version == 2 ? 180 : 55;
    }

    public UnspoiledNode duplicate() {
        UnspoiledNode clone = new UnspoiledNode();
        clone(clone);
        return clone;
    }
}
