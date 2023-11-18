package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseHandler;
import Model.Parcel;
import Model.Personnel;
import Model.TypeParcel;
import Orther.ParcelAdapter;

public class BunkerActivity extends AppCompatActivity {
    ImageButton btnCreate;
    ListView lvDanhSach;
    public static final int REQUEST_CODE_REGISTER = 1;
    public static List<Parcel> data_LV = new ArrayList<>();
    public static List<TypeParcel> typeParcels = new ArrayList<>();
    public static List<Personnel> personnels = new ArrayList<>();
    public static ParcelAdapter parcelAdapter;

    public static DatabaseHandler databaseHandler;
    ArrayAdapter<TypeParcel> arrayAdapter;
    Context context;
    EditText edtSearch;
    public static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_bunker);
        databaseHandler = new DatabaseHandler(this);
        database = databaseHandler.getReadableDatabase();
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
        edtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = edtSearch.getText().toString();
                parcelAdapter.getFilter().filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

        });

    }

    //int parcel_id, String title, String id_type, String name_sender, String phone_sender,
    // String name_receiver, String phone_receiver, String address_receiver, Date date_get,
    // Date date_trans,int weight, int status, int id_personnel, double transport_free
    private void khoiTao() throws ParseException {

        data_LV = getAllParcels();
        typeParcels = getAllTypeParcels();
        personnels = getAllPersonnel();
    }

    public static void upAll(List<Parcel> parcelList, List<TypeParcel> typeParcelList, List<Personnel> personnels) {
        for (Parcel parcel1 :
                parcelList) {
            addParcel(parcel1);
        }
        for (TypeParcel typeParcel : typeParcelList) {
            addTypeParcel(typeParcel);
        }
        for (Personnel personnel : personnels) {
            addPersonnel(personnel);
        }
    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.bunkerLvDanhSach);
        edtSearch = findViewById(R.id.bunkerEdtSearch);
    }

    public static void addPersonnel(Personnel personnel) {
        database = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", personnel.getUserName());
        values.put("passWord", personnel.getPassWord());
        values.put("phone", personnel.getPhone());
        values.put("name", personnel.getName());
        values.put("avatar", personnel.getAvatar());
        database.insert("Users", null, values);
    }

    public static List<Personnel> getAllPersonnel() {
        List<Personnel> personnelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Users";
        database = databaseHandler.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Personnel personnel = new Personnel();
                personnel.setId(cursor.getInt(0));
                personnel.setUserName(cursor.getString(1));
                personnel.setPassWord(cursor.getString(2));
                personnel.setPhone(cursor.getString(3));
                personnel.setName(cursor.getString(4));
                personnel.setAvatar(cursor.getString(5));
                personnelList.add(personnel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return personnelList;
    }

    public int updatePersonnel(Personnel personnel) {
        database = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", personnel.getUserName());
        values.put("passWord", personnel.getPassWord());
        values.put("phone", personnel.getPhone());
        values.put("name", personnel.getName());
        values.put("avatar", personnel.getAvatar());
        return database.update("Users", values, "id_user = ?", new String[]{String.valueOf(personnel.getId_personnel())});
    }

    public void deleteuPersonnel(int id_personnel) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete("Users", "id_user = ?", new String[]{String.valueOf(id_personnel)});

    }

    //        parcel_id INTEGER PRIMARY KEY, " +
//        "id_personnel INTEGER, " +
//                "id_type INTEGER," +
//                "status INTEGER," +
//                "name_sender TEXT," +
//                "phone_sender TEXT, " +
//                "name_receiver TEXT," +
//                "phone_receiver TEXT," +
//                "address_receiver TEXT, " +
//                "decription TEXT," +
//                "weight REAL," +
//                "date_get TEXT, " +
//                "date_trans TEXT)";
    public static void addParcel(Parcel parcel) {
        database = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_personnel", parcel.getParcel_id());
        values.put("id_type", parcel.getId_type());
        values.put("status", parcel.getStatus());
        values.put("name_sender", parcel.getName_sender());
        values.put("phone_sender", parcel.getPhone_sender());
        values.put("phone_receiver", parcel.getName_receiver());
        values.put("address_receiver", parcel.getPhone_receiver());
        values.put("decription", parcel.getDecription());
        values.put("weight", parcel.getWeight());
        values.put("date_get", parcel.getDate_get());
        values.put("date_trans", parcel.getDate_trans());
        database.insert("Parcel", null, values);

    }

    public static List<Parcel> getAllParcels() throws ParseException {
        List<Parcel> parcelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Parcel";
        database = databaseHandler.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Parcel parcel = new Parcel();
                parcel.setParcel_id(cursor.getInt(0));
                parcel.setId_personnel(cursor.getInt(1));
                parcel.setId_type(cursor.getInt(2));
                parcel.setStatus(cursor.getInt(3));
                parcel.setName_sender(cursor.getString(4));
                parcel.setPhone_sender(cursor.getString(5));
                parcel.setName_receiver(cursor.getString(6));
                parcel.setPhone_receiver(cursor.getString(7));
                parcel.setAddress_receiver(cursor.getString(8));
                parcel.setDecription(cursor.getString(9));
                parcel.setWeight(cursor.getInt(10));
                parcel.setDate_get2(cursor.getString(11));
                parcel.setDate_trans(cursor.getString(11));
                parcelList.add(parcel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return parcelList;
    }

    public int updateParcel(Parcel parcel) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_personnel", parcel.getId_personnel());
        values.put("id_type", parcel.getId_type());
        values.put("status", parcel.getStatus());
        values.put("name_sender", parcel.getName_sender());
        values.put("phone_sender", parcel.getPhone_sender());
        values.put("name_receiver", parcel.getName_receiver());
        values.put("phone_receiver", parcel.getPhone_receiver());
        values.put("address_receiver", parcel.getAddress_receiver());
        values.put("decription", parcel.getDecription());
        values.put("weight", parcel.getWeight());
        values.put("date_get", parcel.getDate_get());
        values.put("date_trans", parcel.getDate_trans());
        return db.update("Parcel", values, "parcel_id = ?", new String[]{String.valueOf(parcel.getParcel_id())});
    }

    public void deleteParcel(int parcelId) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete("Parcel", "parcel_id = ?", new String[]{String.valueOf(parcelId)});

    }

    public static void addTypeParcel(TypeParcel typeParcel) {
        database = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", typeParcel.getTitle());
        values.put("pack_free", typeParcel.getPack_free());
        database.insert("TypeParcel", null, values);
    }

    public static List<TypeParcel> getAllTypeParcels() {
        List<TypeParcel> typeParcelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM TypeParcel";
        database = databaseHandler.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TypeParcel typeParcel = new TypeParcel();
                typeParcel.setType_id(cursor.getInt(0));
                typeParcel.setTitle(cursor.getString(1));
                typeParcel.setPack_free(cursor.getInt(2));
                typeParcelList.add(typeParcel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return typeParcelList;
    }

    //    CREATE TABLE TypeParcel (type_id INTEGER PRIMARY KEY, title TEXT, pack_free REAL)";
    public int updateTypeParcel(TypeParcel typeParcel) {
        database = databaseHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", typeParcel.getTitle());
        values.put("pack_free", typeParcel.getPack_free());
        return database.update("TypeParcel", values, "type_id = ?", new String[]{String.valueOf(typeParcel.getType_id())});
    }

    public void deleteTypeParcel(int typeId) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        db.delete("TypeParcel", "type_id = ?", new String[]{String.valueOf(typeId)});
    }


}