package com.example.smartechallan;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartechallan.Models.UpdateChallan;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewChallan extends AppCompatActivity {
    RecyclerView recyclerView;

    FirebaseDatabase database;
    FirebaseAuth auth;
    ViewChallanAdapter viewChallanAdapter;
    DatabaseReference reference = database.getReference().child("UpdateChallan");
    ArrayList<UpdateChallan> list;
    String vehnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_challan);
        recyclerView = (RecyclerView) findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);

        database = FirebaseDatabase.getInstance();
        FirebaseRecyclerOptions<UpdateChallan> options =
                new FirebaseRecyclerOptions.Builder<UpdateChallan>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("UpdateChallan"), UpdateChallan.class)
                        .build();

        list = new ArrayList<UpdateChallan>();
        ViewChallanAdapter challanAdapter = new ViewChallanAdapter(options,list,this);
        recyclerView.setAdapter(challanAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        database.getReference().child("UpdateChallan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                    UpdateChallan updateChallan =dataSnapshot.getValue(UpdateChallan.class);
                    updateChallan.getVehicle_num(dataSnapshot.getKey());
                    list.add(updateChallan);
                }
                challanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setAdapter(viewChallanAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewChallanAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewChallanAdapter.stopListening();
    }
}