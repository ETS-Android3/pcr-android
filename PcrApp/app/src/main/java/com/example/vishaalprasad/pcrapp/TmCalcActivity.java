package com.example.vishaalprasad.pcrapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class TmCalcActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ABtn;
    private Button TBtn;
    private Button CBtn;
    private Button GBtn;
    private Button clearBtn;
    private Button deleteBtn;
    private Button calcBtn;

    private TextView nucleotidesTV;
    private TextView temperatureText;

    private Stack<Character> nucleotidesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tm_calc);

        initialize();
    }

    private void initialize() {
        nucleotidesArr = new Stack<>();

        ABtn = (Button) findViewById(R.id.tm_calc_A_btn);
        TBtn = (Button) findViewById(R.id.tm_calc_T_btn);
        CBtn = (Button) findViewById(R.id.tm_calc_C_btn);
        GBtn = (Button) findViewById(R.id.tm_calc_G_btn);
        calcBtn = (Button) findViewById(R.id.tm_calc_calc_button);
        clearBtn = (Button) findViewById(R.id.tm_calc_clear_button);
        deleteBtn = (Button) findViewById(R.id.tm_calc_delete_button);

        nucleotidesTV = (TextView) findViewById(R.id.tm_calc_nucleotides);
        temperatureText = (TextView) findViewById(R.id.tm_calc_temperature_text);

        ABtn.setOnClickListener(this);
        TBtn.setOnClickListener(this);
        CBtn.setOnClickListener(this);
        GBtn.setOnClickListener(this);
        calcBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        temperatureText.setVisibility(View.INVISIBLE);

        updateText();
    }

    @Override
    public void onClick(View view) {
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
            case R.id.tm_calc_calc_button:
                temperatureText.setVisibility(View.VISIBLE);
                calculateTemperature();
                break;
            case R.id.tm_calc_delete_button:
                if (!nucleotidesArr.isEmpty()) nucleotidesArr.pop();
                temperatureText.setVisibility(View.INVISIBLE);
                break;
            case R.id.tm_calc_clear_button:
                nucleotidesArr.clear();
                temperatureText.setVisibility(View.INVISIBLE);
            default:
                break;
        }

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
        float ftm = 0;

        for (int i = 0; i < nucleotidesArr.size(); i++) {
            switch (nucleotidesArr.get(i)) {
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
        if (nucleotidesArr.size() < 14)
            ftm = ((2f * (fa + ft)) + (4f * (fg + fc)));
        else
            ftm = (64.9f + (41f * (fg + fc - 16.4f)) / (fa + fc + fg + ft));

        temperatureText.setText("" + ftm + (char) 0x00B0 + "C");
    }

    /*
        public static void main(String[] args) {

		Scanner kb= new Scanner (System.in);
		 String fprimer= kb.next();
		 //f primers






		//buffer needs toggle




	}
     */
}
