package com.example.vishaalprasad.pcrapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TmCalcActivity extends AppCompatActivity {

    @BindView(R.id.tm_calc_nucleotides) TextView nucleotidesTV;
    @BindView(R.id.tm_calc_temperature_text) TextView temperatureText;

    private Stack<Character> nucleotidesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm_calc);

        ButterKnife.bind(this);

        nucleotidesArr = new Stack<>();

        temperatureText.setVisibility(View.INVISIBLE);

        updateText();
    }

    @OnClick({R.id.tm_calc_A_btn,
            R.id.tm_calc_T_btn,
            R.id.tm_calc_C_btn,
            R.id.tm_calc_G_btn})
    void push(View view) {
        switch (view.getId()) {
            case R.id.tm_calc_A_btn:
                nucleotidesArr.push('A');
                break;
            case R.id.tm_calc_T_btn:
                nucleotidesArr.push('T');
                break;
            case R.id.tm_calc_C_btn:
                nucleotidesArr.push('C');
                break;
            case R.id.tm_calc_G_btn:
                nucleotidesArr.push('G');
                break;
        }

        updateText();
    }

    @OnClick(R.id.tm_calc_calc_button)
    void onCalculateClick() {
        temperatureText.setVisibility(View.VISIBLE);
        calculateTemperature();

        updateText();
    }


    @OnClick(R.id.tm_calc_delete_button)
    void onDeleteClick() {
        if (!nucleotidesArr.isEmpty()) {
            nucleotidesArr.pop();
        }

        temperatureText.setVisibility(View.INVISIBLE);

        updateText();
    }

    @OnClick(R.id.tm_calc_clear_button)
    void onClearClick() {
        nucleotidesArr.clear();
        temperatureText.setVisibility(View.INVISIBLE);

        updateText();
    }

    private void updateText() {

        if (nucleotidesArr.isEmpty()) {
            nucleotidesTV.setTextColor(Color.parseColor("#aaaaaa"));
            nucleotidesTV.setText(R.string.input_nucleotides);
        } else {
            Character[] nucleotidesListCharacters = new Character[nucleotidesArr.size()];
            nucleotidesArr.toArray(nucleotidesListCharacters);
            StringBuilder sb = new StringBuilder(nucleotidesListCharacters.length);
            for (Character c : nucleotidesListCharacters)
                sb.append(c.charValue());

            nucleotidesTV.setText(sb.toString());
            nucleotidesTV.setTextColor(Color.BLACK);
        }
    }

    private void calculateTemperature() {
        float fa = 0;
        float fg = 0;
        float fc = 0;
        float ft = 0;
        float ftm;

        for (char base : nucleotidesArr) {
            switch (base) {
                case 'A':
                    fa++;
                    break;

                case 'C':
                    fc++;
                    break;

                case 'G':
                    fg++;
                    break;

                case 'T':
                    ft++;
                    break;

                default:
                    break;
            }
        }

        if (nucleotidesArr.size() < 14) {
            ftm = ((2f * (fa + ft)) + (4f * (fg + fc)));
        } else {
            ftm = (64.9f + (41f * (fg + fc - 16.4f)) / (fa + fc + fg + ft));
        }

        temperatureText.setText(getString(R.string.temp_celcius, ftm));
    }
}