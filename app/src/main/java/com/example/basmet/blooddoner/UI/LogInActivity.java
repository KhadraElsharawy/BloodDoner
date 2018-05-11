package com.example.basmet.blooddoner.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.basmet.blooddoner.Firebase.FirebaseApplication;
import com.example.basmet.blooddoner.Helper;
import com.example.basmet.blooddoner.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private EditText edEmail, edPassword;
    private Button butLogIn, butSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = findViewById(R.id.email);
        edPassword = findViewById(R.id.password);
        butLogIn = findViewById(R.id.log_in);
        butSignUp = findViewById(R.id.sign_up);

        butLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   String enterEmail = edEmail.getText().toString();
             //   String enterPassword = edPassword.getText().toString();
                   Intent intent =  new Intent(LogInActivity.this, MainActivity.class);
                 startActivity(intent);
                   }

        });

        butSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }



    }
