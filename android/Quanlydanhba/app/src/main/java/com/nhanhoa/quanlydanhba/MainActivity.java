package com.nhanhoa.quanlydanhba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.nhanhoa.quanlydanhba.com.nhanhoa.quanlydanhba.adapters.MenuAdapter;
import com.nhanhoa.quanlydanhba.com.nhanhoa.quanlydanhba.models.ItemMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private LinearLayout itemEdit, itemLogout, itemHelp;
    private NavigationView nav;

    private String Uiid;
    private DatabaseReference mData;
    private FirebaseStorage storage;


    private List<ItemMenu> arrayMenu;
    private ListView menu;
    private ImageView itemIcon;
    private TextView itemTitle;
    private MenuAdapter menuAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter viewPageAdapter;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uiid = intent.getStringExtra("Uiid");
        mData = FirebaseDatabase.getInstance().getReference(Uiid);

        drawerLayout = findViewById(R.id.activity_main_drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setWidget();

        arrayMenu = new ArrayList<>();
        arrayMenu.add(new ItemMenu(R.drawable.ic_edit_black, "Sửa thông tin"));
        arrayMenu.add(new ItemMenu(R.drawable.ic_exit_to_app_black, "Đăng xuất"));
        arrayMenu.add(new ItemMenu(R.drawable.ic_help_outline_black, "Giúp đỡ"));

        menuAdapter = new MenuAdapter(this, R.layout.item_menu, arrayMenu);
        menu.setAdapter(menuAdapter);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        //nav = findViewById(R.id.nav);

        //them fragment



        Bundle bundle = new Bundle();
        bundle.putString("Uiid", Uiid);
        FragmentCall fragmentCall = new FragmentCall();
        fragmentCall.setArguments(bundle);

        FragmentContact fragmentContact = new FragmentContact();
        fragmentContact.setArguments(bundle);

        FragmentAddContact fragmentAddContact = new FragmentAddContact();
        fragmentAddContact.setArguments(bundle);


        viewPageAdapter.AddFragment(fragmentCall, "");
        viewPageAdapter.AddFragment(fragmentContact, "");
        viewPageAdapter.AddFragment(new FragmentSms(), "");
        viewPageAdapter.AddFragment(fragmentAddContact, "");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_people);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_sms);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_dialpad);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setupTabIcons(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        //Toast.makeText(MainActivity.this, "edit", Toast.LENGTH_SHORT).show();
                        Intent intent =  new Intent(getApplicationContext(),SettingActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1:{
                        //Toast.makeText(MainActivity.this, "logout", Toast.LENGTH_SHORT).show();
                        Intent intent =  new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        break;
                    }
                    case 2:{
                        Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
    }

    private void setupTabIcons(int pos) {
        switch (pos){
            case 0:
//                tabLayout.getTabAt(0).setIcon(R.drawable.ic_people);
//                tabLayout.getTabAt(1).setIcon(tabIconsNoFocus[1]);
//                tabLayout.getTabAt(2).setIcon(tabIconsNoFocus[2]);

                break;
            case 1:
//                tabLayout.getTabAt(0).setIcon(tabIconsNoFocus[0]);
//                tabLayout.getTabAt(1).setIcon(tabIconsFocus[1]);
//                tabLayout.getTabAt(2).setIcon(tabIconsNoFocus[2]);

                break;

            case 2:
//                tabLayout.getTabAt(0).setIcon(tabIconsNoFocus[0]);
//                tabLayout.getTabAt(1).setIcon(tabIconsNoFocus[1]);
//                tabLayout.getTabAt(2).setIcon(tabIconsFocus[2]);

                break;
        }
    }

    private void setWidget(){
        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewpaper_id);

        menu = findViewById(R.id.menu);
        itemIcon = findViewById(R.id.item_icon);
        itemTitle = findViewById(R.id.item_title);



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
