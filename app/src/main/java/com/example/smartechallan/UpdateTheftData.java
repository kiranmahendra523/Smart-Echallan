package com.example.smartechallan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartechallan.Models.TheftData;
import com.example.smartechallan.databinding.ActivityUpdateTheftDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateTheftData extends AppCompatActivity {
    private String police_Station;
    private Spinner polices ,stations;
    private TextView tvpolicespinner;
    private ArrayAdapter<CharSequence> psAdapter;
    TextInputLayout vehilnum,phnum,vh,ph;
    ActivityUpdateTheftDataBinding binding;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.setTitle("Theft Vehicle data");
        binding = ActivityUpdateTheftDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        polices = binding.ps;
        psAdapter = ArrayAdapter.createFromResource(this,R.array.array_policestation,R.layout.location_layout);
        psAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        polices.setAdapter(psAdapter);
        polices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                polices = binding.ps;
                police_Station = polices.getSelectedItem().toString();
                int parentId = adapterView.getId();

                if(parentId==binding.ps.getId()){
                    switch(police_Station){
                        case "select your state" : psAdapter = ArrayAdapter.createFromResource(adapterView.getContext(),R.array.array_policestation,R.layout.location_layout);
                        break;
                        default:break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        vehilnum = binding.vehnum;
        phnum = binding.phoonenumber;
        stations = binding.ps;
        binding.submitArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                TheftData theftData = new TheftData(binding.vehnum.getEditText().getText().toString(),binding.phoonenumber.getEditText().getText().toString(),binding.ps.getSelectedItem().toString());
                reference=database.getReference("Theft Data");
                reference.child(vehilnum.getEditText().getText().toString()).setValue(theftData);
                Toast.makeText(UpdateTheftData.this, "Updated Successfully", Toast.LENGTH_SHORT).show();


            }
        });
        binding.nvToTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateTheftData.this,policeTemplate.class));
            }
        });

    }

}