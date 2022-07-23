package com.example.smartechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.smartechallan.databinding.ActivityPoliceTemplateBinding;

public class policeTemplate extends AppCompatActivity {
    ActivityPoliceTemplateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Police");
        super.onCreate(savedInstanceState);
        binding=ActivityPoliceTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.updatechallaniv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(policeTemplate.this,PoliceUpdation.class));
            }
        });
        binding.theftcariv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(policeTemplate.this,UpdateTheftData.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();
        if(item_id==R.id.logout_id){
            startActivity(new Intent(policeTemplate.this,Policelogin.class));
        }
        return true;
    }
}