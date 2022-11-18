package com.example.proyectoi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectoi.Adapter.RutinasAdapter;
import com.example.proyectoi.entidades.ConsultarRutinas;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class rutinas extends Fragment {

    RecyclerView recyclerRutinass;
    ArrayList<ConsultarRutinas> listaRutinas;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_rutinas, container, false);

        //Declaración del ArrayList
        listaRutinas=new ArrayList<>();

        //Asociación del reciclerview
        recyclerRutinass= (RecyclerView) vista.findViewById(R.id.idRecyclerRD);
        recyclerRutinass.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerRutinass.setHasFixedSize(true);

        //Cargar método de los servicios
        cargarWebServiceRutina();

        return vista;
    }

    //Metodo de datos de asociación al reciclerview
    private void cargarWebServiceRutina() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url="http://192.168.1.65/Alumno/getRutinas.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ConsultarRutinas consultarRutinas=null;

                JSONArray json=response.optJSONArray("ejerciciorutina");
                try {
                    for (int i=0;i<json.length();i++){
                        consultarRutinas=new ConsultarRutinas();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        consultarRutinas.setIdrutina(jsonObject.optInt("idejerciciorutina"));
                        consultarRutinas.setNombreRutina(jsonObject.optString("nombrerutina"));
                        consultarRutinas.setNivel("Nivel: "+jsonObject.optString("nivel"));
                        consultarRutinas.setDiaSemana(jsonObject.optString("diasemana"));
                        consultarRutinas.setParteATrabajar(jsonObject.optString("parteaTrabajar"));
                        consultarRutinas.setNombreEjercicio(jsonObject.optString("nombreejercicio1"));
                        consultarRutinas.setNombreMaquina(jsonObject.optString("nombremaquina") );
                        consultarRutinas.setSeries(("Series: "+jsonObject.optString("series")));
                        consultarRutinas.setNumRepeticion("No. Repeticiones: "+(jsonObject.optString("numrepeticion")));
                        consultarRutinas.setPesoAgregado("Peso Agregado: "+(jsonObject.optString("pesoagregado")));
                        listaRutinas.add(consultarRutinas);
                    }
                    progress.hide();
                    RutinasAdapter adapter=new RutinasAdapter(listaRutinas);
                    recyclerRutinass.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                            " "+response, Toast.LENGTH_LONG).show();
                    progress.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ", error.toString());
                progress.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){

        FloatingActionButton btnVolverIR = view.findViewById(R.id.btnRegresarR);
        ExtendedFloatingActionButton btnAgregarRutina = view.findViewById(R.id.btnAddRutina);

        btnVolverIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_rutinas_to_inMenu);
            }
        });

        btnAgregarRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_rutinas_to_agregarRutina);
            }
        });
    }
}