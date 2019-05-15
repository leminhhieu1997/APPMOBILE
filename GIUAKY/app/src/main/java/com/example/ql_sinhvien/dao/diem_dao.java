package com.example.ql_sinhvien.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.ql_sinhvien.database.DBcontext;
import com.example.ql_sinhvien.model.diem_model;
import com.example.ql_sinhvien.model.monhoc_model;

import java.util.ArrayList;
import java.util.List;


public class diem_dao {

    public static final String TAG = "DiemDao";

    // database fields
    private SQLiteDatabase mDatabase;
    private DBcontext mDBcontext;
    private Context mContext;
    private String[]  mAllColumns = {mDBcontext.COLUMN_MASSVD, mDBcontext.COLUMN_MAMHD,mDBcontext.COLUMN_DIEM};


    public diem_dao(Context context){
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

    public diem_model createDiem(diem_model d){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_MASSVD,d.getMaSSV());
        values.put(DBcontext.COLUMN_MAMHD,d.getMaMH());
        values.put(DBcontext.COLUMN_DIEM,d.getDiem());

        long insertId = mDatabase.insert(DBcontext.TABLE_DIEM,null, values);
        if(insertId == -1){
            Toast.makeText(mContext,"can't insert diem",Toast.LENGTH_LONG).show();
            return null;
        }

        Cursor cursor = mDatabase.query(DBcontext.TABLE_DIEM,mAllColumns,DBcontext.COLUMN_MASSVD+" =? and "+DBcontext.COLUMN_MAMHD+" =? ",new String[]{d.getMaSSV(),d.getMaMH()},null,null,null);
        cursor.moveToFirst();
        diem_model r = cursorToDiem(cursor);
        cursor.close();
        return r;
    }

    public void deleteDiem(diem_model d){
        System.out.println("The deleted diem has the MSSV "+d.getMaSSV());
        mDatabase.delete(DBcontext.TABLE_MONHOC,DBcontext.COLUMN_MAMHD+" = "+"'"+d.getMaMH()+"'"+" and "+DBcontext.COLUMN_MAMHD+" = "+"'"+d.getDiem()+"'",null);
    }

    public List<diem_model> getAllDiem(List<diem_model> listDiem){
         //= new ArrayList<diem_model>();
        Cursor cursor = mDatabase.query(DBcontext.TABLE_DIEM,mAllColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            diem_model d = cursorToDiem(cursor);
            listDiem.add(d);
            cursor.moveToNext();
        }
        cursor.close();
        return  listDiem;
    }

    public diem_model getDiemById(diem_model d){
        Cursor cursor = mDatabase.query(DBcontext.TABLE_DIEM,mAllColumns,DBcontext.COLUMN_MASSVD+" =? and "+DBcontext.COLUMN_MAMHD+" =? ",new String[]{d.getMaSSV(),d.getMaMH()},null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        diem_model d1 = cursorToDiem(cursor);
        return d1;
    }

    private diem_model cursorToDiem(Cursor cursor){
        diem_model d = new diem_model();
        d.setMaSSV(cursor.getString(0));
//        d.setMaMH(cursor.getString(1));
        String mmh = cursor.getString(1);
        String masv = cursor.getString(0);
        monhoc_model m = new monhoc_model();
        d.setDiem(cursor.getInt(2));
        monhoc_dao dao = new monhoc_dao(mContext);
        m = dao.getMonHocById(mmh);
        if(m!=null){
            d.setMaMH(m.getTenMH());
        }

        return d;
    }


    public void updateDiem (diem_model d ){
        ContentValues values = new ContentValues();
        values.put(DBcontext.COLUMN_MASSVD,d.getMaSSV());
        values.put(DBcontext.COLUMN_MAMHD,d.getMaMH());
        values.put(DBcontext.COLUMN_DIEM,d.getDiem());

        mDatabase.update(DBcontext.TABLE_DIEM,values,DBcontext.COLUMN_MASSVD+"= ? and "+DBcontext.COLUMN_MAMHD+"= ?", new String[]{d.getMaSSV(), d.getMaMH()});

    }
}
