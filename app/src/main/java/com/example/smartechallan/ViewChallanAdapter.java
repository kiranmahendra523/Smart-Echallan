package com.example.smartechallan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartechallan.Models.UpdateChallan;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class ViewChallanAdapter extends FirebaseRecyclerAdapter<UpdateChallan,ViewChallanAdapter.myViewChallan> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    ArrayList<UpdateChallan> list;
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;


    public ViewChallanAdapter(@NonNull FirebaseRecyclerOptions<UpdateChallan> options, ArrayList<UpdateChallan> list, ViewChallan cotext) {
        super(options);
        this.list = list;
        this.context = context;
    }



    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewChallan holder, int position, @NonNull UpdateChallan model) {
       UpdateChallan updateChallan =list.get(position);

        holder.vehiclenum.setText(model.getVehicle_num());
        holder.typeofviolation.setText(model.getType_of_violation());
        holder.time.setText(model.getChallan_Time());
        holder.date.setText(model.getChallan_date());
        holder.amount.setText(model.getChallan_amount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @Override
    public myViewChallan onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userchallantemplate,parent,false);
        return new myViewChallan(view);
    }

    class myViewChallan extends RecyclerView.ViewHolder{
        TextView vehiclenum,typeofviolation,time,date,amount;
        public myViewChallan(@NonNull View itemView) {
            super(itemView);
            vehiclenum = (TextView) itemView.findViewById(R.id.vehiclenumtv);
            typeofviolation = (TextView) itemView.findViewById(R.id.typeofvoiltv);
            time = (TextView) itemView.findViewById(R.id.timetv);
            date = (TextView) itemView.findViewById(R.id.datelayout);
            amount =(TextView) itemView.findViewById(R.id.amounttvupadtechallna );
        }
    }
}
