package com.example.vishaalprasad.pcrapp;

import android.content.Context;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.vishaalprasad.pcrapp.reactant_helpers.ConcentrationReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.StockConcentration;

import java.util.Locale;

public class StockConcentrationDialogPreference extends DialogPreference {

    private ConcentrationReactant concentrationReactant;

    private EditText stockConcretationEditText;
    private Spinner stockConcentrationUnitSpinner;

    public StockConcentrationDialogPreference(Context context, @NonNull ConcentrationReactant origConcentrationReactant) {
        super(context);

        setDialogLayoutResource(R.layout.layout_stock_conc_pref);

        this.concentrationReactant = origConcentrationReactant;

        setTitle(concentrationReactant.getName(context.getResources()));
        updateSummary();
    }

    private void updateSummary() {

        if (concentrationReactant.getStockConcentration() == null) {
            setSummary(getContext().getString(R.string.missing_stock_concentration));
        } else {
            setSummary(String.format(Locale.US, "%f %s", concentrationReactant.getStockConcentration().getAmount(),
                    concentrationReactant.getStockConcentration().getUnit().getDisplayName(getContext().getResources())));
        }
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        if (concentrationReactant.getStockConcentration() == null) {
            concentrationReactant.setStockConcentration(new StockConcentration(concentrationReactant.getPossibleStockConcUnits().get(0)));
        }

        stockConcretationEditText = (EditText) view.findViewById(R.id.pref_stock_conc_input);
        stockConcentrationUnitSpinner = (Spinner) view.findViewById(R.id.pref_stock_conc_unit);

        final ArrayAdapter<CharSequence> unitArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_textview,
                concentrationReactant.getPossibleStockConcUnitNames(getContext().getResources()));
        unitArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stockConcretationEditText.setText(concentrationReactant.getStockConcentration().getAmount() + "");

        stockConcentrationUnitSpinner.setAdapter(unitArrayAdapter);
        stockConcentrationUnitSpinner.setSelection(unitArrayAdapter.getPosition(
                concentrationReactant.getStockConcentration().getUnit().getDisplayName(getContext().getResources())));
        stockConcentrationUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                concentrationReactant.getStockConcentration().setUnit(concentrationReactant.getPossibleStockConcUnits().get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { /* do nothing */ }
        });
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (!positiveResult) return;

        try {
            concentrationReactant.getStockConcentration().setAmount(
                    Float.valueOf(stockConcretationEditText.getText().toString()));
        } catch (Exception e) {
            concentrationReactant.getStockConcentration().setAmount(0f);
        }

        updateSummary();

        super.onDialogClosed(positiveResult);
    }
}
