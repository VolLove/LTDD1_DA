package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ActionMenuView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;
import Orther.ParcelAdapter;

public class BunkerActivity extends AppCompatActivity {
    ListView lvDanhSach;
    static List<Parcel> data_LV = new ArrayList<>();
    static List<TypeParcel> typeParcels = new ArrayList<>();
    static ParcelAdapter parcelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        lvDanhSach.setAdapter(parcelAdapter);
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
        typeParcels.add(new TypeParcel(1, "Thư", 10000));
        typeParcels.add(new TypeParcel(2, "Hàng dễ vỡ", 100000));
        typeParcels.add(new TypeParcel(3, "Chất lỏng", 10000));
        typeParcels.add(new TypeParcel(3, "Đồ điện tử", 30000));
        typeParcels.add(new TypeParcel(3, "Hàng đông lạnh", 50000));
        //int parcel_id, int id_personnel, int id_type, int status, String title, String name_sender,
        // String phone_sender, String name_receiver, String phone_receiver, String address_receiver,
        // double transport_free, double weight, Date date_get, Date date_trans
        data_LV.add(new Parcel(1, 1, 1, 0, "Thư gửi anh A", "Nguyen Van A", "0123456789",
                "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội", 100000,
                0.2, "1/10/2023", "1/1/1"));
        data_LV.add(new Parcel(2, 1, 1, 2, "Thư gửi anh A", "Nguyen Van A", "0123456789",
                "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội", 100000,
                0.2, "1/10/2023", "1/1/1"));
    }


    private void setControl() {
        lvDanhSach = findViewById(R.id.bunkerLvDanhSach);
    }
}