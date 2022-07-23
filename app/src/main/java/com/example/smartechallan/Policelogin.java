package com.example.smartechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.smartechallan.databinding.ActivityPoliceLoginBinding;
import com.example.smartechallan.databinding.ActivityPoliceTemplateBinding;
import com.example.smartechallan.databinding.ActivityPoliceUpdationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Policelogin extends AppCompatActivity {
    ActivityPoliceLoginBinding binding;
    ProgressDialog progressDialog;
    TextInputLayout userid,password;
    FirebaseAuth auth;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setTitle("Login");
        progressDialog = new ProgressDialog(Policelogin.this);
        progressDialog.setTitle("we are Logging you in");
        progressDialog.setMessage("In process");
        binding = ActivityPoliceLoginBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        setContentView(binding.getRoot());
        bt = (Button)findViewById(R.id.signin_bt);

       bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!uservalidate()|!passwordvalidate()){
                    return;
                }
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.officerUserIdSignin.getEditText().getText().toString(),binding.officerPasswordSignin.getEditText().getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){
                                    Intent intent=new Intent(Policelogin.this, policeTemplate.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Policelogin.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        binding.navToHomeSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Policelogin.this,MainActivity.class));
            }
        });
      binding.navToSignupTv.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              startActivity(new Intent(Policelogin.this,PoliceMain.class));
          }
      });

    }
    private boolean uservalidate(){
        String val = binding.officerUserIdSignin.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.officerUserIdSignin.setError("*this field cant be empty");
            return false;
        }
        else{
            binding.officerUserIdSignin.setError(null);
            binding.officerUserIdSignin.setErrorEnabled(false);
            return true;
        }
    }
    private boolean passwordvalidate(){
        String val = binding.officerPasswordSignin.getEditText().getText().toString();
        if(val.isEmpty()){
            binding.officerPasswordSignin.setError("*this field cant be empty");
            return false;
        }
        else{
            binding.officerPasswordSignin.setError(null);
            binding.officerPasswordSignin.setErrorEnabled(false);
            return true;
        }
    }


}