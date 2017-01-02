package com.example.vishaalprasad.pcrapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    //views
    private Button calculateBtn;

    //starting variables (inputted by user)
    //assuming fprimer and rprimer are starting from stock of 10 micromolar
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

    private void initialize() {
        calculateBtn = (Button) findViewById(R.id.main_act_calculate_btn);
        calculateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_act_calculate_btn:
                getAllInputs();
                break;

            default:
                break;
        }
    }

    private void getAllInputs() {

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

            doCalculation();

        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.input_format_error, Toast.LENGTH_LONG).show();
        }

    }

    private void doCalculation() {
        calcBuffer();
        calcDntp();
        calcfprimer();
        calcrprimer();
        calcwater();
        calcpol();
    }

    private void calcBuffer() {
        buffer1 = rxnVolume / buffer0;
    }

    private void calcDntp() {
        //dntp0 entered in millimolar
        dntp1 = ((rxnVolume * dntp0) / (10_000f));
    }

    private void calcfprimer() {
        //primer0 entered in micromolar
        fPrimer1 = ((rxnVolume * fPrimer0) / (10f));

    }

    private void calcrprimer() {
        //primer0 entered in micromolar
        rPrimer1 = ((rxnVolume * rPrimer0) / (10f));

    }

    private void calcwater() {
        water = ((rxnVolume - template) - (fPrimer1 + rPrimer1 + buffer1 + pol1 + dntp1));
    }

    private void calcpol() {
        //pol0 entered in Units/microliter
        pol1 = ((rxnVolume * pol0) / 10f);
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
