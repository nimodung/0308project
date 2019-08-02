package com.example.realsmarthome;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class EnvironmentFragment extends Fragment {

    MainActivity activity;
    TextView tvTemper, tvHumi, tvLux;
    Button btnCooler;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_environment, container, false);

        tvTemper = (TextView)view.findViewById(R.id.tv_temper);
        tvHumi = (TextView)view.findViewById(R.id.tv_humi);
        tvLux = (TextView)view.findViewById(R.id.tv_lux);
        btnCooler = (Button)view.findViewById(R.id.btn_cooler);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnCooler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        activity.EnvThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                if(getArguments() != null) {
                     String data = getArguments().getString("env");

                    String[] arr = data.split(" ");


                    tvTemper.setText(arr[4]);
                    tvHumi.setText(arr[2]);
                    tvLux.setText(arr[6]);
                }

            }
        });

        activity.EnvThread.start();
    }



}
