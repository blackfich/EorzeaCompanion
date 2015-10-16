package com.blackfich.eorzeacompanion.activity.vistas;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringFilterConstraints;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringFragment;
import com.blackfich.eorzeacompanion.activity.gathering.GatheringNode;
import com.blackfich.eorzeacompanion.util.filter.EorzeanFilter;
import com.blackfich.eorzeacompanion.util.filter.FilteredResult;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class VistaFilter extends EorzeanFilter {

    private final VistasFragment fragment;


    public VistaFilter(VistasFragment vistasFragment) {
        this.fragment = vistasFragment;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        int time = MainActivity.getEorzeaTime();
        VistaFilterConstraints constraints = fragment.getFilterConstraints();

        constraints.setTime(time);
        List<FilteredResult> filteredResults = new ArrayList<FilteredResult>();

        List<Vista> vistas = MainActivity.getVistas();

        for (int i = 0; i < vistas.size(); i++) {
            Vista vista = vistas.get(i);
            if (!constraints.isFiltered(vista)) {
                int rowColor = AdapterUtil.getTimeColor(vista, time);
                filteredResults.add(new FilteredResult(i, rowColor));
            }
        }

        FilterResults results = new FilterResults();

        results.values = filteredResults;
        results.count = filteredResults.size();

        return results;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        fragment.getData().clear();
        fragment.getData().addAll((List<FilteredResult>) results.values);
        fragment.getAdapter().notifyDataSetChanged();
    }

}
