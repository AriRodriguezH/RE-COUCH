package com.example.proyectoi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
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

public class registroAsesorado extends Fragment {
    Spinner SpinerEntreador;
    Spinner SpinerRutina;
    ArrayList<String> nombreElist = new ArrayList<>();
    ArrayList<String> idlist = new ArrayList<>();
    ArrayList<String> nombreRlist = new ArrayList<>();
    ArrayList<String> idRlist = new ArrayList<>();
    ArrayAdapter<String> NombreAdapter;
    RequestQueue requestQueueEntrenador;
    ArrayAdapter<String> NombreRAdapter;
    RequestQueue requestQueueRutina;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro_asesorado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        EditText nombre = view.findViewById(R.id.etNombreRA);
        EditText apellidop = view.findViewById(R.id.etapellidoPRA);
        EditText apellidom = view.findViewById(R.id.etapellidoMRA);
        EditText fechanacimiento = view.findViewById(R.id.etfechaNacimientoRA);
        EditText altura = view.findViewById(R.id.etAlturaRA);
        EditText peso = view.findViewById(R.id.etPesoRA);
        EditText talla = view.findViewById(R.id.etTallaRA);
        EditText sexo = view.findViewById(R.id.etSexoRA);
        EditText estadoG = view.findViewById(R.id.etEstadoRA);
        EditText fecharegistro = view.findViewById(R.id.etFechaRegistroRA);
        EditText fechaInicioR = view.findViewById(R.id.etFechaInicioRutinaRA);
        EditText fechaFinR = view.findViewById(R.id.etFechaFinRutinaRA);
        Button RegistrarA = view.findViewById(R.id.btnRegistroAsesorado);
        requestQueueRutina = Volley.newRequestQueue(getContext());
        requestQueueEntrenador = Volley.newRequestQueue(getContext());
        SpinerEntreador= view.findViewById(R.id.etIdEntrenadorRA);
        SpinerRutina = view.findViewById(R.id.etRutinaRA);
        FloatingActionButton btnVolverIRA = view.findViewById(R.id.btnRegresarRA);

        /*SPINER FUNCIÓN*/
        String url2 = "http://192.168.1.65/Alumno/spinerRutina.php";
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST,
                url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("rutina");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String RutinaName = jsonObject.optString("nombrerutina");
                        String RutinaID = jsonObject.optString("idrutina");
                        nombreRlist.add(RutinaName);
                        idRlist.add(RutinaID);
                        NombreRAdapter = new ArrayAdapter<>(getActivity(),
                                R.layout.spinner_rutina, nombreRlist);
                        NombreRAdapter.setDropDownViewResource(R.layout.spinner_rutina);
                        SpinerRutina.setAdapter(NombreRAdapter);

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
        requestQueueRutina.add(jsonObjectRequest2);
        SpinerRutina.setAdapter(NombreRAdapter);

