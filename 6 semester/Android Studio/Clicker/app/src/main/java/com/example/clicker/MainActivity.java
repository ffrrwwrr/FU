package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mainText;
    Button plusBtn;
    Button minusBtn;
    Button resetBtn;
    private long score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText = (TextView) findViewById(R.id.mainTxt);
        plusBtn = (Button) findViewById(R.id.plus_btn);
        minusBtn = (Button) findViewById(R.id.minus_btn);
        resetBtn = (Button) findViewById(R.id.reset_btn);
        plusBtn.setOnClickListener(clickListener);
        minusBtn.setOnClickListener(clickListener2);
        resetBtn.setOnClickListener(clickListener3);
    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            score ++;
            String s = "Кликов: " + score;
            mainText.setText(s.toCharArray(),0, s.length());
        }
    };

    View.OnClickListener clickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            score --;
            String s = "Кликов: " + score;
            mainText.setText(s.toCharArray(),0, s.length());
        }
    };
    View.OnClickListener clickListener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            score = 0;
            String s = "Кликов: " + score;
            mainText.setText(s.toCharArray(),0, s.length());
        }
    };
}