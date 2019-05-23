package com.nhanhoa.quanlydanhba;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nhanhoa.quanlydanhba.com.nhanhoa.quanlydanhba.models.User;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class DetailUser extends AppCompatActivity {
    private ImageView linkAvatar;
    private TextView change, language;
    private EditText name, email, birthday, work, relationship;
    private RadioButton male, female;
    public Uri selectedImage;
    private static final int PICK_IMAGE_REQUEST =1;
    private static int RESULT_LOAD_IMAGE = 1;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_user);
        loadLocale();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("UserBundle");
        user =  (User) bundle.getSerializable("User");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Log.e("data",user.getId()+" no ở đây");
        setWidget();

        linkAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DetailUser.this, "language", Toast.LENGTH_SHORT).show();
                showChangeLangDialog();
            }
        });
    }

    private void showChangeLangDialog() {
        final String [] listItems = {"English(US)", "French", "Vietnamese"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailUser.this);
        mBuilder.setTitle(R.string.lang_dialog);
        mBuilder.setSingleChoiceItems(listItems, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

                if(i == 0){
                    setLocale("en");
                    recreate();
                   // Log.e("aef", "onClick:  vao su kien thay doi");
                   // name.setText(R.string.hint_fname);

                }
                else if(i == 1){
                    setLocale("fr");
                    recreate();
                   // name.setText(R.string.hint_fname);
                }
                else if(i == 2 ){
                    setLocale("vi");
                    recreate();
                   /// name.setText(R.string.hint_fname);
                }
                dialog.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        //Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
//        Intent intent = new Intent(DetailUser.this,DetailUser.class);
//        startActivity(intent);

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language =  prefs.getString("My_Lang", "");
        setLocale(language);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==  RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data && data.getData() != null) {
            selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Picasso.get().load(selectedImage).into(linkAvatar);
//            upGaleryProcess(selectedImage);
//            if(flat == 1){
//                //ThemContactDB(user);
//            }else{
//                CapNhatContactDB(user);
//            }


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(nameContact.equals("")||phoneContact.equals("")){
//            Toast.makeText(getApplicationContext(),"Tên hoặc số điện thoại trống",Toast.LENGTH_LONG);
//            return false;
//        }else{
//            user.setPhone(phoneContact.getText().toString());
//            user.setName(nameContact.getText().toString());
//            user.setLastName(lnameContact.getText().toString());
//            user.setCompany(companyContact.getText().toString());
//            user.setBlock(0);
//            user.setImage("https://drive.google.com/file/d/1mcyhbFB2t4-rRwgmLNV7FSn8y93K6_rH/view?usp=sharing");
//        }
//        if(flat == 1){
//            user.setSttCall(R.drawable.ic_call_white);
//            ThemContactDB(user);
//        }else{
//            //Log.e("def","CAp nhat db");
//            CapNhatContactDB(user);
//        }
        if(item.getItemId() == R.id.nav_edit){
            Toast.makeText(this, R.string.saved_1, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setWidget() {
        linkAvatar = findViewById(R.id.linkAvatar);
        change = findViewById(R.id.change);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        birthday = findViewById(R.id.birthday);
        work = findViewById(R.id.work);
        relationship = findViewById(R.id.relationship);
        language = findViewById(R.id.language);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
    }
}
