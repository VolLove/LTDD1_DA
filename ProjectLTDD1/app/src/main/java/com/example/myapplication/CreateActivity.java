package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;


import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;

public class CreateActivity extends AppCompatActivity {
    ImageButton btnback;
    Button btnCrete, btnDestroy;
    EditText edtname_sender, edtphone_sender, edtname_receiver, edtphone_receiver, edtaddress_receiver, edtweight, edtDecription;
    Spinner spType;
    ArrayAdapter<TypeParcel> arrayAdapter;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setControl();
        setEvent();
    }

    private void setEvent() {
        List<TypeParcel> typeParcels = BunkerActivity.typeParcels;
        typeParcels.remove(0);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, typeParcels);
        spType.setAdapter(arrayAdapter);

        btnCrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtname_sender.getText().toString().equals("")) {
                    edtname_sender.setError("Vui lòng nhập tên người gửi!");
                } else if (edtphone_sender.getText().toString().equals("")) {
                    edtphone_sender.setError("Vui lòng nhập số điện thoại!");
                } else if (edtname_receiver.getText().toString().equals("")) {
                    edtname_receiver.setError("Vui lòng nhập tên người nhận!");
                } else if (edtphone_receiver.getText().toString().equals("")) {
                    edtphone_receiver.setError("Vui lòng nhập số điện thoại!");
                } else if (edtweight.getText().toString().equals("") || Integer.parseInt(edtweight.getText().toString()) == 0) {
                    edtweight.setError("Nhập cân nặng!");
                } else if (edtaddress_receiver.getText().toString().equals("")) {
                    edtaddress_receiver.setError("Cần địa chỉ nhận hàng!");
                } else if (edtDecription.getText().toString().equals("")) {
                    edtDecription.setError("Đừng để trống!");
                } else {
                    Parcel parcel = new Parcel();
                    parcel.setStatus(0);
                    parcel.setId_type(spType.getSelectedItemPosition());
                    parcel.setId_personnel(0);
                    parcel.setParcel_id(getCurrentTime());
                    parcel.setDate_get(new Date());
                    try {
                        parcel.setDate_trans("1/1/1");
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    parcel.setAddress_receiver(edtaddress_receiver.getText().toString());
                    parcel.setName_receiver(edtname_receiver.getText().toString());
                    parcel.setName_sender(edtname_sender.getText().toString());
                    parcel.setPhone_receiver(edtphone_receiver.getText().toString());
                    parcel.setPhone_sender(edtphone_sender.getText().toString());
                    parcel.setWeight(Integer.parseInt(edtweight.getText().toString()));
                    parcel.setDecription(edtDecription.getText().toString());
                    BunkerActivity.data_LV.add(parcel);
                    BunkerActivity.parcelAdapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
        btnDestroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public static int getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int number = hour * 10000 + minute * 100 + second;
        return number;
    }

    private void setControl() {
        spType = findViewById(R.id.createSpinType);
        btnback = findViewById(R.id.createbtnBack);
        btnCrete = findViewById(R.id.createbtnAccept);
        btnDestroy = findViewById(R.id.createbtnClose);
        edtaddress_receiver = findViewById(R.id.createedtAddress);
        edtname_receiver = findViewById(R.id.createedtReceiverName);
        edtname_sender = findViewById(R.id.createedtSenderName);
        edtweight = findViewById(R.id.createedtWeight);
        edtphone_sender = findViewById(R.id.createedtSenderPhone);
        edtphone_receiver = findViewById(R.id.createedtReceiverPhone);
        edtDecription = findViewById(R.id.createedtDecrip);
    }
}