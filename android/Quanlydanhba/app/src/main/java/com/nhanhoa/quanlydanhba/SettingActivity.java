package com.nhanhoa.quanlydanhba;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity
{
    private static final int PICK_IMAGE_REQUEST =1;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    public Uri selectedImage;

    private CircleImageView imgContact;
    private TextView changeImg;
    private EditText lnameContact;
    private EditText nameContact;
    private EditText companyContact;
    private TextView clearPhone;
    private TextView removeHomeAddress;
    private TextView removeGmail;
    private TextView removeFB;
    private TextView removeURL;
    private EditText phoneContact;
    private TextView addPhone;
    private TextView addAddress;
    private TextView addGmail;
    private TextView addFB;
    private TextView addURL;
    private TextView blockCall;
    private TextView blockSMS;
    private TextView deleteContact;
    private LinearLayout containerPhone;
    private LinearLayout containerHomeAddress;
    private LinearLayout containerGmail;
    private LinearLayout containerFB;
    private LinearLayout containerURL;
    private TextView activeCall;
    private TextView activeSMS;
    private static int RESULT_LOAD_IMAGE = 1;

    //list nay la danh sach chan cuoc goi
    public static List<Blacklist> blockCallList;
    // Object of BlacklistDAO to query to database
    //private BlacklistDAO blackListDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setWidget();
        blockOrActive();
        mStorageRef = FirebaseStorage.getInstance().getReference("avartar");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("avarter");
        imgContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent,PICK_IMAGE_REQUEST);
//                if(selectedImage != null){
//                    StorageReference fireReference = mStorageRef.child( System.currentTimeMillis()+"."+getFileExtension(selectedImage));
//                    fireReference.putFile(selectedImage)
//                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    Toast.makeText(getApplicationContext(),"Sucess file",Toast.LENGTH_LONG).show();
//
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//
//                                }
//                            })
//                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                                    double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
//
//                                }
//                            });
//                }else{
//                    Toast.makeText(getApplicationContext(),"No file",Toast.LENGTH_LONG).show();
//                }


            }
        });


        changeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });



        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();
            }
        });

        addGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGmail();
            }
        });

        addFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFB();
            }
        });

        addURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addURL();
            }
        });

        clearPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneContact.setText("");
            }
        });

        removeHomeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeHomeAddress();
            }
        });

        removeGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeGmail();
            }
        });

        removeFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFB();
            }
        });

        removeURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeURL();
            }
        });

        blockCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPhoneToBlockList();
            }
        });

        activeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePhoneFromBlockList();
            }
        });

        blockSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSMSToBlockList();
            }
        });

        activeSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSMSFromBlockList();
            }
        });
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

            Picasso.with(this).load(selectedImage).into(imgContact);
            //imgContact.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    public void setWidget(){
        imgContact = findViewById(R.id.img_contact);
        changeImg = findViewById(R.id.change_img);
        phoneContact = findViewById(R.id.phone_contact);
        containerHomeAddress = findViewById(R.id.container_home_address);
        containerGmail = findViewById(R.id.container_gmail);
        containerFB = findViewById(R.id.container_fb);
        containerURL = findViewById(R.id.container_url);
        addAddress = findViewById(R.id.add_address);
        addGmail = findViewById(R.id.add_gmail);
        addFB = findViewById(R.id.add_fb);
        addURL = findViewById(R.id.add_url);
        clearPhone = findViewById(R.id.clear_phone);
        removeHomeAddress = findViewById(R.id.remove_home_address);
        removeGmail = findViewById(R.id.remove_gmail);
        removeFB = findViewById(R.id.remove_fb);
        removeURL = findViewById(R.id.remove_url);
        blockCall = findViewById(R.id.block_call);
        activeCall = findViewById(R.id.active_call);
        blockSMS = findViewById(R.id.block_sms);
        activeSMS = findViewById(R.id.active_sms);
    }

    protected void addAddress(){
        containerHomeAddress.setVisibility(View.VISIBLE);
        addAddress.setVisibility(View.GONE);
    }

    protected void addGmail(){
        containerGmail.setVisibility(View.VISIBLE);
        addGmail.setVisibility(View.GONE);
    }

    protected void addFB(){
        containerFB.setVisibility(View.VISIBLE);
        addFB.setVisibility(View.GONE);
    }

    protected void addURL(){
        containerURL.setVisibility(View.VISIBLE);
        addURL.setVisibility(View.GONE);
    }

    protected void removeHomeAddress(){
        containerHomeAddress.setVisibility(View.GONE);
        addAddress.setVisibility(View.VISIBLE);
    }

    protected void removeGmail(){
        containerGmail.setVisibility(View.GONE);
        addGmail.setVisibility(View.VISIBLE);
    }

    protected void removeFB(){
        containerFB.setVisibility(View.GONE);
        addFB.setVisibility(View.VISIBLE);
    }

    protected void removeURL(){
        containerURL.setVisibility(View.GONE);
        addURL.setVisibility(View.VISIBLE);
    }

    protected void blockOrActive(){
        //String phone = phoneContact.getText().toString();
        //neu sdt khong ton tai trong bang call block thi hien nut chan cuoc goi
        //nguoc lai thi hien nut bo chan cuoc goi
        //tuong tu doi voi sms block
    }

    protected void addPhoneToBlockList(){
        blockCall.setVisibility(View.GONE);
        activeCall.setVisibility(View.VISIBLE);
        // Once click, it's first creates the Blacklist object
        //final Blacklist phone = new Blacklist();

        // Then, set all the values from user input
        //phone.phoneNumber = phoneContact.getText().toString();

        // Insert the object to the database
        //blackListDao.create(phone);

        // Show the success message to user
        Toast.makeText(this, "Chặn thành công", Toast.LENGTH_SHORT).show();
    }

    protected void removePhoneFromBlockList(){
        blockCall.setVisibility(View.VISIBLE);
        activeCall.setVisibility(View.GONE);
        //blackListDao.delete(phoneContact.getText().toString());
        Toast.makeText(this, "Đã hủy chặn", Toast.LENGTH_SHORT).show();
    }

    protected void addSMSToBlockList(){
        blockSMS.setVisibility(View.GONE);
        activeSMS.setVisibility(View.VISIBLE);
        //tuong tu chan cuoc goi
        Toast.makeText(this, "Chặn thành công", Toast.LENGTH_SHORT).show();
    }

    protected void removeSMSFromBlockList(){
        blockSMS.setVisibility(View.VISIBLE);
        activeSMS.setVisibility(View.GONE);
        //tuong tu huy chan cuoc goi
        Toast.makeText(this, "Đã hủy chặn", Toast.LENGTH_SHORT).show();
    }
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
