package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;

public class Bai6Activity extends AppCompatActivity {
    Switch swSetting;
    LinearLayout llBox;
    ImageView imgBongDen;
    ToggleButton tgbCongTac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_6);
        setControl();
        setEvent();
    }

    private void setEvent() {
        swSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swSetting.isChecked()) {
                    llBox.setVisibility(View.VISIBLE);
                } else {
                    llBox.setVisibility(View.GONE);
                    imgBongDen.setImageResource(R.drawable.istockphoto2);
                    tgbCongTac.setChecked(false);
                    tgbCongTac.setText("BẬT");
                }
            }
        });
        tgbCongTac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tgbCongTac.isChecked()) {
                    imgBongDen.setImageResource(R.drawable.istockphoto1);
                    tgbCongTac.setText("TẮT");
                } else {
                    imgBongDen.setImageResource(R.drawable.istockphoto2);
                    tgbCongTac.setText("BẬT");
                }
            }
        });
    }

    private void setControl() {
        swSetting = findViewById(R.id.bai6swSetting);
        llBox = findViewById(R.id.bai6llBox);
        imgBongDen = findViewById(R.id.bai6imgBongDen);
        tgbCongTac = findViewById(R.id.bai6tgbCongTac);
    }
}