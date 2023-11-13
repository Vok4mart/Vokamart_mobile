package com.example.vokamart.MainFamily;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class home extends Fragment {
    private TextView txt_name;
    private View view;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_home, container, false);

        init();

        barchart();

        return view;
    }

    private void init() {
        txt_name = view.findViewById(R.id.txt_name_user);
        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String nameLogin = preferences.getString("nama", "-");
        txt_name.setText(nameLogin);
    }

    private void barchart() {

        BarChart barChart;

        List<String> xValues = Arrays.asList("Desember", "Januari", "Februari", "Maret");

        barChart = view.findViewById(R.id.barchart);
        barChart.getAxisRight().setDrawLabels(false);

        ArrayList <BarEntry> Entries = new ArrayList<>();
        Entries.add(new BarEntry(0,45f));
        Entries.add(new BarEntry(1,80f));
        Entries.add(new BarEntry(2,65f));
        Entries.add(new BarEntry(3,38f));
        Entries.add(new BarEntry(0,45f));
        Entries.add(new BarEntry(1,80f));
        Entries.add(new BarEntry(2,65f));
        Entries.add(new BarEntry(3,38f));
        Entries.add(new BarEntry(0,45f));
        Entries.add(new BarEntry(1,80f));
        Entries.add(new BarEntry(2,65f));
        Entries.add(new BarEntry(3,38f));

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaximum(0f);
        yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(android.R.color.black);
        yAxis.setLabelCount(10);

        BarDataSet dataSet = new BarDataSet(Entries, "Subjects");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        barChart.getXAxis().setValueFormatter( new IndexAxisValueFormatter(xValues));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setGranularity(1f);
        barChart.getXAxis().setGranularityEnabled(true);

    }
}