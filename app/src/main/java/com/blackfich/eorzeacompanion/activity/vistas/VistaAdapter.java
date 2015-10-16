package com.blackfich.eorzeacompanion.activity.vistas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringFragment;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringNode;
import com.blackfich.eorzeacompanion.util.Config;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Weatherable;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class VistaAdapter extends BaseAdapter {

    private final VistasFragment fragment;
    private LayoutInflater inflater;


    public VistaAdapter(VistasFragment vistasFragment) {
        super();
        fragment = vistasFragment;
    }

    @Override
    public int getCount() {
        return fragment.getData().size();
    }

    @Override
    public Vista getItem(int location) {
        return fragment.getFilteredVista(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        MainActivity activity = fragment.getMainActivity();

        if (view == null) {
            if (inflater == null)
                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.vista_item_row, null);
        }

        final Vista vista = getItem(position);

        ImageView imgEmote = (ImageView) view.findViewById(R.id.imgEmote);
        TextView txtVistaName = (TextView) view.findViewById(R.id.txtVistaName);
        ImageView imgViewScreenshot = (ImageView) view.findViewById(R.id.imgViewScreenshot);

        view.setBackgroundColor(fragment.getData(position).getTimeColor());

        Drawable emote = activity.getDrawable("emote_" + vista.getEmote());
        imgEmote.setImageDrawable(emote);
        String nnn = (vista.n < 100 ? "0" : "") + (vista.n < 10 ? "0" : "") + vista.n;
        txtVistaName.setText(nnn + " - " + activity.getString("vista_" + vista.getN() + "_name"));

        AdapterUtil.adaptView(activity, view, (Timable) vista);
        AdapterUtil.adaptView(activity, view, (Mapable) vista);
        AdapterUtil.adaptView(activity, view, (Weatherable) vista, false);

        if (activity.hasDrawable("vista_" + nnn)) {
            imgViewScreenshot.setImageResource(R.drawable.img_view_screenshot);
        } else {
            imgViewScreenshot.setImageResource(R.drawable.img_view_screenshot_none);
        }

        imgEmote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = fragment.getMainActivity();
                String emote = "/" + activity.getString("emote_" + vista.getEmote());
                Toast.makeText(activity.getBaseContext(), emote, Toast.LENGTH_SHORT).show();
            }
        });

        imgViewScreenshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.showVista(vista);
            }
        });

        return view;
    }

}