package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai3Activity extends AppCompatActivity {
    Button btnDangKy, btnNhapLai;
    TextView tvOut;
    EditText edtTaiKhoan, edtDienThoai, edtMatKhau, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_3);
        setcontrol();
        setEvent();
    }

    private void setEvent() {
        btnNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDienThoai.setText(null);
                edtMatKhau.setText(null);
                edtTaiKhoan.setText(null);
                edtEmail.setText(null);
                tvOut.setText(null);
                tvOut.setBackgroundResource(R.color.white);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtTaiKhoan.getText().toString().length() == 0 ||
                        edtEmail.getText().toString().length() == 0 ||
                        edtDienThoai.getText().toString().length() == 0 ||
                        edtMatKhau.toString().length() == 0) {
                    if (edtMatKhau.getText().toString().length()==0){
                        edtMatKhau.setError("Mật khẩu không để trống!");
                    }
                    if (edtEmail.getText().toString().length()==0){
                        edtEmail.setError("Email không để trống!");
                    }
                    if (edtDienThoai.getText().toString().length()==0){
                        edtDienThoai.setError("Điện thoại không để trống!");
                    }
                    if (edtTaiKhoan.getText().toString().length()==0){
                        edtTaiKhoan.setError("Tài khoản không để trống!");
                    }
                } else {
                    String string = "Tài khoản: " + edtTaiKhoan.getText() +
                            "\nMật khẩu: " + edtMatKhau.getText() +
                            "\nSố điện thoại: " + edtDienThoai.getText() +
                            "\nEmail: " + edtEmail.getText();
                    btnNhapLai.callOnClick();
                    tvOut.setText(string);
                    tvOut.setBackgroundResource(R.color.green);
                }
            }
        });
    }

    private void setcontrol() {
        btnDangKy = findViewById(R.id.bai3btnDangky);
        btnNhapLai = findViewById(R.id.bia3btnNhapLai);
        tvOut = findViewById(R.id.bai3tvOut);
        edtEmail = findViewById(R.id.bai3edtEmail);
        edtDienThoai = findViewById(R.id.bai3edtDienThoai);
        edtMatKhau = findViewById(R.id.bai3edtMatKhau);
        edtTaiKhoan = findViewById(R.id.bai3edtTaiKhoan);
    }
}