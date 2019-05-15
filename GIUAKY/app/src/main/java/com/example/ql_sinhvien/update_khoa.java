package com.example.ql_sinhvien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.dao.khoa_dao;
import com.example.ql_sinhvien.model.khoa_model;

public class update_khoa extends AppCompatActivity {

    private EditText txt_id, txt_name;
    private Button btn_ok;
    khoa_dao db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_khoa);
        Intent intent = getIntent();
        txt_id = findViewById(R.id.edit_ma);
        txt_name = findViewById(R.id.edit_name);
        btn_ok = findViewById(R.id.btn_update);

        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");

        txt_id.setText(id);
        txt_name.setText(name);
        txt_id.setEnabled(false);


        getSupportActionBar().setTitle(txt_id.getText().toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        db = new khoa_dao(getApplicationContext());

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
                khoa_model k = new khoa_model();
                k.setMaKhoa(txt_id.getText().toString());
                k.setTenKhoa(txt_name.getText().toString());
                db.updateKhoa(k);

                Toast.makeText(getApplicationContext(),"Update sucessfull",Toast.LENGTH_LONG).show();
            }
        });
    }
}
