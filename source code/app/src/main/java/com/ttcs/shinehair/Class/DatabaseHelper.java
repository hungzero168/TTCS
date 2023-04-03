 package com.ttcs.shinehair.Class;

 import android.content.ContentValues;
 import android.content.Context;
 import android.database.Cursor;
 import android.database.sqlite.SQLiteDatabase;
 import android.database.sqlite.SQLiteOpenHelper;
 import android.provider.BaseColumns;
 import android.util.Log;

 import androidx.annotation.Nullable;

 import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
//     public static final String database_Name = "ShineHair.db";

     public DatabaseHelper(@Nullable Context context) {
         super( context, "ShineHair.db", null, 1 );
     }

     @Override
     public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL( "create Table users(id INTEGER PRIMARY KEY AUTOINCREMENT, password TEXT, usertype TEXT, email TEXT UNIQUE)" );
         sqLiteDatabase.execSQL( "create Table infoUser(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, sdt TEXT, email TEXT, address TEXT, hinhanh BLOB, FOREIGN KEY (email) REFERENCES users(email))" );
         sqLiteDatabase.execSQL( "create Table dichvu(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, gia TEXT, chitiet TEXT, hinhanh BLOB)" );
         sqLiteDatabase.execSQL( "create Table day(thu TEXT PRIMARY KEY, giobatdau TEXT, gioketthuc TEXT, ngaynghi INTEGER)" );
         sqLiteDatabase.execSQL("CREATE TABLE datlich (id INTEGER PRIMARY KEY AUTOINCREMENT, ngaydat TEXT NOT NULL, giodat TEXT NOT NULL, emailkhachhang TEXT NOT NULL, iddichvu INTEGER NOT NULL, idnhanvien INTEGER NOT NULL, trangthai TEXT NOT NULL, ghichu TEXT, FOREIGN KEY (emailkhachhang) REFERENCES users(email), FOREIGN KEY (iddichvu) REFERENCES dichvu(id), FOREIGN KEY (idnhanvien) REFERENCES infoUser(id))");


     }


     @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
         sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS users" );
         sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS infoUser" );
         sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS dichvu" );
         sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS day" );
         sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS DATLICH" );
         onCreate( sqLiteDatabase );

     }



     public String getTenDichVu(int idtendichvu) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery( "SELECT name FROM dichvu WHERE id = " + idtendichvu, null );
            cursor.moveToFirst();
            String tendichvu = cursor.getString( 0 );
            return tendichvu;
     }

     public String getGiaDichVu(String idgiadichvu) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery( "SELECT gia FROM dichvu WHERE id = " + idgiadichvu, null );
            cursor.moveToFirst();
            String gia = cursor.getString( 0 );
            return gia;
     }

    public byte[] getHinhAnh(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        byte[] hinhanh = null;
        Cursor cursor = db.rawQuery("SELECT hinhanh FROM infoUser WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            hinhanh = cursor.getBlob(0);
        }
        cursor.close();
        return hinhanh;
    }

    public byte[] getHinhAnh(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        byte[] hinhanh = null;
        Cursor cursor = db.rawQuery("SELECT hinhanh FROM dichvu WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            hinhanh = cursor.getBlob(0);
        }
        cursor.close();
        return hinhanh;
    }

    public final class UsersContract {
         private UsersContract() {} // private constructor to prevent instantiation

         public class Users implements BaseColumns {
             public static final String TABLE_NAME = "users";
             public static final String COLUMN_NAME_EMAIL = "email";
             public static final String COLUMN_USER_TYPE = "usertype";
             public static final String COLUMN_NAME_PASSWORD = "password";
         }

         public class UserInfo implements BaseColumns {
             public static final String TABLE_NAME = "infoUser";
             public static final String COLUMN_NAME_ADDRESS = "address";
             public static final String COLUMN_NAME_PHONE = "sdt";
             public static final String COLUMN_NAME_NAME = "name";
             public static final String COLUMN_NAME_EMAIL = "email";
             public static final String COLUMN_NAME_IMAGE = "hinhanh";
         }
     }
     public void insertDatLich(String ngaydat, String giodat, String emailkhachhang, int iddichvu, int idnhanvien, String trangthai, String ghichu) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("ngaydat", ngaydat);
         values.put("giodat", giodat);
         values.put("emailkhachhang", emailkhachhang);
         values.put("iddichvu", iddichvu);
         values.put("idnhanvien", idnhanvien);
         values.put("trangthai", trangthai);
         values.put("ghichu", ghichu);
         db.insert("datlich", null, values);
         db.close();
     }
    
    // update type of user
        public void updateTypeUser(String email, String type) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("usertype", type);
            db.update("users", values, "email = ?", new String[]{email});
            db.close();
        }


    // xóa user
        public void deleteUser(String email) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("users", "email = ?", new String[]{email});
            db.close();
        }
    // xóa datlich
        public boolean deleteDatLich(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete("datlich", "id = ?", new String[]{String.valueOf(id)}) > 0;
        }
     public Cursor getDataByEmailKH(String customerEmail) {
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT * FROM datlich WHERE emailkhachhang=?", new String[]{customerEmail});
         return cursor;
     }

    // get gia dich vu
    public int getGiaDichVuById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT gia FROM dichvu WHERE id=?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        int gia = cursor.getInt(0);
        cursor.close();
        return gia;
    }

     public boolean insertDay(Day day) {
         SQLiteDatabase database = this.getWritableDatabase();
             ContentValues contentValues = new ContentValues();
             contentValues.put("thu",day.getThu());
             contentValues.put("giobatdau",day.getGioBatDau());
             contentValues.put( "gioketthuc",day.getGioKetThuc() );
             contentValues.put( "ngaynghi", day.getNgayNghi() );
             long res = database.insert("day",null,contentValues);
             if (res == -1) {
                 return false;
             }
             return true;
     }
     public boolean isTableDayEmpty() {
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM day", null);
         cursor.moveToFirst();
         int count = cursor.getInt(0);
         cursor.close();
         return count == 0;
     }


     public boolean isTableUserEmpty() {
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
         cursor.moveToFirst();
         int count = cursor.getInt(0);
         cursor.close();
         return count == 0;
     }
     public boolean isTableEmpty() {
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM dichvu", null);
         cursor.moveToFirst();
         int count = cursor.getInt(0);
         cursor.close();
         return count == 0;
     }

     public boolean isTableInfoEmpty() {
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM infoUser", null);
         cursor.moveToFirst();
         int count = cursor.getInt(0);
         cursor.close();
         return count == 0;
     }

    // lấy id của user
        public String getIdUser(String email) {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery( "SELECT id FROM users WHERE email = '" + email + "'", null );
            cursor.moveToFirst();
            String id = cursor.getString( 0 );
            return id;
        }
    // lấy idnhanvien và giodat trong bảng datlich
        public Cursor getIdNhanVienAndGioDat() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery( "SELECT idnhanvien, giodat FROM datlich", null );
            return cursor;
        }
     // insert data table dichvu
        public boolean insertDichVu(String name, String gia, String chitiet, byte[] hinhanh) {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put( "name", name );
            contentValues.put( "gia", gia );
            contentValues.put( "chitiet", chitiet );
            contentValues.put("hinhanh",hinhanh);
            long result = database.insert( "dichvu", null, contentValues );
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }

    // get data table dichvu
        public Cursor getData(String sql) {
            SQLiteDatabase database = this.getReadableDatabase();
            return database.rawQuery( sql, null );
        }
