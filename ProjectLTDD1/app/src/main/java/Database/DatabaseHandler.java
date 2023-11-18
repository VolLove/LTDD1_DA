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
    private static final int DATABASE_VERSION = 2;


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

        //Thư: hồ sơ, thư, các loại văn bản viết trên giấy
        //Hàng dễ vỡ
        //Chất lỏng
        //Đồ điện tử
        //Hàng đông lạnh
        BunkerActivity.typeParcels.add(new TypeParcel(0, "All", 0));
        BunkerActivity.typeParcels.add(new TypeParcel(1, "Thư", 10000));
        BunkerActivity.typeParcels.add(new TypeParcel(2, "Hàng dễ vỡ", 100000));
        BunkerActivity.typeParcels.add(new TypeParcel(3, "Chất lỏng", 10000));
        BunkerActivity.typeParcels.add(new TypeParcel(4, "Đồ điện tử", 30000));
        BunkerActivity.typeParcels.add(new TypeParcel(5, "Hàng đông lạnh", 50000));
        //int parcel_id, int id_personnel, int id_type, int status, String title, String name_sender,
        // String phone_sender, String name_receiver, String phone_receiver, String address_receiver,
        // double transport_free, double weight, Date date_get, Date date_trans
        try {
            BunkerActivity.data_LV.add(new Parcel(0, 0, 1, 0, "Nguyen Van A", "0123456789",
                    "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                    "decription11", 0.2, "1/10/2023", "1/1/1"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            BunkerActivity.data_LV.add(new Parcel(1, 1, 2, 2, "Nguyen Van A", "0123456789",
                    "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                    "decription11", 0.2, "1/10/2023", "1/1/1"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            BunkerActivity.data_LV.add(new Parcel(2, 2, 3, 2, "Nguyen Van A", "0123456789",
                    "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                    "decription11", 0.2, "1/10/2023", "1/1/1"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            BunkerActivity.data_LV.add(new Parcel(3, 2, 4, 2, "Nguyen Van A", "0123456789",
                    "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                    "decription11", 0.2, "1/10/2023", "1/1/1"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            BunkerActivity.data_LV.add(new Parcel(4, 1, 5, 2, "Nguyen Van A", "0123456789",
                    "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                    "decription11", 0.2, "1/10/2023", "1/1/1"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            BunkerActivity.data_LV.add(new Parcel(5, 0, 1, 2, "Nguyen Van A", "0123456789",
                    "Nguyen Van B", "0123456789", "43, Nguyễn Chí Thanh, Ba Đình, Hà Nội",
                    "decription11", 0.2, "1/10/2023", "1/1/1"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        //
        BunkerActivity.personnels.add(new Personnel(0, "User1", "1234567890", "Nhan vien 1", "0123456789", "abc"));
        BunkerActivity.personnels.add(new Personnel(1, "User2", "1234567890", "Nhan vien 2", "0123456789", "abc"));
        BunkerActivity.personnels.add(new Personnel(2, "User3", "1234567890", "Nhan vien 3", "0123456789", "abc"));
        BunkerActivity.upAll(BunkerActivity.data_LV, BunkerActivity.typeParcels, BunkerActivity.personnels);

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
