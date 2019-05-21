package com.nhanhoa.quanlydanhba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    TextView haveLogin;
    EditText userName,pass,comfirm_pass;
    LinearLayout signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setWidget();

        haveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKy();
            }
        });
    }

    void setWidget(){
        haveLogin = findViewById(R.id.have_login);
        userName = findViewById(R.id.userName);
        pass = findViewById(R.id.pass);
        comfirm_pass = findViewById(R.id.comfirm_pass);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();

    }
    private void DangKy(){
        String email = userName.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String comfirm_password = comfirm_pass.getText().toString().trim();

        if(email.equals("")||password.equals("")){
            Toast.makeText(getApplicationContext(),"Các trường không được trống",Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(comfirm_password)){
            Toast.makeText(getApplicationContext(),"Password and confirm Pass khong hop le",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Dang ky thanh cong",Toast.LENGTH_LONG).show();
                            Intent intent =  new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(),"Bug: "+task.getException(),Toast.LENGTH_LONG).show();
                            Log.e("AAA",task.getException().toString());
                            // If sign in fails, display a message to the user.

                        }

                        // ...
                    }
                });

    }
}
