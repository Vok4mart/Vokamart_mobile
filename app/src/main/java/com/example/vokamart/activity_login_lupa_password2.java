package com.example.vokamart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.LoginFamily.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class    activity_login_lupa_password2 extends AppCompatActivity {

    private EditText etPass;
    private Button btnReset;
    String fetchEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_lupa_password2);

        etPass = findViewById(R.id.Edit_Pass_new);
        btnReset = findViewById(R.id.btn_Reset_Pass);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        fetchEmail = preferences.getString("emailAkun", null);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = etPass.getText().toString().trim();
                sendPasswordToServer(pass, fetchEmail);
                if (pass != null) {
                    Log.d("pass", pass);
                } else {
                    Log.d("pass", "pass is null");
                }

                if (fetchEmail != null) {
                    Log.d("fetch", fetchEmail);
                } else {
                    Log.d("fetch", "fetchEmail is null");
                }
            }
        });
    }

//    private void updatePasswordRequest(String newPassword, String fetchEmail) {
//        String url = "https://vok4mart.000webhostapp.com/updatePass.php";
//        JSONObject jsonRequest = new JSONObject();
//        try {
//            jsonRequest.put("email_akun", fetchEmail);
//            jsonRequest.put("password", newPassword);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.POST,
//                url,
//                jsonRequest,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            int code = response.getInt("code");
//                            String status = response.getString("status");
//
//                            Toast.makeText(activity_login_lupa_password2.this, status, Toast.LENGTH_SHORT).show();
//
//                            if (code == 200) {
//                                Toast.makeText(activity_login_lupa_password2.this, "Password berhasil direset", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(activity_login_lupa_password2.this, status, Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(activity_login_lupa_password2.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        // Add the request to the RequestQueue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }

    private void sendPasswordToServer(String newPassword, String fetchEmail) {
        String url = "https://vok4mart.000webhostapp.com/Api_mobile/updatePass.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Intent intent = new Intent(activity_login_lupa_password2.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Masukkan parameter ke dalam body permintaan POST
                Map<String, String> params = new HashMap<>();
                if (newPassword != null) {
                    params.put("password", newPassword);
                }

                if (fetchEmail != null) {
                    params.put("email_akun", fetchEmail);
                }
                return params;
            }
        };

        // Tambahkan request ke dalam queue Volley
        Volley.newRequestQueue(activity_login_lupa_password2.this).add(stringRequest);
    }
}