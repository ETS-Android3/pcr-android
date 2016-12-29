package com.example.vishaalprasad.pcrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    //views
    private Button doneBtn;

    //starting variables (inputted by user)
    private float dntp0;
    private float fPrimer0;
    private float rPrimer0;
    private float rxnVolume;
    private int rxnQty;
    private float pol0;
    private float buffer0;

    //final variables
    private float template;
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

    private void initialize(){
        doneBtn = (Button) findViewById(R.id.main_act_done_btn);
        doneBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_act_done_btn:
                getAllInputs();
                break;

            default:
                break;
        }
    }

    private void getAllInputs(){

        try {
            dntp0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_dntp)).getText().toString());
            buffer0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_buffer)).getText().toString());
            pol0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_polymerase)).getText().toString());
            water = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_water)).getText().toString());
            fPrimer0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_fprimer)).getText().toString());
            rPrimer0 = Float.parseFloat(((TextView) findViewById(R.id.main_act_et_rprimer)).getText().toString());



        } catch (NumberFormatException e){
            /* TODO: AlertDialog: bad number format */
        }

    }

    private void calcBuffer(){
        buffer1 = rxnVolume / buffer0;
    }

    private float calcDntp(){
        dntp1=(dntpstock)

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
