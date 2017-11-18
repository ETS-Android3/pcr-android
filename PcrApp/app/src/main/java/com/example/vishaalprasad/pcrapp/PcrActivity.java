package com.example.vishaalprasad.pcrapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vishaalprasad.pcrapp.reactant_helpers.BufferReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.CustomReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.DntpReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ForwardPrimerReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.MissingStockConcentrationException;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrEngine;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrQuantity;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrReactable;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PolymeraseReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReactionVolume;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReversePrimerReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.TemplateReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.UnitMismatchException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PcrActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PcrActivity";

    private static final int REQUEST_STOCK_CONCENTRATIONS = 1001;

    // Views
    private Button calculateButton;
    private Button customReactantButton;
    private RecyclerView reactantsRecyclerView;

    private List<PcrReactable> reactables;
    private ReactableAdapter reactantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcr);

        initializeDefaultReactants();
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.pcr_menu_stock:

                Intent stockConcentrationIntent = new Intent(this, StockConcentrationActivity.class);
                stockConcentrationIntent.putExtra(StockConcentrationActivity.KEY_REACTABLE_LIST_INTENT, (Serializable) reactables);
                startActivityForResult(stockConcentrationIntent, REQUEST_STOCK_CONCENTRATIONS);
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_STOCK_CONCENTRATIONS:

                if (resultCode == RESULT_OK) {
                    reactables = (List<PcrReactable>) data.getExtras().getSerializable(StockConcentrationActivity.KEY_REACTABLE_LIST_RESULT);
                    reactantAdapter.setItems(reactables);

                    ForwardPrimerReactant forwardPrimerReactant = null;
                    ReversePrimerReactant reversePrimerReactant = null;
                    for (PcrReactable reactable : reactables) {

                        if (reactable instanceof ForwardPrimerReactant) {
                            forwardPrimerReactant = (ForwardPrimerReactant) reactable;

                        } else if (reactable instanceof ReversePrimerReactant) {
                            reversePrimerReactant = (ReversePrimerReactant) reactable;
                        }
                    }
                    reversePrimerReactant.setForwardPrimerReference(forwardPrimerReactant);
                }
                break;

            default:
                break;
        }
    }

    private void initialize() {
        calculateButton = (Button) findViewById(R.id.pcr_act_calculate_btn);
        calculateButton.setOnClickListener(this);
        customReactantButton = (Button) findViewById(R.id.pcr_act_custom_reactant);
        customReactantButton.setOnClickListener(this);

        reactantsRecyclerView = (RecyclerView) findViewById(R.id.pcr_act_recycler_view);

        reactantAdapter = new ReactableAdapter(reactables);

        reactantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reactantsRecyclerView.setAdapter(reactantAdapter);
    }

    private void initializeDefaultReactants() {
        reactables = new ArrayList<>();
        reactables.add(new DntpReactant());
        reactables.add(new BufferReactant());
        reactables.add(new PolymeraseReactant());
        reactables.add(new TemplateReactant());
        ForwardPrimerReactant forwardPrimerReactant = new ForwardPrimerReactant();
        reactables.add(forwardPrimerReactant);
        reactables.add(new ReversePrimerReactant(forwardPrimerReactant));
        reactables.add(new ReactionVolume());
        reactables.add(new PcrQuantity());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.pcr_act_calculate_btn:

                // engine -- calculation
//                try {

                    Intent resultActivityIntent = new Intent(this, PcrResultActivity.class);
                    resultActivityIntent.putExtra(PcrResultActivity.KEY_REACTABLE_LIST, (Serializable)
                            PcrEngine.Companion.calculatePcr(reactables, (ReactionVolume) reactables.get(reactables.size() - 2), getResources()));
                    resultActivityIntent.putExtra(PcrResultActivity.KEY_RACTION_VOLUME, ((ReactionVolume) reactables.get(reactables.size() - 2)).getAmount());
                    resultActivityIntent.putExtra(PcrResultActivity.KEY_RACTION_QUANTITY, ((PcrQuantity) reactables.get(reactables.size() - 1)).getQuantity());
                    startActivity(resultActivityIntent);

//                } catch (UnitMismatchException e) {
//                    errorDialog(getString(R.string.error_mismatch));
//                } catch (MissingStockConcentrationException e1) {
//                    Log.e(TAG, "PcrEngine - ", e1);
//                    errorDialog(getString(R.string.error_missing_stock));
//                }

                break;

            case R.id.pcr_act_custom_reactant:

                createCustomReactant();
                break;

            default:
                break;
        }
    }

    /**
     * Allow the user to input a custom reactant
     * To be used with the Custom Reactant button
     */
    private void createCustomReactant() {

        EditText reactantNameEditText = new EditText(this);
        reactantNameEditText.setHint(R.string.custom_reactant_dialog_message);

        new AlertDialog.Builder(this)
                .setTitle(R.string.custom_reactant)
                .setView(reactantNameEditText)
                .setPositiveButton(R.string.done, (dialog, which) -> {

                    reactables.add(reactables.size() - 2,
                            new CustomReactant(reactantNameEditText.getText().toString()));

                })
                .setNegativeButton(R.string.cancel, ((dialog, which) -> dialog.dismiss()))
                .show();

    }


    private void errorDialog(String message) {

        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setNeutralButton(R.string.okay, (dialog, which) -> dialog.dismiss())
                .show();
    }
}