//     public void QueryData(String query){
//         SQLiteDatabase database = getWritableDatabase();
//         database.execSQL(query);
//     }
    public int queryData(String data ){
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(data, null);
        if (cursor.getCount() > 0){
            return 1;
        }else {
            return 0;
        }
    }
    public void deleteItem(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("dichvu", "id = ?", new String[]{String.valueOf(position)});
        db.close();
    }
     public boolean deleteData(int id) {
         SQLiteDatabase db = this.getWritableDatabase();
         int result = db.delete("dichvu", "ID = ?", new String[] {String.valueOf(id)});
         return result != -1;
     }
    public LichSu getdatlichByemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM datlich WHERE emailkhachhang == " + email;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            LichSu lichSu = new LichSu();
            lichSu.setIdtendichvu(cursor.getString(cursor.getColumnIndex("id")));
            lichSu.setIdtendichvu(cursor.getString(cursor.getColumnIndex("iddichvu")));
            lichSu.setIdnhanvien(cursor.getString(cursor.getColumnIndex("idnhanvien")));
            lichSu.setEmailkhachhang(cursor.getString(cursor.getColumnIndex("emailkhachhang")));
            lichSu.setNgaydat(cursor.getString(cursor.getColumnIndex("ngaydat")));
            lichSu.setGiodat(cursor.getString(cursor.getColumnIndex("giodat")));
            lichSu.setTrangthai(cursor.getString(cursor.getColumnIndex("trangthai")));
            lichSu.setGhichu(cursor.getString(cursor.getColumnIndex("ghichu")));
            // liên kết ngoại khóa FOREIGN KEY (emailkhachhang) REFERENCES users(email), FOREIGN KEY (iddichvu) REFERENCES dichvu(id), FOREIGN KEY (idnhanvien) REFERENCES infoUser(id))");

             return lichSu;
         }
         return null;
     }

    // get datlichbyid
        public LichSu getdatlichById(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM datlich WHERE id == " + id;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                LichSu lichSu = new LichSu();
                lichSu.setIdtendichvu(cursor.getString(cursor.getColumnIndex("id")));
                lichSu.setIdtendichvu(cursor.getString(cursor.getColumnIndex("iddichvu")));
                lichSu.setIdnhanvien(cursor.getString(cursor.getColumnIndex("idnhanvien")));
                lichSu.setEmailkhachhang(cursor.getString(cursor.getColumnIndex("emailkhachhang")));
                lichSu.setNgaydat(cursor.getString(cursor.getColumnIndex("ngaydat")));
                lichSu.setGiodat(cursor.getString(cursor.getColumnIndex("giodat")));
                lichSu.setTrangthai(cursor.getString(cursor.getColumnIndex("trangthai")));
                lichSu.setGhichu(cursor.getString(cursor.getColumnIndex("ghichu")));
                
                 return lichSu;
             }
             return null;
         }
     public DichVu getDichVuById(int id) {
         SQLiteDatabase db = this.getReadableDatabase();
         // Tạo câu truy vấn SQL
         String selectQuery = "SELECT * FROM dichvu WHERE id = " + id;
         Cursor cursor = db.rawQuery(selectQuery, null);

         // Kiểm tra xem cursor có dữ liệu không
         if (cursor.moveToFirst()) {
             DichVu dichVu = new DichVu();
             dichVu.setID(cursor.getInt(cursor.getColumnIndex("id")));
             dichVu.setTenDichVu(cursor.getString(cursor.getColumnIndex("name")));
             dichVu.setGiaDichVu(cursor.getString(cursor.getColumnIndex("gia")));
             dichVu.setChiTiet(cursor.getString(cursor.getColumnIndex("chitiet")));
             // Trả về đối tượng DichVu
             return dichVu;
         } else {
             // Không tìm thấy dịch vụ với ID tương ứng
             return null;
         }
     }
     public Day getDay(String ngay) {
         SQLiteDatabase database = this.getReadableDatabase();
         String selectQuery = "SELECT * FROM day WHERE thu = ?";
         String[] selectionArgs = { ngay };
         Cursor cursor = database.rawQuery(selectQuery, selectionArgs);
         if (cursor.moveToNext()) {
             Day day2 = new Day();
             day2.setThu(cursor.getString(cursor.getColumnIndex("thu")));
             day2.setGioBatDau(cursor.getString(cursor.getColumnIndex("giobatdau")));
             day2.setGioKetThuc(cursor.getString(cursor.getColumnIndex("gioketthuc")));
             day2.setNgayNghi(cursor.getInt(cursor.getColumnIndex("ngaynghi")));
             return day2;
         } else {
             return null;
         }
     }


     public Users getUserById(int id) {
         SQLiteDatabase db = this.getReadableDatabase();

         // Tạo câu truy vấn SQL
         String selectQuery = "SELECT * FROM users WHERE id = " + id;

         Cursor cursor = db.rawQuery(selectQuery, null);

         // Kiểm tra xem cursor có dữ liệu không
         if (cursor.moveToFirst()) {
             Users user = new Users();
             user.setId(cursor.getInt(cursor.getColumnIndex("id")));
             user.setEmail(cursor.getString(cursor.getColumnIndex("email")));


             // Lấy thông tin tên từ bảng infoUser
             String email = cursor.getString(cursor.getColumnIndex("email"));
             String selectInfoQuery = "SELECT name FROM infoUser WHERE email = ?";
             String[] selectionArgs = { email };
             Cursor infoCursor = db.rawQuery(selectInfoQuery, selectionArgs);
             if (infoCursor.moveToFirst()) {
                 user.setName(infoCursor.getString(infoCursor.getColumnIndex("name")));
             }

             // Trả về đối tượng User
             return user;
         } else {
             // Không tìm thấy user với ID tương ứng
             return null;
         }
     }

     public Users getUserByEmail(String email) {
    SQLiteDatabase db = this.getReadableDatabase();

    // Tạo câu truy vấn SQL
    String selectQuery = "SELECT * FROM users WHERE email = ?";

    // Thực hiện truy vấn với tham số truyền vào
    Cursor cursor = db.rawQuery(selectQuery, new String[]{email});

    // Kiểm tra xem cursor có dữ liệu không
    if (cursor.moveToFirst()) {
        Users user = new Users();
        user.setId(cursor.getInt(cursor.getColumnIndex("id")));
        user.setEmail(cursor.getString(cursor.getColumnIndex("email")));

        // Lấy thông tin tên từ bảng infoUser
        String selectInfoQuery = "SELECT name FROM infoUser WHERE email = ?";
        Cursor infoCursor = db.rawQuery(selectInfoQuery, new String[]{email});
        if (infoCursor.moveToFirst()) {
            user.setName(infoCursor.getString(infoCursor.getColumnIndex("name")));
        }
        return user;
    } else {
        return null;
    }
}


     public boolean updateDichVu(DichVu dichVu) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put("name", dichVu.getTenDichVu());
         values.put("gia", dichVu.getGiaDichVu());
         values.put("chitiet", dichVu.getChiTiet());
         values.put("hinhanh", dichVu.getHinh());

         int rowsAffected = db.update("dichvu", values, "id=?", new String[]{String.valueOf(dichVu.getID())});
         db.close();

         return rowsAffected > 0;
     }


     public void insertUsers(Users users) {
         SQLiteDatabase database = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put( "email", users.getEmail() );
         contentValues.put( "password", users.getPassword() );
         contentValues.put( "usertype", users.getTypeUser() );
         database.insert( "users", null, contentValues );
     }

     public boolean updateGioMo(String thu, String giobatdau, String gioketthuc, int ngaynghi) {
         SQLiteDatabase database = this.getWritableDatabase();
         if (database == null) return false;

         ContentValues contentValues = new ContentValues();
         contentValues.put("giobatdau", giobatdau);
         contentValues.put("gioketthuc", gioketthuc);
         contentValues.put("ngaynghi", ngaynghi);

         long res = database.update("day", contentValues, "thu = ?", new String[]{thu});
         database.close();
         return res > 0;
     }

     public boolean updateHuyLichDat(int id, String trangthai) {
         SQLiteDatabase database = this.getWritableDatabase();
         if (database == null) return false;
         ContentValues contentValues = new ContentValues();
         contentValues.put("trangthai", trangthai);
         long res = database.update("datlich", contentValues, "id = ?", new String[]{String.valueOf(id)});
         database.close();
         Log.d("updateHuyLichDat", "Rows updated: " + res);
         return res > 0;
     }

     public boolean updateLichDat(String email, String trangthai) {
         SQLiteDatabase database = this.getWritableDatabase();
         if (database == null) return false;
         ContentValues contentValues = new ContentValues();
         contentValues.put("trangthai", trangthai);
         long res = database.update("datlich", contentValues, "emailkhachhang = ?", new String[]{email});
         database.close();
         return res > 0;
     }




     public Boolean insertUsers(String email, String password, int type) {
         SQLiteDatabase database = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put( "email", email );
         contentValues.put( "password", password );
         contentValues.put( "usertype", type );
         long result = database.insert( "users", null, contentValues );
         if (result == -1) {
             return false;
         } else {
             return true;
         }
     }

     public Boolean checkEmail(String email) {
         SQLiteDatabase database = this.getWritableDatabase();
         Cursor cursor = database.rawQuery( "Select * from users where email = ?", new String[]{email} );
         if (cursor.getCount() > 0) {
             return true;
         } else {
             return false;
         }
     }

     public Boolean checkEmailPassword(String email, String password) {
         SQLiteDatabase database = this.getWritableDatabase();
         Cursor cursor = database.rawQuery( "Select * from users where email = ? and password = ?", new String[]{email, password} );
         if (cursor.getCount() > 0) {
             return true;
         } else {
             return false;
         }
     }

     //  lấy  loai user
     public int getUserType(String email) {
         SQLiteDatabase database = this.getReadableDatabase();
         String[] projection = {"usertype"};
         String selection = "email = ?";
         String[] selectionArgs = {email};
         Cursor cursor = database.query( "users", projection, selection, selectionArgs, null, null, null );
         int type = 0;
         if (cursor != null && cursor.moveToFirst()) {
             type = cursor.getInt( cursor.getColumnIndexOrThrow( "usertype" ) );
             cursor.close();
         }
         return type;
     }

     // đổi mật khẩu
     public Boolean changePassword(String email, String password) {
         SQLiteDatabase database = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put( "password", password );
         database.update( "users", contentValues, "email = ?", new String[]{email} );
         return true;
     }

    public String getName(String email) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] projection = {"name"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query( "infoUser", projection, selection, selectionArgs, null, null, null );
        String name = "";
        if (cursor != null && cursor.moveToFirst()) {
            name = cursor.getString( cursor.getColumnIndexOrThrow( "name" ) );
            cursor.close();
        }
        return name;
    }
    public String getPhone(String email) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] projection = {"sdt"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query( "infoUser", projection, selection, selectionArgs, null, null, null );
        String sdt = "";
        if (cursor != null && cursor.moveToFirst()) {
            sdt = cursor.getString( cursor.getColumnIndexOrThrow( "sdt" ) );
            cursor.close();
        }
        return sdt;
    }
    public String getAdd(String email) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] projection = {"address"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query( "infoUser", projection, selection, selectionArgs, null, null, null );
        String address = "";
        if (cursor != null && cursor.moveToFirst()) {
            address = cursor.getString( cursor.getColumnIndexOrThrow( "address" ) );
            cursor.close();
        }
        return address;
    }


