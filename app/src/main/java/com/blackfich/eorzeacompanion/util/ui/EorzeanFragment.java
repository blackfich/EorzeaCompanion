package com.blackfich.eorzeacompanion.util.ui;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import com.blackfich.eorzeacompanion.MainActivity;

/**
 * Created by Marc Fichant on 14/10/2015.
 */
public abstract class EorzeanFragment extends Fragment {

    public void updateEorzeaTime() {

    }

    public abstract MainActivity getMainActivity();

    public abstract String getName();

    public String getString(String name) {
        return getMainActivity().getString(name);
    }

    public boolean hasDrawable(String name) {
        return getMainActivity().hasDrawable(name);
    }

    public Drawable getDrawable(String name) {
        return getMainActivity().getDrawable(name);
    }


}
