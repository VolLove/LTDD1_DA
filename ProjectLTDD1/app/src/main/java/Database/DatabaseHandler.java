package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.BunkerActivity;

import java.text.ParseException;

import Model.Parcel;
import Model.Personnel;
import Model.TypeParcel;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 4;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Cursor CreateListUser(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Cursor CreateListParcel(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public Cursor CreateListTypeParcel(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createUsersTable = "CREATE TABLE Users (id_user INTEGER PRIMARY KEY, userName TEXT, passWord TEXT, phone TEXT, name TEXT, avatar TEXT)";
        String createParcelTable = "CREATE TABLE Parcel (parcel_id INTEGER PRIMARY KEY, " +
                "id_personnel INTEGER, " +
                "id_type INTEGER," +
                "status INTEGER," +
                "name_sender TEXT," +
                "phone_sender TEXT, " +
                "name_receiver TEXT," +
                "phone_receiver TEXT," +
                "address_receiver TEXT, " +
                "decription TEXT," +
                "weight REAL," +
                "date_get TEXT, " +
                "date_trans TEXT)";
        String createTypeParcelTable = "CREATE TABLE TypeParcel (type_id INTEGER PRIMARY KEY, title TEXT, pack_free REAL)";

        sqLiteDatabase.execSQL(createUsersTable);
        sqLiteDatabase.execSQL(createParcelTable);
        sqLiteDatabase.execSQL(createTypeParcelTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int newVer) {
        sqLiteDatabase.execSQL("drop table if exists Users");
        sqLiteDatabase.execSQL("drop table if exists Parcel");
        sqLiteDatabase.execSQL("drop table if exists TypeParcel");
        onCreate(sqLiteDatabase);
    }

    public boolean login(String username, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM Users WHERE user_name = ? AND password = ?";
        Cursor cursor = database.rawQuery(query, new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}
