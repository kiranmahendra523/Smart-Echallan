package com.example.smartechallan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartechallan.Models.UpdateChallan;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<UpdateChallan> arrayList;

    public ViewAdapter(Context context, ArrayList<UpdateChallan> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.challan_item,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UpdateChallan updateChallan = arrayList.get(position);
        holder.amount.setText(updateChallan.getChallan_amount());
        holder.vehnumber.setText(updateChallan.getVehicle_num());
        holder.voilation.setText(updateChallan.getType_of_violation());
        holder.date.setText(updateChallan.getChallan_date());
        holder.time.setText(updateChallan.getChallan_Time());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        EditText amount,time,date,vehnumber,voilation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amounted);
            time = itemView.findViewById(R.id.timeed);
            date = itemView.findViewById(R.id.dateed);
            vehnumber = itemView.findViewById(R.id.vhnumin);
            voilation = itemView.findViewById(R.id.vled);
        }
    }
}
