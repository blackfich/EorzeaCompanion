package com.blackfich.eorzeacompanion.activity.vistas;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TextView;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Weatherable;
import com.blackfich.eorzeacompanion.util.ui.EorzeanFragment;
import com.blackfich.eorzeacompanion.util.Config;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;
import com.blackfich.eorzeacompanion.util.filter.FilteredResult;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;

import java.security.SecurityPermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marc Fichant on 14/10/2015.
 */
public class VistasFragment extends EorzeanFragment {

    public static final String NAME = "vistas";

    private ListView lstVistas;


    private final List<FilteredResult> data = new ArrayList<FilteredResult>();

    private final VistaAdapter vistaAdapter = new VistaAdapter(this);

    private final VistaFilterConstraints vistaFilterConstraints = new VistaFilterConstraints();

    private final VistaFilter vistaFilter = new VistaFilter(this);

    private ImageView imgFilterARR;

    private ImageView imgFilterHW;

    private ImageView imgFilterTime;

    private ImageView imgSortNumber;

    private ImageView imgSortTime;

    public VistasFragment() {
        applyFilter();
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public VistaFilterConstraints getFilterConstraints() {
        return vistaFilterConstraints;
    }

    public BaseAdapter getAdapter() {
        return vistaAdapter;
    }

    public List<FilteredResult> getData() {
        return data;
    }

    public FilteredResult getData(int position) {
        return data.get(position);
    }

    public Vista getFilteredVista(int position) {
        return getMainActivity().getVistas().get(data.get(position).getRealPosition());
    }

    public void showVista(Vista vista) {
        View popupLayout = getMainActivity().showPopup(R.layout.vista_details);
        TextView txtVistaName = (TextView) popupLayout.findViewById(R.id.txtVistaName);
        TextView txtVistaHint = (TextView) popupLayout.findViewById(R.id.txtVistaHint);
        ImageView imgVistaScreenshot = (ImageView) popupLayout.findViewById(R.id.imgVistaScreenshot);

        String nnn = (vista.getN() < 100 ? "0" : "") + (vista.getN() < 10 ? "0" : "") + vista.getN();
        txtVistaName.setText(nnn + " - " + getString("vista_" + vista.getN() + "_name"));

        AdapterUtil.adaptView(getMainActivity(), popupLayout, (Timable) vista);
        AdapterUtil.adaptView(getMainActivity(), popupLayout, (Mapable) vista);
        AdapterUtil.adaptView(getMainActivity(), popupLayout, (Weatherable) vista, true);

        txtVistaHint.setText(getString("vista_" + vista.getN() + "_hint"), TextView.BufferType.SPANNABLE);
        Drawable drawable = getDrawable("vista_" + nnn);
        if (drawable == null) {
            drawable = getDrawable("vista_no_screenshot");
        }
        imgVistaScreenshot.setImageDrawable(drawable);

    }

    public void applyFilter() {
        Log.i(null, "applyFilter");
        vistaFilter.filter("");
        if ( lstVistas != null ) {
            lstVistas.invalidate();
        }
    }

    private AdapterView.OnItemClickListener lstOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Vista vista = vistaAdapter.getItem(position);
            showVista(vista);
        }
    };

    private View.OnClickListener imgFilterARROnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        boolean show = vistaFilterConstraints.toggleShowARR();
        imgFilterARR.setImageResource(show ? R.drawable.gathering_filter_arr_on : R.drawable.gathering_filter_arr_off);
        applyFilter();
        }
    };
    private View.OnClickListener imgFilterHWOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        boolean show = vistaFilterConstraints.toggleShowHW();
        imgFilterHW.setImageResource(show ? R.drawable.gathering_filter_hw_on : R.drawable.gathering_filter_hw_off);
        applyFilter();
        }
    };
    private View.OnClickListener imgSortNumberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Config.setVistaSort(Config.VISTA_SORT_NUMBER);
        Collections.sort(getMainActivity().getVistas());
        imgSortNumber.setClickable(false);
        imgSortTime.setClickable(true);
        imgSortNumber.setImageResource(R.drawable.sort_number_on);
        imgSortTime.setImageResource(R.drawable.sort_time_off);
        applyFilter();
        }
    };
    private View.OnClickListener imgSortTimeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        Config.setVistaSort(Config.VISTA_SORT_TIME);
        Collections.sort(getMainActivity().getVistas());
        imgSortNumber.setClickable(true);
        imgSortTime.setClickable(false);
        imgSortNumber.setImageResource(R.drawable.sort_number_off);
        imgSortTime.setImageResource(R.drawable.sort_time_on);
        applyFilter();
        }
    };
    private View.OnClickListener imgFilterTimeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        int timeFilter = vistaFilterConstraints.getTimeFiltering();
        timeFilter = (timeFilter + 1) % Config.FILTER_TIME_MOD;
        vistaFilterConstraints.setTimeFiltering(timeFilter);
        if (timeFilter == Config.FILTER_TIME_NONE) {
            imgFilterTime.setImageResource(R.drawable.filter_time_off);
        } else if (timeFilter == Config.FILTER_TIME_CURRENT) {
            imgFilterTime.setImageResource(R.drawable.filter_time_on);
        } else if (timeFilter == Config.FILTER_TIME_CURRENT_PLUS_1) {
            imgFilterTime.setImageResource(R.drawable.filter_time_next_on);
        }
        applyFilter();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vistas_fragment, container, false);
        MainActivity activity = (MainActivity) getActivity();
        lstVistas = (ListView) view.findViewById(R.id.lstVistas);
        lstVistas.setAdapter(vistaAdapter);
        lstVistas.setOnItemClickListener(lstOnItemClickListener);

        imgFilterARR = (ImageView) view.findViewById(R.id.imgFilterARR);
        imgFilterHW = (ImageView) view.findViewById(R.id.imgFilterHW);
        imgSortNumber = (ImageView) view.findViewById(R.id.imgSortNumber);
        imgSortTime = (ImageView) view.findViewById(R.id.imgSortTime);
        imgFilterTime = (ImageView) view.findViewById(R.id.imgFilterTime);

        imgFilterARR.setOnClickListener(imgFilterARROnClickListener);
        imgFilterHW.setOnClickListener(imgFilterHWOnClickListener);
        imgSortNumber.setOnClickListener(imgSortNumberClickListener);
        imgSortTime.setOnClickListener(imgSortTimeClickListener);
        imgFilterTime.setOnClickListener(imgFilterTimeOnClickListener);

        updateEorzeaTime();

        return view;
    }

    @Override
    public void updateEorzeaTime() {
        super.updateEorzeaTime();
        if (lstVistas != null && AdapterUtil.isListDamaged(data, vistaFilter)) {
            Log.d(null, "" + EorzeaUtils.getEorzeaTime() + " ->  vistas list is damaged");
            applyFilter();
        }
    }


    @Override
    public String getName() {
        return NAME;
    }
}
