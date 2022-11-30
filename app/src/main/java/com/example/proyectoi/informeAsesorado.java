package com.example.proyectoi;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.example.proyectoi.Adapter.HistoricoAsesoradoAdapter;
import com.example.proyectoi.entidades.ConsultaHistoricoAsesorado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;


public class informeAsesorado extends Fragment {

    RecyclerView recyclerUsuarios;
    ArrayList<ConsultaHistoricoAsesorado> listaConsultaHistorico;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    EditText miIDEntrenador;
    private PreferenceHelper preferenceHelper;

    private XYPlot plot;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
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

        miIDEntrenador = vista.findViewById(R.id.identrenadorInformeA);
        preferenceHelper = new PreferenceHelper(getContext());
        miIDEntrenador.setText(preferenceHelper.getHobby());


        plot = (XYPlot) vista.findViewById(R.id.plot);

        // create a couple arrays of y-values to plot:
        final Number[] domainLabels = {1, 2, 3, 6, 7, 8, 9, 10, 13, 14};
        Number[] series1Numbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};

        // turn the above arrays into XYSeries':
        // (Y_VALS_ONLY means use the element index as the x value)
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(getContext(), R.xml.line_point_formatter_with_labels);

        LineAndPointFormatter series2Format =
                new LineAndPointFormatter(getActivity(), R.xml.line_point_formatter_with_labels_2);

        // add an "dash" effect to the series2 line:
        series2Format.getLinePaint().setPathEffect(new DashPathEffect(new float[] {

                // always use DP when specifying pixel sizes, to keep things consistent across devices:
                PixelUtils.dpToPix(20),
                PixelUtils.dpToPix(15)}, 0));

        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        series2Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });

        //Cargar método de los servicios
        cargarWSIA();

        return vista;
    }


        //Metodo de datos de asociación al reciclerview
    private void cargarWSIA() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String IdEntrenador = miIDEntrenador.getText().toString();

        String url="https://gdxblackstar.000webhostapp.com/getHistoricoasesorado.php?identrenador="+IdEntrenador;
        String urllocal="http://192.168.1.65/Alumno/getHistoricoasesorado.php?identrenador="+IdEntrenador;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urllocal, (JSONObject) null, new Response.Listener<JSONObject>() {
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