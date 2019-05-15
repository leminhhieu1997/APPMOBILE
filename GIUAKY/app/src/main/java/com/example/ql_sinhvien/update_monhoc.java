package com.example.ql_sinhvien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sinhvien.dao.monhoc_dao;
import com.example.ql_sinhvien.model.monhoc_model;

public class update_monhoc extends AppCompatActivity {

    private EditText txt_id, txt_name,txt_soTiet;
    private Button btn_ok;
    monhoc_dao db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_monhoc);
        Intent intent = getIntent();
        txt_id = findViewById(R.id.edit_ma);
        txt_name = findViewById(R.id.edit_name);
        txt_soTiet = findViewById(R.id.edit_soTiet);

        btn_ok = findViewById(R.id.btn_update);

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String soTiet = intent.getStringExtra("soTiet");

        txt_id.setText(id);
        txt_name.setText(name);
        txt_id.setEnabled(false);
        txt_soTiet.setText(soTiet);


        getSupportActionBar().setTitle(txt_id.getText().toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new monhoc_dao(getApplicationContext());

        clickButton();
    }

    public void clickButton(){
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_id.getText().equals("")&&txt_name.getText().equals("")){
                    Toast.makeText(getApplicationContext(),"id or name empty",Toast.LENGTH_LONG).show();
                    return;
                }
                monhoc_model mh = new monhoc_model();
                mh.setMaMH(txt_id.getText().toString());
                mh.setTenMH(txt_name.getText().toString());
                mh.setSoTiet(Integer.parseInt(txt_soTiet.getText().toString()));
                db.updateMonHoc(mh);

                Toast.makeText(getApplicationContext(),"Update sucessfull",Toast.LENGTH_LONG).show();
            }
        });
    }
}
