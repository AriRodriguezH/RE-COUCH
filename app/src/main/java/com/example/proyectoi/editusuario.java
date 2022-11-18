package com.example.proyectoi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class editusuario extends Fragment {
    private PreferenceHelper preferenceHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_editusuario, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState){

        //Metodo para la creaciónd e una contraseña
        final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // al menos 1 caracter especial
                        "(?=\\S+$)" +            // sin espacios en blanco
                        ".{4,}" +                // al menos 4 caracteres
                        "$");

        EditText idUsers= view.findViewById(R.id.etIDEntrenadorEU);
        EditText nombre = view.findViewById(R.id.etNombreEU);
        EditText apellidop = view.findViewById(R.id.etapellidoPEU);
        EditText apellidom = view.findViewById(R.id.etapellidoMEU);
        EditText fechanacimiento = view.findViewById(R.id.etfechaNacimientoEU);
        EditText notelefono = view.findViewById(R.id.etnoTelefonoEU);
        EditText sexo = view.findViewById(R.id.etSexoEU);
        EditText correo = view.findViewById(R.id.etCorreoEU);
        EditText contrasenia = view.findViewById(R.id.etPasswordEU);
        EditText fechaactualizacion = view.findViewById(R.id.etfechaActualizacionEU);
        Button GuardarCambios = view.findViewById(R.id.btnEditarUser);
        FloatingActionButton Volverinicio = view.findViewById(R.id.btnRegresarEU);

        preferenceHelper = new PreferenceHelper(getContext());
        idUsers.setText(preferenceHelper.getHobby());


        /*Inicio Metodo de Fechas visualización*/
        fechaactualizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfechaActualizacionEU:
                        showDatePickerDialog(fechaactualizacion);
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
                    case R.id.etfechaNacimientoEU:
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
        /*Fin Metodo de Fechas visualización*/

        /*Apartado de la Elección del Sexo*/
        AutoCompleteTextView autoCompleteTextView;
        autoCompleteTextView = view.findViewById(R.id.etSexoEU);

        String[]sexos = new String[]{
                "Hombre",
                "Mujer"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_sexo,sexos
        );
        autoCompleteTextView.setAdapter(adapter);
        /*Fin apartado de elección del sexo*/

        RequestQueue queueeu = Volley.newRequestQueue(getActivity());

        GuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validateEmail() | !validatePassword()) {
                    return;
                }else {
                    ActualizarUsuario();
                }
            }


            /*----------------------VALIDACIONES CORREO Y CONTRASEÑA------------------------*/
            private boolean validateEmail() {
                // Extracto del  EditText
                String emailInput = correo.getText().toString().trim();

                // Si el campo de correo esta vacio
                if (emailInput.isEmpty()) {
                    correo.setError("Ingrese su Correo");
                    return false;
                }
                // comparaciones de la validacion de los email
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    correo.setError("Ingrese un correo valido");
                    return false;
                } else {
                    correo.setError(null);
                    return true;
                }
            }

            private boolean validatePassword() {
                String passwordInput = contrasenia.getText().toString().trim();
                // si el campo de contraseña está vacío
                // Se mostrará un mensaje de error "Field can not be empty"
                if (passwordInput.isEmpty()) {
                    contrasenia.setError("Ingrese una contraseña fuerte");
                    return false;
                }

                // si la contraseña no coincide con el patrón
                // mostrará un mensaje de error "La contraseña es demasiado débil"
                else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
                    contrasenia.setError("La contraseña es demasiado débil");
                    return false;
                } else {
                    contrasenia.setError(null);
                    return true;
                }
            }

            private void ActualizarUsuario(){
                String IDENTRENADOR = idUsers.getText().toString().trim();
                String NombreEU = nombre.getText().toString().trim();
                String ApellidoPEU = apellidop.getText().toString().trim();
                String ApellidoMEU = apellidom.getText().toString().trim();
                String fechaNacimientoEU = fechanacimiento.getText().toString().trim();
                String NumeroTelefonoEU = notelefono.getText().toString().trim();
                String SexoEU = sexo.getText().toString().trim();
                String emailEU = correo.getText().toString().trim();
                String ContraseniaEU = contrasenia.getText().toString().trim();
                String FechaActualizacionEU = fechaactualizacion.getText().toString().trim();


                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Cargando");

                if(NombreEU.isEmpty()){
                    nombre.setError("Complete el campo de Nombre");
                    return;
                }else if (ApellidoPEU.isEmpty()){
                    apellidop.setError("Completa el campo de Apellido Paterno");
                }else if (ApellidoMEU.isEmpty()){
                    apellidom.setError("Completa el campo de Apellido Materno");
                }else if (fechaNacimientoEU.isEmpty()){
                    fechanacimiento.setError("Completa el campo de Fecha de Nacimiento");
                }else if (NumeroTelefonoEU.isEmpty()){
                    notelefono.setError("Completa el campo de Número de Telefono");
                }else if (SexoEU.isEmpty()){
                    sexo.setError("Completa el campo de Sexo");
                }else if (emailEU.isEmpty()){
                    correo.setError("Completa el campo de Correo");
                }else if (ContraseniaEU.isEmpty()){
                    contrasenia.setError("Completa el campo de Contraseña");
                }else if (FechaActualizacionEU.isEmpty()){
                    fechaactualizacion.setError("Completa el campo de Fecha de Actualización");
                }else {
                    progressDialog.show();
                    String url="http://192.168.1.65/Alumno/updateEntrenador.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity(), "Actualización exitosa", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("identrenador", idUsers.getText().toString().trim());
                            params.put("nombre",nombre.getText().toString().trim());
                            params.put("apellidop",apellidop.getText().toString().trim());
                            params.put("apellidom",apellidom.getText().toString().trim());
                            params.put("fechanacimiento",fechanacimiento.getText().toString().trim());
                            params.put("sexo",sexo.getText().toString().trim());
                            params.put("correo",correo.getText().toString().trim());
                            params.put("contrasenia",contrasenia.getText().toString().trim());
                            params.put("notelefono",notelefono.getText().toString().trim());
                            params.put("fechaactualizacion",fechaactualizacion.getText().toString().trim());
                            return params;
                        }
                    };
                    queueeu.add(stringRequest);
                }

            }
        });

        Volverinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_editusuario_to_inMenu);
            }
        });

    }
}