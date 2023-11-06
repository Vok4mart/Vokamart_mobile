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

public class home extends Fragment {
    private TextView txt_name;
    private View view;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_home, container, false);

        init();

        return view;
    }

    private void init() {
        txt_name = view.findViewById(R.id.txt_name_user);
        preferences = getContext().getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        String nameLogin = preferences.getString("nama", "-");
        txt_name.setText(nameLogin);
    }
}