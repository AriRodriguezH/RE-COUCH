package com.example.proyectoi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import cn.pedant.SweetAlert.SweetAlertDialog;

public class historicoAsesorado extends Fragment {
    Spinner spinnerCountry;
    ArrayList<String> countryList = new ArrayList<>();
    ArrayList<String> idlist = new ArrayList<>();
    ArrayAdapter<String> countryAdapter;
    EditText IDUser;
    private PreferenceHelper preferenceHelper;
    RequestQueue requestQueue2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_historico_asesorado, container, false);
        return vista;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        FloatingActionButton btnVolverI = view.findViewById(R.id.btnRegresar);
        Button btnAceptar = view.findViewById(R.id.aceptarHA);
        EditText Peso = view.findViewById(R.id.etPesoHA);
        EditText Altura = view.findViewById(R.id.etAlturaHA);
        EditText Talla = view.findViewById(R.id.etTallaHA);
        IDUser = view.findViewById(R.id.identrenadorHA);
        preferenceHelper = new PreferenceHelper(getContext());
        IDUser.setText(preferenceHelper.getHobby());
        requestQueue2 = Volley.newRequestQueue(getContext());
        spinnerCountry = view.findViewById(R.id.spinnerCountry);

        String IDEntrenador = IDUser.getText().toString();

        String url2 = "https://gdxblackstar.000webhostapp.com/spinerAsesorado.php?identrenador="+IDEntrenador;
        String url2local = "http://192.168.1.65/Alumno/spinerAsesorado.php?identrenador="+IDEntrenador;
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                url2local, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("asesorado");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String countryName = jsonObject.optString("nombre");
                        String countryID = jsonObject.optString("idasesorado");
                        countryList.add(countryName);
                        idlist.add(countryID);
                        countryAdapter = new ArrayAdapter<>(getActivity(),
                                R.layout.spinner_item, countryList);
                        countryAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spinnerCountry.setAdapter(countryAdapter);

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
        requestQueue2.add(jsonObjectRequest2);
        spinnerCountry.setAdapter(countryAdapter);

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        btnVolverI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_historicoAsesorado_to_inMenu);
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistraHistoricoA();
            }

            private void RegistraHistoricoA() {

                String AlturaHA = Altura.getText().toString().trim();
                String PesoHA = Peso.getText().toString().trim();
                String TallaHA = Talla.getText().toString().trim();
                String IDASESORADO = idlist.get(spinnerCountry.getSelectedItemPosition()).trim();
                Log.i("VALOR", IDASESORADO);


                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Cargando");

                if (AlturaHA.isEmpty()&&PesoHA.isEmpty()&&TallaHA.isEmpty()) {
                    Altura.setError("Completa el campo de Altura");
                    Peso.setError("Completa el campo de Peso");
                    Talla.setError("Completa el campo de Talla");
                } else if (AlturaHA.isEmpty()) {
                    Altura.setError("Completa el campo de Altura");
                }else if (PesoHA.isEmpty()) {
                    Peso.setError("Completa el campo de Peso");
                } else if (TallaHA.isEmpty()) {
                    Talla.setError("Completa el campo de Talla");
                } else if (IDASESORADO.isEmpty() && idlist.isEmpty()) {
                    spinnerCountry.getAdapter().isEmpty();
                } else {
                    progressDialog.show();
                    String url = "https://gdxblackstar.000webhostapp.com/insertHistoricoasesorado.php?altura=" + AlturaHA +
                            "&peso=" + PesoHA +
                            "&talla=" + TallaHA +
                            "&idasesorado=" + IDASESORADO;

                    String urllocal = "http://192.168.1.65/Alumno/insertHistoricoasesorado.php?altura=" + AlturaHA +
                            "&peso=" + PesoHA +
                            "&talla=" + TallaHA +
                            "&idasesorado=" + IDASESORADO;

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET, urllocal, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Histórico Agregado").show();
                            //Toast.makeText(view.getContext(), response.toString(), Toast.LENGTH_LONG).show();
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
        });

    }
}