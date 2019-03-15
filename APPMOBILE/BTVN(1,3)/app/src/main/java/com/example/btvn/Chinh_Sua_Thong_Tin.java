package com.example.btvn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;

public class Chinh_Sua_Thong_Tin extends AppCompatActivity {
    private CheckBox C,G,H,T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh__sua__thong__tin);
        C = findViewById(R.id.checkBoxCh);
        G = findViewById(R.id.checkBoxG);
        H = findViewById(R.id.checkBoxH);
        T = findViewById(R.id.checkBoxT);
    }
}
