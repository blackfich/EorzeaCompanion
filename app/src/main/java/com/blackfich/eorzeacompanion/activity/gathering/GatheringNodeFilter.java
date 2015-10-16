package com.blackfich.eorzeacompanion.activity.gathering;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.util.filter.FilteredResult;
import com.blackfich.eorzeacompanion.util.filter.EorzeanFilter;
import com.blackfich.eorzeacompanion.util.filter.constraints.Constrainable;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public class GatheringNodeFilter extends EorzeanFilter {

    private final GatheringFragment fragment;

    public GatheringNodeFilter(GatheringFragment gatheringFragment) {
        this.fragment = gatheringFragment;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        int time = MainActivity.getEorzeaTime();
        GatheringFilterConstraints constraints = fragment.getFilterConstraints();

        constraints.setTime(time);
        List<FilteredResult> filteredResults = new ArrayList<FilteredResult>();

        for (int i = 0; i < MainActivity.getGatheringNodes().size(); i++) {
            GatheringNode node = MainActivity.getGatheringNodes().get(i);
            if (!constraints.isFiltered(node)) {
                int rowColor = AdapterUtil.getTimeColor(node, time);
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
