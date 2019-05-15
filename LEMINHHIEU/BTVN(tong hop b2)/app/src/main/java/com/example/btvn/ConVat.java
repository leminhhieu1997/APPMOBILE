package com.example.btvn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConVat extends AppCompatActivity {


    private ImageView hinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_vat);

        hinh = findViewById(R.id.hinh);
        RadioButton B = findViewById(R.id.radioBird);
        RadioButton C = findViewById(R.id.radioCat);
        RadioButton D = findViewById(R.id.radioDog);
        RadioButton rabi = findViewById(R.id.radioRabbit);
        RadioButton P = findViewById(R.id.radioPig);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hinh.setImageResource(R.drawable.bird);
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hinh.setImageResource(R.drawable.cat);
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hinh.setImageResource(R.drawable.dog);
            }
        });
        rabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hinh.setImageResource(R.drawable.rabbit);
            }
        });
        P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hinh.setImageResource(R.drawable.pig);
            }
        });
//        groupA.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case 1:
//                        hinh.setImageResource(R.drawable.bird);
//                        break;
//                    case 2:
//                        hinh.setImageResource(R.drawable.cat);
//                        break;
//                    case 3:
//                        hinh.setImageResource(R.drawable.dog);
//                        break;
//                    case 4:
//                        hinh.setImageResource(R.drawable.rabbit);
//                        break;
//                    case 5:
//                        hinh.setImageResource(R.drawable.pig);
//                        break;
//                }
//            }
//        });
    }
}
