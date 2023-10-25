package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Bai5Activity extends AppCompatActivity {
    Button btnDangKy, btnNhapLai;
    CheckBox cb1, cb2, cb3;
    RadioButton rad1, rad2, rad3;
    EditText edtName, edtCCCD, edtOrther;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_5);
        setControl();
        setEvent();
    }

    private void setEvent() {
        btnNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCCCD.setText(null);
                edtName.setText(null);
                edtOrther.setText(null);
                tvResult.setText(null);
                rad1.setChecked(true);
                cb1.setChecked(false);
                cb2.setChecked(false);
                cb3.setChecked(false);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKyThongTin();
            }
        });
    }

    private void DangKyThongTin() {
        String bang = "",soThich = "";
        if (rad1.isChecked()){
            bang = rad1.getText().toString();
        }
        if (rad2.isChecked()){
            bang = rad2.getText().toString();
        }
        if (rad3.isChecked()){
            bang = rad3.getText().toString();
        }
        String msg = "---------//---------\n"
                + "Họ tên: " + edtName.getText() + "\n"
                + "CCCD: " + edtCCCD . getText()+"\n"
                +"Bằng cấp: " + bang + "\n"
                +"Sở thích: " + soThich + "\n"
                +"Thông tin khác: " + edtOrther;
        tvResult.setText(msg);
        tvResult.setTextSize(18);
        tvResult.setBackgroundResource(R.color.green);

    }

    private void setControl() {
        btnDangKy = findViewById(R.id.bai5btnDangKy);
        btnNhapLai = findViewById(R.id.bai5btnNhapLai);
        cb1 = findViewById(R.id.bai5cb1);
        cb2 = findViewById(R.id.bai5cb2);
        cb3 = findViewById(R.id.bai5cb3);
        rad1 = findViewById(R.id.bai5rad1);
        rad2 = findViewById(R.id.bai5rad2);
        rad3 = findViewById(R.id.bai5rad3);
        edtCCCD = findViewById(R.id.bai5edtCCCD);
        edtName = findViewById(R.id.bai5edtName);
        edtOrther = findViewById(R.id.bai5edtOrther);
        tvResult = findViewById(R.id.ai5tvResult);
        rad1.setChecked(true);
    }
}