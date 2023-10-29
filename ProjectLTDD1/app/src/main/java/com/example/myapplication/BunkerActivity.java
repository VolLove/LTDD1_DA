package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;
import Orther.ParcelAdapter;

public class BunkerActivity extends AppCompatActivity {
    ImageButton btnCreate;
    ListView lvDanhSach;
    static List<Parcel> data_LV = new ArrayList<>();
    public static final int REQUEST_CODE_REGISTER = 1;
    public static List<TypeParcel> typeParcels = new ArrayList<>();
    static ParcelAdapter parcelAdapter;
    Context context;
    Toolbar bunkerTb;
    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_bunker);
        setControl();
        try {
            khoiTao();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        setEvent();
    }


    private void setEvent() {
        parcelAdapter = new ParcelAdapter(this, R.layout.card_layout, data_LV);
        lvDanhSach.setTextFilterEnabled(true);
        lvDanhSach.setAdapter(parcelAdapter);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CreateActivity.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = edtSearch.getText().toString();
                    parcelAdapter.getFilter().filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }

    //int parcel_id, String title, String id_type, String name_sender, String phone_sender,
    // String name_receiver, String phone_receiver, String address_receiver, Date date_get,
    // Date date_trans,int weight, int status, int id_personnel, double transport_free
    private void khoiTao() throws ParseException {
        //Thư: hồ sơ, thư, các loại văn bản viết trên giấy
        //Hàng dễ vỡ
        //Chất lỏng
        //Đồ điện tử
        //Hàng đông lạnh
        typeParcels.add(new TypeParcel(0, "Thư", 10000));
        typeParcels.add(new TypeParcel(1, "Hàng dễ vỡ", 100000));
        typeParcels.add(new TypeParcel(2, "Chất lỏng", 10000));
        typeParcels.add(new TypeParcel(3, "Đồ điện tử", 30000));
        typeParcels.add(new TypeParcel(4, "Hàng đông lạnh", 50000));
        //int parcel_id, int id_personnel, int id_type, int status, String title, String name_sender,
        // String phone_sender, String name_receiver, String phone_receiver, String address_receiver,
        // double transport_free, double weight, Date date_get, Date date_trans
        data_LV.add(new Parcel(1, 1, 1, 0,  "Nguyen Van A", "0123456789",
                "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                "decription", 0.2, "1/10/2023", "1/1/1"));
        data_LV.add(new Parcel(2, 1, 1, 2,"Nguyen Van A", "0123456789",
                "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                "decription", 0.2, "1/10/2023", "1/1/1"));
    }


    private void setControl() {
        lvDanhSach = findViewById(R.id.bunkerLvDanhSach);
        btnCreate = findViewById(R.id.bunkerBtnCreate);
        edtSearch = findViewById(R.id.bunkerEdtSearch);
    }
}