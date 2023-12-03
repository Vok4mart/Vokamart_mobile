package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vokamart.EmailSender;
import com.example.vokamart.OTPGenerator;
import com.example.vokamart.R;
import com.example.vokamart.activity_login_lupa_password2;

import java.util.Objects;

public class Lupa_Password extends AppCompatActivity {

    EditText etEmail, etOTP, etNewPass;

    Button buttonOTP, buttonCekOTP;

    String generatedOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_lupa_password);

        TextView text4 = findViewById(R.id.text_disini_reset);
        buttonOTP = findViewById(R.id.btn_send_otp);
        etEmail = findViewById(R.id.Edit_Email_Reset);
        etOTP = findViewById(R.id.Edit_OTP);
        buttonCekOTP = findViewById(R.id.btn_cek_otp);

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Lupa_Password.this, Login.class);
                startActivity(i);
            }
        });

        buttonOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Objects.requireNonNull(etEmail.getText()).toString();

                if (email.isEmpty()) {
                    Toast.makeText(Lupa_Password.this, "Harap isi field!", Toast.LENGTH_SHORT).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Lupa_Password.this, "Harap masukkan email yang valid", Toast.LENGTH_SHORT).show();
                } else {
                    sendOTP(email);
                }

            }
        });

        buttonCekOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = Objects.requireNonNull(etOTP.getText()).toString();

                if (otp.isEmpty()){
                    Toast.makeText(Lupa_Password.this, "Harap isi field!", Toast.LENGTH_SHORT).show();
                }else {
                    if(otp.equals(generatedOTP)){
                        Toast.makeText(Lupa_Password.this, "Kode OTP valid", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Lupa_Password.this, activity_login_lupa_password2.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Lupa_Password.this, "Kode OTP tidak valid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void sendOTP(String email){
        OTPGenerator otpGenerator = new OTPGenerator();
        generatedOTP = otpGenerator.generateOTP(6);
        EmailSender.sendEmail(email, generatedOTP);
        Toast.makeText(this, "OTP berhasil dikirim ke email", Toast.LENGTH_SHORT).show();
    }
}