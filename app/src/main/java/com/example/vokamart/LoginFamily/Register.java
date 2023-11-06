package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vokamart.R;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword;

    private Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        btnRegist = findViewById(R.id.btn_daftar);
        etEmail = findViewById(R.id.Edit_Email_Register);
        etUsername = findViewById(R.id.Edit_Username_Register);
        etPassword = findViewById(R.id.Edit_Pass_Register);
        TextView text3 = findViewById(R.id.text_disini_register);



        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

    }
}