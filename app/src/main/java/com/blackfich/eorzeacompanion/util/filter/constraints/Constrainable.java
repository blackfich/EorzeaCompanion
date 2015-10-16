package com.blackfich.eorzeacompanion.util.filter.constraints;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public interface Constrainable<T> {

    public boolean isFiltered(T data);

}
