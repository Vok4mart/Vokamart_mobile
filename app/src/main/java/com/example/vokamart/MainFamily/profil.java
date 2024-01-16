package com.example.vokamart.MainFamily;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.vokamart.DetailActivity.DetailProduk;
import com.example.vokamart.LoginFamily.Login;
import com.example.vokamart.R;
import com.example.vokamart.UbahProfil;

import java.util.HashMap;
import java.util.Map;


public class profil extends Fragment {

    public TextView txt_email, txt_nama;
    private View view;
    private SharedPreferences preferences;
    ImageView foto_profil;
    Button btnUbah, btnSimpan, btnHapusAkun;
    String idAkun;

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
        btnHapusAkun = view.findViewById(R.id.hapus_akun);
        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        idAkun = preferences.getString("id", "-");

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

        btnHapusAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDeleteAcc();
            }
        });
    }

    private void ConfirmDeleteAcc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Konfirmasi Hapus Akun");
        builder.setMessage("Apakah anda yakin ingin menghapus akun?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteAccount(idAkun);
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteAccount(String id){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://vok4mart.000webhostapp.com/ApiHapusAkun.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "Akun berhasil dihapus", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            // Override getParams() to include parameters in the request
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_akun", id);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}