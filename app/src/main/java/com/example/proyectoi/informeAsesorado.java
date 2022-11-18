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
import com.androidplot.xy.XYPlot;
import com.example.proyectoi.Adapter.HistoricoAsesoradoAdapter;
import com.example.proyectoi.entidades.ConsultaHistoricoAsesorado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class informeAsesorado extends Fragment {
    RecyclerView recyclerUsuarios;
    ArrayList<ConsultaHistoricoAsesorado> listaConsultaHistorico;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;

    Number[] seriesOfNumbers;
    private XYPlot mySimpleXYPlot;
    // double totalprofitd;
    private HistoricoAsesoradoAdapter dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_informe_asesorado, container, false);
        //Declaración del ArrayList
        listaConsultaHistorico=new ArrayList<>();

        //Asociación del reciclerview
        recyclerUsuarios= (RecyclerView) vista.findViewById(R.id.idRecyclerInfA);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);

        dbHelper = new HistoricoAsesoradoAdapter(listaConsultaHistorico);

        //Cargar método de los servicios
        cargarWSIA();

//        chart = (BarChart) vista.findViewById(R.id.chart);
       // load_data_from_server();

        return vista;
    }


        //Metodo de datos de asociación al reciclerview
    private void cargarWSIA() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String url="http://192.168.1.65/Alumno/getHistoricoasesorado.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, (JSONObject) null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ConsultaHistoricoAsesorado consultaHistoricoAsesorado=null;

                JSONArray json=response.optJSONArray("asesorado");
                try {
                    for (int i=0;i<json.length();i++){
                        consultaHistoricoAsesorado=new ConsultaHistoricoAsesorado();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);

                        consultaHistoricoAsesorado.setNombreHA(jsonObject.optString("nombre"));
                        consultaHistoricoAsesorado.setApellidopHA(jsonObject.optString("apellidop"));
                        consultaHistoricoAsesorado.setApellidomHA(jsonObject.optString("apellidom"));
                        consultaHistoricoAsesorado.setAlturaHA("Altura: "+jsonObject.optString("altura") +" cm");
                        consultaHistoricoAsesorado.setPesoHA("Peso: "+jsonObject.optString("peso")+" kilos");
                        consultaHistoricoAsesorado.setTallaHA("Talla: "+jsonObject.optString("talla") +" cm");
                        consultaHistoricoAsesorado.setFechaCreacionHA(jsonObject.optString("fechacreacion"));
                        listaConsultaHistorico.add(consultaHistoricoAsesorado);
                    }
                    progress.hide();
                    HistoricoAsesoradoAdapter adapter=new HistoricoAsesoradoAdapter(listaConsultaHistorico);
                    recyclerUsuarios.setAdapter(adapter);

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

        FloatingActionButton btnVolverIIA = view.findViewById(R.id.btnRegresarIA);

        btnVolverIIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_informeAsesorado_to_inMenu);
            }
        });
    }
}