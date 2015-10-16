package com.blackfich.eorzeacompanion.activity.gathering;

import android.content.Context;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.blackfich.eorzeacompanion.MainActivity;
import com.blackfich.eorzeacompanion.R;
import com.blackfich.eorzeacompanion.activity.vistas.Vista;
import com.blackfich.eorzeacompanion.util.ui.EorzeanFragment;
import com.blackfich.eorzeacompanion.util.EorzeaUtils;
import com.blackfich.eorzeacompanion.util.filter.FilteredResult;
import com.blackfich.eorzeacompanion.util.filter.constraints.TimeConstrainable;
import com.blackfich.eorzeacompanion.util.ui.AdapterUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marc Fichant on 14/10/2015.
 */
public class GatheringFragment extends EorzeanFragment {

    private ListView lstGatheringNodes;

    private final GatheringNodeAdapter gatheringNodeAdapter = new GatheringNodeAdapter(this);

    private final GatheringNodeFilter gatheringNodeFilter = new GatheringNodeFilter(this);

    private final GatheringFilterConstraints gatheringFilterConstraints = new GatheringFilterConstraints();

    private final List<FilteredResult> data = new ArrayList<FilteredResult>();

    private ImageView imgFilterBotanist;

    private ImageView imgFilterMiner;

    private ImageView imgFilterFisher;

    private ImageView imgFilterARR;

    private ImageView imgFilterHW;

    private ImageView imgFilterUnspoiled;

    private ImageView imgFilterEphemeral;

    private ImageView imgFilterFolklore;

    private ImageView imgFilterTime;

    public GatheringFragment() {
        applyFilter();
    }

    @Override
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    public List<FilteredResult> getData() {
        return data;
    }

    public FilteredResult getData(int position) {
        return data.get(position);
    }

    public GatheringNode getFilteredNode(int position) {
        return getMainActivity().getGatheringNodes().get(data.get(position).getRealPosition());
    }


    public GatheringFilterConstraints getFilterConstraints() {
        return gatheringFilterConstraints;
    }

    public GatheringNodeFilter getFilter() {
        return gatheringNodeFilter;
    }

    public BaseAdapter getAdapter() {
        return gatheringNodeAdapter;
    }

    public void applyFilter() {
        Log.i(null, "applyFilter");
        gatheringNodeFilter.filter("");
        if ( lstGatheringNodes != null ) {
            lstGatheringNodes.invalidate();
        }
    }

