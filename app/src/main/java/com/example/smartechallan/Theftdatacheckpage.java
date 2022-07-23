package com.example.smartechallan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartechallan.databinding.ActivityTheftdatacheckpageBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Theftdatacheckpage extends AppCompatActivity {
    ActivityTheftdatacheckpageBinding binding;
    TextInputLayout vehnum1;
    TextInputLayout vehm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityTheftdatacheckpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vehnum1=binding.vhnumvich;
        binding.buttonview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!validateVehicleNumber()){
                    return;
                }
                Intent intent = new Intent(Theftdatacheckpage.this,ViewChallan2.class);
                intent.putExtra("KEY_BUFFER",vehnum1.getEditText().getText().toString());
                startActivity(intent);
            }
        });
        binding.backnavbarw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Theftdatacheckpage.this,UserMain.class));
            }
        });
    }
    private boolean validateVehicleNumber(){
        String val = binding.vhnumvich.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.vhnumvich.setError("Field can't be empty ");
            return false;
        }
        else if(val.length()!=4){
            binding.vhnumvich.setError("Invalid vehicle number");
            return false;
        }
        else{
            binding.vhnumvich.setError(null);
            binding.vhnumvich.setErrorEnabled(false);
            return true;
        }
    }


}