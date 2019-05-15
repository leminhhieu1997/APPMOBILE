package com.example.ql_sinhvien;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_sinhvien.adapter.diem_adapter;
import com.example.ql_sinhvien.adapter.spinner_monhoc;
import com.example.ql_sinhvien.dao.diem_dao;
import com.example.ql_sinhvien.dao.monhoc_dao;
import com.example.ql_sinhvien.model.diem_model;
import com.example.ql_sinhvien.model.monhoc_model;

import java.util.ArrayList;
import java.util.List;

public class update_diem extends AppCompatActivity {


    private EditText edt_diem;
    private Spinner listMonHoc;
    private Button btn_ok;
    String id = "";
    String Mssv = "";

    monhoc_dao db ;
    diem_dao db_diem;

    //private RecyclerView listDiem;
   // private List<diem_model> List_Diem;
    private diem_adapter adapter;
    private CoordinatorLayout coordinatorLayout;

    private List<monhoc_model> monHoc_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diem);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String diem = intent.getStringExtra("diem");
        Mssv = intent.getStringExtra("mssv");

        db = new monhoc_dao(getApplicationContext());
        db_diem = new diem_dao(getApplicationContext());

        edt_diem = findViewById(R.id.edt_diem2);
        edt_diem.setText(diem);
        listMonHoc = findViewById(R.id.listMonHoc3);
        btn_ok = findViewById(R.id.btn_ok2);
        getSupportActionBar().setTitle(Mssv);
        monHoc_list = new ArrayList<monhoc_model>();
        db.getAllMonHoc(monHoc_list);
        spinner_monhoc adapterMonhoc = new spinner_monhoc(getApplicationContext(), R.layout.row_item, monHoc_list);
        adapterMonhoc.setDropDownViewResource(R.layout.row_item);
        listMonHoc.setAdapter(adapterMonhoc);
        Click();

    }

    public void Click() {
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (edt_diem.getText().toString().equals("")) {
                    // Toast.makeText(getApplicationContext(),"Could not field emty!", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Không được để trống bất kỳ trường nào", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    diem_model diem = new diem_model();
                    diem.setMaSSV( Mssv);

                    diem.setMaMH(monHoc_list.get(listMonHoc.getSelectedItemPosition()).getMaMH());
                    diem.setDiem(Integer.parseInt(edt_diem.getText().toString()));
                    db_diem.updateDiem(diem);
                    Toast.makeText(getApplicationContext(),"Update sucessfull" , Toast.LENGTH_SHORT).show();//"update sucessful"

                }
            }
        });
    }
}
