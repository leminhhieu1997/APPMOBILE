package com.example.ql_sinhvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.ql_sinhvien.database.DBcontext;
import com.example.ql_sinhvien.model.monhoc_model;

import java.util.ArrayList;
import java.util.List;

public class monhoc_dao {
    public static final String TAG = "MonHocDao";

    // database fields
    private SQLiteDatabase mDatabase;
    private DBcontext mDBcontext;
    private Context mContext;
    private String[]  mAllColumns = {mDBcontext.COLUMN_MAMH, mDBcontext.COLUMN_TENMH,mDBcontext.COLUMN_SOTIET};


    public monhoc_dao(Context context){
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

    public monhoc_model createMonHoc(monhoc_model mh){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_MAMH,mh.getMaMH());
        values.put(DBcontext.COLUMN_TENMH,mh.getTenMH());
        values.put(DBcontext.COLUMN_SOTIET,mh.getSoTiet());

        long insertId = mDatabase.insert(DBcontext.TABLE_MONHOC,null, values);
        if(insertId == -1){
            Toast.makeText(mContext,"can't insert mon hoc",Toast.LENGTH_LONG).show();
            return null;
        }

        Cursor cursor = mDatabase.query(DBcontext.TABLE_MONHOC,mAllColumns,DBcontext.COLUMN_MAMH+" = "+" '"+mh.getMaMH()+"'",null,null,null,null);
        cursor.moveToFirst();
        monhoc_model r = cursorToMonHoc(cursor);
        cursor.close();
        return r;
    }

    public void deleteMonHoc(monhoc_model mh){
        String id = mh.getMaMH();
        System.out.println("The deleted mon hoc has the id "+id);
        mDatabase.delete(DBcontext.TABLE_MONHOC,DBcontext.COLUMN_MAMH+" = "+"'"+id+"'",null);
    }

    public List<monhoc_model> getAllMonHoc(List<monhoc_model> listMonHoc){
        //List<monhoc_model> listMonHoc = new ArrayList<monhoc_model>();
        Cursor cursor = mDatabase.query(DBcontext.TABLE_MONHOC,mAllColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            monhoc_model mh = cursorToMonHoc(cursor);
            listMonHoc.add(mh);
            cursor.moveToNext();
        }
        cursor.close();
        return  listMonHoc;
    }

    public void updateMonHoc (monhoc_model mh ){
        ContentValues values = new ContentValues();
        //values.put(DBcontext.COLUMN_MAMH,mh.getMaMH());
        values.put(DBcontext.COLUMN_TENMH,mh.getTenMH());
        values.put(DBcontext.COLUMN_SOTIET,mh.getSoTiet());
        mDatabase.update(DBcontext.TABLE_MONHOC,values,DBcontext.COLUMN_MAMH+"= ?", new String[]{mh.getMaMH()});

    }
    public monhoc_model getMonHocById(String id){
        Cursor cursor = mDatabase.query(DBcontext.TABLE_MONHOC,mAllColumns,DBcontext.COLUMN_MAMH+" = ?",new String[]{id},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        monhoc_model mh = cursorToMonHoc(cursor);
        return mh;
    }

    private monhoc_model cursorToMonHoc(Cursor cursor){
        monhoc_model mh = new monhoc_model();
        mh.setMaMH(cursor.getString(0));
        mh.setTenMH(cursor.getString(1));
        mh.setSoTiet(cursor.getInt(2));
        // chuyen ma khoa thanh ten

//        khoa_dao dao = new khoa_dao(mContext);
//        khoa_model k = dao.getKhoaById(maKhoa);
//        if(k!=null){
//            sinhvien.setMaKhoa(k.getTenKhoa());
//        }
        return mh;
    }
}
