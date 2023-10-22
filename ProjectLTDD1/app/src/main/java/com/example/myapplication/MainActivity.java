package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Database.DatabaseHandler;
import Orther.HashPassword;

public class MainActivity extends AppCompatActivity {
    Button loginBtnRegister, loginBtnLogin;
    EditText loginEdtUserName, loginEdtPassword;
    TextView loginTvMessage;
    private DatabaseHandler databaseHandler;
    public static final int REQUEST_CODE_REGISTER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
        SQLiteDatabase database = databaseHandler.getReadableDatabase();
        setControl();
        setEvent();
    }

    private void setEvent() {
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = loginEdtUserName.getText().toString();
                String password = loginEdtPassword.getText().toString();

                if (username.length() != 0 && password.length() != 0) {
                    Login(username, password);
                } else {
                    loginTvMessage.setText("Vui lòng nhập tài khoản và mật khẩu!");
                }
                Clear();
            }
        });
        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterView();
            }
        });

    }

    private void setControl() {
        loginBtnRegister = findViewById(R.id.loginBtnRegister);
        loginEdtPassword = findViewById(R.id.loginEdtPassword);
        loginEdtUserName = findViewById(R.id.loginEdtUserName);
        loginBtnLogin = findViewById(R.id.loginBtnLogin);
        loginTvMessage = findViewById(R.id.loginTvMessage);
    }

    private void Login(String userName, String passsWord) {
        String pass = HashPassword.hashPassword(passsWord);

        if (databaseHandler.login(userName, pass)) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } else {
            loginTvMessage.setText("Tên tài khoản hoặc mật khẩu sai!");
        }
    }

    private void RegisterView() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void Clear() {
        loginEdtPassword.setText("");
        loginEdtUserName.setText("");
    }
}