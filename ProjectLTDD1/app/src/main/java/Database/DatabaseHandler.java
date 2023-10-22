package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "buCucManger";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "user";
    //column
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "user_name";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE = "phone";
    private static final String COL_AVATAR = "avatar";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
