package com.blackfich.eorzeacompanion.util.ui;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.util.Config;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.bean.Weatherable;
import com.blackfich.eorzeacompanion.util.filter.EorzeanFilter;
import com.blackfich.eorzeacompanion.util.filter.FilteredResult;
import com.blackfich.eorzeacompanion.util.filter.Filters;

import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class AdapterUtil {

    public static boolean isListDamaged(List<FilteredResult> currentData, EorzeanFilter filter) {
        List<FilteredResult> newData = filter.getFilteredResults();

        boolean same = true;

        if (newData.size() != currentData.size() ) {
            Log.d(null, "nb datum changed");
            same = false;
        } else {
            for ( int i = newData.size() - 1 ; same && i >= 0 ; i-- ) {
                FilteredResult newDatum = newData.get(i);
                FilteredResult currentDatum = currentData.get(i);
                if ( !currentDatum.equals(newDatum) ) {
                    Log.d(null, "datum changed");
                    same = false;
                }
            }
        }

        return !same;
    }

    public static void adaptView(final MainActivity activity, final View view, final Timable timable) {

        TextView txtTimeFrame = (TextView) view.findViewById(R.id.txtTimeFrame);
        if (txtTimeFrame != null) {
            StringBuilder sb = new StringBuilder(16);
            sb.append(EorzeaUtils.toTimeString(timable.getStartTime()));
            sb.append(" - ");
            sb.append(EorzeaUtils.toTimeString(timable.getEndTime()));
            txtTimeFrame.setText(sb.toString());
        }

    }

    public static void adaptView(final MainActivity activity, final View view, final Mapable mapable) {

        TextView txtMapAndPosition = (TextView) view.findViewById(R.id.txtMapAndPosition);
        if (txtMapAndPosition != null) {
            String map = activity.getString(mapable.getMap());
            txtMapAndPosition.setText(map + " (" + mapable.getX() + "," + mapable.getY() + ")");
        }
    }



    public static void adaptView(final MainActivity activity, final View view, final Weatherable weatherable, boolean showWeatherNames) {
        LinearLayout lstWeathers = (LinearLayout) view.findViewById(R.id.lstWeathers);
        if (lstWeathers != null) {
            DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
            lstWeathers.removeAllViews();
            boolean first = true;
            for (String weather : weatherable.getWeathers()) {
                ImageView imgWeather = new ImageView(view.getContext());
                LinearLayout.LayoutParams imgLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imgLayout.setMargins(EorzeaUtils.dpToPx(showWeatherNames && !first ? 8 : 0, displayMetrics), 0, 0, 0);
                imgWeather.setLayoutParams(imgLayout);
                imgWeather.setImageDrawable(activity.getDrawable("weather_" + weather));
                lstWeathers.addView(imgWeather);
                if ( showWeatherNames ) {
                    TextView txtWeather = new TextView(view.getContext());
                    txtWeather.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    txtWeather.setText(activity.getString("weather_" + weather));
                    txtWeather.setTextColor(Color.WHITE);
                    lstWeathers.addView(txtWeather);
                }
                first = false;
            }
        }
    }

    public static int getTimeColor(Timable timable, int time) {
        int color = Color.TRANSPARENT;
        if (Filters.isInTime(time, timable) ) {
            color = Config.getColorOpen();
        } else if ( Filters.isNextTime(time, timable) ) {
            color = Config.getColorNextHour();
        }
        return  color;
    }
}

