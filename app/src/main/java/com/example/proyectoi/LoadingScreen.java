package com.example.proyectoi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class LoadingScreen extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_loading_screen, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasInicioS", Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion", false);
                if (sesion){
                    Navigation.findNavController(vista).navigate(R.id.action_loadingScreen_to_inMenu);
                }else{
                    Navigation.findNavController(vista).navigate(R.id.action_loadingScreen_to_inicio);
                }
            }
        }, 2000);
        return vista;
    }
}