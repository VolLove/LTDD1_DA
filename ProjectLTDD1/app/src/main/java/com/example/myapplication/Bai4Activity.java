package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bai4Activity extends AppCompatActivity {
    Button btnCong, btnTru, btnNhan, btnChia;
    TextView tvKetQua;
    EditText edtNum1, edtNum2, edtKetQua;
    ArrayList<PhepTinh> phepTinhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_4);
        setControl();
        setEvent();
    }

    private void setEvent() {
        phepTinhs = new ArrayList<>();

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtNum1.getText().toString().length() != 0 && edtNum2.getText().toString().length() != 0 && edtKetQua.getText().toString().length() != 0) {
                    int num1 = Integer.parseInt(edtNum1.getText().toString());
                    int num2 = Integer.parseInt(edtNum2.getText().toString());
                    int ketqua = Integer.parseInt(edtKetQua.getText().toString());
                    if ((num1 + num2) != ketqua) {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 0, "Sai"));
                    } else {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 0, "Đúng"));
                    }
                    updateList();
                }
            }
        });
        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtNum1.getText().toString().length() != 0 && edtNum2.getText().toString().length() != 0 && edtKetQua.getText().toString().length()!=0) {
                    int num1 = Integer.parseInt(edtNum1.getText().toString());
                    int num2 = Integer.parseInt(edtNum2.getText().toString());
                    int ketqua = Integer.parseInt(edtKetQua.getText().toString());
                    if ((num1 - num2) != ketqua) {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 1, "Sai"));
                    } else {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 1, "Đúng"));
                    }
                    updateList();
                }
            }
        });
        btnNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtNum1.getText().toString().length() != 0 && edtNum2.getText().toString().length() != 0 && edtKetQua.getText().toString().length()!=0) {
                    int num1 = Integer.parseInt(edtNum1.getText().toString());
                    int num2 = Integer.parseInt(edtNum2.getText().toString());
                    int ketqua = Integer.parseInt(edtKetQua.getText().toString());
                    if ((num1 * num2) != ketqua) {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 2, "Sai"));
                    } else {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 2, "Đúng"));
                    }
                    updateList();
                }
            }
        });
        btnChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtNum1.getText().toString().length() != 0 && edtNum2.getText().toString().length() != 0 && edtKetQua.getText().toString().length()!=0) {
                    int num1 = Integer.parseInt(edtNum1.getText().toString());
                    int num2 = Integer.parseInt(edtNum2.getText().toString());
                    int ketqua = Integer.parseInt(edtKetQua.getText().toString());
                    if ((num1 / num2) != ketqua) {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 3, "Sai"));
                    } else {
                        phepTinhs.add(new PhepTinh(num1, num2, ketqua, 3, "Đúng"));
                    }
                    updateList();
                }
            }
        });

    }

    private void setControl() {
        btnChia = findViewById(R.id.bai4btnChia);
        btnCong = findViewById(R.id.bai4btnCong);
        btnNhan = findViewById(R.id.bai4btnNhan);
        btnTru = findViewById(R.id.bai4btnTru);
        tvKetQua = findViewById(R.id.bai4tvKetQua);
        edtKetQua = findViewById(R.id.bai4edtResult);
        edtNum1 = findViewById(R.id.bai4edtNum1);
        edtNum2 = findViewById(R.id.bai4edtNum2);
    }

    private void updateList() {
        String x = "";
        int dung = 0, sai = 0;

        for (PhepTinh phepTinh : phepTinhs) {
            if (phepTinh.chinhxac.equals("Đúng")){
                dung++;
            }
            else {
                sai++;
            }
            x = x + "\n" + phepTinh.toString();
        }
        tvKetQua.setText("Kết Quả: Đúng: " +dung + " Sai: " + sai + x);
    }

}