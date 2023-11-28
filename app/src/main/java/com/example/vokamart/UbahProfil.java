package com.example.vokamart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class UbahProfil extends AppCompatActivity {

    private EditText etEmail, etNama;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ubah_profil);

        initUpProfile();
    }

    private void initUpProfile() {
        etNama = findViewById(R.id.nama_profil);
        etEmail = findViewById(R.id.email_profil);

        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String nameProfil = preferences.getString("email", "-");
        etEmail.setText(nameProfil);

        String emailProfil = preferences.getString("nama", "-");
        etNama.setText(emailProfil);
    }


}