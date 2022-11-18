package com.example.proyectoi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class agregarRutina extends Fragment {
    Spinner spinerLE1,spinerLE2,spinerLE3,spinerLE4,spinerME1,spinerME2,spinerME3,spinerME4,
            spinerMiE1,spinerMiE2,spinerMiE3,spinerMiE4,spinerJE1,spinerJE2,spinerJE3,spinerJE4,
            spinerVE1,spinerVE2,spinerVE3,spinerVE4,spinerSE1,spinerSE2,spinerSE3,spinerSE4,
            spinerDE1,spinerDE2,spinerDE3,spinerDE4;
    ArrayList<String> MaquinaList = new ArrayList<>();
    ArrayList<String> idMlist = new ArrayList<>();
    ArrayAdapter<String> MaquinaAdapter;
    RequestQueue requestQueueM;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_rutina, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){

        Button btnGuardarNR = view.findViewById(R.id.GuardarR);
        FloatingActionButton btnVolverAR = view.findViewById(R.id.btnRegresarAR);
        AutoCompleteTextView autoCompleteTextView;
        autoCompleteTextView = view.findViewById(R.id.etNivelRutina);
        requestQueueM = Volley.newRequestQueue(getContext());
        //Spinners de las maquinas a utilizar dentro de la rutina
        spinerLE1= view.findViewById(R.id.spinerMaquinaLunesE1);
        spinerLE2= view.findViewById(R.id.spinerMaquinaLunesE2);
        spinerLE3= view.findViewById(R.id.spinerMaquinaLunesE3);
        spinerLE4= view.findViewById(R.id.spinerMaquinaLunesE4);
        spinerME1= view.findViewById(R.id.spinerMaquinaMartesE1);
        spinerME2= view.findViewById(R.id.spinerMaquinaMartesE2);
        spinerME3= view.findViewById(R.id.spinerMaquinaMartesE3);
        spinerME4= view.findViewById(R.id.spinerMaquinaMartesE4);
        spinerMiE1= view.findViewById(R.id.spinerMaquinaMiercolesE1);
        spinerMiE2= view.findViewById(R.id.spinerMaquinaMiercolesE2);
        spinerMiE3= view.findViewById(R.id.spinerMaquinaMiercolesE3);
        spinerMiE4= view.findViewById(R.id.spinerMaquinaMiercolesE4);
        spinerJE1= view.findViewById(R.id.spinerMaquinaJuevesE1);
        spinerJE2= view.findViewById(R.id.spinerMaquinaJuevesE2);
        spinerJE3= view.findViewById(R.id.spinerMaquinaJuevesE3);
        spinerJE4= view.findViewById(R.id.spinerMaquinaJuevesE4);
        spinerVE1= view.findViewById(R.id.spinerMaquinaViernesE1);
        spinerVE2= view.findViewById(R.id.spinerMaquinaViernesE2);
        spinerVE3= view.findViewById(R.id.spinerMaquinaViernesE3);
        spinerVE4= view.findViewById(R.id.spinerMaquinaViernesE4);
        spinerSE1= view.findViewById(R.id.spinerMaquinaSabadoE1);
        spinerSE2= view.findViewById(R.id.spinerMaquinaSabadoE2);
        spinerSE3= view.findViewById(R.id.spinerMaquinaSabadoE3);
        spinerSE4= view.findViewById(R.id.spinerMaquinaSabadoE4);
        spinerDE1= view.findViewById(R.id.spinerMaquinaDomingoE1);
        spinerDE2= view.findViewById(R.id.spinerMaquinaDomingoE2);
        spinerDE3= view.findViewById(R.id.spinerMaquinaDomingoE3);
        spinerDE4= view.findViewById(R.id.spinerMaquinaDomingoE4);

        //CheckBox de lunes a viernes
        CheckBox cbLunes = view.findViewById(R.id.DiaLunes);
        CheckBox cbMartes = view.findViewById(R.id.Martes);
        CheckBox cbMiercoles = view.findViewById(R.id.Miercoles);
        CheckBox cbJueves = view.findViewById(R.id.Jueves);
        CheckBox cbViernes = view.findViewById(R.id.Viernes);
        CheckBox cbSabado = view.findViewById(R.id.Sabado);
        CheckBox cbDomingo = view.findViewById(R.id.Domingo);
        //ScrollView de lunes a viernes
        ScrollView ScrollL = view.findViewById(R.id.SCLunes);
        ScrollView ScrollM = view.findViewById(R.id.SCMartes);
        ScrollView ScrollMi = view.findViewById(R.id.SCMiercoles);
        ScrollView ScrollJ = view.findViewById(R.id.SCJueves);
        ScrollView ScrollV = view.findViewById(R.id.SCViernes);
        ScrollView ScrollS = view.findViewById(R.id.SCSabado);
        ScrollView ScrollD = view.findViewById(R.id.SCDomingo);

        AutoCompleteTextView noseries= view.findViewById(R.id.etnoSeriesAR);
        AutoCompleteTextView noRepeticiones= view.findViewById(R.id.etnoRepeticionAR);
        AutoCompleteTextView pesoLevantar1L= view.findViewById(R.id.pesolevantar1Lunes);
        AutoCompleteTextView pesoLevantar2L= view.findViewById(R.id.pesolevantar2Lunes);
        AutoCompleteTextView pesoLevantar3L= view.findViewById(R.id.pesolevantar3Lunes);
        AutoCompleteTextView pesoLevantar4L= view.findViewById(R.id.pesolevantar4Lunes);
        AutoCompleteTextView noseriesM= view.findViewById(R.id.etnoSeriesARMartes);
        AutoCompleteTextView noRepeticionesM= view.findViewById(R.id.etnoRepeticionARMartes);
        AutoCompleteTextView pesoLevantar1M= view.findViewById(R.id.pesolevantar1Martes);
        AutoCompleteTextView pesoLevantar2M= view.findViewById(R.id.pesolevantar2Martes);
        AutoCompleteTextView pesoLevantar3M= view.findViewById(R.id.pesolevantar3Martes);
        AutoCompleteTextView pesoLevantar4M= view.findViewById(R.id.pesolevantar4Martes);
        AutoCompleteTextView noseriesMI= view.findViewById(R.id.etnoSeriesARMiercoles);
        AutoCompleteTextView noRepeticionesMI= view.findViewById(R.id.etnoRepeticionARMiercoles);
        AutoCompleteTextView pesoLevantar1MI= view.findViewById(R.id.pesolevantar1Miercoles);
        AutoCompleteTextView pesoLevantar2MI= view.findViewById(R.id.pesolevantar2Miercoles);
        AutoCompleteTextView pesoLevantar3MI= view.findViewById(R.id.pesolevantar3Miercoles);
        AutoCompleteTextView pesoLevantar4MI= view.findViewById(R.id.pesolevantar4Miercoles);
        AutoCompleteTextView noseriesJ= view.findViewById(R.id.etnoSeriesARJueves);
        AutoCompleteTextView noRepeticionesJ= view.findViewById(R.id.etnoRepeticionARJueves);
        AutoCompleteTextView pesoLevantar1J= view.findViewById(R.id.pesolevantar1Jueves);
        AutoCompleteTextView pesoLevantar2J= view.findViewById(R.id.pesolevantar2Jueves);
        AutoCompleteTextView pesoLevantar3J= view.findViewById(R.id.pesolevantar3Jueves);
        AutoCompleteTextView pesoLevantar4J= view.findViewById(R.id.pesolevantar4Jueves);
        AutoCompleteTextView noseriesV= view.findViewById(R.id.etnoSeriesARViernes);
        AutoCompleteTextView noRepeticionesV= view.findViewById(R.id.etnoRepeticionARViernes);
        AutoCompleteTextView pesoLevantar1V= view.findViewById(R.id.pesolevantar1Viernes);
        AutoCompleteTextView pesoLevantar2V= view.findViewById(R.id.pesolevantar2Viernes);
        AutoCompleteTextView pesoLevantar3V= view.findViewById(R.id.pesolevantar3Viernes);
        AutoCompleteTextView pesoLevantar4V= view.findViewById(R.id.pesolevantar4Viernes);
        AutoCompleteTextView noseriesS= view.findViewById(R.id.etnoSeriesARSabado);
        AutoCompleteTextView noRepeticionesS= view.findViewById(R.id.etnoRepeticionARSabado);
        AutoCompleteTextView pesoLevantar1S= view.findViewById(R.id.pesolevantar1Sabado);
        AutoCompleteTextView pesoLevantar2S= view.findViewById(R.id.pesolevantar2Sabado);
        AutoCompleteTextView pesoLevantar3S= view.findViewById(R.id.pesolevantar3Sabado);
        AutoCompleteTextView pesoLevantar4S= view.findViewById(R.id.pesolevantar4Sabado);
        AutoCompleteTextView noseriesD= view.findViewById(R.id.etnoSeriesARDomingo);
        AutoCompleteTextView noRepeticionesD= view.findViewById(R.id.etnoRepeticionARDomingo);
        AutoCompleteTextView pesoLevantar1D= view.findViewById(R.id.pesolevantar1Domingo);
        AutoCompleteTextView pesoLevantar2D= view.findViewById(R.id.pesolevantar2Domingo);
        AutoCompleteTextView pesoLevantar3D= view.findViewById(R.id.pesolevantar3Domingo);
        AutoCompleteTextView pesoLevantar4D= view.findViewById(R.id.pesolevantar4Domingo);
        EditText NombreRutina= view.findViewById(R.id.etNombreRutina);

        AutoCompleteTextView ParteTrabajar = view.findViewById(R.id.parteTrabajarLunes);
        AutoCompleteTextView ParteTrabajarM = view.findViewById(R.id.parteTrabajarMartes);
        AutoCompleteTextView ParteTrabajarMI = view.findViewById(R.id.parteTrabajarMiercoles);
        AutoCompleteTextView ParteTrabajarJ = view.findViewById(R.id.parteTrabajarJueves);
        AutoCompleteTextView ParteTrabajarV = view.findViewById(R.id.parteTrabajarViernes);
        AutoCompleteTextView ParteTrabajarS = view.findViewById(R.id.parteTrabajarSabado);
        AutoCompleteTextView ParteTrabajarD = view.findViewById(R.id.parteTrabajarDomingo);

        EditText MiNIVELR = view.findViewById(R.id.etNivelRutina);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        EditText Ejercicio1L = view.findViewById(R.id.nombreEjercicio1Lunes);
        EditText Ejercicio2L = view.findViewById(R.id.nombreEjercicio2Lunes);
        EditText Ejercicio3L = view.findViewById(R.id.nombreEjercicio3Lunes);
        EditText Ejercicio4L = view.findViewById(R.id.nombreEjercicio4Lunes);
        EditText Ejercicio1M = view.findViewById(R.id.nombreEjercicio1Martes);
        EditText Ejercicio2M = view.findViewById(R.id.nombreEjercicio2Martes);
        EditText Ejercicio3M = view.findViewById(R.id.nombreEjercicio3Martes);
        EditText Ejercicio4M = view.findViewById(R.id.nombreEjercicio4Martes);
        EditText Ejercicio1Mi = view.findViewById(R.id.nombreEjercicio1Miercoles);
        EditText Ejercicio2Mi = view.findViewById(R.id.nombreEjercicio2Miercoles);
        EditText Ejercicio3Mi = view.findViewById(R.id.nombreEjercicio3Miercoles);
        EditText Ejercicio4Mi = view.findViewById(R.id.nombreEjercicio4Miercoles);
        EditText Ejercicio1J = view.findViewById(R.id.nombreEjercicio1Jueves);
        EditText Ejercicio2J = view.findViewById(R.id.nombreEjercicio2Jueves);
        EditText Ejercicio3J = view.findViewById(R.id.nombreEjercicio3Jueves);
        EditText Ejercicio4J = view.findViewById(R.id.nombreEjercicio4Jueves);
        EditText Ejercicio1V = view.findViewById(R.id.nombreEjercicio1Viernes);
        EditText Ejercicio2V = view.findViewById(R.id.nombreEjercicio2Viernes);
        EditText Ejercicio3V = view.findViewById(R.id.nombreEjercicio3Viernes);
        EditText Ejercicio4V = view.findViewById(R.id.nombreEjercicio4Viernes);
        EditText Ejercicio1S = view.findViewById(R.id.nombreEjercicio1Sabado);
        EditText Ejercicio2S = view.findViewById(R.id.nombreEjercicio2Sabado);
        EditText Ejercicio3S = view.findViewById(R.id.nombreEjercicio3Sabado);
        EditText Ejercicio4S = view.findViewById(R.id.nombreEjercicio4Sabado);
        EditText Ejercicio1D = view.findViewById(R.id.nombreEjercicio1Domingo);
        EditText Ejercicio2D = view.findViewById(R.id.nombreEjercicio2Domingo);
        EditText Ejercicio3D = view.findViewById(R.id.nombreEjercicio3Domingo);
        EditText Ejercicio4D = view.findViewById(R.id.nombreEjercicio4Domingo);

        /*Funcion Sipners*/

        String urlMaquina = "http://192.168.1.65/Alumno/spinerMaquina.php";
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                urlMaquina, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("maquina");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String MaquinaName = jsonObject.optString("nombremaquina");
                        String MaquinaID = jsonObject.optString("idmaquina");
                        MaquinaList.add(MaquinaName);
                        idMlist.add(MaquinaID);
                        MaquinaAdapter = new ArrayAdapter<>(getActivity(),
                                R.layout.spinner_item, MaquinaList);
                        MaquinaAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spinerLE1.setAdapter(MaquinaAdapter);
                        spinerLE2.setAdapter(MaquinaAdapter);
                        spinerLE3.setAdapter(MaquinaAdapter);
                        spinerLE4.setAdapter(MaquinaAdapter);
                        spinerME1.setAdapter(MaquinaAdapter);
                        spinerME2.setAdapter(MaquinaAdapter);
                        spinerME3.setAdapter(MaquinaAdapter);
                        spinerME4.setAdapter(MaquinaAdapter);
                        spinerMiE1.setAdapter(MaquinaAdapter);
                        spinerMiE2.setAdapter(MaquinaAdapter);
                        spinerMiE3.setAdapter(MaquinaAdapter);
                        spinerMiE4.setAdapter(MaquinaAdapter);
                        spinerJE1.setAdapter(MaquinaAdapter);
                        spinerJE2.setAdapter(MaquinaAdapter);
                        spinerJE3.setAdapter(MaquinaAdapter);
                        spinerJE4.setAdapter(MaquinaAdapter);
                        spinerVE1.setAdapter(MaquinaAdapter);
                        spinerVE2.setAdapter(MaquinaAdapter);
                        spinerVE3.setAdapter(MaquinaAdapter);
                        spinerVE4.setAdapter(MaquinaAdapter);
                        spinerSE1.setAdapter(MaquinaAdapter);
                        spinerSE2.setAdapter(MaquinaAdapter);
                        spinerSE3.setAdapter(MaquinaAdapter);
                        spinerSE4.setAdapter(MaquinaAdapter);
                        spinerDE1.setAdapter(MaquinaAdapter);
                        spinerDE2.setAdapter(MaquinaAdapter);
                        spinerDE3.setAdapter(MaquinaAdapter);
                        spinerDE4.setAdapter(MaquinaAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueueM.add(jsonObjectRequest2);
        spinerLE1.setAdapter(MaquinaAdapter);
        spinerLE2.setAdapter(MaquinaAdapter);
        spinerLE3.setAdapter(MaquinaAdapter);
        spinerLE4.setAdapter(MaquinaAdapter);
        spinerME1.setAdapter(MaquinaAdapter);
        spinerME2.setAdapter(MaquinaAdapter);
        spinerME3.setAdapter(MaquinaAdapter);
        spinerME4.setAdapter(MaquinaAdapter);
        spinerMiE1.setAdapter(MaquinaAdapter);
        spinerMiE2.setAdapter(MaquinaAdapter);
        spinerMiE3.setAdapter(MaquinaAdapter);
        spinerMiE4.setAdapter(MaquinaAdapter);
        spinerJE1.setAdapter(MaquinaAdapter);
        spinerJE2.setAdapter(MaquinaAdapter);
        spinerJE3.setAdapter(MaquinaAdapter);
        spinerJE4.setAdapter(MaquinaAdapter);
        spinerVE1.setAdapter(MaquinaAdapter);
        spinerVE2.setAdapter(MaquinaAdapter);
        spinerVE3.setAdapter(MaquinaAdapter);
        spinerVE4.setAdapter(MaquinaAdapter);
        spinerSE1.setAdapter(MaquinaAdapter);
        spinerSE2.setAdapter(MaquinaAdapter);
        spinerSE3.setAdapter(MaquinaAdapter);
        spinerSE4.setAdapter(MaquinaAdapter);
        spinerDE1.setAdapter(MaquinaAdapter);
        spinerDE2.setAdapter(MaquinaAdapter);
        spinerDE3.setAdapter(MaquinaAdapter);
        spinerDE4.setAdapter(MaquinaAdapter);

        String[]PartesTrabajar = new String[]{
                "Pecho",
                "Hombros",
                "Tríceps",
                "Espalda",
                "Bíceps",
                "Abdominales",
                "Isquiotibiales",
                "Cuádriceps",
                "Glúteos",
                "Femoral",
                "Gemelos"
        };
        ArrayAdapter<String> parteatrabajar = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_parteatrabajar,PartesTrabajar
        );
        ParteTrabajar.setAdapter(parteatrabajar);
        ParteTrabajarM.setAdapter(parteatrabajar);
        ParteTrabajarMI.setAdapter(parteatrabajar);
        ParteTrabajarJ.setAdapter(parteatrabajar);
        ParteTrabajarV.setAdapter(parteatrabajar);
        ParteTrabajarS.setAdapter(parteatrabajar);
        ParteTrabajarD.setAdapter(parteatrabajar);

        String[]niveles = new String[]{
                "Principiante",
                "Intermedio",
                "Experto"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_ar,niveles
        );
        autoCompleteTextView.setAdapter(adapter);

        String[]series = new String[]{
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12"
        };
        ArrayAdapter<String> miserie = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_series,series
        );
        noseries.setAdapter(miserie);
        noseriesM.setAdapter(miserie);
        noseriesMI.setAdapter(miserie);
        noseriesJ.setAdapter(miserie);
        noseriesV.setAdapter(miserie);
        noseriesS.setAdapter(miserie);
        noseriesD.setAdapter(miserie);
        noRepeticiones.setAdapter(miserie);
        noRepeticionesM.setAdapter(miserie);
        noRepeticionesMI.setAdapter(miserie);
        noRepeticionesJ.setAdapter(miserie);
        noRepeticionesV.setAdapter(miserie);
        noRepeticionesS.setAdapter(miserie);
        noRepeticionesD.setAdapter(miserie);


        cbLunes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbLunes.isChecked()){
                    ScrollL.setVisibility(View.VISIBLE);
                }else {
                    ScrollL.setVisibility(View.GONE);
                }
            }
        });

        cbMartes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMartes.isChecked()){
                    ScrollM.setVisibility((View.VISIBLE));
                }else {
                    ScrollM.setVisibility(View.GONE);
                }
            }
        });

        cbMiercoles.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbMiercoles.isChecked()){
                    ScrollMi.setVisibility((View.VISIBLE));
                }else {
                    ScrollMi.setVisibility(View.GONE);
                }
            }
        });

        cbJueves.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbJueves.isChecked()){
                    ScrollJ.setVisibility((View.VISIBLE));
                }else {
                    ScrollJ.setVisibility(View.GONE);
                }
            }
        });

        cbViernes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbViernes.isChecked()){
                    ScrollV.setVisibility((View.VISIBLE));
                }else {
                    ScrollV.setVisibility(View.GONE);
                }
            }
        });

        cbSabado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbSabado.isChecked()){
                    ScrollS.setVisibility((View.VISIBLE));
                }else {
                    ScrollS.setVisibility(View.GONE);
                }
            }
        });

        cbDomingo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbDomingo.isChecked()){
                    ScrollD.setVisibility((View.VISIBLE));
                }else {
                    ScrollD.setVisibility(View.GONE);
                }
            }
        });

        btnVolverAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_agregarRutina_to_rutinas);
            }
        });

        btnGuardarNR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistraDatosRutina();
                //Toast.makeText(getActivity(),"Rutina Guardada ", Toast.LENGTH_SHORT).show();
            }

            private void RegistraDatosRutina() {
                String miNombreRutina = NombreRutina.getText().toString().trim();
                String Nivel = MiNIVELR.getText().toString().trim();

                String parteTrabajarL = ParteTrabajar.getText().toString().trim();
                String parteTrabajarM = ParteTrabajarM.getText().toString().trim();
                String parteTrabajarMi = ParteTrabajarMI.getText().toString().trim();
                String parteTrabajarJ = ParteTrabajarJ.getText().toString().trim();
                String parteTrabajarV = ParteTrabajarV.getText().toString().trim();
                String parteTrabajarS = ParteTrabajarS.getText().toString().trim();
                String parteTrabajarD = ParteTrabajarD.getText().toString().trim();

                String NumRepeticionL = noRepeticiones.getText().toString().trim();
                String SeriesL = noseries.getText().toString().trim();

                String NumRepeticionM = noRepeticionesM.getText().toString().trim();
                String SeriesM = noseriesM.getText().toString().trim();

                String NumRepeticionMi = noRepeticionesMI.getText().toString().trim();
                String SeriesMi = noseriesMI.getText().toString().trim();

                String NumRepeticionJ = noRepeticionesJ.getText().toString().trim();
                String SeriesJ = noseriesJ.getText().toString().trim();

                String NumRepeticionV = noRepeticionesV.getText().toString().trim();
                String SeriesV = noseriesV.getText().toString().trim();

                String NumRepeticionS = noRepeticionesS.getText().toString().trim();
                String SeriesS = noseriesS.getText().toString().trim();

                String NumRepeticionD = noRepeticionesD.getText().toString().trim();
                String SeriesD = noseriesD.getText().toString().trim();

                String NombreEjercio1L = Ejercicio1L.getText().toString().trim();
                String NombreEjercio2L = Ejercicio2L.getText().toString().trim();
                String NombreEjercio3L = Ejercicio3L.getText().toString().trim();
                String NombreEjercio4L = Ejercicio4L.getText().toString().trim();

                String NombreEjercio1M = Ejercicio1M.getText().toString().trim();
                String NombreEjercio2M = Ejercicio2M.getText().toString().trim();
                String NombreEjercio3M = Ejercicio3M.getText().toString().trim();
                String NombreEjercio4M = Ejercicio4M.getText().toString().trim();

                String NombreEjercio1Mi = Ejercicio1Mi.getText().toString().trim();
                String NombreEjercio2Mi = Ejercicio2Mi.getText().toString().trim();
                String NombreEjercio3Mi = Ejercicio3Mi.getText().toString().trim();
                String NombreEjercio4Mi = Ejercicio4Mi.getText().toString().trim();

                String NombreEjercio1J = Ejercicio1J.getText().toString().trim();
                String NombreEjercio2J = Ejercicio2J.getText().toString().trim();
                String NombreEjercio3J = Ejercicio3J.getText().toString().trim();
                String NombreEjercio4J = Ejercicio4J.getText().toString().trim();

                String NombreEjercio1V = Ejercicio1V.getText().toString().trim();
                String NombreEjercio2V = Ejercicio2V.getText().toString().trim();
                String NombreEjercio3V = Ejercicio3V.getText().toString().trim();
                String NombreEjercio4V = Ejercicio4V.getText().toString().trim();

                String NombreEjercio1S = Ejercicio1S.getText().toString().trim();
                String NombreEjercio2S = Ejercicio2S.getText().toString().trim();
                String NombreEjercio3S = Ejercicio3S.getText().toString().trim();
                String NombreEjercio4S = Ejercicio4S.getText().toString().trim();

                String NombreEjercio1D = Ejercicio1D.getText().toString().trim();
                String NombreEjercio2D = Ejercicio2D.getText().toString().trim();
                String NombreEjercio3D = Ejercicio3D.getText().toString().trim();
                String NombreEjercio4D = Ejercicio4D.getText().toString().trim();


                String lunes = cbLunes.getText().toString().trim();
                String martes = cbMartes.getText().toString().trim();
                String miercoles = cbMiercoles.getText().toString().trim();
                String jueves = cbJueves.getText().toString().trim();
                String viernes = cbViernes.getText().toString().trim();
                String sabado = cbSabado.getText().toString().trim();
                String domingo = cbDomingo.getText().toString().trim();

                String pesoAgregado1L = pesoLevantar1L.getText().toString().trim();
                String pesoAgregado2L = pesoLevantar2L.getText().toString().trim();
                String pesoAgregado3L = pesoLevantar3L.getText().toString().trim();
                String pesoAgregado4L = pesoLevantar4L.getText().toString().trim();

                String pesoAgregado1M = pesoLevantar1M.getText().toString().trim();
                String pesoAgregado2M = pesoLevantar2M.getText().toString().trim();
                String pesoAgregado3M = pesoLevantar3M.getText().toString().trim();
                String pesoAgregado4M = pesoLevantar4M.getText().toString().trim();

                String pesoAgregado1Mi = pesoLevantar1MI.getText().toString().trim();
                String pesoAgregado2Mi = pesoLevantar2MI.getText().toString().trim();
                String pesoAgregado3Mi = pesoLevantar3MI.getText().toString().trim();
                String pesoAgregado4Mi = pesoLevantar4MI.getText().toString().trim();

                String pesoAgregado1J = pesoLevantar1J.getText().toString().trim();
                String pesoAgregado2J = pesoLevantar2J.getText().toString().trim();
                String pesoAgregado3J = pesoLevantar3J.getText().toString().trim();
                String pesoAgregado4J = pesoLevantar4J.getText().toString().trim();

                String pesoAgregado1V = pesoLevantar1V.getText().toString().trim();
                String pesoAgregado2V = pesoLevantar2V.getText().toString().trim();
                String pesoAgregado3V = pesoLevantar3V.getText().toString().trim();
                String pesoAgregado4V = pesoLevantar4V.getText().toString().trim();

                String pesoAgregado1S = pesoLevantar1S.getText().toString().trim();
                String pesoAgregado2S = pesoLevantar2S.getText().toString().trim();
                String pesoAgregado3S = pesoLevantar3S.getText().toString().trim();
                String pesoAgregado4S = pesoLevantar4S.getText().toString().trim();

                String pesoAgregado1D = pesoLevantar1D.getText().toString().trim();
                String pesoAgregado2D = pesoLevantar2D.getText().toString().trim();
                String pesoAgregado3D = pesoLevantar3D.getText().toString().trim();
                String pesoAgregado4D = pesoLevantar4D.getText().toString().trim();

                String maquinaL1 = MaquinaList.get(spinerLE1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaL1);
                String maquinaL2 = MaquinaList.get(spinerLE2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaL2);
                String maquinaL3 = MaquinaList.get(spinerLE3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaL3);
                String maquinaL4 = MaquinaList.get(spinerLE4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaL4);

                String maquinaM1 = MaquinaList.get(spinerME1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaM1);
                String maquinaM2 = MaquinaList.get(spinerME2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaM2);
                String maquinaM3 = MaquinaList.get(spinerME3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaM3);
                String maquinaM4 = MaquinaList.get(spinerME4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaM4);

                String maquinaMi1 = MaquinaList.get(spinerMiE1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaMi1);
                String maquinaMi2 = MaquinaList.get(spinerMiE2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaMi2);
                String maquinaMi3 = MaquinaList.get(spinerMiE3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaMi3);
                String maquinaMi4 = MaquinaList.get(spinerMiE4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaMi4);

                String maquinaJ1 = MaquinaList.get(spinerJE1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaJ1);
                String maquinaJ2 = MaquinaList.get(spinerJE2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaJ2);
                String maquinaJ3 = MaquinaList.get(spinerJE3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaJ3);
                String maquinaJ4 = MaquinaList.get(spinerJE4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaJ4);

                String maquinaV1 = MaquinaList.get(spinerVE1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaV1);
                String maquinaV2 = MaquinaList.get(spinerVE2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaV2);
                String maquinaV3 = MaquinaList.get(spinerVE3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaV3);
                String maquinaV4 = MaquinaList.get(spinerVE4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaV4);

                String maquinaS1 = MaquinaList.get(spinerSE1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaS1);
                String maquinaS2 = MaquinaList.get(spinerSE2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaS2);
                String maquinaS3 = MaquinaList.get(spinerSE3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaS3);
                String maquinaS4 = MaquinaList.get(spinerSE4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaS4);

                String maquinaD1 = MaquinaList.get(spinerDE1.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaD1);
                String maquinaD2 = MaquinaList.get(spinerDE2.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaD2);
                String maquinaD3 = MaquinaList.get(spinerDE3.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaD3);
                String maquinaD4 = MaquinaList.get(spinerDE4.getSelectedItemPosition()).trim();
                Log.i("VALOR", maquinaD4);

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Cargando");

                if(miNombreRutina.isEmpty()){
                    NombreRutina.setError("Complete el campo de Nombre");
                    return;
                }else if (Nivel.isEmpty()){
                    MiNIVELR.setError("Completa el campo del nivel");
                }else {
                    if (cbLunes.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarL +
                                "&diasemana=" + lunes +
                                "&nombremaquina=" + maquinaL1 +
                                "&nombremaquina2=" + maquinaL2 +
                                "&nombremaquina3=" + maquinaL3 +
                                "&nombremaquina4=" + maquinaL4 +
                                "&numrepeticion=" + NumRepeticionL +
                                "&series=" + SeriesL +
                                "&pesoAgregado1L=" + pesoAgregado1L +
                                "&pesoAgregado2L=" + pesoAgregado2L +
                                "&pesoAgregado3L=" + pesoAgregado3L +
                                "&pesoAgregado4L=" + pesoAgregado4L +
                                "&nombreejercicio1=" + NombreEjercio1L+
                                "&nombreejercicio2=" + NombreEjercio2L +
                                "&nombreejercicio3=" + NombreEjercio3L+
                                "&nombreejercicio4=" + NombreEjercio4L;

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                    if (cbMartes.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarM +
                                "&diasemana=" + martes +
                                "&nombremaquina=" + maquinaM1 +
                                "&nombremaquina2=" + maquinaM2 +
                                "&nombremaquina3=" + maquinaM3 +
                                "&nombremaquina4=" + maquinaM4 +
                                "&numrepeticion=" + NumRepeticionM +
                                "&series=" + SeriesM +
                                "&pesoAgregado1L=" + pesoAgregado1M +
                                "&pesoAgregado2L=" + pesoAgregado2M+
                                "&pesoAgregado3L=" + pesoAgregado3M +
                                "&pesoAgregado4L=" + pesoAgregado4M +
                                "&nombreejercicio1=" + NombreEjercio1M+
                                "&nombreejercicio2=" + NombreEjercio2M +
                                "&nombreejercicio3=" + NombreEjercio3M+
                                "&nombreejercicio4=" + NombreEjercio4M;
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                    if (cbMiercoles.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarMi +
                                "&diasemana=" + miercoles +
                                "&nombremaquina=" + maquinaMi1 +
                                "&nombremaquina2=" + maquinaMi2 +
                                "&nombremaquina3=" + maquinaMi3 +
                                "&nombremaquina4=" + maquinaMi4 +
                                "&numrepeticion=" + NumRepeticionMi +
                                "&series=" + SeriesMi +
                                "&pesoAgregado1L=" + pesoAgregado1Mi +
                                "&pesoAgregado2L=" + pesoAgregado2Mi +
                                "&pesoAgregado3L=" + pesoAgregado3Mi +
                                "&pesoAgregado4L=" + pesoAgregado4Mi +
                                "&nombreejercicio1=" + NombreEjercio1Mi+
                                "&nombreejercicio2=" + NombreEjercio2Mi +
                                "&nombreejercicio3=" + NombreEjercio3Mi+
                                "&nombreejercicio4=" + NombreEjercio4Mi;
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                    if (cbJueves.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarJ +
                                "&diasemana=" + jueves +
                                "&nombremaquina=" + maquinaJ1 +
                                "&nombremaquina2=" + maquinaJ2 +
                                "&nombremaquina3=" + maquinaJ3 +
                                "&nombremaquina4=" + maquinaJ4 +
                                "&numrepeticion=" + NumRepeticionJ +
                                "&series=" + SeriesJ +
                                "&pesoAgregado1L=" + pesoAgregado1J +
                                "&pesoAgregado2L=" + pesoAgregado2J +
                                "&pesoAgregado3L=" + pesoAgregado3J +
                                "&pesoAgregado4L=" + pesoAgregado4J +
                                "&nombreejercicio1=" + NombreEjercio1J+
                                "&nombreejercicio2=" + NombreEjercio2J +
                                "&nombreejercicio3=" + NombreEjercio3J+
                                "&nombreejercicio4=" + NombreEjercio4J;
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                    if (cbViernes.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarV +
                                "&diasemana=" + viernes +
                                "&nombremaquina=" + maquinaV1 +
                                "&nombremaquina2=" + maquinaV2 +
                                "&nombremaquina3=" + maquinaV3 +
                                "&nombremaquina4=" + maquinaV4 +
                                "&numrepeticion=" + NumRepeticionV +
                                "&series=" + SeriesV +
                                "&pesoAgregado1L=" + pesoAgregado1V +
                                "&pesoAgregado2L=" + pesoAgregado2V +
                                "&pesoAgregado3L=" + pesoAgregado3V +
                                "&pesoAgregado4L=" + pesoAgregado4V +
                                "&nombreejercicio1=" + NombreEjercio1V+
                                "&nombreejercicio2=" + NombreEjercio2V +
                                "&nombreejercicio3=" + NombreEjercio3V+
                                "&nombreejercicio4=" + NombreEjercio4V;
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                    if (cbSabado.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarS +
                                "&diasemana=" + sabado +
                                "&nombremaquina=" + maquinaS1 +
                                "&nombremaquina2=" + maquinaS2 +
                                "&nombremaquina3=" + maquinaS3 +
                                "&nombremaquina4=" + maquinaS4 +
                                "&numrepeticion=" + NumRepeticionS +
                                "&series=" + SeriesS +
                                "&pesoAgregado1L=" + pesoAgregado1S +
                                "&pesoAgregado2L=" + pesoAgregado2S +
                                "&pesoAgregado3L=" + pesoAgregado3S +
                                "&pesoAgregado4L=" + pesoAgregado4S +
                                "&nombreejercicio1=" + NombreEjercio1S+
                                "&nombreejercicio2=" + NombreEjercio2S +
                                "&nombreejercicio3=" + NombreEjercio3S+
                                "&nombreejercicio4=" + NombreEjercio4S;
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                    if (cbDomingo.isChecked()) {
                        progressDialog.show();
                        String url = "http://192.168.1.12/Alumno/insertRutina.php?nombrerutina=" + miNombreRutina +
                                "&nivel=" + Nivel +
                                "&parteatrabajar=" + parteTrabajarD +
                                "&diasemana=" + domingo +
                                "&nombremaquina=" + maquinaD1 +
                                "&nombremaquina2=" + maquinaD2 +
                                "&nombremaquina3=" + maquinaD3 +
                                "&nombremaquina4=" + maquinaD4 +
                                "&numrepeticion=" + NumRepeticionD +
                                "&series=" + SeriesD +
                                "&pesoAgregado1L=" + pesoAgregado1D +
                                "&pesoAgregado2L=" + pesoAgregado2D +
                                "&pesoAgregado3L=" + pesoAgregado3D +
                                "&pesoAgregado4L=" + pesoAgregado4D +
                                "&nombreejercicio1=" + NombreEjercio1D+
                                "&nombreejercicio2=" + NombreEjercio2D
                                +"&nombreejercicio3=" + NombreEjercio3D+
                                "&nombreejercicio4=" + NombreEjercio4D;
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                        );
                        queue.add(jsonObjectRequest);
                    }
                }
            }
        });
    }
}