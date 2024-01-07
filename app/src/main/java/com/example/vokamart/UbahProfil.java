package com.example.vokamart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.databinding.ActivityMainUbahProfilBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class UbahProfil extends AppCompatActivity {

    private EditText etEmail, etNama;
    private RoundedImageView Profil;
    private Bitmap imageBitmap;
    private Button btnUbahProfil;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ubah_profil);

        Profil = findViewById(R.id.img_profil);
        btnUbahProfil = findViewById(R.id.ubah_profil);
        etNama = findViewById(R.id.nama_profil);
        etEmail = findViewById(R.id.email_profil);

        Profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(UbahProfil.this)
                        .galleryOnly()
                        .cropSquare()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });

        initUpProfile();
    }

    private void initUpProfile() {
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String emailProfil = preferences.getString("email", "-");
        etEmail.setText(emailProfil);

        String nameProfil = preferences.getString("nama", "-");
        etNama.setText(nameProfil);

        String id = preferences.getString("id", "-");

        btnUbahProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfil(id);
            }
        });
    }

    private void EditProfil(String id) {
        RequestQueue queue = Volley.newRequestQueue(UbahProfil.this);
        String url = "https://vok4mart.000webhostapp.com/editProfil.php";  // Set your URL here

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(UbahProfil.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                Toast.makeText(UbahProfil.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = imageToString(imageBitmap);
                String nama = etNama.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                Map<String, String> params = new HashMap<>();
                params.put("id_akun", id);
                params.put("foto_profil", image);
                params.put("email_akun", email);
                params.put("nama_akun", nama);

                return params;
            }
        };

        // Add the request to the request queue
        queue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            Profil.setImageURI(uri);

            // Konversi Uri ke Bitmap menggunakan ImagePicker
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString(Bitmap imageBitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }
}