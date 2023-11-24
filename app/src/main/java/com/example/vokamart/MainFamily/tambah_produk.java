package com.example.vokamart.MainFamily;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vokamart.Adapter.SpinnerAdapter;
import com.example.vokamart.Models.kategoriList;
import com.example.vokamart.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class tambah_produk extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<kategoriList> kategoriList;
    private SpinnerAdapter adapter;
    private Button btPick, btnCancel;
    private ImageView iv1, iv2, iv3, iv4, iv5;
    private ImageView[] imageViews;
    private int currentImageIndex = 0;
    private static final int MAX_IMAGES = 5;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Set<String> selectedImageUris = new HashSet<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk_tambah_produk);

        kategoriList = new ArrayList<>();
        Spinner spinner = findViewById(R.id.spinner_kategori);
        TextView textView = findViewById(R.id.text_tambah_variasi);
        btPick = findViewById(R.id.Btn_Pick);
        btnCancel = findViewById(R.id.Btn_Cancel);
        iv1 = findViewById(R.id.img1);
        iv2 = findViewById(R.id.img2);
        iv3 = findViewById(R.id.img3);
        iv4 = findViewById(R.id.img4);
        iv5 = findViewById(R.id.img5);
        imageViews = new ImageView[]{iv1, iv2, iv3, iv4, iv5};

        adapter = new SpinnerAdapter(this, kategoriList);

        fetchDataFromServer();
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); // Set the listener for item selection

        pickImage();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on the TextView if needed
            }
        });
    }

    private void pickImage() {

        btPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
                cnclpicture();
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

    private void opengallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                // Handle multiple images
                ClipData clipData = data.getClipData();
                int itemCount = Math.min(clipData.getItemCount(), MAX_IMAGES - currentImageIndex);

                for (int i = 0; i < itemCount; i++) {
                    handleImageSelection(clipData.getItemAt(i).getUri());
                }
            } else if (data.getData() != null) {
                // Handle single image
                handleImageSelection(data.getData());
            }
        }
    }

    private void handleImageSelection(android.net.Uri imageUri) {
        try {
            // Check if the image is already selected
            String uriString = imageUri.toString();
            if (!selectedImageUris.contains(uriString)) {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Set the selected image to the current ImageView
                if (currentImageIndex < MAX_IMAGES) {
                    imageViews[currentImageIndex].setImageBitmap(bitmap);

                    // Add the URI to the set of selected image URIs
                    selectedImageUris.add(uriString);
                    imageViews[currentImageIndex].setTag(uriString); // Tag the ImageView with the URI

                    currentImageIndex++;
                } else {
                    Toast.makeText(this, "You can select up to " + MAX_IMAGES + " images", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Image already selected", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ImageSelection", "Error loading image: " + e.getMessage());
        }
    }

    private void clearImageView(int index) {
        if (index >= 0 && index < imageViews.length) {
            // Load and set the default picture
            imageViews[index].setImageResource(R.drawable.baseline_image_24); // Replace with your default image resource
            imageViews[index].setTag(null); // Remove the tag
        }
    }

    private void cnclpicture() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset currentImageIndex to indicate no image is selected
                currentImageIndex = 0;

                // Clear all ImageViews
                for(ImageView imageView : imageViews) {
                    imageView.setImageResource(R.drawable.baseline_image_24);
                }

                // Clear the set of selected image URIs
                selectedImageUris.clear();

            }
        });
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
}
