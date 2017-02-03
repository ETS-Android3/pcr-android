package com.example.vishaalprasad.pcrapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final int RESULT_SETTINGS = 1001;

//    private static final int RESULT_DISPLAY_RESULT = 9000;

    //views
    private Button calculateBtn;
    private Button tmCalcBtn;

    //starting variables (inputted by user)
    //assuming fprimer and rprimer are starting from stock of 10 micromolar
    private float frPrimerStockConc;
    private float dntpStockPrimer;
    private float polStockPrimer;
    private float dntp0;
    private float fPrimer0;
    private float rPrimer0;
    private float rxnVolume;
    private int rxnQty;
    private float pol0;
    private float buffer0;
    private float template;

    //final variables
    private float water;
    private float dntp1;
    private float fPrimer1;
    private float rPrimer1;
    private float pol1;
    private float buffer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            case R.id.menu_prefs:

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

                getStockConcentrations();

                break;

            default:
                break;
        }

    }

    private void getStockConcentrations() {


        frPrimerStockConc = Float.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.key_pref_dntp_stock_conc), "10"));
        dntpStockPrimer = Float.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.key_pref_dntp_stock_conc), "10"));
        polStockPrimer = Float.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.key_pref_pol_stock_conc), "1"));

//        Toast.makeText(this, "Fr Primer Stock concentration: " + frPrimerStockConc, Toast.LENGTH_LONG).show();

    }

    private void initialize() {
        calculateBtn = (Button) findViewById(R.id.main_act_calculate_btn);
        calculateBtn.setOnClickListener(this);
        tmCalcBtn = (Button) findViewById(R.id.main_act_tm_calc_btn);
        tmCalcBtn.setOnClickListener(this);

        getStockConcentrations();

//        PreferenceManager.getDefaultSharedPreferences(this).getFloat(MainSettingsActivity.`)
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_act_calculate_btn:
                if (getAllInputs()) calculateAndShow();
                break;

            case R.id.main_act_tm_calc_btn:

                Intent tmCalcActivityIntent = new Intent(this, TmCalcActivity.class);
                startActivity(tmCalcActivityIntent);

            default:
                break;
        }
    }

    private boolean getAllInputs() {

        try {
            dntp0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_dntp)).getText().toString().trim());
            buffer0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_buffer)).getText().toString().trim());
            pol0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_polymerase)).getText().toString().trim());
            template = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_template)).getText().toString().trim());
            rxnVolume = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_rxn_vol)).getText().toString().trim());
            rxnQty = Integer.parseInt(((TextView) findViewById(R.id.main_act_et_rxn_qty)).getText().toString().trim());
            fPrimer0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_fprimer)).getText().toString().trim());
            rPrimer0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_rprimer)).getText().toString().trim());

            //check the numbers
            if (buffer0 == 0f)
                Toast.makeText(this, R.string.buffer_zero_error, Toast.LENGTH_LONG).show();

            return true;

        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.input_format_error, Toast.LENGTH_LONG).show();
            return false;
        }

    }

    private void calculateAndShow() {
        calcBuffer();
        calcDntp();
        calcfprimer();
        calcrprimer();
        calcwater();
        calcpol();

        Intent displayPcrResultIntent = new Intent(this, DisplayPcrResultActivity.class);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.WATER_KEY, water);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.DNTP_KEY, dntp1);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.F_PRIMER_KEY, fPrimer1);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.R_PRIMER_KEY, rPrimer1);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.POLYMERASE_KEY, pol1);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.BUFFER_KEY, buffer1);
        displayPcrResultIntent.putExtra(DisplayPcrResultActivity.RXN_QTY_KEY, rxnQty);
        startActivity(displayPcrResultIntent);
    }

    private void calcBuffer() {
        buffer1 = rxnVolume / buffer0;
    }

    private void calcDntp() {
        //dntp0 entered in micromolar
        dntp1 = (1000*(dntpStockPrimer / dntp0) / rxnVolume);
    }

    private void calcfprimer() {
        //primer0 entered in micromolar
        fPrimer1 = ((rxnVolume * fPrimer0) / (frPrimerStockConc));

    }

    private void calcrprimer() {
        //primer0 entered in micromolar
        rPrimer1 = ((rxnVolume * rPrimer0) / (frPrimerStockConc));

    }

    private void calcwater() {
        water = ((rxnVolume - template) - (fPrimer1 + rPrimer1 + buffer1 + pol1 + dntp1));
    }

    private void calcpol() {
        //pol0 entered in Units/microliter
        pol1 = ((pol0) / polStockPrimer);
    }
}
/*
    public static void main(String[] args) {
		double c1=0;
		double c2=0;
		double v1=0;
		double v2=0;
		double pipette=0;


		Scanner kb= new Scanner (System.in);
		//enter final concentration
		 c1=kb.nextDouble();
		 //enter final volume
		 v1=kb.nextDouble();
		 //enter starting concentration (stock)
		 c2=kb.nextDouble();

		 		v2=((c1*v1)/c2);
		 		pipette=v1-v2;
		 System.out.println(v2);
		 System.out.println("pipette " +v2 + " microliters into " +pipette + " microliters of solution");
 */
