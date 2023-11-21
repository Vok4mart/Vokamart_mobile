package com.example.vokamart.MainFamily;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.API.Constant;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tambah_produk extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<String> kategori;
    private JSONArray result;
    private Spinner spinner;
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_tambah_produk);
        kategori = new ArrayList<String>();
        spinner = findViewById(R.id.spinner_kategori);
        spinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        TextView textView = findViewById(R.id.text_tambah_variasi);
//        textViewName = (TextView) findViewById(R.id.textViewName);
//        textViewCourse = (TextView) findViewById(R.id.textViewCourse);
//        textViewSession = (TextView) findViewById(R.id.textViewSession);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        }

        private void PopSpinner() {

            StringRequest stringRequest = new StringRequest(Constant.DROPPOP, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject j = null;
                    try {
                        j = new JSONObject(response);
                        result = j.getJSONArray("result");
                        getcat(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle error response
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    private void getcat(JSONArray j) {
        for (int i = 0; i < j.length(); i++) {
            try {
                JSONObject json = j.getJSONObject(i);
                kategori.add(json.getString("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<>(tambah_produk.this, android.R.layout.simple_spinner_dropdown_item, kategori));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
