package com.androidguys.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        tv = findViewById(R.id.textViewNewUser);
        btn = findViewById(R.id.buttonLogin);
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                if(username.length()==0 || password.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                }
                else {
                    if(db.login(username, password) == 1) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();

                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Invalid Details!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}