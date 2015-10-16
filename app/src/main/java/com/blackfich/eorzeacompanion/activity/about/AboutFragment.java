package com.blackfich.eorzeacompanion.activity.about;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.util.ui.EorzeanFragment;
import com.blackfich.eorzeacompanion.util.HtmlTagHandler;

/**
 * Created by Marc Fichant on 14/10/2015.
 */
public class AboutFragment extends EorzeanFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);
        MainActivity activity = (MainActivity) getActivity();

        TextView txtAbout = (TextView) view.findViewById(R.id.txtAbout);
        String about = activity.getString(R.string.about_text);
        Log.d(null, "about = " + about);
        Spanned marked_up = Html.fromHtml(about, null, new HtmlTagHandler());
        txtAbout.setText(marked_up, TextView.BufferType.SPANNABLE);

        return view;
    }

    @Override
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}

