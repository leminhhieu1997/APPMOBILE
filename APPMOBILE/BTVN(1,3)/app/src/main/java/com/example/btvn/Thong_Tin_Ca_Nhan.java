package com.example.btvn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Thong_Tin_Ca_Nhan extends AppCompatActivity {
    private Button Gui;
    private RadioGroup group;
    EditText name, id,  add;
    CheckBox DB,DS,DC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong__tin__ca__nhan);
        Gui = findViewById(R.id.BtnGTT);
        group = findViewById(R.id.GroupBC);
        DB = findViewById(R.id.checkBoxDB);
        DS = findViewById(R.id.checkBoxDS);
        DC = findViewById(R.id.checkBoxDC);
        name = findViewById(R.id.txtTen);
        id = findViewById(R.id.txtCMND);
        add = findViewById(R.id.txtTTT);

       Gui.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ThucHienGui();
           }
       });


    }
    public void ThucHienGui(){
        // kiem tra tính hợp lệ của tên
        String ten = name.getText().toString();
        ten = ten.trim();
        if(ten.length()<3){
            name.requestFocus();
            name.selectAll();
            Toast.makeText(this,"Tên phải >= 3 ký tự",Toast.LENGTH_LONG).show();
            return;
        }
        String CMND = id.getText().toString();
        if(CMND.length()!=9){
            id.requestFocus();
            id.selectAll();
            Toast.makeText(this,"CMND đúng 9 số nha có sai lên phường giải thích !",Toast.LENGTH_LONG).show();
            return;
        }
        String bangcap ="";
        int id3 = group.getCheckedRadioButtonId();
        if(id3==-1){
            Toast.makeText(this, "Không có bằng cấp thì ko được r", Toast.LENGTH_LONG).show();

            return;
        }
        RadioButton rd = findViewById(id3);
        bangcap = rd.getText().toString();
        String sothich ="";
        if(DB.isChecked()){
            sothich+=DB.getText()+"\n";
        }
        if(DS.isChecked()){
            sothich+=DS.getText()+"\n";
        }
        if(DC.isChecked()){
            sothich+=DC.getText()+"\n";
        }
        String TTBS = add.getText().toString();
        AlertDialog.Builder HienThongTin = new AlertDialog.Builder(this);
        HienThongTin.setTitle("THÔNG TIN CÁ NHÂN");
        HienThongTin.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // kết nội dung lại
        String noidung = ten+"\n"+CMND+"\n"+bangcap+"\n"+sothich+"\n"
                +"---------------\n"+"Thông tin bổ xung:\n"+TTBS+"\n"+"---------------\n";
        HienThongTin.setMessage(noidung);
        HienThongTin.create().show();

    }





}
