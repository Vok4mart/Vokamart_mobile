package com.example.vokamart.DetailActivity;



import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vokamart.Models.MPerluDikirim;
import com.example.vokamart.R;

public class DetailPesananPerluDikirim extends AppCompatActivity {

    Intent intent;
    private MPerluDikirim PerluDikirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_perlu_dikirim);

        intent = getIntent();
        PerluDikirim = (MPerluDikirim) intent.getSerializableExtra("dataPesananPerluDikirim");
    }
}
