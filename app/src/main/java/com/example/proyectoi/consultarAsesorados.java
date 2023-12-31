package com.example.proyectoi;

import android.app.ProgressDialog;
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
import com.example.proyectoi.Adapter.UsuarioAdapter;
import com.example.proyectoi.entidades.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class consultarAsesorados extends Fragment {

    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;
    EditText miIDUSER;
    ProgressDialog progress;
    JsonObjectRequest jsonObjectRequest;
    private PreferenceHelper preferenceHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.fragment_consultar_asesorados, container, false);

        //Declaración del ArrayList
        listaUsuarios=new ArrayList<>();

        //Asociación del reciclerview
        recyclerUsuarios= (RecyclerView) vista.findViewById(R.id.idRecyclerCa);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);

        miIDUSER = vista.findViewById(R.id.identrenadorCA);
        preferenceHelper = new PreferenceHelper(getContext());
        miIDUSER.setText(preferenceHelper.getHobby());

        //Cargar método de los servicios
        cargarWebService();

        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){

        FloatingActionButton btnVolverICA = view.findViewById(R.id.btnRegresarCA);

        btnVolverICA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_onsultarAsesorados_to_inMenu);
            }
        });
    }



    //Metodo de datos de asociación al reciclerview
    private void cargarWebService() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity());

        progress=new ProgressDialog(getContext());
        progress.setMessage("Consultando...");
        progress.show();

        String IDENTRENADOR = miIDUSER.getText().toString();
        Log.i("IDUSER", IDENTRENADOR);

        String url="https://gdxblackstar.000webhostapp.com/getAsesorado.php?identrenador="+IDENTRENADOR;
        String urllocal="http://192.168.0.101/Alumno/getAsesorado.php?identrenador="+IDENTRENADOR;
        Log.e("URL", url);
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urllocal, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Usuario usuario=null;

                JSONArray json=response.optJSONArray("asesorado");
                try {
                    for (int i=0;i<json.length();i++){
                        usuario=new Usuario();
                        JSONObject jsonObject=null;
                        jsonObject=json.getJSONObject(i);
                        usuario.setIdasesorado(jsonObject.optInt("idasesorado"));
                        usuario.setNombre(jsonObject.optString("nombre"));
                        usuario.setApellidop(jsonObject.optString("apellidop"));
                        usuario.setApellidom(jsonObject.optString("apellidom"));
                        usuario.setAltura(jsonObject.optString("altura") +" cm");
                        usuario.setPeso(jsonObject.optString("peso")+" kilos");
                        usuario.setTalla(jsonObject.optString("talla") +" cm");
                        usuario.setSexo(jsonObject.optString("sexo"));
                        usuario.setFechanacimiento(jsonObject.optString("fechanacimiento"));
                        usuario.setEstado(jsonObject.optString("estado"));
                        usuario.setNombreEntrenador(jsonObject.optString("nombreEntrenador"));
                        usuario.setNombreRutina(jsonObject.optString("nombrerutina"));
                        usuario.setEdad(jsonObject.optString("edad")+" años");
                        listaUsuarios.add(usuario);
                    }
                    progress.hide();
                    UsuarioAdapter adapter=new UsuarioAdapter(listaUsuarios);
                    recyclerUsuarios.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Usted no tiene asesorados registrados" , Toast.LENGTH_LONG).show();
                    progress.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getContext(), "No se puede conectar "+error.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Usted no tiene asesorados registrados", Toast.LENGTH_LONG).show();
                System.out.println();
                Log.d("ERROR: ", error.toString());
                progress.hide();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}