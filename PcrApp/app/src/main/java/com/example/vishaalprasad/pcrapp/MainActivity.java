package com.example.vishaalprasad.pcrapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_welcome_txt)
    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new PcrApplication();

        ButterKnife.bind(this);

        initialize();
    }

    private void initialize() {

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));

        // custom font (Roboto Slab) for the welcome text
        AssetManager manager = getAssets();
        Typeface robotoSlabTypeface = Typeface.createFromAsset(manager, String.format(Locale.US, "fonts/%s", "RobotoSlab-Light.ttf"));
        welcomeTextView.setTypeface(robotoSlabTypeface);
    }

    @OnClick(R.id.main_pcr_calculator_button)
    void pcrCalcButtonClick() {
        Intent pcrActivityIntent = new Intent(this, PcrActivity.class);
        startActivity(pcrActivityIntent);
    }

    @OnClick(R.id.main_tm_calculator_button)
    void tmCalcButtonClick() {
        Intent tmActivityIntent = new Intent(this, TmCalcActivity.class);
        startActivity(tmActivityIntent);
    }

}
