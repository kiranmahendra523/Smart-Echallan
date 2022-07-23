package com.example.smartechallan.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartechallan.Policelogin;
import com.example.smartechallan.R;
import com.example.smartechallan.UserMain;
import com.example.smartechallan.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Activity context;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }
    @Override
    public void onStart(){
        super.onStart();
        ImageView im = (ImageView) context.findViewById(R.id.policeimage);
        ImageView im1 = (ImageView) context.findViewById(R.id.userimage);
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Policelogin.class));
            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, UserMain.class));
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}