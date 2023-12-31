package com.example.vokamart.MainFamily;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vokamart.R;
import com.example.vokamart.UbahProfil;


public class profil extends Fragment {

    TextView txt_email, txt_nama;
    private View view;
    private SharedPreferences preferences;
    ImageView foto_profil;
    Button btnUbah, btnSimpan;

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
        btnUbah = view.findViewById(R.id.button_ubah_profil);
        foto_profil = view.findViewById(R.id.profil);
        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String nameProfil = preferences.getString("nama", "-");
        txt_nama.setText(nameProfil);

        String emailProfil = preferences.getString("email", "-");
        txt_email.setText(emailProfil);

        String foto = preferences.getString("foto", "-");
        Glide.with(this)
                        .load(foto)
                                .placeholder(R.drawable.baseline_image_24)
                                        .into(foto_profil);


        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), UbahProfil.class);
                startActivity(i);
            }
        });
    }
}