package com.example.proyectoi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;


public class inMenu extends Fragment {

    MaterialToolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;
    private PreferenceHelper preferenceHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_in_menu, container, false);

        preferenceHelper = new PreferenceHelper(getContext());
        toolbar = view.findViewById(R.id.toolbar);
        AppCompatActivity activity =(AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        frameLayout = view.findViewById(R.id.main_frameLayout);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = view.findViewById(R.id.nav_views);

        //ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        /*Funciones de Navigation*/
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){
                    case R.id.confPerfil:
                        Navigation.findNavController(view).navigate(R.id.action_inMenu_to_editusuario);break;
                    case R.id.opCerrarSesion:
                        SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasInicioS", Context.MODE_PRIVATE);
                        preferences.edit().clear().commit();
                        Navigation.findNavController(view).navigate(R.id.action_inMenu_to_inicio);break;

                    default:
                        return true;
                }
                return true;
            }
        });
        return view;
    }

    //Traspase de las navegaciones de los botones
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){
        LinearLayout ConsultaA = view.findViewById(R.id.btnConsultaA);
        LinearLayout RegistroA = view.findViewById(R.id.btnRegistraA);
        LinearLayout Rutina = view.findViewById(R.id.btnRutinas);
        LinearLayout HistoricoA = view.findViewById(R.id.btnHisoricoA);
        LinearLayout InformeA = view.findViewById(R.id.btnInformeA);



        preferenceHelper = new PreferenceHelper(getContext());

        TextView correoo= view.findViewById(R.id.vercorreo);

        correoo.setText("Bienvenid@ "+preferenceHelper.getName());


        //correoo.setText(preferenceHelper.getHobby());

        HistoricoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inMenu_to_historicoAsesorado);
            }
        });

        InformeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inMenu_to_informeAsesorado);
            }
        });

        RegistroA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inMenu_to_registroAsesorado);
            }
        });

        Rutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inMenu_to_rutinas);
            }
        });

        ConsultaA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inMenu_to_onsultarAsesorados);
            }
        });
    }
}
