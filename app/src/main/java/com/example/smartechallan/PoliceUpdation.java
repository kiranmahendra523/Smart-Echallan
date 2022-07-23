package com.example.smartechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smartechallan.Models.UpdateChallan;
import com.example.smartechallan.databinding.ActivityPoliceUpdationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PoliceUpdation extends AppCompatActivity {

    ActivityPoliceUpdationBinding binding;
    Spinner sp;
    String vehicle_num,type_of_violation,challan_Time,challan_date,challan_amount;
    TextInputLayout vehm,amt,tm,dat;
    EditText vltn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayAdapter<CharSequence> voil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPoliceUpdationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp=binding.spinner;
        voil=ArrayAdapter.createFromResource(this,R.array.array_voil,R.layout.voilation_layout);
        voil.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sp.setAdapter(voil);
        auth=FirebaseAuth.getInstance();
        this.setTitle("Update Challan");
        binding.upadtechallansubmitbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!vehvaliadate()|!amountvaliadte()|!timevalidate()|!datevalidate()){
                    return;
                }

                vehicle_num = binding.vehiclenumber.getEditText().getText().toString();
                type_of_violation = binding.spinner.getSelectedItem().toString();
                challan_Time = binding.timetf.getEditText().getText().toString();
                challan_date = binding.datetf.getEditText().getText().toString();
                challan_amount = binding.amounttv.getEditText().getText().toString();
                if(!vehicle_num.isEmpty()&&!type_of_violation.isEmpty()&&!challan_Time.isEmpty()&&!challan_date.isEmpty()&&!challan_amount.isEmpty()){
                    UpdateChallan updateChallan = new UpdateChallan(vehicle_num,type_of_violation,challan_Time,challan_date,challan_amount);
                    database= FirebaseDatabase.getInstance();
                   databaseReference = database.getReference("UpdateChallan");
                   databaseReference.child(vehicle_num).setValue(updateChallan).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           String val = "Select your option";
                           ArrayAdapter ad = (ArrayAdapter) sp.getAdapter();
                           int pos = ad.getPosition(val);
                           sp.setSelection(pos);
                           binding.vehiclenumber.getEditText().setText("");
                           binding.timetf.getEditText().setText("");
                           binding.datetf.getEditText().setText("");
                           binding.amounttv.getEditText().setText("");
                           Toast.makeText(PoliceUpdation.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                       }
                   });
                }
            }
        });
        binding.back23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PoliceUpdation.this,policeTemplate.class));
            }
        });

    }
    private boolean vehvaliadate(){
        String val = binding.vehiclenumber.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.vehiclenumber.setError("this field can't empty");
            return false;
        }
        else if(val.length()!=4){
            binding.vehiclenumber.setError("Invalid vehicle number");
            return false;
        }
        else{
            binding.vehiclenumber.setError(null);
            binding.vehiclenumber.setErrorEnabled(false);
            return true;
        }
    }
    private boolean timevalidate(){
        String val = binding.timetf.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.timetf.setError("this field can't empty");
            return false;
        }else{
            binding.timetf.setError(null);
            binding.timetf.setErrorEnabled(false);
            return true;
        }
    }
    private boolean datevalidate(){
        String val = binding.datetf.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.datetf.setError("this field can't be empty");
            return false;
        }
        else{
            binding.datetf.setError(null);
            binding.datetf.setErrorEnabled(false);
            return true;
        }
    }
    private boolean amountvaliadte(){
        String val = binding.amounttv.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.amounttv.setError("this field can't be empty");
            return false;
        }
        else{
            binding.amounttv.setError(null);
            binding.amounttv.setErrorEnabled(false);
            return true;
        }

    }
}