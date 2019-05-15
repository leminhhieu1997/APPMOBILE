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

import java.util.ArrayList;
import java.util.List;

public class khoa_dao {
    public static final String TAG = "KhoaDao";

    // database fields
    private SQLiteDatabase mDatabase;
    private DBcontext mDBcontext;
    private Context mContext;
    private String[]  mAllColumns = {mDBcontext.COLUMN_MaKhoa, mDBcontext.COLUMN_TenKhoa};


    public khoa_dao(Context context){
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

    public khoa_model createKhoa(khoa_model k){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_MaKhoa,k.getMaKhoa());
        values.put(DBcontext.COLUMN_TenKhoa,k.getTenKhoa());

        Log.e(TAG,k.getMaKhoa()+"  day la ma khoa");

//        mDatabase.insert(DBcontext.TABLE_KHOA,null, values);
        mDatabase = mDBcontext.getWritableDatabase();
        long insertId = mDatabase.insert(DBcontext.TABLE_KHOA,null, values);
        if(insertId == -1){
            Toast.makeText(mContext,"can't insert khoa because MaKhoa is ready",Toast.LENGTH_LONG).show();
            return null;
        }
        Cursor cursor = mDatabase.query(DBcontext.TABLE_KHOA,mAllColumns,DBcontext.COLUMN_MaKhoa+" = "+"'"+k.getMaKhoa()+"' ",null,null,null,null);
        cursor.moveToFirst();
        khoa_model r = cursorToKhoa(cursor);
        cursor.close();

        return r;
    }

    public void updateKhoa (khoa_model k ){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_MaKhoa,k.getMaKhoa());
        values.put(DBcontext.COLUMN_TenKhoa,k.getTenKhoa());
        mDatabase.update(DBcontext.TABLE_KHOA,values,DBcontext.COLUMN_MaKhoa+"= ?", new String[]{k.getMaKhoa()});

    }

    public void deleteKhoa(khoa_model k){
        String id = k.getMaKhoa();
        System.out.println("The deleted khoa has the id "+id);
        mDatabase.delete(DBcontext.TABLE_KHOA,DBcontext.COLUMN_MaKhoa+" = "+"'"+id+"'",null);
    }

    public List<khoa_model> getAllKhoa( List<khoa_model> listKhoa ){

        mDatabase = mDBcontext.getWritableDatabase();
        Cursor cursor = mDatabase.query(DBcontext.TABLE_KHOA,mAllColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            khoa_model k = cursorToKhoa(cursor);
            listKhoa.add(k);
            cursor.moveToNext();
        }
        cursor.close();
       // mDatabase.close();
        return  listKhoa;
    }
    public khoa_model getKhoaById(String id){
        Cursor cursor = mDatabase.query(DBcontext.TABLE_KHOA,mAllColumns,DBcontext.COLUMN_MaKhoa+" = ?",new String[]{id},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }else{
            Log.e(TAG,cursor+" null");
        }
        khoa_model k = cursorToKhoa(cursor);
        return k;
    }
    private khoa_model cursorToKhoa(Cursor cursor){
        khoa_model khoa = new khoa_model();
        khoa.setMaKhoa(cursor.getString(0));
        khoa.setTenKhoa(cursor.getString(1));
        return khoa;
    }
}
