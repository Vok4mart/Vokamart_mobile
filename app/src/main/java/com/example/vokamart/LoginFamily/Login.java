package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.API.Constant;
import com.example.vokamart.Navbar;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {
    private TextView text, text2;
    private EditText etEmail, etPassword;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {
        etEmail = findViewById(R.id.edit_Email_Login);
        etPassword = findViewById(R.id.Edit_Pass_Login);
        btnlogin = findViewById(R.id.btn_Login);
        text = findViewById(R.id.text_disini_login);
        text2 = findViewById(R.id.Lupa_Pass_Text);

        text2.setOnClickListener(v-> {
            Intent i = new Intent(Login.this, Lupa_Password.class);
            startActivity(i);
        });

        text.setOnClickListener(v-> {
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
        });

        btnlogin.setOnClickListener(v-> {
            if (validate()) {
                login();
            }
        });
    }

    private boolean validate() {
        if (etEmail.getText().toString().isEmpty()) {
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            return false;
        }

        if (etPassword.getText().toString().length() < 5) {
            return false;
        }

        return true;
    }

    private void login() {
        StringRequest request = new StringRequest(Request.Method.POST, Constant.LOGIN, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);

                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");

                if (code == 200 && status.equals("Sukses")) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");

                    if (dataArray.length() > 0) {
                        JSONObject res = dataArray.getJSONObject(0);

                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("id", res.getString("id_akun"));
                        editor.putString("email", res.getString("email_akun"));
                        editor.putString("level", res.getString("user_level"));
                        editor.putString("nama", res.getString("nama_akun"));
                        editor.apply();

                        startActivity(new Intent(Login.this, Navbar.class));
                        finish();

                        Toast.makeText(getApplicationContext(), "Login Sukses!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No data found in the response.", Toast.LENGTH_SHORT).show();
                    }
                } else if (code == 404 && !status.equals("Sukses")) {
                    Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email_akun", etEmail.getText().toString().trim());
                map.put("password", etPassword.getText().toString().trim());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


}
