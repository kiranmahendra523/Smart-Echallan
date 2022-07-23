package com.example.smartechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartechallan.databinding.ActivityCheckTheftDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CheckTheftData extends AppCompatActivity {

    ActivityCheckTheftDataBinding binding;
    DatabaseReference reference;
    TextInputLayout vehm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Find your Vehicle");
        binding = ActivityCheckTheftDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String veh = binding.theftvehnum.getEditText().getText().toString();
                if(!veh.isEmpty()){
                    readData(veh);
                }
                else {
                    Toast.makeText(CheckTheftData.this, "Please Enter Vehicle Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void readData(String veh){
        reference = FirebaseDatabase.getInstance().getReference("Theft Data");
        reference.child(veh).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if (task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String vehilcenum = String.valueOf(dataSnapshot.child("vehilnun").getValue());
                        String stat = String.valueOf(dataSnapshot.child("stations").getValue());
                        binding.vehnumretrun.setText(vehilcenum);
                        binding.pset.setText(stat);
                    }
                    else{
                        Toast.makeText(CheckTheftData.this, "No Record", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(CheckTheftData.this, "Failed to read Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.imageViewbackarraow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckTheftData.this,UserMain.class));
            }
        });
    }


}