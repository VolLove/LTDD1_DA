package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

import Model.Parcel;
import Model.Personnel;
import Model.TypeParcel;

public class UpdateActivity extends AppCompatActivity {
    EditText edtname_sender, edtphone_sender, edtname_receiver, edtphone_receiver, edtaddress_receiver, edtdecription, edtweight;
    Spinner spipersonnel, sptype;
    Button btnSave, btnClose;
    ImageButton btnBack;
    Parcel parcel;
    List<TypeParcel> typeParcels;
    ArrayAdapter<TypeParcel> arrayAdapter;
    List<Personnel> personnels;
    ArrayAdapter<Personnel> arrayAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setControl();
        setEvent();
    }

    private void setEvent() {
        Intent intent = getIntent();
        int i = intent.getIntExtra("ID", 0);
        parcel = BunkerActivity.data_LV.get(i);

        typeParcels = BunkerActivity.typeParcels;
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, typeParcels);
        sptype.setAdapter(arrayAdapter);
        personnels = BunkerActivity.personnels;
        arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personnels);
        spipersonnel.setAdapter(arrayAdapter1);

        edtname_sender.setText(parcel.getName_sender());
        edtname_receiver.setText(parcel.getName_receiver());
        edtphone_sender.setText(parcel.getPhone_sender());
        edtphone_receiver.setText(parcel.getPhone_receiver());
        edtdecription.setText(parcel.getDecription());
        edtweight.setText(String.valueOf(parcel.getWeight()));
        edtaddress_receiver.setText(parcel.getAddress_receiver());
        for (int x = 0; x < typeParcels.size(); x++) {
            if (parcel.getId_type() == typeParcels.get(x).getType_id()) {
                sptype.setSelection(x);
            }
        }
        for (int x = 0; x < personnels.size(); x++) {
            if (parcel.getId_personnel() == personnels.get(x).getId_personnel()) {
                spipersonnel.setSelection(x);
            }
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
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
                } else if (edtweight.getText().toString().equals("")) {
                    edtweight.setError("Nhập cân nặng!");
                } else if (edtaddress_receiver.getText().toString().equals("")) {
                    edtaddress_receiver.setError("Cần địa chỉ nhận hàng!");
                } else if (edtdecription.getText().toString().equals("")) {
                    edtdecription.setError("Đừng để trống!");
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    builder.setTitle("Update!")
                            .setMessage("Bạn có muốn lưu thay đổi!")
                            .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    parcel.setId_type(sptype.getSelectedItemPosition());
                                    parcel.setId_personnel(spipersonnel.getSelectedItemPosition());
                                    parcel.setAddress_receiver(edtaddress_receiver.getText().toString());
                                    parcel.setName_receiver(edtname_receiver.getText().toString());
                                    parcel.setName_sender(edtname_sender.getText().toString());
                                    parcel.setPhone_receiver(edtphone_receiver.getText().toString());
                                    parcel.setPhone_sender(edtphone_sender.getText().toString());
                                    try {
                                        parcel.setWeight(Double.valueOf(edtweight.getText().toString()));
                                    } catch (NumberFormatException e) {
                                    }
                                    parcel.setDecription(edtdecription.getText().toString());


                                    Intent intent = new Intent(UpdateActivity.this, DetailActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("update", parcel);
                                    intent.putExtras(bundle);
                                    setResult(2, intent);
                                    BunkerActivity.parcelAdapter.notifyDataSetChanged();
                                    finish();
                                }
                            })
                            .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void setControl() {
        edtname_receiver = findViewById(R.id.updateedtReceiverName);
        edtname_sender = findViewById(R.id.updateedtSenderName);
        edtphone_receiver = findViewById(R.id.updateedtReceiverPhone);
        edtphone_sender = findViewById(R.id.updateedtSenderPhone);
        edtaddress_receiver = findViewById(R.id.updateedtAddress);
        edtdecription = findViewById(R.id.updateedtDecrip);
        edtweight = findViewById(R.id.updateedtWeight);
        spipersonnel = findViewById(R.id.updateSpinPerson);
        sptype = findViewById(R.id.updateSpinType);
        btnSave = findViewById(R.id.updatebtnAccept);
        btnClose = findViewById(R.id.updatebtnClose);
        btnSave = findViewById(R.id.updatebtnAccept);
        btnBack = findViewById(R.id.updatebtnBack);
    }
}