package com.example.vishaalprasad.pcrapp;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceCategory;

import com.example.vishaalprasad.pcrapp.reactant_helpers.ConcentrationReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrReactable;

import java.io.Serializable;
import java.util.List;

public class StockConcentrationActivity extends PreferenceActivity {

    public static final String KEY_REACTABLE_LIST_INTENT = "key_reactable_list_intent";
    public static final String KEY_REACTABLE_LIST_RESULT = "key_reactable_list_result";

    private List<PcrReactable> reactables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        // add Stock Concentration PreferenceCategory
        PreferenceCategory stockConcentrationCategory = new PreferenceCategory(this);
        getPreferenceScreen().addPreference(stockConcentrationCategory);

        // get all PcrReactable from Intent
        reactables = (List<PcrReactable>) getIntent().getSerializableExtra(KEY_REACTABLE_LIST_INTENT);

        // create a Preference for each ConcentrationReactant
        for (PcrReactable reactable : reactables) {

            if (reactable instanceof ConcentrationReactant) {
                ConcentrationReactant currentConcentrationReactable = (ConcentrationReactant) reactable;

                if (currentConcentrationReactable.getPossibleStockConcUnits() == null) continue;

                StockConcentrationDialogPreference stockConcentrationPreference =
                        new StockConcentrationDialogPreference(this, currentConcentrationReactable);

                stockConcentrationCategory.addPreference(stockConcentrationPreference);
            }
        }
    }

    @Override
    public void onBackPressed() {

        setResult(RESULT_OK, new Intent().putExtra(KEY_REACTABLE_LIST_RESULT, (Serializable) reactables));
        finish();
    }
}