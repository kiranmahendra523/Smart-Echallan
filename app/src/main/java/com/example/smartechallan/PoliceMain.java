package com.example.smartechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartechallan.Models.Police;
import com.example.smartechallan.databinding.ActivityPoliceMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PoliceMain extends AppCompatActivity {

    private TextView tv;
    ActivityPoliceMainBinding binding;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private ProgressDialog progressDialog;
    TextInputLayout policename,policeid,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPoliceMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        progressDialog = new ProgressDialog(PoliceMain.this);
        progressDialog.setTitle("Creating Police ID");
        progressDialog.setMessage("In process");
        tv = findViewById(R.id.nav_to_signup_tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PoliceMain.this,Policelogin.class));
            }
        });

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        binding.signUpBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateOfficerName()||!validateOfficerUserId()||!validatePassword()){
                   return;
                }
                progressDialog.show();
                auth.createUserWithEmailAndPassword
                        (binding.officerUserIdSignup.getEditText().getText().toString(),binding.officerPasswordSignup.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Police pl = new Police(binding.officerUserIdSignup.getEditText().getText().toString(),binding.officerNameSignup.getEditText().getText().toString(),binding.officerPasswordSignup.getEditText().getText().toString());
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Police").child(id).setValue(pl);
                            Toast.makeText(PoliceMain.this, "ID created Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(PoliceMain.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                startActivity(new Intent(PoliceMain.this,Policelogin.class));
            }
        });
        binding.navToHomeSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PoliceMain.this,MainActivity.class));
            }
        });



    }

    private boolean validateOfficerName(){
        policeid = findViewById(R.id.officer_name_signup);
        String val = policeid.getEditText().getText().toString().trim();
        if(TextUtils.isEmpty(val)){
            policeid.setError("*Field can't be empty");
            return false;
        }
        else if(val.length()>18){
            policeid.setError("*Cant exceed more than 18 characters");
            return false;
        }
        else{
            policeid.setError(null);
            policeid.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateOfficerUserId(){
        policename = findViewById(R.id.officer_user_id_signup);
        String val = policename.getEditText().getText().toString();
        String spaces = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(val)){
           policename.setError("*Field can't be empty");
            return false;
        }
        else if(!val.matches(spaces)){
            policename.setError("*not a proper form mail ID");
            return false;
        }
        else if(val.length()>40){
            policename.setError("*Cant exceed more than 18 characters");
            return false;
        }
        else{
            policename.setError(null);
            policename.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(){
        password = findViewById(R.id.officer_password_signup);
        String val = password.getEditText().getText().toString().trim();
        String check =  "^"+"(?=.*[a-zA-Z])"+ "(?=S+$)"+"$";
        if(TextUtils.isEmpty(val)){
           password.setError("* field can't be empty");
            return false;
        }
       else if(val.length()>15){
           password.setError("*cant exceed 15 char");
           return false;
        }
        else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

}