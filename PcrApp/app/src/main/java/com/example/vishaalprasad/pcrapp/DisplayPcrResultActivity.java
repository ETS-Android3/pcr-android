package com.example.vishaalprasad.pcrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Locale;

public class DisplayPcrResultActivity extends AppCompatActivity {

    //used for intent
    public final static String DNTP_KEY = "dntp_key";
    public final static String F_PRIMER_KEY = "f_primer_key";
    public final static String R_PRIMER_KEY = "r_primer_key";
    public final static String POLYMERASE_KEY = "polymerase_key";
    public final static String BUFFER_KEY = "buffer_key";
    public final static String WATER_KEY = "water_key";
    public final static String RXN_QTY_KEY = "rxnqtykey";

    private float waterPerTube;
    private float dntpPerTube;
    private float fPrimerPerTube;
    private float rPrimerPerTube;
    private float polymerasePerTube;
    private float bufferPerTube;
    private int rxnQty;

    private GridLayout resultsContianer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pcr_result);

        initialize();
    }

    private void initialize() {

        waterPerTube = getIntent().getFloatExtra(WATER_KEY, 0);
        dntpPerTube = getIntent().getFloatExtra(DNTP_KEY, 0);
        fPrimerPerTube = getIntent().getFloatExtra(F_PRIMER_KEY, 0);
        rPrimerPerTube = getIntent().getFloatExtra(R_PRIMER_KEY, 0);
        polymerasePerTube = getIntent().getFloatExtra(POLYMERASE_KEY, 0);
        bufferPerTube = getIntent().getFloatExtra(BUFFER_KEY, 0);
        rxnQty = getIntent().getIntExtra(RXN_QTY_KEY, 1);

        resultsContianer = (GridLayout) findViewById(R.id.activity_display_pcr_result);

        //show the results

        ((TextView) findViewById(R.id.display_act_dntp_tube_result)).setText(String.format("%.4f", dntpPerTube));
        ((TextView) findViewById(R.id.display_act_f_primer_tube_result)).setText(String.format("%.4f", fPrimerPerTube));
        ((TextView) findViewById(R.id.display_act_r_primer_tube_result)).setText(String.format("%.4f", rPrimerPerTube));
        ((TextView) findViewById(R.id.display_act_polymerase_tube_result)).setText(String.format("%.4f", polymerasePerTube));
        ((TextView) findViewById(R.id.display_act_buffer_tube_result)).setText(String.format("%.4f", bufferPerTube));

        ((TextView) findViewById(R.id.display_act_dntp_tube_master)).setText(String.format("%.4f", dntpPerTube * rxnQty));
        ((TextView) findViewById(R.id.display_act_f_primer_tube_master)).setText(String.format("%.4f", fPrimerPerTube * rxnQty));
        ((TextView) findViewById(R.id.display_act_r_primer_tube_master)).setText(String.format("%.4f", rPrimerPerTube * rxnQty));
        ((TextView) findViewById(R.id.display_act_polymerase_tube_master)).setText(String.format("%.4f", polymerasePerTube * rxnQty));
        ((TextView) findViewById(R.id.display_act_buffer_tube_master)).setText(String.format("%.4f", bufferPerTube * rxnQty));
    }
}
