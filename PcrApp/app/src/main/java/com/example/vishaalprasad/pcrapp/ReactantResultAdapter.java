package com.example.vishaalprasad.pcrapp;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrResult;

import java.util.List;

/**
 * Recycler View Adapter to be used with {@link PcrResult}
 */
public class ReactantResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ReactableAdapter";

    private List<PcrResult> items;
    private int reactionQuantity;

    public ReactantResultAdapter(List<PcrResult> items, int reactionQuantity) {
        setItems(items,reactionQuantity);
    }

    public void setItems(List<PcrResult> items, int reactionQuantity) {
        this.items = items;
        this.reactionQuantity = reactionQuantity;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        
        PcrResult pcrResult = items.get(position);
        ResultViewHolder reactantViewHolder = (ResultViewHolder) holder;

        Resources res = PcrApplication.getContext().getResources();

        reactantViewHolder.titleTextView.setText(pcrResult.getName());
        reactantViewHolder.perTubeTextView.setText(res.getString(R.string.result_unit_microliter_string, pcrResult.getPerTube()));
        reactantViewHolder.masterMixTextView.setText(res.getString(R.string.result_unit_microliter_string, pcrResult.getPerTube() * reactionQuantity));
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResultViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_result_reactant, parent, false));
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView perTubeTextView;
        private TextView masterMixTextView;

        public ResultViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.cell_result_reactant_title);
            perTubeTextView = (TextView) itemView.findViewById(R.id.cell_result_reactant_per_tube);
            masterMixTextView = (TextView) itemView.findViewById(R.id.cell_result_reactant_master_mix);
        }
    }
}
