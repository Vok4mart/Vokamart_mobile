package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.EmailSender;
import com.example.vokamart.OTPGenerator;
import com.example.vokamart.R;
import com.example.vokamart.activity_login_lupa_password2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Lupa_Password extends AppCompatActivity {

    EditText etEmail, etOTP, etNewPass;

    Button buttonOTP, buttonCekOTP;

    String generatedOTP, email;

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

                email = Objects.requireNonNull(etEmail.getText()).toString();

                if (email.isEmpty()) {
                    Toast.makeText(Lupa_Password.this, "Harap isi field!", Toast.LENGTH_SHORT).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Lupa_Password.this, "Harap masukkan email yang valid", Toast.LENGTH_SHORT).show();
                } else {
                    checkEmailRegistered(email);
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

                        i.putExtra("emailAkun", email);
                        startActivity(i);

                    }else{
                        Toast.makeText(Lupa_Password.this, "Kode OTP tidak valid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void checkEmailRegistered(String email) {
        String url = "https://vok4mart.000webhostapp.com/CheckEmail.php";

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("email_akun", email);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int code = response.getInt("code");
                            String status = response.getString("status");

                            Log.d("Response", "Code: " + code + ", Status: " + status);

                            if (code == 200) {
                                Log.d("babi", email);
                                Toast.makeText(Lupa_Password.this, status, Toast.LENGTH_SHORT).show();

                            } else {
                                sendOTP(email);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Lupa_Password.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void sendOTP(String email){
        OTPGenerator otpGenerator = new OTPGenerator();
        generatedOTP = otpGenerator.generateOTP(6);
        String OTP = generatedOTP;
        EmailSender.sendEmail(email, generatedOTP);
//        Toast.makeText(this, "OTP berhasil dikirim ke email", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, OTP, Toast.LENGTH_SHORT).show();
    }


}