    private AdapterView.OnItemClickListener lstOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            GatheringNode node = gatheringNodeAdapter.getItem(position);
            showGatheringNode(node);
        }
    };

    private View.OnClickListener imgFilterBotanistOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowBotanist();
            imgFilterBotanist.setImageResource(show ? R.drawable.gathering_filter_botanist_on : R.drawable.gathering_filter_botanist_off);
            applyFilter();
        }
    };
    private View.OnClickListener imgFilterMinerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowMiner();
            imgFilterMiner.setImageResource(show ? R.drawable.gathering_filter_miner_on : R.drawable.gathering_filter_miner_off);
            applyFilter();
        }
    };
    private View.OnClickListener imgFilterFisherOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowFisher();
            imgFilterFisher.setImageResource(show ? R.drawable.gathering_filter_fisher_on : R.drawable.gathering_filter_fisher_off);
            applyFilter();
        }
    };
    private View.OnClickListener imgFilterARROnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowARR();
            imgFilterARR.setImageResource(show ? R.drawable.gathering_filter_arr_on : R.drawable.gathering_filter_arr_off);
            applyFilter();
        }
    };

    private View.OnClickListener imgFilterHWOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowHW();
            imgFilterHW.setImageResource(show ? R.drawable.gathering_filter_hw_on : R.drawable.gathering_filter_hw_off);
            if (!show) {
                // disable Ephemeral & Folklore nodes is filtering out HW nodes
                imgFilterEphemeral.setClickable(false);
                imgFilterEphemeral.setImageResource(R.drawable.gathering_filter_ephemeral_off);
                imgFilterFolklore.setClickable(false);
                imgFilterFolklore.setImageResource(R.drawable.gathering_filter_folklore_off);
            } else {
                // enable Ephemeral & Folklore nodes is filtering out HW nodes
                // and restore their state to what it was
                boolean showEphemeral = gatheringFilterConstraints.isShowEphemeralNodes();
                imgFilterEphemeral.setClickable(true);
                imgFilterEphemeral.setImageResource(showEphemeral ? R.drawable.gathering_filter_ephemeral_on : R.drawable.gathering_filter_ephemeral_off);
                boolean showFolklore = gatheringFilterConstraints.isShowFolkloreNodes();
                imgFilterFolklore.setClickable(true);
                imgFilterFolklore.setImageResource(showFolklore ? R.drawable.gathering_filter_folklore_on : R.drawable.gathering_filter_folklore_off);
            }
            applyFilter();
        }
    };
    private View.OnClickListener imgFilterUnspoiledOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowUnspoiledNodes();
            imgFilterUnspoiled.setImageResource(show ? R.drawable.gathering_filter_unspoiled_on : R.drawable.gathering_filter_unspoiled_off);
            applyFilter();
        }
    };

    private View.OnClickListener imgFilterEphemeralOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowEphemeralNodes();
            imgFilterEphemeral.setImageResource(show ? R.drawable.gathering_filter_ephemeral_on : R.drawable.gathering_filter_ephemeral_off);
            applyFilter();
        }
    };

    private View.OnClickListener imgFilterFolkloreOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean show = gatheringFilterConstraints.toggleShowFolkloreNodes();
            imgFilterFolklore.setImageResource(show ? R.drawable.gathering_filter_folklore_on : R.drawable.gathering_filter_folklore_off);
            applyFilter();
        }
    };

    private View.OnClickListener imgFilterTimeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int timeFilter = gatheringFilterConstraints.getTimeFiltering();
            timeFilter = (timeFilter + 1) % TimeConstrainable.FILTER_MOD;

            gatheringFilterConstraints.setTimeFiltering(timeFilter);
            if (timeFilter == TimeConstrainable.FILTER_NONE) {
                imgFilterTime.setImageResource(R.drawable.filter_time_off);
            } else if (timeFilter == TimeConstrainable.FILTER_CURRENT) {
                imgFilterTime.setImageResource(R.drawable.filter_time_on);
            } else if (timeFilter == TimeConstrainable.FILTER_CURRENT_PLUS_1) {
                imgFilterTime.setImageResource(R.drawable.filter_time_next_on);
            }
            applyFilter();
        }
    };

    public void showGatheringNode(GatheringNode node) {
        View popupLayout = getMainActivity().showPopup(R.layout.gathering_node_new);

        LinearLayout lstSlots = (LinearLayout) popupLayout.findViewById(R.id.lstSlots);
        TextView txtNodeType = (TextView) popupLayout.findViewById(R.id.txtNodeType);

        if ("botanist".equals(node.getType())) {
            txtNodeType.setText(R.string.botanist);
        } else if ("miner".equals(node.getType())) {
            txtNodeType.setText(R.string.miner);
        } else if ("fisher".equals(node.getType())) {
            txtNodeType.setText(R.string.fisher);
        }

        LayoutInflater inflater = (LayoutInflater) getMainActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        lstSlots.removeAllViews();

        for ( GatheringNodeItem item : node.getItems() ) {
            View itemView = inflater.inflate(R.layout.gathering_node_slot, null);
            ImageView imgItemIcon = (ImageView) itemView.findViewById(R.id.imgItem);
            TextView txtItemName = (TextView) itemView.findViewById(R.id.txtItemName);
            TextView txtItemLevel = (TextView) itemView.findViewById(R.id.txtItemLevel);

            if (item == null) {
                imgItemIcon.setImageResource(R.drawable.item_nothing);
                txtItemName.setText(getMainActivity().getString("item_slot_nothing"));
                txtItemName.setTypeface(null, Typeface.BOLD_ITALIC);
                txtItemLevel.setVisibility(View.GONE);
            } else {
                Drawable itemIcon = getMainActivity().getDrawable("item_" + item.getName());
                if (itemIcon == null) {
                    itemIcon = getMainActivity().getDrawable("item_unknown");
                }
                imgItemIcon.setImageDrawable(itemIcon);
                txtItemName.setText(getString(item.getName()));
                txtItemName.setTypeface(null, Typeface.BOLD);

                String lvl = getString("item_slot_level") + item.getLevel();
                if (item.getStars() > 0) {
                    lvl += " ";
                    for (int ii = item.getStars(); ii > 0; ii--) {
                        lvl += "★";
                    }
                }
                if (item.isHidden() || item.isVariableSlot()) {
                    lvl += " (";
                    if (item.isHidden()) {
                        lvl += getString("item_hidden");
                    }
                    if (item.isHidden() && item.isVariableSlot()) {
                        lvl += ", ";
                    }
                    if (item.isVariableSlot()) {
                        lvl += getString("item_variable_slot");
                    }
                    lvl += ")";
                }
                txtItemLevel.setText(lvl);
            }
            lstSlots.addView(itemView);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gathering_fragment, container, false);
        MainActivity activity = (MainActivity) getActivity();
        lstGatheringNodes = (ListView) view.findViewById(R.id.lstGatheringNodes);
        lstGatheringNodes.setAdapter(gatheringNodeAdapter);
        imgFilterBotanist = (ImageView) view.findViewById(R.id.imgFilterBotanist);
        imgFilterMiner = (ImageView) view.findViewById(R.id.imgFilterMiner);
        imgFilterFisher = (ImageView) view.findViewById(R.id.imgFilterFisher);
        imgFilterARR = (ImageView) view.findViewById(R.id.imgFilterARR);
        imgFilterHW = (ImageView) view.findViewById(R.id.imgFilterHW);
        imgFilterUnspoiled = (ImageView) view.findViewById(R.id.imgFilterUnspoiled);
        imgFilterEphemeral = (ImageView) view.findViewById(R.id.imgFilterEphemeral);
        imgFilterFolklore = (ImageView) view.findViewById(R.id.imgFilterFolklore);
        imgFilterTime = (ImageView) view.findViewById(R.id.imgFilterTime);

        lstGatheringNodes.setOnItemClickListener(lstOnItemClickListener);
        imgFilterBotanist.setOnClickListener(imgFilterBotanistOnClickListener);
        imgFilterMiner.setOnClickListener(imgFilterMinerOnClickListener);
        imgFilterFisher.setOnClickListener(imgFilterFisherOnClickListener);
        imgFilterARR.setOnClickListener(imgFilterARROnClickListener);
        imgFilterHW.setOnClickListener(imgFilterHWOnClickListener);
        imgFilterUnspoiled.setOnClickListener(imgFilterUnspoiledOnClickListener);
        imgFilterEphemeral.setOnClickListener(imgFilterEphemeralOnClickListener);
        imgFilterFolklore.setOnClickListener(imgFilterFolkloreOnClickListener);
        imgFilterTime.setOnClickListener(imgFilterTimeOnClickListener);

        return view;
    }

    @Override
    public void updateEorzeaTime() {
        super.updateEorzeaTime();
        if (lstGatheringNodes != null && AdapterUtil.isListDamaged(data, gatheringNodeFilter)) {
            Log.d(null, "" + EorzeaUtils.getEorzeaTime() + " ->  gathering list is damaged");
            applyFilter();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GatheringFragment{");
        sb.append('}');
        return sb.toString();
    }
}
