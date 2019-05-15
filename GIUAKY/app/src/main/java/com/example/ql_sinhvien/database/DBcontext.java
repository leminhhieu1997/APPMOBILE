package com.example.ql_sinhvien.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBcontext extends SQLiteOpenHelper {
    public static final String TAG = "SQLite_Helper";

    //table Khoa và it's columns
    public static final String TABLE_KHOA = "KHOA";
    public static final String COLUMN_MaKhoa = "MaKhoa";
    public static final String COLUMN_TenKhoa = "TenKhoa";

    // table SinhVien và it's columns
    public static final String TABLE_SINHVIEN = "SINHVIEN";
    public static final String COLUMN_MASSV = "MaSSV";
    public static final String COLUMN_HOVATEN = "HoVaTen";
    public static final String COLUMN_PHAI = "Phai";
    public static final String COLUMN_NGAYSINH = "NgaySinh";
    public static final String COLUMN_DIACHI = "DiaChi";
    public static final String COLUMN_SDT = "SDT";
    public static final String COLUMN_MAKHOASV = COLUMN_MaKhoa;

    // table Mon Hoc và it's columns
    public static final String TABLE_MONHOC = "MONHOC";
    public static final String COLUMN_MAMH = "MaMH";
    public static final String COLUMN_TENMH = "TenMH";
    public static final String COLUMN_SOTIET = "SoTiet";

    // table Diem và it's columns

    public static final String TABLE_DIEM = "DIEM";
    public static final String COLUMN_MASSVD = COLUMN_MASSV;
    public static final String COLUMN_MAMHD = COLUMN_MAMH;
    public static final String COLUMN_DIEM = "Diem";

    // Database
    public  static final String DATABASE_NAME = "QL_SINHVIEN";
    public static  final  int DATABASE_VERSION = 1;


    // statement of the Khoa table createion
    public static final String SQL_CREATE_TABLE_KHOA = "CREATE TABLE "+ TABLE_KHOA + " ("
            + COLUMN_MaKhoa + " TEXT NOT NULL PRIMARY KEY, "
            + COLUMN_TenKhoa + " TEXT NOT NULL "
            +");";

    // statement of the SInhVien table createion

    public static final String SQL_CREATE_TABLE_SINHVIEN = "CREATE TABLE "+ TABLE_SINHVIEN + " ("
            + COLUMN_MASSV + " TEXT NOT NULL PRIMARY KEY, "
            + COLUMN_HOVATEN + " TEXT NOT NULL, "
            + COLUMN_PHAI + " INTEGER NOT NULL, "
            + COLUMN_NGAYSINH + " DATE NOT NULL, "
            + COLUMN_DIACHI + " TEXT NOT NULL, "
            + COLUMN_SDT + " TEXT NOT NULL, "
            + COLUMN_MAKHOASV + " TEXT NOT NULL, "
            +"FOREIGN KEY("+COLUMN_MAKHOASV+") REFERENCES "+TABLE_KHOA+" ("+COLUMN_MaKhoa+") "
            +");";
    // statement of the MONHOC table createion

    public static final String SQL_CREATE_TABLE_MONHOC = "CREATE TABLE "+ TABLE_MONHOC + " ("
            + COLUMN_MAMH + " TEXT NOT NULL PRIMARY KEY, "
            + COLUMN_TENMH + " TEXT NOT NULL, "
            + COLUMN_SOTIET + " INTEGER NOT NULL "
            +");";
    // statement of the Diem table createion

    public static final String SQL_CREATE_TABLE_DIEM = "CREATE TABLE "+ TABLE_DIEM + " ("
            + COLUMN_MASSVD + " TEXT NOT NULL , "
            + COLUMN_MAMHD + " TEXT NOT NULL, "
            + COLUMN_DIEM + " INTEGER NOT NULL, "
            +"FOREIGN KEY("+COLUMN_MASSVD+") REFERENCES "+TABLE_DIEM+" ("+COLUMN_MASSV+"), "
            +"FOREIGN KEY("+COLUMN_MAMHD+") REFERENCES "+TABLE_MONHOC+" ("+COLUMN_MAMH+"), "
            +"PRIMARY KEY( "+COLUMN_MASSVD+" , "+COLUMN_MAMHD+" )"
            +");";

    public DBcontext( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_KHOA);
        db.execSQL(SQL_CREATE_TABLE_SINHVIEN);
        db.execSQL(SQL_CREATE_TABLE_MONHOC);
        db.execSQL(SQL_CREATE_TABLE_DIEM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_KHOA);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SINHVIEN);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MONHOC);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DIEM);

        onCreate(db);
    }
}
