package com.example.arrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvLogin;
    private EditText etRegEmail;
    private EditText etRegPassword;
    private Button btnSubmit;
    private ProgressBar pbRegister;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.initFirebase();
        this.initComponents();
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents() {
        this.tvLogin = findViewById(R.id.tv_reg_login);
        this.etRegEmail = findViewById(R.id.et_reg_email);
        this.etRegPassword = findViewById(R.id.et_reg_password);
        this.btnSubmit = findViewById(R.id.btn_submit);
        this.pbRegister = findViewById(R.id.pb_register);

        this.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        this.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etRegEmail.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();
                if (!checkEmpty(email, password)){
                    // Add a new user to DB
                    User user = new User(email, password);
                    storeUser(user);
                }
            }
        });
    }

    private boolean checkEmpty(String email, String password) {
        boolean hasEmpty = false;
        if (email.isEmpty()){
            this.etRegEmail.setError("Required Field");
            this.etRegEmail.requestFocus();
            hasEmpty = true;
        } else if (password.isEmpty()){
            this.etRegPassword.setError("Required Field");
            hasEmpty = true;
        }

        return hasEmpty;
    }

    private void storeUser(User user) {
        this.pbRegister.setVisibility(View.VISIBLE);

        // Register to Firebase
        this.mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            successfulRegistration();
                        } else {
                            failedRegistration();
                        }
                    }
                });
    }

    private void successfulRegistration() {
        this.pbRegister.setVisibility(View.GONE);
        Toast.makeText(this, "User Registration Success", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void failedRegistration() {
        this.pbRegister.setVisibility(View.GONE);
        Toast.makeText(this, "User Registration Failed", Toast.LENGTH_SHORT).show();
    }
}
