package com.example.khialerahat.experts_khialerahat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.khialerahat.experts_khialerahat.R;

public class Homefrg extends Fragment {

    android.support.v7.widget.Toolbar toolbar;


View view;

    AppBarLayout appBarLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.home, container, false);
        View view1=inflater.inflate(R.layout.home2,container,false);


        return view;
    }










    @Override
    public void onResume() {


        super.onResume();
    }














    }

