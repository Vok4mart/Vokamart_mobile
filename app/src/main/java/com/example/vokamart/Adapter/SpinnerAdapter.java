package com.example.vokamart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.vokamart.Models.kategoriList;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<kategoriList> {

    public SpinnerAdapter(Context context, ArrayList<kategoriList> data) {
        super(context, android.R.layout.simple_spinner_item, data);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        // Inflate the view if it's not recycled
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        // Get the data item for this position
        kategoriList item = getItem(position);

        // Set the item's name as the text for the spinner item
        TextView textView = convertView.findViewById(android.R.id.text1);
        if (item != null) {
            textView.setText(item.getNama_kategori());
        }

        return convertView;
    }
}