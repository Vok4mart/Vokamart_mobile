package com.example.vokamart.MainFamily;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.SpinnerAdapter;
import com.example.vokamart.Models.kategoriList;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tambah_produk extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<kategoriList> kategoriList;
    private SpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_tambah_produk);

        kategoriList = new ArrayList<>();
        Spinner spinner = findViewById(R.id.spinner_kategori);
        adapter = new SpinnerAdapter(this, kategoriList);

        TextView textView = findViewById(R.id.text_tambah_variasi);

        fetchDataFromServer();

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); // Set the listener for item selection

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on the TextView if needed
            }
        });
    }

    private void fetchDataFromServer() {
        String url = "https://vok4mart.000webhostapp.com/SpinnerPop.php";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parse the JSON response and add data to the list
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray kategoriArray = data.getJSONArray("kategori_produk");

                            for (int i = 0; i < kategoriArray.length(); i++) {
                                JSONObject jsonObject = kategoriArray.getJSONObject(i);
                                String name = jsonObject.getString("nama_kategori");
                                kategoriList dataModel = new kategoriList(name);
                                kategoriList.add(dataModel);
                            }

                            // Notify the adapter that the data set has changed
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Log.e("FetchData", "Error fetching data: " + error.getMessage());
                    }
                }
        );


        queue.add(jsonObjectRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Handle item selection if needed
        kategoriList selectedCategory = (kategoriList) parent.getItemAtPosition(position);
        if (selectedCategory != null) {
            String categoryName = selectedCategory.getNama_kategori();
            Log.d("ItemSelected", "Category selected: " + categoryName);
            // Perform any actions based on the selected category
            Toast.makeText(tambah_produk.this, "Category selected: " + categoryName, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle nothing selected if needed
        Log.d("ItemSelected", "Nothing selected");
    }
}
