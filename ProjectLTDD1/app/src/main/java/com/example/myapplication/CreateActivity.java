package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ActionMenuView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import Model.TypeParcel;

public class CreateActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageButton ibback;
    ActionMenuView actionMenu;
    Button btnCrete, btnDestroy;
    EditText edtname_sender, edtphone_sender, edtname_receiver, edtphone_receiver, edtaddress_receiver, edtweight;
    Spinner spType;
    ArrayAdapter<TypeParcel> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setControl();
        setEvent();
    }

    private void setEvent() {
        List<TypeParcel> typeParcels = BunkerActivity.typeParcels;
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, typeParcels);
        spType.setAdapter(arrayAdapter);
    }

    private void setControl() {
        spType = findViewById(R.id.createSpinType);
    }
}