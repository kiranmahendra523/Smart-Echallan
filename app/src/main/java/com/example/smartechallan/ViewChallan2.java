package com.example.smartechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.smartechallan.Models.UpdateChallan;
import com.example.smartechallan.databinding.ActivityViewChallan2Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewChallan2 extends AppCompatActivity {
    ActivityViewChallan2Binding binding;
    RecyclerView recyclerView;
    DatabaseReference reference,ref;
    ArrayList<UpdateChallan> list;

    ViewAdapter viewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewChallan2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.challanlist;
        Intent intent = getIntent();
        String num = intent.getStringExtra("KEY_BUFFER");
        reference = (DatabaseReference) FirebaseDatabase.getInstance().getReference("UpdateChallan");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        viewAdapter = new ViewAdapter(this,list);
        recyclerView.setAdapter(viewAdapter);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        if(dataSnapshot.child("vehicle_num").getValue().toString().equalsIgnoreCase(num)){
                            UpdateChallan updateChallan = dataSnapshot.getValue(UpdateChallan.class);
                            updateChallan.getVehicle_num(dataSnapshot.getKey());
                            list.add(updateChallan);
                        }
                }
                viewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}