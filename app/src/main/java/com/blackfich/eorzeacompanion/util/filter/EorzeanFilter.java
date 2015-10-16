package com.blackfich.eorzeacompanion.util.filter;

import android.widget.Filter;

import com.blackfich.eorzeacompanion.util.filter.FilteredResult;

import java.util.List;

/**
 * Created by Marc Fichant on 16/10/2015.
 */
public abstract class EorzeanFilter extends Filter {

    protected static final String TEST_CONSTRAINTS;

    static {
        TEST_CONSTRAINTS = "~TEST~";
    }
    public List<FilteredResult> getFilteredResults() {
        FilterResults filterResults = performFiltering(TEST_CONSTRAINTS);
        return (List<FilteredResult>) filterResults.values;
    }

}
