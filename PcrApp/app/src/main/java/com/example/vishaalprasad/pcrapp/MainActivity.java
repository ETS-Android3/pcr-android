package com.example.vishaalprasad.pcrapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private TextView welcomeTextView;
    private Button pcrCalculatorButton;
    private Button tmCalculatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new PcrApplication();

        initialize();

    }

    private void initialize() {
        welcomeTextView = (TextView) findViewById(R.id.main_welcome_txt);
        pcrCalculatorButton = (Button) findViewById(R.id.main_pcr_calculator_button);
        pcrCalculatorButton.setOnClickListener(this);
        tmCalculatorButton = (Button) findViewById(R.id.main_tm_calculator_button);
        tmCalculatorButton.setOnClickListener(this);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        // custom font (Roboto Slab) for the welcome text
        AssetManager manager = getAssets();
        Typeface robotoSlabTypeface = Typeface.createFromAsset(manager, String.format(Locale.US, "fonts/%s", "RobotoSlab-Light.ttf"));
        welcomeTextView.setTypeface(robotoSlabTypeface);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_pcr_calculator_button:

                Intent pcrActivityIntent = new Intent(this, PcrActivity.class);
                startActivity(pcrActivityIntent);
                break;

            case R.id.main_tm_calculator_button:

                Intent tmActivityIntent = new Intent(this, TmCalcActivity.class);
                startActivity(tmActivityIntent);
                break;

            default:
                break;
        }
    }
}
