package Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Parcel;
import Model.TypeParcel;
import Model.User;
import Orther.HashPassword;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db.sqlite";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createParcelTable = "CREATE TABLE Parcel (parcel_id INTEGER PRIMARY KEY, " +
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
        String CREATE_USER_TABLE = "CREATE TABLE User ( id INTEGER PRIMARY KEY, username  TEXT, password TEXT)";
        sqLiteDatabase.execSQL(createParcelTable);
        sqLiteDatabase.execSQL(createTypeParcelTable);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

//        User user1 = new User("admin", "admin");
//        User user2 = new User("user", "password");
        String insertUserData = "INSERT INTO User ( username, password) VALUES ( 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918')";
        sqLiteDatabase.execSQL(insertUserData);
        insertUserData = "INSERT INTO User ( username, password) VALUES ( 'user', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8')";
        sqLiteDatabase.execSQL(insertUserData);

        String insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Thư', 10000)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ('Hàng dễ vỡ', 20000)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ('Chất lỏng', 20000)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Đồ điện tử', 30000)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Hàng đông lạnh', 30000)";
        sqLiteDatabase.execSQL(insertTypeParcelData);
        insertTypeParcelData = "INSERT INTO TypeParcel ( title, pack_free) VALUES ( 'Hàng đông lạnh 2', 30000)";
        sqLiteDatabase.execSQL(insertTypeParcelData);

        String insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (  2, 0, 'Sender Name 1', '123456789', 'Receiver Name 1', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (  3, 1, 'Sender Name 2', '123456789', 'Receiver Name 2', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (  4, 3, 'Sender Name 3', '123456789', 'Receiver Name 3', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 1, 1, 'Sender Name 4', '123456789', 'Receiver Name 4', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (  2, 1, 'Sender Name 5', '123456789', 'Receiver Name 5', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES ( 3, 3, 'Sender Name 6', '123456789', 'Receiver Name 6', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (  4, 0, 'Sender Name 7', '123456789', 'Receiver Name 7', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);
        insertParcelData = "INSERT INTO Parcel (  id_type, status, name_sender, phone_sender, name_receiver, phone_receiver, address_receiver, decription, weight, date_get, date_trans) " +
                "VALUES (  1, 1, 'Sender Name 8', '123456789', 'Receiver Name 8', '987654321', '43, Nguyễn Chí Thanh, Ba Đình, Hà Nội', 'Description', 2.5, '18/01/2023', '1/1/1')";
        sqLiteDatabase.execSQL(insertParcelData);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int newVer) {
        sqLiteDatabase.execSQL("drop table if exists Users");
        sqLiteDatabase.execSQL("drop table if exists Parcel");
        sqLiteDatabase.execSQL("drop table if exists TypeParcel");
        onCreate(sqLiteDatabase);
    }

    // CRUD cho bảng Parcel
    // Thêm một parcel mới vào database
//              "id_type INTEGER," +
//              "status INTEGER," +
//              "name_sender TEXT," +
//              "phone_sender TEXT, " +
//              "name_receiver TEXT," +
//              "phone_receiver TEXT," +
//              "address_receiver TEXT, " +
//              "decription TEXT," +
//              "weight REAL," +
//              "date_get TEXT, " +
//              "date_trans TEXT)";
    public void addParcel(Parcel parcel) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

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

        db.insert("Parcel", null, values);

    }

    // Lấy thông tin tất cả các parcel từ bảng Parce
    //              "id_type INTEGER," +
//              "status INTEGER," +
//              "name_sender TEXT," +
//              "phone_sender TEXT, " +
//              "name_receiver TEXT," +
//              "phone_receiver TEXT," +
//              "address_receiver TEXT, " +
//              "decription TEXT," +
//              "weight REAL," +
//              "date_get TEXT, " +
//              "date_trans TEXT)";
    public List<Parcel> getAllParcels() {
        List<Parcel> parcelList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Parcel";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Parcel parcel = new Parcel();

                parcel.setParcel_id(cursor.getInt(0));
                parcel.setId_type(cursor.getInt(1));
                parcel.setStatus(cursor.getInt(2));
                parcel.setName_sender(cursor.getString(3));
                parcel.setPhone_sender(cursor.getString(4));
                parcel.setName_receiver(cursor.getString(5));
                parcel.setPhone_receiver(cursor.getString(6));
                parcel.setAddress_receiver(cursor.getString(7));
                parcel.setDecription(cursor.getString(8));
                parcel.setWeight(cursor.getDouble(9));
                parcel.setDate_get(cursor.getString(10));
                parcel.setDate_trans(cursor.getString(11));

                parcelList.add(parcel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return parcelList;
    }

    // Cập nhật thông tin của một parcel trong bảng Parcel
    public int updateParcel(Parcel parcel) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

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

    // Xóa một parcel từ bảng Parcel
    public void deleteParcel(int parcelId) {
        db = this.getWritableDatabase();
        db.delete("Parcel", "parcel_id = ?", new String[]{String.valueOf(parcelId)});

    }

    // CRUD cho bảng TypeParcel
    // Thêm một TypeParcel mới vào database
    //    title TEXT, pack_free REAL
    public void addTypeParcel(TypeParcel typeParcel) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("title", typeParcel.getTitle());
        values.put("pack_free", typeParcel.getPack_free());

        db.insert("TypeParcel", null, values);

    }

    // Lấy thông tin tất cả các TypeParcel từ bảng TypeParcel
    public List<TypeParcel> getAllTypeParcels() {
        List<TypeParcel> typeParcels = new ArrayList<>();
        String selectQuery = "SELECT * FROM TypeParcel";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                TypeParcel typeParcel = new TypeParcel();

                typeParcel.setType_id(cursor.getInt(0));
                typeParcel.setTitle(cursor.getString(1));
                typeParcel.setPack_free(cursor.getDouble(2));

                typeParcels.add(typeParcel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return typeParcels;
    }

    // Cập nhật thông tin của một parcel trong bảng TypeParcel
    public int updateTypeParcel(TypeParcel typeParcel) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Gán các giá trị từ đối tượng TypeParcel vào ContentValues
        values.put("title", typeParcel.getTitle());
        values.put("pack_free", typeParcel.getPack_free());
        // Cập nhật dữ liệu trong bảng TypeParcel dựa trên type_id
        return db.update("TypeParcel", values, "type_id = ?", new String[]{String.valueOf(typeParcel.getType_id())});
    }  // Xóa một parcel từ bảng Parcel

    public void deleteTypeParcel(int typeParcellId) {
        db = this.getWritableDatabase();
        db.delete("TypeParcel", "type_id = ?", new String[]{String.valueOf(typeParcellId)});

    }

    @SuppressLint("Range")
    public Parcel getParcelById(int parcelId) {
        db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "Parcel",
                null,
                "parcel_id = ?",
                new String[]{String.valueOf(parcelId)},
                null,
                null,
                null
        );
//              "id_type INTEGER," +
//              "status INTEGER," +
//              "name_sender TEXT," +
//              "phone_sender TEXT, " +
//              "name_receiver TEXT," +
//              "phone_receiver TEXT," +
//              "address_receiver TEXT, " +
//              "decription TEXT," +
//              "weight REAL," +
//              "date_get TEXT, " +
//              "date_trans TEXT)";
        Parcel parcel = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Tạo đối tượng Parcel từ dữ liệu trong Cursor
            parcel = new Parcel();
            parcel.setParcel_id(cursor.getInt(cursor.getColumnIndex("parcel_id")));
            parcel.setId_type(cursor.getInt(cursor.getColumnIndex("id_type")));
            parcel.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            parcel.setName_sender(cursor.getString(cursor.getColumnIndex("name_sender")));
            parcel.setPhone_sender(cursor.getString(cursor.getColumnIndex("phone_sender")));
            parcel.setName_receiver(cursor.getString(cursor.getColumnIndex("name_receiver")));
            parcel.setPhone_receiver(cursor.getString(cursor.getColumnIndex("phone_receiver")));
            parcel.setAddress_receiver(cursor.getString(cursor.getColumnIndex("address_receiver")));
            parcel.setDecription(cursor.getString(cursor.getColumnIndex("decription")));
            parcel.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
            parcel.setDate_get(cursor.getString(cursor.getColumnIndex("date_get")));
            parcel.setDate_trans(cursor.getString(cursor.getColumnIndex("date_trans")));
        }

        // Đóng Cursor và Database sau khi sử dụng xong
        if (cursor != null) {
            cursor.close();
        }

        return parcel;
    }

    @SuppressLint("Range")
    public List<Parcel> getParcelsByStatus(int status) {
        List<Parcel> parcelList = new ArrayList<>();
        db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "Parcel",
                null,
                "status = ?",
                new String[]{String.valueOf(status)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Parcel parcel = new Parcel();
                parcel.setParcel_id(cursor.getInt(cursor.getColumnIndex("parcel_id")));
                parcel.setId_type(cursor.getInt(cursor.getColumnIndex("id_type")));
                parcel.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
                parcel.setName_sender(cursor.getString(cursor.getColumnIndex("name_sender")));
                parcel.setPhone_sender(cursor.getString(cursor.getColumnIndex("phone_sender")));
                parcel.setName_receiver(cursor.getString(cursor.getColumnIndex("name_receiver")));
                parcel.setPhone_receiver(cursor.getString(cursor.getColumnIndex("phone_receiver")));
                parcel.setAddress_receiver(cursor.getString(cursor.getColumnIndex("address_receiver")));
                parcel.setDecription(cursor.getString(cursor.getColumnIndex("decription")));
                parcel.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
                parcel.setDate_get(cursor.getString(cursor.getColumnIndex("date_get")));
                parcel.setDate_trans(cursor.getString(cursor.getColumnIndex("date_trans")));

                parcelList.add(parcel);
            } while (cursor.moveToNext());
        }

        // Đóng Cursor và Database sau khi sử dụng xong
        if (cursor != null) {
            cursor.close();
        }

        return parcelList;
    }

    @SuppressLint("Range")
    public TypeParcel getTypeParcelById(int typeId) {
        db = this.getReadableDatabase();
        TypeParcel typeParcel = null;

        Cursor cursor = db.query(
                "TypeParcel",
                null,
                "type_id = ?",
                new String[]{String.valueOf(typeId)},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            typeParcel = new TypeParcel();
            typeParcel.setType_id(cursor.getInt(cursor.getColumnIndex("type_id")));
            typeParcel.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            typeParcel.setPack_free(cursor.getDouble(cursor.getColumnIndex("pack_free")));

            cursor.close();
        }

        return typeParcel;
    }

    public int countParcelsByType(int typeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM Parcel WHERE id_type = ?", // Truy vấn đếm số lượng Parcel với id_type tương ứng
                new String[]{String.valueOf(typeId)}
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0); // Lấy kết quả đếm từ cột COUNT(*)
            }
            cursor.close();
        }


        return count;
    }

    //    "CREATE TABLE User ( id INTEGER PRIMARY KEY, username  TEXT, password TEXT)";
    public void addUser(User user) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getPassWord());

        // Chèn user vào bảng User
        db.insert("User", null, values);

    }

    public boolean checkUserLogin(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean result = false;

        Cursor cursor = db.query(
                "User",
                 new String[]{"id"},
                "username" + "=? AND " + "password" + "=?",
                new String[]{user.getUserName(), user.getPassWord()},
                null,
                null,
                null
        );

        if (cursor != null && cursor.getCount() > 0) {
            result = true; // Người dùng có tồn tại với thông tin đăng nhập cung cấp
        }

        if (cursor != null) {
            cursor.close();
        }

        return result;
    }
}