//     public ArrayList<Users> getStaffUsers() {
//         SQLiteDatabase db = getReadableDatabase();
//         String[] projection = {
//                 "u." + UsersContract.Users.COLUMN_NAME_EMAIL,
//                 "u." + UsersContract.Users.COLUMN_USER_TYPE,
//                 "i." + UsersContract.UserInfo.COLUMN_NAME_NAME
//         };
//         String selection = "u." + UsersContract.Users.COLUMN_USER_TYPE + " == 2";
//         String table = UsersContract.Users.TABLE_NAME + " u JOIN " +
//                 UsersContract.UserInfo.TABLE_NAME + " i ON " +
//                 "u." + UsersContract.Users.COLUMN_NAME_EMAIL + " = " +
//                 "i." + UsersContract.UserInfo.COLUMN_NAME_EMAIL;
//         Cursor cursor = db.query(table, projection, selection, null, null, null, null);
//
//         ArrayList<Users> users = new ArrayList<>();
//         while (cursor.moveToNext()) {
//             String email = cursor.getString(cursor.getColumnIndexOrThrow(UsersContract.Users.COLUMN_NAME_EMAIL));
//             String name = cursor.getString(cursor.getColumnIndexOrThrow(UsersContract.UserInfo.COLUMN_NAME_NAME));
//             int usertype = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(UsersContract.Users.COLUMN_USER_TYPE)));
//
//             Users user = new Users(email, usertype, name);
//             users.add(user);
//         }
//         cursor.close();
//         return users;
//     }

    public ArrayList<Users> getStaffUsers() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                "u." + UsersContract.Users.COLUMN_NAME_EMAIL,
                "u." + UsersContract.Users.COLUMN_USER_TYPE,
                "i." + UsersContract.UserInfo.COLUMN_NAME_NAME,
                "i." + UsersContract.UserInfo.COLUMN_NAME_IMAGE
        };
        String selection = "u." + UsersContract.Users.COLUMN_USER_TYPE + " == 2";
        String table = UsersContract.Users.TABLE_NAME + " u JOIN " +
                UsersContract.UserInfo.TABLE_NAME + " i ON " +
                "u." + UsersContract.Users.COLUMN_NAME_EMAIL + " = " +
                "i." + UsersContract.UserInfo.COLUMN_NAME_EMAIL;
        Cursor cursor = db.query(table, projection, selection, null, null, null, null);

        ArrayList<Users> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UsersContract.Users.COLUMN_NAME_EMAIL));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UsersContract.UserInfo.COLUMN_NAME_NAME));
            int usertype = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(UsersContract.Users.COLUMN_USER_TYPE)));
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(UsersContract.UserInfo.COLUMN_NAME_IMAGE));

            Users user = new Users(email, usertype, name, image);
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public boolean updateInfo(String email, String name, String phone, String address, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
         ContentValues userContentValues = new ContentValues();
         userContentValues.put( "name", name );
         userContentValues.put( "sdt", phone );
         userContentValues.put( "address", address );
        userContentValues.put( "hinhanh", image );
         String userSelection = "email = ?";
         String[] userSelectionArgs = {email};
         int userResult = db.update( "infoUser", userContentValues, userSelection, userSelectionArgs );
         if (userResult == 0) {
             userContentValues.put( "email", email );
             userResult = (int) db.insert( "infoUser", null, userContentValues );
         }
            return userResult != -1;
    }
    public byte[] getImage(String email) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] projection = {"hinhanh"};
        String selection = "email = ?";
        String[] selectionArgs = {email};
        Cursor cursor = database.query( "infoUser", projection, selection, selectionArgs, null, null, null );
        byte[] image = null;
        if (cursor != null && cursor.moveToFirst()) {
            image = cursor.getBlob( cursor.getColumnIndexOrThrow( "hinhanh" ) );
            cursor.close();
        }
        return image;
    }
}