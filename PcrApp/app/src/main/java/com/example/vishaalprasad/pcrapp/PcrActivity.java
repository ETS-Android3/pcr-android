package com.example.vishaalprasad.pcrapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.vishaalprasad.pcrapp.reactant_helpers.BufferReactant;
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
    private Button calculateBtn;
    private RecyclerView reactantsRecyclerView;

    private List<PcrReactable> reactables;
    private ReactantAdapter reactantAdapter;

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
                }
                break;

            default:
                break;
        }
    }

    private void initialize() {
        calculateBtn = (Button) findViewById(R.id.pcr_act_calculate_btn);
        calculateBtn.setOnClickListener(this);
        reactantsRecyclerView = (RecyclerView) findViewById(R.id.pcr_act_recycler_view);

        reactantAdapter = new ReactantAdapter(reactables);

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
                try {
                    
                    PcrEngine.calculatePcr(reactables, (ReactionVolume) reactables.get(reactables.size() - 1));
                    
                    // TODO: 10/2/17 show the list (new screen)
                    
                } catch (UnitMismatchException e) {
                    errorDialog(getString(R.string.error_mismatch));
                } catch (MissingStockConcentrationException e1) {
                    errorDialog(getString(R.string.missing_stock_concentration));
                }

                break;

            default:
                break;
        }
    }

    private void errorDialog(String message) {

        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setNeutralButton(R.string.okay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}