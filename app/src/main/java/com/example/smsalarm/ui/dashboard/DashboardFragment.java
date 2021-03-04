package com.example.smsalarm.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smsalarm.R;
import com.google.android.material.textfield.TextInputEditText;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button b = root.findViewById(R.id.save);
        b.setOnClickListener(v -> {
            TextInputEditText e = root.findViewById(R.id.keyword);
            //e.setText("ok");

            SharedPreferences sharedPref =getContext().getSharedPreferences("fname",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("saved_high_score_key", String.valueOf(e.getText()));
            editor.apply();





        });
        setConfigs(root);
        return root;
    }
    public void setConfigs(View root){
        SharedPreferences sharedPref =getContext().getSharedPreferences("fname",Context.MODE_PRIVATE);

        String configValue = sharedPref.getString("saved_high_score_key", "SICUREZZAVIOLATA");


        TextInputEditText b = root.findViewById(R.id.keyword);
        b.setText(configValue);
    }
}