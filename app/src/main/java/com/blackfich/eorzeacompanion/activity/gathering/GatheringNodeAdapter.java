package com.blackfich.eorzeacompanion.activity.gathering;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.util.Config;
import com.blackfich.eorzeacompanion.util.bean.Mapable;
import com.blackfich.eorzeacompanion.util.bean.Timable;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;

/**
 * Created by Marc Fichant on 07/10/2015.
 */
public class GatheringNodeAdapter extends BaseAdapter {

    private final GatheringFragment fragment;
    private LayoutInflater inflater;

    public GatheringNodeAdapter(GatheringFragment gatheringFragment) {
        super();
        this.fragment = gatheringFragment;
     }

    @Override
    public int getCount() {
        return fragment.getData().size();
    }

    @Override
    public GatheringNode getItem(int location) {
        return fragment.getFilteredNode(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        MainActivity activity = fragment.getMainActivity();

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.gathering_item_row, null);

        final GatheringNode gatheringNode = getItem(position);

        ImageView imgIconType = (ImageView) view.findViewById(R.id.imgGatheringNodeType);
        //ImageView imgShowNode = (ImageView) view.findViewById(R.id.imgShowSpots);

        view.setBackgroundColor(fragment.getData(position).getTimeColor());

        if ("botanist".equals(gatheringNode.getType())) {
            imgIconType.setImageResource(R.drawable.gathering_botanist);
        } else if ("miner".equals(gatheringNode.getType())) {
            imgIconType.setImageResource(R.drawable.gathering_miner);
        } else if ("fisher".equals(gatheringNode.getType())) {
            imgIconType.setImageResource(R.drawable.gathering_fisher);
        }

        if (gatheringNode instanceof EphemeralNode) {
            imgIconType.setColorFilter(Config.getColorTintEphemeral());
        } else if (gatheringNode instanceof FolkloreNode) {
            imgIconType.setColorFilter(Config.getColorTintFolklore());
        } else {
            imgIconType.setColorFilter(Color.TRANSPARENT);
        }

        AdapterUtil.adaptView(activity, view, (Mapable) gatheringNode);
        AdapterUtil.adaptView(activity, view, (Timable) gatheringNode);

        /* removed image
        imgShowNode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.showGatheringNode(gatheringNode);
            }
        });
        */

        return view;
    }



}