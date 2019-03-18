package com.example.testtemp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView a = findViewById(R.id.listproduct);
        ArrayList<String> abc = new ArrayList<>();
        abc.add("Cau 1: dung");
        abc.add("Cau 2: dung");
        abc.add("Cau 3: dung");
        abc.add("Cau 4: dung");
        abc.add("Cau 0: dung");
        abc.add("Cau 5: dung");
        abc.add("Cau 6: dung");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,abc);
        a.setAdapter(arrayAdapter);
    }

}
