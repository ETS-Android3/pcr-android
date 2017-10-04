package com.example.vishaalprasad.pcrapp;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.vishaalprasad.pcrapp.reactant_helpers.PcrResult;
import com.example.vishaalprasad.pcrapp.reactant_helpers.ReactionVolume;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PcrResultActivity extends AppCompatActivity {

    public static final String KEY_REACTABLE_LIST = "key_reactable_list";
    public static final String KEY_RACTION_VOLUME = "key_reaction_volume";
    public static final String KEY_RACTION_QUANTITY = "key_reactable_qty";

    private List<PcrResult> pcrResults;
    private int quantity;
    private double reactionVolume;

    private TextView quantityTextView;
    private TextView volumeTextView;
    private RecyclerView resultsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pcr_result);

        getData();

        initializeAndShowResults();
    }

    private void getData() {
        pcrResults = (List<PcrResult>) getIntent().getSerializableExtra(KEY_REACTABLE_LIST);

        reactionVolume = getIntent().getDoubleExtra(KEY_RACTION_VOLUME, 0d);
        quantity = getIntent().getIntExtra(KEY_RACTION_QUANTITY, 1);
    }

    private void initializeAndShowResults() {

        quantityTextView = (TextView) findViewById(R.id.act_result_quantity);
        volumeTextView = (TextView) findViewById(R.id.act_result_volume);
        resultsRecyclerView = (RecyclerView) findViewById(R.id.act_result_recycler);

        AssetManager manager = getAssets();
        Typeface robotoSlabTypeface = Typeface.createFromAsset(manager, String.format(Locale.US, "fonts/%s", "RobotoSlab-Light.ttf"));

        quantityTextView.setTypeface(robotoSlabTypeface);
        volumeTextView.setTypeface(robotoSlabTypeface);

        quantityTextView.setText(getString(R.string.result_unit_multiplication, quantity));
        volumeTextView.setText(getString(R.string.result_unit_microliter_string, reactionVolume));

        ReactantResultAdapter resultAdapter = new ReactantResultAdapter(pcrResults, quantity);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultsRecyclerView.setAdapter(resultAdapter);
    }
}
