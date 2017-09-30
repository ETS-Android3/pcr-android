package com.example.vishaalprasad.pcrapp.reactant_helpers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vishaalprasad.pcrapp.PcrApplication;
import com.example.vishaalprasad.pcrapp.R;

import java.util.List;

/**
 * Recycler View Adapter to be used with {@link Reactant}
 */
public class ReactantAdapter extends RecyclerView.Adapter<ReactantAdapter.ViewHolder> {

    private static final String TAG = "ReactantAdapter";

    private List<Reactant> items;

    public ReactantAdapter(List<Reactant> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void setItems(List<Reactant> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.titleTextView.setText(items.get(position).getName(PcrApplication.getContext().getResources()));
        holder.valueEditText.setText(items.get(position).getAmount() + "");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(PcrApplication.getContext(),
                R.layout.spinner_textview, items.get(position).getPossibleUnitNames(PcrApplication.getContext().getResources()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.unitSpinner.setAdapter(adapter);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_reactant, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private EditText valueEditText;
        private Spinner unitSpinner;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.cell_reactant_title);
            valueEditText = (EditText) itemView.findViewById(R.id.cell_reactant_edit_text);
            unitSpinner = (Spinner) itemView.findViewById(R.id.cell_reactant_unit_spinner);
        }
    }
}
