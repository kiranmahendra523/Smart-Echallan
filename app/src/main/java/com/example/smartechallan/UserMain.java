package com.example.smartechallan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.smartechallan.databinding.ActivityUserMainBinding;
import com.example.smartechallan.ui.home.HomeFragment;


public class UserMain extends AppCompatActivity {
    ActivityUserMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        binding = ActivityUserMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        this.setTitle("User Main");
        binding.viewChallan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMain.this,Theftdatacheckpage.class));
            }
        });
        binding.theftUserDataFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMain.this,CheckTheftData.class));
            }
        });
        binding.arrowbacktomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMain.this,MainActivity.class));
            }
        });
        binding.trafficPrediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMain.this,Predict_traffic_2.class));
            }
        });
    }
}