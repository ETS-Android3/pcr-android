package com.example.vishaalprasad.pcrapp;

import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.CharacterPickerDialog;
import android.text.method.QwertyKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vishaalprasad.pcrapp.PcrApplication;
import com.example.vishaalprasad.pcrapp.R;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrQuantity;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrReactable;
import com.example.vishaalprasad.pcrapp.reactant_helpers.Reactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReactionVolume;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Recycler View Adapter to be used with {@link Reactant}
 */
public class ReactantAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ReactantAdapter";

    private static final int ITEM_TYPE_REACTANT = 1;
    private static final int ITEM_TYPE_VOLUME = 2;
    private static final int ITEM_TYPE_QUANTITY = 3;

    private List<PcrReactable> items;

    public ReactantAdapter(List<PcrReactable> items) {
        setItems(items);
    }

    public void setItems(List<PcrReactable> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        PcrReactable reactable = items.get(position);

        if (reactable instanceof ReactionVolume) return ITEM_TYPE_VOLUME;

        else if (reactable instanceof PcrQuantity) return ITEM_TYPE_QUANTITY;

        else return ITEM_TYPE_REACTANT;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {
            case ITEM_TYPE_REACTANT:

                final Reactant reactant = (Reactant) items.get(position);
                ReactantViewHolder reactantViewHolder = (ReactantViewHolder) holder;

                reactantViewHolder.titleTextView.setText(reactant.getName(PcrApplication.getContext().getResources()));
                reactantViewHolder.valueEditText.setText(reactant.getAmount() + "");

                reactantViewHolder.valueEditText.addTextChangedListener(new TextWatcher() {
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { /* do nothing */ }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            reactant.setAmount(Float.valueOf(s.toString()));
                        } catch (Exception e) {
                            reactant.setAmount(0f);
                        }
                    }
                });

                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(PcrApplication.getContext(),
                        R.layout.spinner_textview, reactant.getPossibleUnitNames(PcrApplication.getContext().getResources()));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                reactantViewHolder.unitSpinner.setAdapter(adapter);
                reactantViewHolder.unitSpinner.setSelection(adapter.getPosition(
                        reactant.getUnit().getDisplayName(PcrApplication.getContext().getResources())));

                reactantViewHolder.unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        reactant.setUnit(reactant.getPossibleUnits().get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { /* do nothing */ }
                });

                break;

            case ITEM_TYPE_VOLUME:

                final ReactionVolume reactionVolume = (ReactionVolume) items.get(position);
                VolumeViewHolder volumeViewHolder = (VolumeViewHolder) holder;

                volumeViewHolder.inputVolumeEditText.setText(reactionVolume.getAmount() + "");

                volumeViewHolder.inputVolumeEditText.addTextChangedListener(new TextWatcher() {
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { /* do nothing */ }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            reactionVolume.setAmount(Float.valueOf(s.toString()));
                        } catch (NumberFormatException e) {
                            reactionVolume.setAmount(0f);
                        }
                    }
                });

                break;

            case ITEM_TYPE_QUANTITY:

                final PcrQuantity pcrQuantity = (PcrQuantity) items.get(position);
                QuantityViewHolder quantityViewHolder = (QuantityViewHolder) holder;

                quantityViewHolder.inputQuantityEditText.setText(pcrQuantity.getQuantity() + "");

                quantityViewHolder.inputQuantityEditText.addTextChangedListener(new TextWatcher() {
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* do nothing */ }

                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) { /* do nothing */ }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            pcrQuantity.setQuantity(Integer.valueOf(s.toString()));
                        } catch (NumberFormatException e) {
                            pcrQuantity.setQuantity(1);
                        }
                    }
                });

                break;

            default:
                return;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case ITEM_TYPE_REACTANT:
                return new ReactantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_reactant, parent, false));

            case ITEM_TYPE_VOLUME:
                return new VolumeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_volume, parent, false));

            case ITEM_TYPE_QUANTITY:
                return new QuantityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_quantity, parent, false));

            default:
                return null;
        }
    }

    public class ReactantViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private EditText valueEditText;
        private Spinner unitSpinner;

        public ReactantViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.cell_reactant_title);
            valueEditText = (EditText) itemView.findViewById(R.id.cell_reactant_edit_text);
            unitSpinner = (Spinner) itemView.findViewById(R.id.cell_reactant_unit_spinner);
        }
    }

    public class QuantityViewHolder extends RecyclerView.ViewHolder {

        private EditText inputQuantityEditText;

        public QuantityViewHolder(View itemView) {
            super(itemView);

            inputQuantityEditText = (EditText) itemView.findViewById(R.id.cell_quantity_edit_text);
        }
    }

    public class VolumeViewHolder extends RecyclerView.ViewHolder {

        private EditText inputVolumeEditText;

        public VolumeViewHolder(View itemView) {
            super(itemView);

            inputVolumeEditText = (EditText) itemView.findViewById(R.id.cell_volume_edit_text);
        }
    }
}
