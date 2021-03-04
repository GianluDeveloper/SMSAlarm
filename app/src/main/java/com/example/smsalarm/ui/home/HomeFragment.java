package com.example.smsalarm.ui.home;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        Button b = root.findViewById(R.id.grantalert);
       if(this.RequestAccessNotificationPolicyPermission(false)==true){
           b.setVisibility(View.GONE);
       }
        b.setOnClickListener(v->{
            this.RequestAccessNotificationPolicyPermission(true);
        });

        return root;
    }
    @TargetApi(Build.VERSION_CODES.N)
    private  boolean RequestAccessNotificationPolicyPermission(boolean doRequest) {
        Activity ac = getActivity();
        NotificationManager notificationManager =
                (NotificationManager) ac.getSystemService(Context.NOTIFICATION_SERVICE);
        if (!notificationManager.isNotificationPolicyAccessGranted()) {
            if(doRequest) {
                Intent intent = new Intent(
                        android.provider.Settings
                                .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                ac.startActivity(intent);
            }
            return false;
        }else{
            return true;
        }
    }
}