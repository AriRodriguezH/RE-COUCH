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

import org.json.JSONObject;

import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Registro extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){

        final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "(?=\\S+$)" +            // no white spaces
                        ".{4,}" +                // at least 4 characters
                        "$");

        EditText nombre = view.findViewById(R.id.etNombre);
        EditText apellidop = view.findViewById(R.id.etapellidoP);
        EditText apellidom = view.findViewById(R.id.etapellidoM);
        EditText fechanacimiento = view.findViewById(R.id.etfechaNacimiento);
        EditText notelefono = view.findViewById(R.id.etnoTelefono);
        EditText sexo = view.findViewById(R.id.etSexo);
        EditText correo = view.findViewById(R.id.etCorreo);
        EditText contrasenia = view.findViewById(R.id.etPassword);
        Button Registrar = view.findViewById(R.id.btnRegistro);
        FloatingActionButton VolverPrincipal = view.findViewById(R.id.btnRegresarRegistro);


        /*Inicio Metodo de Fechas visualización*/

        fechanacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfechaNacimiento:
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
        autoCompleteTextView = view.findViewById(R.id.etSexo);

        String[]sexos = new String[]{
                "Hombre",
                "Mujer"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_sexo,sexos
        );

        autoCompleteTextView.setAdapter(adapter);
        /*Fin apartado de elección del sexo*/

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }else {
                    RegistraDatos();
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


            private void RegistraDatos() {
                String Nombre = nombre.getText().toString().trim();
                String ApellidoP = apellidop.getText().toString().trim();
                String ApellidoM = apellidom.getText().toString().trim();
                String fechaNacimiento = fechanacimiento.getText().toString().trim();
                String NumeroTelefono = notelefono.getText().toString().trim();
                String Sexo = sexo.getText().toString().trim();
                String email= correo.getText().toString().trim();
                String Contrasenia = contrasenia.getText().toString().trim();

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Cargando");

                if (Nombre.isEmpty() && ApellidoP.isEmpty() && ApellidoM.isEmpty()&&fechaNacimiento.isEmpty()&&NumeroTelefono.isEmpty()&&Sexo.isEmpty()&&email.isEmpty()&&Contrasenia.isEmpty()) {
                    nombre.setError("Complete el campo de Nombre");
                    apellidop.setError("Completa el campo de Apellido Paterno");
                    apellidom.setError("Completa el campo de Apellido Materno");
                    fechanacimiento.setError("Completa el campo de Fecha de Nacimiento");
                    notelefono.setError("Completa el campo de Número de Telefono");
                    sexo.setError("Completa el campo de Sexo");
                    correo.setError("Complete el campo de Correo");
                    contrasenia.setError("Completa el campo de Contrasenia");
                    return;
                    //Toast.makeText(getActivity(), "Ingrese su correo", Toast.LENGTH_SHORT).show();
                }else if(Nombre.isEmpty()){
                    nombre.setError("Complete el campo de Nombre");
                }else if (ApellidoP.isEmpty()){
                    apellidop.setError("Completa el campo de Apellido Paterno");
                }else if (ApellidoM.isEmpty()){
                    apellidom.setError("Completa el campo de Apellido Materno");
                }else if (fechaNacimiento.isEmpty()){
                    fechanacimiento.setError("Completa el campo de Fecha de Nacimiento");
                }else if (NumeroTelefono.isEmpty()){
                    notelefono.setError("Completa el campo de Número de Telefono");
                }else if (Sexo.isEmpty()){
                    sexo.setError("Completa el campo de Sexo");
                }else if (email.isEmpty()){
                    correo.setError("Completa el campo de Correo");
                }else if (Contrasenia.isEmpty()){
                    contrasenia.setError("Completa el campo de Contraseña");
                }else {
                    progressDialog.show();

                    String url="https://gdxblackstar.000webhostapp.com/insertEntrenador.php?nombre="+Nombre+
                            "&apellidop="+ApellidoP+
                            "&apellidom="+ApellidoM+
                            "&fechanacimiento="+fechaNacimiento+
                            "&notelefono="+NumeroTelefono+
                            "&sexo="+Sexo+
                            "&correo="+email+
                            "&contrasenia="+ Contrasenia;

                    String urllocal="http://192.168.1.65/Alumno/insertEntrenador.php?nombre="+Nombre+
                            "&apellidop="+ApellidoP+
                            "&apellidom="+ApellidoM+
                            "&fechanacimiento="+fechaNacimiento+
                            "&notelefono="+NumeroTelefono+
                            "&sexo="+Sexo+
                            "&correo="+email+
                            "&contrasenia="+ Contrasenia;

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                            Request.Method.GET, urllocal, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Usuario Registrado").show();
                            nombre.setText("");
                            apellidop.setText("");
                            apellidom.setText("");
                            fechanacimiento.setText("");
                            notelefono.setText("");
                            sexo.setText("");
                            correo.setText("");
                            contrasenia.setText("");
                            progressDialog.dismiss();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE).setTitleText("Usuario No Registrado, compruebe los datos ingresados").show();
                            //Toast.makeText(view.getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                    );
                    queue.add(jsonObjectRequest);
                }
            }
        });

        VolverPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_registro_to_inicio);
            }
        });
    }


}