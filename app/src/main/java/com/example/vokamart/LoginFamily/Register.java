package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.vokamart.API.Constant;
import com.example.vokamart.R;

import java.util.HashMap;
import java.util.Map;

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

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
            }
        });

    }


    private void register() {
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("email_akun", etEmail.getText().toString().trim());
            jsonRequest.put("password", etPassword.getText().toString().trim());
            jsonRequest.put("nama_akun", etUsername.getText().toString().trim()); // Add user name
            jsonRequest.put("user_level", "1"); // Set user level as needed
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Constant.REGIST, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int code = response.getInt("code");
                            String status = response.getString("status");

                            if (code == 201 && status.equals("User Registered")) {
                                // Registration successful
                                Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                            } else if (code == 406 && status.equals("User has been registered!")) {
                                // User already registered
                                Toast.makeText(getApplicationContext(), "User already registered.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle other status codes or errors
                                Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }



}