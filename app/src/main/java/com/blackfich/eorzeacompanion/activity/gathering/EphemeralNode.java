package com.blackfich.eorzeacompanion.activity.gathering;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class EphemeralNode extends GatheringNode {

    @Override
    public int getDuration() {
        return 240;
    }

    public EphemeralNode duplicate() {
        EphemeralNode clone = new EphemeralNode();
        clone(clone);
        return clone;
    }
}
