package com.example.proyectoi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Inicio extends Fragment {
    private final String KEY_SUCCESS = "status";
    EditText correo, contrasenia;
    private PreferenceHelper preferenceHelper;
    String Correo,Contrasenia;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){

        RequestQueue requestQueue;
        correo= view.findViewById(R.id.txtEmail);
        contrasenia= view.findViewById(R.id.etPass);
        CheckBox SavePreferences = view.findViewById(R.id.guardarPreferencias);

        preferenceHelper = new PreferenceHelper(getContext());

        Button IniciarSesion = view.findViewById(R.id.btnIniciaSesion);
        Button Registrar = view.findViewById(R.id.btnCrearCuenta);
        requestQueue = Volley.newRequestQueue(getActivity());


        reloadPreferencias();
        IniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario();
            }

            //Funcion del ingreso de Sesión
            private void validarUsuario(){
                Correo = correo.getText().toString().trim();
                Contrasenia = contrasenia.getText().toString().trim();

                String url = "http://192.168.1.12/Alumno/simplelogin.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (Correo.isEmpty() && Contrasenia.isEmpty()) {
                            correo.setError("Complete el campo de Correo");
                            contrasenia.setError("Completa el campo de Contrasenia");
                            return;
                            //Toast.makeText(getActivity(), "Ingrese su correo", Toast.LENGTH_SHORT).show();
                        } else if (Correo.isEmpty()) {
                            correo.setError("Complete el campo de Correo");
                            //Toast.makeText(getActivity(), "Ingrese su correo", Toast.LENGTH_SHORT).show();
                        } else if (Contrasenia.isEmpty()) {
                            contrasenia.setError("Completa el campo de Contrasenia");
                            //Toast.makeText(getActivity(), "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                        } else {
                            preferenceHelper.putIsLogin(true);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getString(KEY_SUCCESS).equals("true")) {
                                    JSONArray dataArray = jsonObject.getJSONArray("data");
                                    for (int i = 0; i < dataArray.length(); i++) {
                                        guardarDatosInicio();
                                       Toast.makeText(getActivity().getApplicationContext(), "Bienvenido ", Toast.LENGTH_SHORT).show();
                                        Navigation.findNavController(view).navigate(R.id.action_inicio_to_inMenu);
                                        JSONObject dataobj = dataArray.getJSONObject(i);
                                        preferenceHelper.putName(dataobj.getString(AndyConstants.Params.NAME));
                                       preferenceHelper.putHobby(dataobj.getString(AndyConstants.Params.HOBBY));
                                    }
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Error" , Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("correo",correo.getText().toString().trim());
                        hashMap.put("contrasenia",contrasenia.getText().toString().trim());
                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });


        //Traspase al Registro de nuevos usuarios
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_inicio_to_registro);
            }
        });

    }

    private  void guardarDatosInicio(){
        SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasInicioS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion", true);
        editor.putString("correo", Correo);
        editor.putString("contrasenia", Contrasenia);
        editor.commit();
    }

    private void reloadPreferencias(){
        SharedPreferences preferences = getActivity().getSharedPreferences("preferenciasInicioS", Context.MODE_PRIVATE);
        correo.setText(preferences.getString("correo", null));
        contrasenia.setText(preferences.getString("contrasenia", null));
    }

}