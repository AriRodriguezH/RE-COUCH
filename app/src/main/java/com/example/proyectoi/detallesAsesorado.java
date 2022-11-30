package com.example.proyectoi;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class detallesAsesorado extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detalles_asesorado, container, false);
        TextView text = (TextView) view.findViewById(R.id.titulo);//Find textview Id
        TextView ids= (TextView) view.findViewById(R.id.vvv);
        TextView textapm = (TextView) view.findViewById(R.id.apellidoMDA);//Find textview Id
        TextView textapp = (TextView) view.findViewById(R.id.tituldo);//Find textview Id
        TextView textSexo =(TextView) view.findViewById(R.id.selectSex);
        TextView textFechaNacimiento =(TextView) view.findViewById(R.id.idfechanacimientoDA);
        TextView textAltura =(TextView) view.findViewById(R.id.idAlturaDA);
        TextView textPeso =(TextView) view.findViewById(R.id.idPesoDA);
        TextView textTalla =(TextView) view.findViewById(R.id.idTallaDA);
        TextView textEstado =(TextView) view.findViewById(R.id.selectEstado);
        TextView textEntrenador =(TextView) view.findViewById(R.id.idEntrenadorDA);
        TextView textEdad =(TextView) view.findViewById(R.id.idEdadDA);
        TextView textrutina =(TextView) view.findViewById(R.id.idRutinaDA);
        String getArgument = getArguments().getString("NombreDetalle");
        String getArgumentid = getArguments().getString("numbreid");
        String getArguments = getArguments().getString("NombreApellidoP");
        String getArgumentsapm = getArguments().getString("NombreApellidoM");
        String getArgumentSexo = getArguments().getString("Sexo");
        String getArgumentFechaNa = getArguments().getString("FechaNacimiento");
        String getArgumentAltura = getArguments().getString("Altura");
        String getArgumentPeso = getArguments().getString("Peso");
        String getArgumentTalla = getArguments().getString("Talla");
        String getArgumentEstado = getArguments().getString("Estado");
        String getArgumentEntrenador= getArguments().getString("idEntrenador");
        String getArgumentEdad= getArguments().getString("Edad");
        String getArgumentRutina= getArguments().getString("NombreR");
        ids.setText(getArgumentid);
        text.setText(getArgument);//set string over textview
        textapp.setText(getArguments);
        textapm.setText(getArgumentsapm);
        textSexo.setText(getArgumentSexo);
        textFechaNacimiento.setText(getArgumentFechaNa);
        textAltura.setText(getArgumentAltura);
        textPeso.setText(getArgumentPeso);
        textTalla.setText(getArgumentTalla);
        textEstado.setText(getArgumentEstado);
        textEntrenador.setText(getArgumentEntrenador);
        textEdad.setText(getArgumentEdad);
        textrutina.setText(getArgumentRutina);

        return view;//return view

    }

    //Traspase de las navegaciones de los botones
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Switch editar = view.findViewById(R.id.fabi);
        Button btnGuardarD= view.findViewById(R.id.btnGuardarDetalles);
        AutoCompleteTextView miid= view.findViewById(R.id.vvv);
        AutoCompleteTextView sexo = view.findViewById(R.id.selectSex);
        AutoCompleteTextView nombre= view.findViewById(R.id.titulo);
        AutoCompleteTextView app= view.findViewById(R.id.tituldo);
        AutoCompleteTextView apm= view.findViewById(R.id.apellidoMDA);
        AutoCompleteTextView peso= view.findViewById(R.id.idPesoDA);
        AutoCompleteTextView altura= view.findViewById(R.id.idAlturaDA);
        AutoCompleteTextView talla= view.findViewById(R.id.idTallaDA);
        AutoCompleteTextView estado= view.findViewById(R.id.selectEstado);
        AutoCompleteTextView fechaNA= view.findViewById(R.id.idfechanacimientoDA);
        TextInputLayout CuadroS= view.findViewById(R.id.idSejxoDA);
        TextInputLayout CuadroE= view.findViewById(R.id.idEstadoDA);
        ImageView Eliminar= view.findViewById(R.id.deleteAsesorado);

        /*Apartado de la Elección del Sexo*/
        AutoCompleteTextView autoCompleteTextView;
        autoCompleteTextView = view.findViewById(R.id.selectSex);

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
        autoCompleteTextViewEstado = view.findViewById(R.id.selectEstado);

        String[]Estados = new String[]{
                "Activo",
                "Inactivo"
        };
        ArrayAdapter<String> adapterE = new ArrayAdapter<>(
                getActivity(), R.layout.dropdown_item_estadogym,Estados
        );
        autoCompleteTextViewEstado.setAdapter(adapterE);
        /*Fin apartado de elección del Estado*/

        fechaNA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.idfechanacimientoDA:
                        showDatePickerDialog(fechaNA);
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


        editar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    fechaNA.setEnabled(true);
                    nombre.setEnabled(true);
                    app.setEnabled(true);
                    apm.setEnabled(true);
                    CuadroS.setEnabled(true);
                    CuadroE.setEnabled(true);
                    sexo.setEnabled(true);
                    sexo.setClickable(true);
                    sexo.setFocusable(true);
                    peso.setEnabled(true);
                    altura.setEnabled(true);
                    talla.setEnabled(true);
                    estado.setEnabled(true);
                    estado.setFocusable(true);
                    estado.setClickable(true);
                    btnGuardarD.setVisibility(View.VISIBLE);
                }else {
                    fechaNA.setEnabled(false);
                    nombre.setEnabled(false);
                    app.setEnabled(false);
                    apm.setEnabled(false);
                    peso.setEnabled(false);
                    altura.setEnabled(false);
                    talla.setEnabled(false);
                    CuadroS.setEnabled(false);
                    CuadroE.setEnabled(false);
                    sexo.setEnabled(false);
                    sexo.setClickable(false);
                    sexo.setFocusable(false);
                    estado.setFocusable(false);
                    estado.setEnabled(false);
                    estado.setClickable(false);
                    btnGuardarD.setVisibility(View.GONE);
                }
            }
        });

        RequestQueue queueeu = Volley.newRequestQueue(getActivity());


        btnGuardarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarAsesorado();
            }

            private void ActualizarAsesorado(){
                String IDEA= miid.getText().toString().trim();
                String NombreEA = nombre.getText().toString().trim();
                String ApellidoPEA = app.getText().toString().trim();
                String ApellidoMEA = apm.getText().toString().trim();
                String fechaNacimientoEA = fechaNA.getText().toString().trim();
                String PesoEA = peso.getText().toString().trim();
                String AlturaEA = altura.getText().toString().trim();
                String TallaEA = talla.getText().toString().trim();
                String SexoEA = sexo.getText().toString().trim();
                String EstadoEA = estado.getText().toString().trim();

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Cargando");

                if(NombreEA.isEmpty()){
                    nombre.setError("Complete el campo de Nombre");
                    return;
                }else if (ApellidoPEA.isEmpty()){
                    app.setError("Completa el campo de Apellido Paterno");
                }else if (ApellidoMEA.isEmpty()){
                    apm.setError("Completa el campo de Apellido Materno");
                }else if (fechaNacimientoEA.isEmpty()){
                    fechaNA.setError("Completa el campo de Fecha de Nacimiento");
                }else if (PesoEA.isEmpty()){
                    peso.setError("Completa el campo de Número de Telefono");
                }else if (SexoEA.isEmpty()){
                    sexo.setError("Completa el campo de Sexo");
                }else if (AlturaEA.isEmpty()){
                    altura.setError("Completa el campo de Correo");
                }else if (TallaEA.isEmpty()){
                    talla.setError("Completa el campo de Contraseña");
                }else if (EstadoEA.isEmpty()){
                    estado.setError("Completa el campo de Fecha de Actualización");
                }else if (IDEA.isEmpty()){
                    miid.setError("Completa el campo de Fecha de Actualización");
                }else {
                    progressDialog.show();
                    String url="https://gdxblackstar.000webhostapp.com/updateAsesorado.php";
                    String urlLocal="http://192.168.1.65/Alumno/updateasesorado.php";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, urlLocal, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE).setTitleText("Asesorado actualizado con éxito").show();
                            Navigation.findNavController(view).navigate(R.id.action_consultarAsesorados_self);
                            //Toast.makeText(getActivity(), "Actualización exitosa", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("idasesorado", miid.getText().toString().trim());
                            params.put("nombre",nombre.getText().toString().trim());
                            params.put("apellidop",app.getText().toString().trim());
                            params.put("apellidom",apm.getText().toString().trim());
                            params.put("fechanacimiento",fechaNA.getText().toString().trim());
                            params.put("sexo",sexo.getText().toString().trim());
                            params.put("peso",peso.getText().toString().trim());
                            params.put("talla",talla.getText().toString().trim());
                            params.put("altura",altura.getText().toString().trim());
                            params.put("estado",estado.getText().toString().trim());
                            return params;
                        }
                    };
                    queueeu.add(stringRequest);
                }

            }
        });

        RequestQueue queueeuEliminar = Volley.newRequestQueue(getActivity());
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog( getActivity(), SweetAlertDialog.WARNING_TYPE).setTitleText("¿Estas seguro de eliminar a este asesorado?")
                                .setContentText("No se podrá recuperar este Asesorado ")
                                .setCancelText("No, cancelar acción")
                                .setConfirmText("Si, eliminar asesorado")
                                .showCancelButton(true)
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                       sweetAlertDialog.setTitleText("Acción cancelada")
                                               .setContentText("Asesorado no eliminado")
                                               .setConfirmText("Okey")
                                               .showCancelButton(false)
                                               .setCancelClickListener(null)
                                               .setConfirmClickListener(null)
                                               .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    }
                                }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.setTitleText("Eliminación Confirmada")
                                        .setContentText("El Asesorado se eliminó con éxito")
                                        .setConfirmText("Okey")
                                        .showCancelButton(false)
                                        .setCancelClickListener(null)
                                        .setConfirmClickListener(null)
                                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                EliminarAsesordo();
                            }
                        }).show();
            }

            private void EliminarAsesordo() {
                String ureliminar = "https://gdxblackstar.000webhostapp.com/deleteAsesorado.php";
                String urleliminarLocal="http://192.168.1.65/Alumno/deleteAsesorado.php";
                StringRequest stringRequest2 = new StringRequest(Request.Method.POST, urleliminarLocal, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Navigation.findNavController(view).navigate(R.id.action_consultarAsesorados_self);
                        //Toast.makeText(getActivity(), "Asesorado Eliminado", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params2 = new HashMap<String, String>();
                        params2.put("idasesorado", miid.getText().toString());
                        return params2;
                    }
                };
                queueeuEliminar.add(stringRequest2);
            }
        });

        FloatingActionButton btnVolverIcnio = view.findViewById(R.id.btnRegresarDA);

        btnVolverIcnio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_consultarAsesorados_self);
            }
        });

    }

}