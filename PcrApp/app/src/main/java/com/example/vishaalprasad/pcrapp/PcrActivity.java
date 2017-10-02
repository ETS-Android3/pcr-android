package com.example.vishaalprasad.pcrapp;

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
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrQuantity;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrReactable;
import com.example.vishaalprasad.pcrapp.reactant_helpers.PolymeraseReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReactantAdapter;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReactionVolume;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReversePrimerReactant;
import com.example.vishaalprasad.pcrapp.reactant_helpers.TemplateReactant;

import java.util.ArrayList;
import java.util.List;

public class PcrActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PcrActivity";

    private static final int RESULT_SETTINGS = 1001;

    // Views
    private Button calculateBtn;
    private RecyclerView reactantsRecyclerView;

    private List<PcrReactable> reactables;
    private ReactantAdapter reactantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcr);

        reactables = new ArrayList<>();
        reactantAdapter = new ReactantAdapter(reactables);

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

                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivityForResult(settingsIntent, RESULT_SETTINGS);

            default:
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case RESULT_SETTINGS:

                // TODO: 9/29/17 implement this                  

//                getStockConcentrations();

                break;

            default:
                break;
        }

    }

    private void initialize() {
        calculateBtn = (Button) findViewById(R.id.pcr_act_calculate_btn);
        calculateBtn.setOnClickListener(this);
        reactantsRecyclerView = (RecyclerView) findViewById(R.id.pcr_act_recycler_view);

        reactantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reactantsRecyclerView.setAdapter(reactantAdapter);
    }

    private void initializeDefaultReactants() {
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
//                PcrEngine.calculatePcr(reactables, quantity, volume);

                // send results to results screen

                break;


            default:
                break;
        }
    }
}