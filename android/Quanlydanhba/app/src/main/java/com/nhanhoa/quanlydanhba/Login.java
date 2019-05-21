package com.nhanhoa.quanlydanhba;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    RelativeLayout btn_login;
    TextView register;
    EditText userName,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setWidget();
        userName = findViewById(R.id.userName);
        pass  = findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent home =  new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(home);
               DangNhap();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }

    void setWidget(){
        btn_login = findViewById(R.id.login);
        register = findViewById(R.id.textView2);
    }
    private void DangNhap(){
        String email = userName.getText().toString().trim();
        String password = pass.getText().toString().trim();
        if(email.equals("")||password.equals("")){
            Toast.makeText(getApplicationContext(),"Các trường không được trống",Toast.LENGTH_LONG).show();
            return;
        }
        Log.e("AAAA",email + password);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("abc", "onComplete: ");

                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"done: "+task.getResult()+"  "+user.getUid(),Toast.LENGTH_LONG).show();
                            Intent home =  new Intent(getApplicationContext(),MainActivity.class);
                            home.putExtra("Uiid",user.getUid());
                            startActivity(home);
                            finish();
                        } else {
                            Log.e("AAA",task.getException().toString());
                            Toast.makeText(getApplicationContext(),"Lỗi!!: "+ task.getException(),Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }
}
