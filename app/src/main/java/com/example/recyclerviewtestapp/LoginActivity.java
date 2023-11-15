package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email_txt, pwd_txt;
    private Button siginBtn;
    private TextView reg_btn;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_txt = findViewById(R.id.email_txt);
        pwd_txt = findViewById(R.id.password_txt);
        siginBtn = findViewById(R.id.btn_sigin);
        reg_btn = findViewById(R.id.register_btn);
        progressBar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();


        siginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_txt.getText().toString();
                String password = pwd_txt.getText().toString();

                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(LoginActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
                    email_txt.setError("Please enter email!");
                    email_txt.requestFocus();

                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please fill all details", Toast.LENGTH_LONG).show();
                    pwd_txt.setError("Please enter password!");
                    pwd_txt.requestFocus();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                    email_txt.setError("Invalid Email");
                    email_txt.requestFocus();
                } else {

                    loginUser(email, password);

                }

            }
        });


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    private void loginUser(String email, String password) {
        changeProgressBar(true);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                changeProgressBar(false);
                if (task.isSuccessful()) {

                   if(mAuth.getCurrentUser().isEmailVerified())
                   {

                       Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       startActivity(intent);
                       finish();

                   }else
                   {

                       Toast.makeText(LoginActivity.this,"Email not verified,Please verify Email",Toast.LENGTH_LONG).show();
                   }


                }else {

                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }

    public void changeProgressBar(Boolean isProgress) {

        if (isProgress) {
            progressBar.setVisibility(View.VISIBLE);
            siginBtn.setVisibility(View.GONE);
        } else {

            progressBar.setVisibility(View.GONE);
            siginBtn.setVisibility(View.VISIBLE);

        }


    }

}