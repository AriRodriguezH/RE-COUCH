package com.example.proyectoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class cargaPantalla extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_carga_pantalla, container, false);
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), cargaPantalla.class);
                startActivity(intent);
                SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasInicioS", Context.MODE_PRIVATE);
                boolean sesion = preferences.getBoolean("sesion", false);
                if (sesion){
                    Navigation.findNavController(vista).navigate(R.id.action_cargaPantalla_to_inMenu);
                }else{
                    Navigation.findNavController(vista).navigate(R.id.action_cargaPantalla_to_inicio);
                }
            }
        }, 2000);*/
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        //getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}