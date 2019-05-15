package com.example.ql_sinhvien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMain extends AppCompatActivity {
    Button btn_sinhvien, btn_khoa,btn_monhoc, btn_diem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        btn_sinhvien = findViewById(R.id.btn_sinhVien);
        btn_khoa = findViewById(R.id.btn_khoa);
        btn_monhoc = findViewById(R.id.btn_monhoc);
        btn_diem = findViewById(R.id.btn_diem);

        btn_sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(getApplicationContext(),Home_sv.class);
                //intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                getApplicationContext().startActivity(intent_add);
            }
        });

        btn_khoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(getApplicationContext(),Home.class);
                //intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                getApplicationContext().startActivity(intent_add);
            }
        });

        btn_monhoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(getApplicationContext(),Home_mh.class);
                //intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                getApplicationContext().startActivity(intent_add);
            }
        });

        btn_diem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(getApplicationContext(),view_sinhvien.class);
                //intent_update.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                getApplicationContext().startActivity(intent_add);
            }
        });
    }
}
