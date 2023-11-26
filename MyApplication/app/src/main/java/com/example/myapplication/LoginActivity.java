package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHandler;
import Model.User;

public class LoginActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ánh ạ
        EditText userName = findViewById(R.id.loginEdtUserName);
        EditText password = findViewById(R.id.loginEdtPass);
        Button btnLogin = findViewById(R.id.loginBtnLogin);
        Button btnClose = findViewById(R.id.loginBtnClose);
        //main
        db = new DatabaseHandler(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().length() == 0) {
                    userName.setError("Yêu cầu tài khoản!");
                } else if (password.toString().length() == 0) {
                    password.setError("Yêu cầu mật khẩu!");
                } else {
                    User user = new User();
                    user.setUserName(userName.getText().toString());
                    user.setPassWord(password.getText().toString());
                    if (db.checkUserLogin(user)){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        TextView textView = findViewById(R.id.loginwaring);
                        textView.setText("Tài khoản hoặc mật khẩu không đúng!");
                    }
                }
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}