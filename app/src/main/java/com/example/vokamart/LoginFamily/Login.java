package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vokamart.MainFamily.home;
import com.example.vokamart.R;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etEmail = findViewById(R.id.edit_Email_Login);
        EditText etPassword = findViewById(R.id.Edit_Pass_Login);
        Button btnlogin = findViewById(R.id.btn_Login);
        TextView text = findViewById(R.id.text_disini_login);
        TextView text2 = findViewById(R.id.Lupa_Pass_Text);

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Lupa_Password.class);
                startActivity(i);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();



            }
        });


    }



}
