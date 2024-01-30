package com.example.vokamart.MainFamily;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.vokamart.API.Constant;
import com.example.vokamart.Adapter.SpinnerAdapter;
import com.example.vokamart.Models.kategoriList;
import com.example.vokamart.Models.produk;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UpdateProdukActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<kategoriList> kategoriList;
    private SpinnerAdapter adapter;
    private Button btPick, btnCancel, btnTambah;
    private ImageView iv1, iv2, iv3, iv4, iv5;
    private ImageView[] imageViews;
    private int currentImageIndex = 0;
    private static final int MAX_IMAGES = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Set<String> selectedImageUris = new HashSet<>();
    private Spinner spinner;
    private EditText etNama, etStok, etHarga, etBerat, etDeskripsi, etNamaKtgr;
    private Bitmap bitmap = null;
    private SharedPreferences preferences;
    private Intent intent;
    private produk Produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_tambah_produk);

        // Initialize views
        initializeViews();

        // Fetch data for the spinner
        fetchDataFromServer();

        // Set values from Intent data
        setValuesFromIntent();

        // Set up click listeners
        pickImage();
//        cancelPicture();
    }

    private void initializeViews() {
        kategoriList = new ArrayList<>();
        adapter = new SpinnerAdapter(this, kategoriList);
        btPick = findViewById(R.id.Btn_Pick);
        btnCancel = findViewById(R.id.Btn_Cancel);
        btnTambah = findViewById(R.id.btn_tambah_produk);
        iv1 = findViewById(R.id.img1);
        etBerat = findViewById(R.id.edit_berat);
        etDeskripsi = findViewById(R.id.edit_deskripsi);
        etHarga = findViewById(R.id.edit_harga_produk);
        etNama = findViewById(R.id.edit_nama_produk);
        etStok = findViewById(R.id.edit_stok);
        spinner = findViewById(R.id.spinner_kategori);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        imageViews = new ImageView[]{iv1};
    }


    private void setValuesFromIntent() {
        intent = getIntent();
        if (intent != null) {
            Produk = (produk) intent.getSerializableExtra("data");
            if (Produk != null) {
                etNama.setText(Produk.getNama());
                etHarga.setText(String.valueOf(Produk.getHarga()));
                etBerat.setText(String.valueOf(Produk.getBerat()));
                etDeskripsi.setText(Produk.getDeskripsi_produk());
                etStok.setText(String.valueOf(Produk.getStok()));

                String idProduk = Produk.getId();

                // Set the selected item in the spinner
//                String namaKategori = Produk.getNama_kategori();
//                etNamaKtgr
                String namaKategori = Produk.getNama_kategori();
                setSpinnerSelection(namaKategori);

                // Load the image using Glide
                Glide.with(getApplicationContext())
                        .load(Produk.getMimageUrl())
                        .placeholder(R.drawable.baseline_fastfood_24)
                        .into(iv1);

                btnTambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addData(idProduk);
                    }
                });
            }
        }
    }

    private void setSpinnerSelection(String selectedItem) {
        for (int i = 0; i < kategoriList.size(); i++) {
            if (kategoriList.get(i).getNama_kategori().equals(selectedItem)) {
                Spinner spinner = findViewById(R.id.spinner_kategori);
                spinner.setSelection(i);
                break;
            }
        }
    }

    private void pickImage() {
        btPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });

        // Call cnclpicture separately
        cnclpicture();
    }

    private void opengallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    private void clearImageView(int index) {
        if (index >= 0 && index < imageViews.length) {
            // Load and set the default picture
            imageViews[index].setImageResource(R.drawable.baseline_image_24); // Replace with your default image resource
            imageViews[index].setTag(null); // Remove the tag
        }
    }

    private void cnclpicture() {
        if (btnCancel != null) {
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonClick", "Cancel button clicked");

                    // Reset currentImageIndex to indicate no image is selected
                    currentImageIndex = 0;

                    // Check if imageViews is not null before accessing it
                    if (imageViews != null) {
                        // Clear all ImageViews
                        for (ImageView imageView : imageViews) {
                            if (imageView != null) {
                                imageView.setImageResource(R.drawable.baseline_image_24);
                            }
                        }
                    } else {
                        Log.e("ImageViews", "ImageViews is null");
                    }

                    // Check if selectedImageUris is not null before accessing it
                    if (selectedImageUris != null) {
                        // Clear the set of selected image URIs
                        selectedImageUris.clear();
                    } else {
                        Log.e("SelectedImageUris", "SelectedImageUris is null");
                    }
                }
            });
        } else {
            Log.e("CancelButton", "CancelButton is null");
        }
    }

    private void cancelImageAtIndex(int index) {
        if (index >= 0 && index < imageViews.length) {
            clearImageView(index);

            // Remove the URI from the set of selected image URIs
            String uriString = getSelectedUriAtIndex(index);
            if (uriString != null) {
                selectedImageUris.remove(uriString);
            }
        }
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        // Convert Bitmap to byte array
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            // Encode byte array to Base64 string
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        return "";
    }

    private String getSelectedUriAtIndex(int index) {
        if (index >= 0 && index < imageViews.length) {
            ImageView imageView = imageViews[index];

            // Retrieve the URI from the tag
            Object tag = imageView.getTag();
            if (tag instanceof String) {
                return (String) tag;
            }
        }
        return null;
    }

    private void fetchDataFromServer() {
        String url = "https://vok4mart.000webhostapp.com/Api_mobile/SpinnerPop.php";

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
                                String id = jsonObject.getString("id_kategori");
                                kategoriList dataModel = new kategoriList(name, id);
                                kategoriList.add(dataModel);
                            }

                            // Notify the adapter that the data set has changed
                            adapter.notifyDataSetChanged();
                            setValuesFromIntent(); // Pindahkan ini ke sini agar dijalankan setelah adapter diupdate

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
            String categoryId = selectedCategory.getId_kategori();
            String categoryName = selectedCategory.getNama_kategori();
            Log.d("ItemSelected", "Category selected: " + categoryId);
            // Perform any actions based on the selected category
            Toast.makeText(UpdateProdukActivity.this, "Category selected: " + categoryName, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle nothing selected if needed
        Log.d("ItemSelected", "Nothing selected");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imgUri = data.getData();
            iv1.setImageURI(imgUri);

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
//            Uri imgUri = data.getData();
//            iv1.setImageURI(imgUri);
//
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

//    private void addData(String id_produk) {
//        String url = "https://vok4mart.000webhostapp.com/Api_mobile/update_produk.php";
//        try {
//            Map<String, String> params = createParamsMap(id_produk); // Pass id_produk as a parameter
//            Log.d("PARAMS_MAP", "Params: " + params);
//
//            StringRequest request = new StringRequest(Request.Method.POST, url, this::handleResponse, this::handleError) {
//                @Override
//                protected Map<String, String> getParams() {
//                    return params;
//                }
//            };
//
//            Volley.newRequestQueue(getApplicationContext()).add(request);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            showToast("JSON ERROR");
//        }
//    }

    private void addData(String id_produk) {
        String url = "https://vok4mart.000webhostapp.com/Api_mobile/update_produk.php";
        try {
            Map<String, String> params = createParamsMap(id_produk); // Pass id_produk as a parameter
            Log.d("PARAMS_MAP", "Params: " + params);

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    handleResponse(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    handleError(error);
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }
            };

            Volley.newRequestQueue(getApplicationContext()).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("JSON ERROR");
        }
    }

    private Map<String, String> createParamsMap(String id_produk) throws JSONException {
        Map<String, String> params = new HashMap<>();

        // Log values
        logValue("Harga", etHarga);
        logValue("Stok", etStok);
        logValue("Berat", etBerat);

        params.put("id_produk", id_produk); // Use the provided id_produk parameter
        params.put("nama_produk", getValue(etNama));
        params.put("harga_produk", String.valueOf(parseIntValue(etHarga)));
        params.put("deskripsi_produk", getValue(etDeskripsi));
        params.put("stok", String.valueOf(parseIntValue(etStok)));
        params.put("berat", String.valueOf(parseIntValue(etBerat)));
        if (bitmap != null) {
            params.put("gbr_produk", bitmapToBase64(bitmap));
        }

        kategoriList selectedItem = (kategoriList) spinner.getSelectedItem();
        if (selectedItem != null) {
            params.put("id_kategori", selectedItem.getId_kategori());
        }

        return params;
    }

    private void handleResponse(String response) {
        try {
            if (!TextUtils.isEmpty(response)) {
                JSONObject jsonResponse = new JSONObject(response);
                int code = jsonResponse.getInt("code");
                String status = jsonResponse.getString("status");

                showToast((code == 201 && status.equals("Tambah Produk berhasil")) ? "Produk Ditambahkan" : status);
            } else {
                showToast("Invalid server response");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            showToast("JSON ERROR");
        }
    }

    private void handleError(VolleyError error) {
        error.printStackTrace();
        Log.e("AddDataError", "Volley Error: " + error.toString());

        if (error.networkResponse != null && error.networkResponse.data != null) {
            try {
                String errorResponse = new String(error.networkResponse.data);
                JSONObject jsonResponse = new JSONObject(errorResponse);

                if (jsonResponse != null && jsonResponse.length() > 0) {
                    int code = jsonResponse.getInt("code");
                    String status = jsonResponse.getString("status");

                    showToast("Error: " + status);
                } else {
                    showToast("Invalid server response");
                }
            } catch (JSONException e) {
                e.printStackTrace();
                showToast("Invalid server response");
            }
        } else {
            showToast("Network Error");
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void logValue(String label, EditText editText) {
        Log.d("VALUES", label + ": " + getValue(editText));
    }

    private String getValue(EditText editText) {
        return editText.getText().toString().trim();
    }

    private int parseIntValue(EditText editText) {
        try {
            return Integer.parseInt(getValue(editText));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0; // or handle the default value accordingly
        }
    }
}

