package com.example.ql_sinhvien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ql_sinhvien.adapter.sinhvien_adapter;
import com.example.ql_sinhvien.dao.sinhvien_dao;
import com.example.ql_sinhvien.model.sinhvien_model;

import java.util.ArrayList;
import java.util.List;

public class view_sinhvien extends AppCompatActivity {
    private static final String TAG = "Home sv view";

    private RecyclerView listSinhVien;
    private List<sinhvien_model> List_Sinhvien;
    private sinhvien_adapter adapter;
    sinhvien_dao db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sinhvien);

        listSinhVien = findViewById(R.id.list_sinhvien);
        List_Sinhvien = new ArrayList<sinhvien_model>();

        adapter = new sinhvien_adapter(List_Sinhvien,getApplicationContext(),1);
        RecyclerView.LayoutManager mlayoutM = new LinearLayoutManager(getApplicationContext());
        listSinhVien.setLayoutManager(mlayoutM);
        listSinhVien.setItemAnimator(new DefaultItemAnimator());
        listSinhVien.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listSinhVien.setAdapter(adapter);
        db = new sinhvien_dao(getApplicationContext());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List_Sinhvien.clear();
        db.getAllSinhVien(List_Sinhvien);
        adapter.notifyDataSetChanged();
    }
}
