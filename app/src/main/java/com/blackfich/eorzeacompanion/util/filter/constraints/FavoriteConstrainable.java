package com.blackfich.eorzeacompanion.util.filter.constraints;

import com.blackfich.eorzeacompanion.util.bean.Favoritable;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public interface FavoriteConstrainable {

    public boolean isShowFavorite();

    public void setShowFavorite(boolean showFavorite);

    public boolean toggleShowFavorite();
}
