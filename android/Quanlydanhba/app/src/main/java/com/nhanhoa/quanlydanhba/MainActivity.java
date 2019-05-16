package com.nhanhoa.quanlydanhba;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewpaper_id);
        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        //them fragment
        viewPageAdapter.AddFragment(new FragmentCall(), "");
        viewPageAdapter.AddFragment(new FragmentContact(), "");
        viewPageAdapter.AddFragment(new FragmentSms(), "");
        viewPageAdapter.AddFragment(new FragmentAddContact(), "");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_people);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_sms);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_person_add_black);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }

    private void checkAndRequestPermission() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissions.add(permission);
            }
        }
        if (!listPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissions.toArray(new String[listPermissions.size()]), 1);
        }
    }
}
