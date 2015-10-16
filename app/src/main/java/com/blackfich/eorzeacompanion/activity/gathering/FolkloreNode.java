package com.blackfich.eorzeacompanion.activity.gathering;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class FolkloreNode extends GatheringNode {

    @Override
    public int getDuration() {
        return 60;
    }

    public FolkloreNode duplicate() {
        FolkloreNode clone = new FolkloreNode();
        clone(clone);
        return clone;
    }
}
