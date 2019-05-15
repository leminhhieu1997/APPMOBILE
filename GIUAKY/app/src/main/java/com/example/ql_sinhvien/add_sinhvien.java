package com.example.ql_sinhvien;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ql_sinhvien.adapter.khoa_adapter;
import com.example.ql_sinhvien.adapter.spinner_khoa;
import com.example.ql_sinhvien.dao.khoa_dao;
import com.example.ql_sinhvien.dao.sinhvien_dao;
import com.example.ql_sinhvien.model.khoa_model;
import com.example.ql_sinhvien.model.sinhvien_model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

public class add_sinhvien extends AppCompatActivity {

    private EditText edt_maSV,edt_ngaySinh,edt_diaChi,edt_dienThoai,edt_hoVaTen;
    private RadioGroup rG_gioiTinh;
    private RadioButton radioSex;
   // private RadioButton nu;
    private Spinner listKhoa;
    private Button btn_ok;
    khoa_dao db ;
    sinhvien_dao db_sinhvien;

    private List<khoa_model> khoa_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinhvien);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edt_maSV = findViewById(R.id.edit_maSV);
        edt_hoVaTen = findViewById(R.id.edit_hoVaTen);
        edt_ngaySinh = findViewById(R.id.edt_ngaySinh);
        edt_diaChi = findViewById(R.id.edt_diaChi);
        edt_dienThoai = findViewById(R.id.edt_dienThoai);

        rG_gioiTinh = findViewById(R.id.rG_gioiTinh);


        listKhoa = findViewById(R.id.listKhoa);
        btn_ok = findViewById(R.id.btn_ok);

        khoa_list = new ArrayList<khoa_model>();

        db = new khoa_dao(getApplicationContext());
        db_sinhvien = new sinhvien_dao(getApplicationContext());

        db.getAllKhoa(khoa_list);
        spinner_khoa adapterKhoa = new spinner_khoa(getApplicationContext(), R.layout.row_item, khoa_list);
        adapterKhoa.setDropDownViewResource(R.layout.row_item);
        listKhoa.setAdapter(adapterKhoa);
        Click();

    }

    public void Click(){
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if(edt_maSV.getText().toString().equals("")||edt_diaChi.toString().equals("")||edt_dienThoai.getText().toString().equals("")|| edt_ngaySinh.getText().equals("")&&edt_hoVaTen.getText().toString().equals("")){
                   // Toast.makeText(getApplicationContext(),"Could not field emty!", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Không được để trống bất kỳ trường nào",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    sinhvien_model sv = new sinhvien_model();
                    sv.setMaSSV(edt_maSV.getText().toString());

                    sv.setHoVaTen(edt_hoVaTen.getText().toString());

                    int selectedId = rG_gioiTinh.getCheckedRadioButtonId();
                    radioSex = findViewById(selectedId);
                    if(radioSex.getText().toString().equals("Nam")){
                        sv.setPhai(1);
                    }else {
                        sv.setPhai(0);
                    }


                    //date
                    //if(Integer.parseInt(check[0])<=31 && Integer.parseInt(check[0]) >1 )

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formatter.setLenient(false);
                    try {
                        Date date = formatter.parse(edt_ngaySinh.getText().toString().trim());
                        Calendar myCal = new GregorianCalendar();
                        myCal.setTime(date);
                        ZoneId zoneId = ZoneId.systemDefault();
                        //int year = myCal.get(Calendar.YEAR);
                        myCal.get(Calendar.YEAR);

                        if(myCal.get(Calendar.YEAR)>=Integer.parseInt(Year.now(zoneId).toString())){
                            Toast.makeText(getApplicationContext(),"Năm chưa hợp lý bạn nên xem xét lại !", Toast.LENGTH_LONG).show();
                            return;
                        }
                        sv.setNgaySinh(String.valueOf(date));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Định dạng ngày không đúng dd/MM/yyyy", Toast.LENGTH_LONG).show();
                        return;
                    }
                    sv.setDiaChi(edt_diaChi.getText().toString());

                    if(isValidPhone(edt_dienThoai.getText().toString())){
                       sv.setSdt(edt_dienThoai.getText().toString());
                    }else {

                        Toast.makeText(getApplicationContext(),"SDT không hợp lý",Toast.LENGTH_SHORT).show();
                        return;

                    }

                    sv.setMaKhoa(khoa_list.get(listKhoa.getSelectedItemPosition()).getMaKhoa());
                    db_sinhvien.createSinhVien(sv);
                    Toast.makeText(getApplicationContext(),"create sucessful",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private boolean isValidPhone(String phone)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length() < 6 || phone.length() > 13)
            {
                check = false;

            }
            else
            {
                check = true;

            }
        }
        else
        {
            check=false;
        }
        return check;
    }
}