        String url3 = "http://192.168.1.65/Alumno/spinerEntrenador.php";
        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(Request.Method.POST,
                url3, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("entrenador");
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String EntrenadorName = jsonObject.optString("nombre");
                        String EntrenadorID = jsonObject.optString("identrenador");
                        nombreElist.add(EntrenadorName);
                        idlist.add(EntrenadorID);
                        NombreAdapter = new ArrayAdapter<>(getActivity(),
                                R.layout.spinner_entrenador, nombreElist);
                        NombreAdapter.setDropDownViewResource(R.layout.spinner_entrenador);
                        SpinerEntreador.setAdapter(NombreAdapter);

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
        requestQueueEntrenador.add(jsonObjectRequest3);
        SpinerEntreador.setAdapter(NombreAdapter);


        /*Inicio Metodo de Fechas visualización*/
        fecharegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etFechaRegistroRA:
                        showDatePickerDialog(fecharegistro);
                        break;
                }
            }

            private void showDatePickerDialog(final EditText editText) {
                datePickerFragment newFragment = datePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        final String selectedDate =  year+ "-" + twoDigits(month+1)+"-"+ twoDigits(day);
                        editText.setText(selectedDate);
                    }
                });

                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }

            private String twoDigits(int n) {
                return (n<=9) ? ("0"+n) : String.valueOf(n);
            }
        });

        fechanacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfechaNacimientoRA:
                        showDatePickerDialog(fechanacimiento);
                        break;
                }
            }

            private void showDatePickerDialog(final EditText editText) {
                datePickerFragment newFragment = datePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        final String selectedDate =  year+ "-" + twoDigits(month+1)+"-"+ twoDigits(day);
                        editText.setText(selectedDate);
                    }
                });

                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }

            private String twoDigits(int n) {
                return (n<=9) ? ("0"+n) : String.valueOf(n);
            }
        });

        fechaInicioR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etFechaInicioRutinaRA:
                        showDatePickerDialog(fechaInicioR);
                        break;
                }
            }

            private void showDatePickerDialog(final EditText editText) {
                datePickerFragment newFragment = datePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        final String selectedDate =  year+ "-" + twoDigits(month+1)+"-"+ twoDigits(day);
                        editText.setText(selectedDate);
                    }
                });

                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }

            private String twoDigits(int n) {
                return (n<=9) ? ("0"+n) : String.valueOf(n);
            }
        });

        fechaFinR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etFechaFinRutinaRA:
                        showDatePickerDialog(fechaFinR);
                        break;
                }
            }

            private void showDatePickerDialog(final EditText editText) {
                datePickerFragment newFragment = datePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        final String selectedDate =  year+ "-" + twoDigits(month+1)+"-"+ twoDigits(day);
                        editText.setText(selectedDate);
                    }
                });

                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }

            private String twoDigits(int n) {
                return (n<=9) ? ("0"+n) : String.valueOf(n);
            }
        });
        /*Fin Metodo de Fechas visualización*/

        /*Apartado de la Elección del Sexo*/
        AutoCompleteTextView autoCompleteTextView;
        autoCompleteTextView = view.findViewById(R.id.etSexoRA);

        String[]sexos = new String[]{
                "Hombre",
                "Mujer"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_sexo,sexos
        );
        autoCompleteTextView.setAdapter(adapter);
        /*Fin apartado de elección del sexo*/

        /*Apartado de la Elección del Estado*/
        AutoCompleteTextView autoCompleteTextViewEstado;
        autoCompleteTextViewEstado = view.findViewById(R.id.etEstadoRA);

        String[]Estados = new String[]{
                "Activo",
                "Inactivo"
        };
        ArrayAdapter<String> adapterE = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_estadogym,Estados
        );
        autoCompleteTextViewEstado.setAdapter(adapterE);
        /*Fin apartado de elección del Estado*/

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        RegistrarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistraDatosA();
            }

            private void RegistraDatosA() {
                String NombreRA = nombre.getText().toString().trim();
                String ApellidoPRA = apellidop.getText().toString().trim();
                String ApellidoMRA = apellidom.getText().toString().trim();
                String fechaNacimientoRA = fechanacimiento.getText().toString().trim();
                String Altura = altura.getText().toString().trim();
                String Peso = peso.getText().toString().trim();
                String Talla = talla.getText().toString().trim();
                String Sexo = sexo.getText().toString().trim();
                String FechaRegistro = fecharegistro.getText().toString().trim();
                String FechaInicioR= fechaInicioR.getText().toString().trim();
                String FechaFinR= fechaFinR.getText().toString().trim();
                String EstadoAsesorado = estadoG.getText().toString().trim();
                String IdEntrenador = idlist.get(SpinerEntreador.getSelectedItemPosition()).trim();
                String IdRutina = idRlist.get(SpinerRutina.getSelectedItemPosition()).trim();
                Log.i("VALOR", IdRutina);

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Cargando");

                if(NombreRA.isEmpty()){
                    nombre.setError("Complete el campo de Nombre");
                    return;
                }else if (ApellidoPRA.isEmpty()){
                    apellidop.setError("Completa el campo de Apellido Paterno");
                }else if (ApellidoMRA.isEmpty()){
                    apellidom.setError("Completa el campo de Apellido Materno");
                }else if (fechaNacimientoRA.isEmpty()){
                    fechanacimiento.setError("Completa el campo de Fecha de Nacimiento");
                }else if (Altura.isEmpty()){
                    altura.setError("Completa el campo de Altura");
                }else if (Peso.isEmpty()){
                    peso.setError("Completa el campo de Peso");
                }else if (Talla.isEmpty()){
                    talla.setError("Completa el campo de Talla");
                }else if (Sexo.isEmpty()){
                    sexo.setError("Completa el campo de Sexo");
                }else if (FechaRegistro.isEmpty()){
                    fecharegistro.setError("Completa el campo de Fecha de Registro");
                }else if (FechaInicioR.isEmpty()){
                    fechaInicioR.setError("Completa el campo de Fecha de Registro");
                }else if (FechaFinR.isEmpty()){
                    fechaFinR.setError("Completa el campo de Fecha de Registro");
                }else if (EstadoAsesorado.isEmpty()){
                    estadoG.setError("Completa el campo de Fecha de Registro");
                }else if (IdEntrenador.isEmpty()&& idlist.isEmpty()){
                    //  SpinerEntreador.getSelectedView().setEr
                    SpinerEntreador.getAdapter().isEmpty();
                    idlist.isEmpty();
                    //idlist.get(SpinerEntreador.getTextAlignment());
                }
                else if (IdRutina.isEmpty()&& idRlist.isEmpty()){
                      SpinerEntreador.getAdapter().isEmpty();
                      idRlist.isEmpty();
                }else {
                    progressDialog.show();
                    String url="http://192.168.1.65/Alumno/insertAsesorado.php?nombre="+NombreRA+"&apellidop="+ApellidoPRA+"&apellidom="+ApellidoMRA+
                            "&fechanacimiento="+fechaNacimientoRA+"&altura="+Altura+"&peso="+Peso+"&talla="+Talla+"&sexo="+Sexo+"&fecharegistro="+FechaRegistro+"&estado="+EstadoAsesorado+"&identrenador="+IdEntrenador+"&idrutina="+IdRutina+"&fechainicio="+FechaInicioR+"&fechafinalizacion="+FechaFinR;

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET, url , null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            nombre.setText("");
                            apellidop.setText("");
                            apellidom.setText("");
                            fechanacimiento.setText("");
                            altura.setText("");
                            peso.setText("");
                            talla.setText("");
                            sexo.setText("");
                            fecharegistro.setText("");
                            fechaInicioR.setText("");
                            fechaFinR.setText("");
                            estadoG.setText("");
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
        });

        btnVolverIRA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_registroAsesorado_to_inMenu);
            }
        });
    }
}