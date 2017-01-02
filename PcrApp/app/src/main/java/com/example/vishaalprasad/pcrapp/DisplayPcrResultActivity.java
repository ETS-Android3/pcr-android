package com.example.vishaalprasad.pcrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;

public class DisplayPcrResultActivity extends AppCompatActivity {

    //used for intent
    public final static String DNTP_KEY = "dntp_key";
    public final static String F_PRIMER_KEY = "f_primer_key";
    public final static String R_PRIMER_KEY = "r_primer_key";
    public final static String POLYMERASE_KEY = "polymerase_key";
    public final static String BUFFER_KEY = "buffer_key";
    public final static String WATER_KEY = "water_key";

    private float water;
    private float dntp;
    private float fPrimer;
    private float rPrimer;
    private float polymerase;
    private float buffer;

    private GridLayout resultsContianer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pcr_result);

        initialize();

    }

    private void initialize(){

        water = getIntent().getFloatExtra(WATER_KEY, 0);
        dntp = getIntent().getFloatExtra(DNTP_KEY, 0);
        fPrimer = getIntent().getFloatExtra(F_PRIMER_KEY, 0);
        rPrimer = getIntent().getFloatExtra(R_PRIMER_KEY, 0);
        polymerase = getIntent().getFloatExtra(POLYMERASE_KEY, 0);
        buffer = getIntent().getFloatExtra(BUFFER_KEY, 0);

        resultsContianer = (GridLayout) findViewById(R.id.activity_display_pcr_result);
        //todo::draw grid lines



    }
}
