package com.example.ql_sinhvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.ql_sinhvien.database.DBcontext;
import com.example.ql_sinhvien.model.khoa_model;
import com.example.ql_sinhvien.model.sinhvien_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class sinhvien_dao {
    public static final String TAG = "SinhVienDao";

    // database fields
    private SQLiteDatabase mDatabase;
    private DBcontext mDBcontext;
    private Context mContext;
    private String[]  mAllColumns = {mDBcontext.COLUMN_MASSV, mDBcontext.COLUMN_HOVATEN,mDBcontext.COLUMN_PHAI,mDBcontext.COLUMN_NGAYSINH,mDBcontext.COLUMN_DIACHI,
    mDBcontext.COLUMN_SDT,mDBcontext.COLUMN_MAKHOASV};


    public sinhvien_dao(Context context){
        this.mContext = context;
        mDBcontext = new DBcontext(mContext);
        // open database
        try{
            open();
        }catch (SQLException ex){
            Log.e(TAG,"SQLException on openning database"+ ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void open() throws SQLException{
        mDatabase = mDBcontext.getWritableDatabase();
    }

    public void close() throws SQLException{
        mDBcontext.close();
    }

    public sinhvien_model createSinhVien(sinhvien_model sv){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_MASSV,sv.getMaSSV());
        values.put(DBcontext.COLUMN_HOVATEN,sv.getHoVaTen());
        values.put(DBcontext.COLUMN_PHAI,sv.getPhai());
        values.put(DBcontext.COLUMN_NGAYSINH,sv.getNgaySinh());
        values.put(DBcontext.COLUMN_DIACHI,sv.getDiaChi());
        values.put(DBcontext.COLUMN_SDT,sv.getSdt());
        values.put(DBcontext.COLUMN_MAKHOASV,sv.getMaKhoa());

        mDatabase = mDBcontext.getWritableDatabase();
        long insertId = mDatabase.insert(DBcontext.TABLE_SINHVIEN,null, values);
        if(insertId == -1){
            Toast.makeText(mContext,"can't insert sinh vien",Toast.LENGTH_LONG).show();
            return null;
        }

        Cursor cursor = mDatabase.query(DBcontext.TABLE_SINHVIEN,mAllColumns,DBcontext.COLUMN_MASSV+" = "+"'"+sv.getMaSSV()+"'",null,null,null,null);
        cursor.moveToFirst();
        sinhvien_model r = cursorToSinhVien(cursor);
        cursor.close();
        return r;
    }


    public sinhvien_model updateSinhVien(sinhvien_model sv){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_HOVATEN,sv.getHoVaTen());
        values.put(DBcontext.COLUMN_PHAI,sv.getPhai());
        values.put(DBcontext.COLUMN_NGAYSINH,sv.getNgaySinh());
        values.put(DBcontext.COLUMN_DIACHI,sv.getDiaChi());
        values.put(DBcontext.COLUMN_SDT,sv.getSdt());
        values.put(DBcontext.COLUMN_MAKHOASV,sv.getMaKhoa());

        mDatabase.update(DBcontext.TABLE_SINHVIEN,values,DBcontext.COLUMN_MASSV+"= ?", new String[]{sv.getMaSSV()});

        Cursor cursor = mDatabase.query(DBcontext.TABLE_SINHVIEN,mAllColumns,DBcontext.COLUMN_MASSV+" = "+"'"+sv.getMaSSV()+"'",null,null,null,null);
        cursor.moveToFirst();
        sinhvien_model r = cursorToSinhVien(cursor);
        cursor.close();
        return r;
    }


    public void deleteSinhVien(sinhvien_model sv){
        String id = sv.getMaSSV();
        System.out.println("The deleted sinh vien has the id "+id);
        mDatabase.delete(DBcontext.TABLE_SINHVIEN,DBcontext.COLUMN_MASSV+" = "+"'"+id+"'",null);
    }

    public List<sinhvien_model> getAllSinhVien( List<sinhvien_model> listSinhVien){
        Cursor cursor = mDatabase.query(DBcontext.TABLE_SINHVIEN,mAllColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            sinhvien_model sv = cursorToSinhVien(cursor);
            listSinhVien.add(sv);
            cursor.moveToNext();
        }
        Log.e(TAG,"Sinh vienload");
        cursor.close();
        return  listSinhVien;
    }

    public sinhvien_model getSinhVienById(String id){
        Cursor cursor = mDatabase.query(DBcontext.TABLE_SINHVIEN,mAllColumns,DBcontext.COLUMN_MASSV+" = ?",new String[]{id},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        sinhvien_model sv = cursorToSinhVien(cursor);
        return sv;
    }

    private sinhvien_model cursorToSinhVien(Cursor cursor){
        sinhvien_model sinhvien = new sinhvien_model();
        sinhvien.setMaSSV(cursor.getString(0));
        sinhvien.setHoVaTen(cursor.getString(1));
        sinhvien.setPhai(Integer.parseInt(cursor.getString(2)));
        sinhvien.setNgaySinh(cursor.getString(3));

        sinhvien.setDiaChi(cursor.getString(4));
        sinhvien.setSdt(cursor.getString(5));
        String maKhoa = cursor.getString(6);
        khoa_dao dao = new khoa_dao(mContext);
        khoa_model k = dao.getKhoaById(maKhoa);
        if(k!=null){
            sinhvien.setMaKhoa(k.getTenKhoa());
        }
        return sinhvien;
    }
}
