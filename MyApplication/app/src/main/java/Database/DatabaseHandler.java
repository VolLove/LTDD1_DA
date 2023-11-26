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

        String insertPersonnelData = "INSERT INTO Users ( userName, passWord, phone, name, avatar) VALUES ( 'user1', 'pass1', '10000:a346e2157104c64bc4e6b52b2f178f86:e427d0b71706cc8f4344866e6840af0d68cc57094424faad4239b0e9dee6d62d', 'Nhan vien 1', 'avatar1')";
        sqLiteDatabase.execSQL(insertPersonnelData);
        insertPersonnelData = "INSERT INTO Users ( userName, passWord, phone, name, avatar) VALUES ( 'user1', 'pass1', '10000:a346e2157104c64bc4e6b52b2f178f86:e427d0b71706cc8f4344866e6840af0d68cc57094424faad4239b0e9dee6d62d', 'Nhan vien 1', 'avatar1')";
        sqLiteDatabase.execSQL(insertPersonnelData);
        insertPersonnelData = "INSERT INTO Users ( userName, passWord, phone, name, avatar) VALUES ( 'user1', 'pass1', '10000:a346e2157104c64bc4e6b52b2f178f86:e427d0b71706cc8f4344866e6840af0d68cc57094424faad4239b0e9dee6d62d', 'Nhan vien 1', 'avatar1')";
        sqLiteDatabase.execSQL(insertPersonnelData);

        String insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Thư', 0)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ('Hàng dễ vỡ', 0)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ('Chất lỏng', 0)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Đồ điện tử', 0)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Hàng đông lạnh', 0)";
        sqLiteDatabase.execSQL(insertTypeParcelData);


        String insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 2, 0, 'Sender Name 1', '123456789', 'Receiver Name 1', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 3, 0, 'Sender Name 2', '123456789', 'Receiver Name 2', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 4, 0, 'Sender Name 3', '123456789', 'Receiver Name 3', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 1, 0, 'Sender Name 4', '123456789', 'Receiver Name 4', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 2, 0, 'Sender Name 5', '123456789', 'Receiver Name 5', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (1, 3, 0, 'Sender Name 6', '123456789', 'Receiver Name 6', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 4, 0, 'Sender Name 7', '123456789', 'Receiver Name 7', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel ( id_personnel, id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 1, 0, 'Sender Name 8', '123456789', 'Receiver Name 8', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
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
