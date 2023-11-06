package com.example.vokamart.LoginFamily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vokamart.apiResponse;
import com.example.vokamart.retrofit_client;
import com.example.vokamart.userpostrequest;
import com.example.vokamart.MainFamily.home;
import com.example.vokamart.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etEmail = findViewById(R.id.edit_Email_Login);
        EditText etPassword = findViewById(R.id.Edit_Pass_Login);
        Button btnlogin = findViewById(R.id.btn_Login);
        TextView text = findViewById(R.id.text_disini_login);
        TextView text2 = findViewById(R.id.Lupa_Pass_Text);

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Lupa_Password.class);
                startActivity(i);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                createPost(email,password);

            }
        });


    }

    private void createPost(String email, String password) {

        retrofit_client.INSTANCE.getInstance().post(new userpostrequest(email,password)).enqueue(new Callback<apiResponse>() {
            @Override
            public void onResponse(Call<apiResponse> call, Response<apiResponse> response) {
                if(response.isSuccessful()){
                    apiResponse apiResponse = response.body();
                    if(apiResponse != null && apiResponse.getStatus().equals("Sukses")){

                        System.out.println("Request success. Response: " + email);
                        System.out.println("Request success. Response: " + password);

                        Intent i = new Intent(Login.this, home.class);
                        startActivity(i);

                    } else {
                        System.out.println("Unexpected response: " + apiResponse);
                    }
                } else {
                    System.out.println("Server error, Status Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<apiResponse> call, Throwable t) {
                System.out.println("Request Failed: " + t.getMessage());

            }
        });
    }



}
