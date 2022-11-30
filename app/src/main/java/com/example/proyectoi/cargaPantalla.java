package com.example.proyectoi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class cargaPantalla extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carga_pantalla, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasInicioS", Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion", false);
                if (sesion){
                    Navigation.findNavController(view).navigate(R.id.action_cargaPantalla_to_inMenu);
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_cargaPantalla_to_inicio);
                }
            }
        }, 2000);
    }
}