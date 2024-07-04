package com.androidguys.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegUsername);
        edEmail = findViewById(R.id.editTextRegEmail);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        tv = findViewById(R.id.textViewExistingUser);
        btn = findViewById(R.id.buttonRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);

                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                }
                else {
                    if(password.compareTo(confirm)==0) {
                        if(isValid(password)) {
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Record inserted!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords mismatch", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public static Boolean isValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        if (!password.matches(".*\\d.*")) {
            return false;
        }

        if (!password.matches(".*[!@#$%^&*].*")) {
            return false;
        }
        return true;
    }
}