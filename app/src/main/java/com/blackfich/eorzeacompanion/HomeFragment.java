package com.blackfich.eorzeacompanion;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blackfich.eorzeacompanion.activity.about.AboutFragment;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringFragment;
import com.blackfich.eorzeacompanion.activity.vistas.VistasFragment;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;
import com.blackfich.eorzeacompanion.util.ui.EorzeanFragment;

/**
 * Created by Blackfich on 19/10/2015.
 */
public class HomeFragment extends EorzeanFragment {

    public static final String NAME = "home";

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        Button btnGathering = (Button) view.findViewById(R.id.btnGathering);
        Button btnVistas = (Button) view.findViewById(R.id.btnVistas);
        Button btnAbout = (Button) view.findViewById(R.id.btnAbout);

        btnGathering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showFragment(GatheringFragment.NAME);
            }
        });

        btnVistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showFragment(VistasFragment.NAME);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().showFragment(AboutFragment.NAME);
            }
        });

        return view;
    }

    @Override
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public String getName() {
        return NAME;
    }

}
