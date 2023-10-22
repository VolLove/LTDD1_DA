package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "buuCucManger.sqlite";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTableUsers = "CREATE TABLE IF NOT EXISTS Users (id_user INTEGER PRIMARY KEY AUTOINCREMENT, user_name Varchar(200), password varchar(200), name  varchar(200), phone varchar(200), avatar varchar(200))";
        sqLiteDatabase.execSQL(createTableUsers);
        String createTableParcels = "CREATE TABLE IF NOT EXISTS Parcels (id_parcel INTEGER PRIMARY KEY AUTOINCREMENT, title Varchar(200), id_type integer,name_sender varchar(200),phone_sender varchar(200), name_receiver varchar(200), phone_receiver varchar(200), address_receiver varchar(200), transport_free integer, weight integer)";
        sqLiteDatabase.execSQL(createTableParcels);
        String createTableTypes = "CREATE TABLE IF NOT EXISTS Types (id_type INTEGER PRIMARY KEY AUTOINCREMENT, title Varchar(200), decription text, postage integer)";
        sqLiteDatabase.execSQL(createTableTypes);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int newVer) {
            sqLiteDatabase.execSQL("drop table if exists Users");
            sqLiteDatabase.execSQL("drop table if exists Parcels");
            onCreate(sqLiteDatabase);
    }
    public boolean login(String username, String password){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM Users WHERE user_name = ? AND password = ?";
        Cursor cursor = database.rawQuery(query,new String[]{username,password});
    if (cursor.getCount()>0){
        return true;
    }
        return false;
    }
}
