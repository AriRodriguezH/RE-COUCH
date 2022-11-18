package com.example.proyectoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class detallesRutina extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detalles_rutina, container, false);
        TextView text = (TextView) view.findViewById(R.id.nombreRutinaDR);//Find textview Id
        TextView ids= (TextView) view.findViewById(R.id.vvvRutina);
        TextView textapm = (TextView) view.findViewById(R.id.NivelRutinaDR);//Find textview Id
        String getArgument = getArguments().getString("NombreRutina");
        String getArgumentid = getArguments().getString("Rutinaid");
        String getArguments = getArguments().getString("NivelRutina");
        ids.setText(getArgumentid);
        text.setText(getArgument);//set string over textview
        textapm.setText(getArguments);

        return view;
    }

    //Traspase de las navegaciones de los botones
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton regresarlista= view.findViewById(R.id.btnRegresarDR);

        regresarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_rutinas_self);
            }
        });
    }
}