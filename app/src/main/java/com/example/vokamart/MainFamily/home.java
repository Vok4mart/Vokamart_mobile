package com.example.vokamart.MainFamily;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vokamart.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class home extends Fragment {
    private TextView txtName;
    private View view;
    private SharedPreferences preferences;
    private BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_home, container, false);

        init();
        new FetchDataTask().execute(); // Ambil data dari API

        return view;
    }

    private void init() {
        txtName = view.findViewById(R.id.txt_name_user);
        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String nameLogin = preferences.getString("nama", "-");
        txtName.setText(nameLogin);

        barChart = view.findViewById(R.id.barchart);

        // Set layout parameters to make the BarChart smaller
//        ViewGroup.LayoutParams params = barChart.getLayoutParams();
//        params.width = 600;  // Set your desired width
//        params.height = 400; // Set your desired height
//        barChart.setLayoutParams(params);
//;
        barChart.getAxisRight().setDrawLabels(false);
    }

    private class FetchDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://vok4mart.000webhostapp.com/Api_mobile/DashboardApi.php"); // Ganti dengan URL API PHP Anda
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
                connection.disconnect();
                return result.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonData) {
            super.onPostExecute(jsonData);
            if (jsonData != null) {
                createBarChart(jsonData);
            }
        }
    }

    private void createBarChart(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            List<String> xValues = new ArrayList<>();
            ArrayList<BarEntry> entries = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String month = jsonObject.getString("bulan");
                float value = (float) jsonObject.getDouble("total_pemasukan");
                entries.add(new BarEntry(i, value));
                xValues.add(month);
            }

            YAxis yAxis = barChart.getAxisLeft();
            yAxis.setAxisMinimum(0f);
//             yAxis.setAxisMaximum(100f); // Dibatalkan agar nilai maksimum disesuaikan dengan total pemasukan
            yAxis.setAxisLineWidth(2f);
            yAxis.setAxisLineColor(android.R.color.black);
            yAxis.setLabelCount(10);

            BarDataSet dataSet = new BarDataSet(entries, "Total Pemasukan");
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            BarData barData = new BarData(dataSet);
            barChart.setData(barData);

            barChart.getDescription().setEnabled(false);
            barChart.invalidate();

            // Set X-axis values
            barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
            barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            barChart.getXAxis().setGranularity(1f);
            barChart.getXAxis().setGranularityEnabled(true);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}