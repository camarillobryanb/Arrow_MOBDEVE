package com.example.arrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnGoogle;
    private TextView tvRegister;
    private ProgressBar pbLogin;

    // Firebase
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        createRequest();
        this.initFirebase();
        this.initComponents();
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents() {
        this.etEmail = findViewById(R.id.et_email);
        this.etPassword = findViewById(R.id.et_password);
        this.btnLogin = findViewById(R.id.btn_login);
        this.btnGoogle = findViewById(R.id.btn_google);
        this.tvRegister = findViewById(R.id.tv_register);
        this.pbLogin = findViewById(R.id.pb_login);

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (!checkEmpty(email, password)){
                    signIn(email, password);
                }
            }
        });

        this.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("438315523124-s1i6rggs4fd92ecv6s8hgbmm81u50phn.apps.googleusercontent.com")
                .requestEmail()
                .build();

        this.mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn(String email, String password) {
        this.pbLogin.setVisibility(View.VISIBLE);
        this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                            pbLogin.setVisibility(View.GONE);
                            startActivity(i);
                            finish();
                        } else {
                            loginFailed();
                        }
                    }
                });
    }

    private boolean checkEmpty(String email, String password) {
        boolean hasEmpty = false;
        if (email.isEmpty()){
            this.etEmail.setError("Required Field");
            this.etEmail.requestFocus();
            hasEmpty = true;
        } else if (password.isEmpty()){
            this.etPassword.setError("Required Field");
            hasEmpty = true;
        }
        return hasEmpty;
    }

    private void loginFailed() {
        this.pbLogin.setVisibility(View.GONE);
        Toast.makeText(this, "User Login Failed", Toast.LENGTH_SHORT).show();
    }
}