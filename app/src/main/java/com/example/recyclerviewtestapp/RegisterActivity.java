package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private EditText pwd_reg, name_reg, email_reg;
    private TextView login_txt;
    private Button reg_btn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String userId;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pwd_reg = findViewById(R.id.password_reg);
        name_reg = findViewById(R.id.name_reg);
        email_reg = findViewById(R.id.email_reg);
        reg_btn = findViewById(R.id.btn_sigup);
        login_txt = findViewById(R.id.login_btn);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        progressBar = findViewById(R.id.progressBar);

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = name_reg.getText().toString();
                String password = pwd_reg.getText().toString();
                String email = email_reg.getText().toString();

                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(RegisterActivity.this,"Fill name!",Toast.LENGTH_LONG).show();
                    name_reg.setError("Enter name!");
                    name_reg.requestFocus();
                }else if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(RegisterActivity.this,"Fill password!",Toast.LENGTH_LONG).show();
                    pwd_reg.setError("Fill password");
                    pwd_reg.requestFocus();
                }else if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(RegisterActivity.this,"Fill Email!",Toast.LENGTH_LONG).show();
                    email_reg.setError("Enter email");
                    email_reg.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    Toast.makeText(RegisterActivity.this,"Invalid Email!",Toast.LENGTH_LONG).show();
                    email_reg.setError("Invalid Email!");
                    email_reg.requestFocus();

                }else if(password.length()<6)
                {
                    Toast.makeText(RegisterActivity.this,"Password short!",Toast.LENGTH_LONG).show();
                    pwd_reg.setError("Password short!");
                    pwd_reg.requestFocus();
                }else
                {


                    registerUser(name, email, password);

                }

            }
        });




    }



    private void  registerUser(String name, String email, String password)
    {

        changeProgress(true);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NotNull Task<AuthResult> task) {
                changeProgress(false);
                if(task.isSuccessful())
                {

                    Toast.makeText(RegisterActivity.this, "Registering user successful",Toast.LENGTH_LONG).show();
                    mAuth.getCurrentUser().sendEmailVerification();
                    userId = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = db.collection("users").document(userId);
                    Map<String,Object> user = new HashMap<>();
                    user.put("name",name);
                    user.put("email",email);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG,"OnSuccess : user is created for "+userId);
                        }
                    });
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();

                }else
                {

                    Toast.makeText(RegisterActivity.this,  task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });

    }
    public void changeProgress(Boolean isProgress)
    {

        if(isProgress)
        {
            progressBar.setVisibility(View.VISIBLE);
            reg_btn.setVisibility(View.GONE);
        }else
        {
            progressBar.setVisibility(View.GONE);
            reg_btn.setVisibility(View.VISIBLE);
        }


    }
}