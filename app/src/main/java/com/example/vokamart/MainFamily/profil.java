package com.example.vokamart.MainFamily;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.vokamart.R;


public class profil extends Fragment {

    TextView txt_email, txt_nama;
    private View view;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_profil, container, false);

        initprofile();

        return view;
    }

    private void initprofile() {
        txt_nama = view.findViewById(R.id.nama_profil);
        txt_email = view.findViewById(R.id.email_profil);
        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String nameProfil = preferences.getString("nama", "-");
        txt_nama.setText(nameProfil);

        String emailProfil = preferences.getString("email", "-");
        txt_email.setText(emailProfil);
    }
}