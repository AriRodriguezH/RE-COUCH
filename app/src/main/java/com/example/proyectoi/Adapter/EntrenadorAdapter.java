package com.example.proyectoi.Adapter;

import static com.example.proyectoi.R.layout.fragment_editusuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoi.R;
import com.example.proyectoi.editusuario;
import com.example.proyectoi.entidades.Entrenador;

import java.util.List;

public class EntrenadorAdapter  extends RecyclerView.Adapter<EntrenadorAdapter.EntrenadoresHolder> {
    List<Entrenador> listaEntrenadores;
    public EntrenadorAdapter(List<Entrenador> listaEntrenadores) {
        this.listaEntrenadores = listaEntrenadores;
    }

    @NonNull
    @Override
    public EntrenadoresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(fragment_editusuario,parent,false);
        return new EntrenadorAdapter.EntrenadoresHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EntrenadoresHolder holder, int position) {
        holder.txtNombre.setText(listaEntrenadores.get(position).getNombre().toString());
        //holder.txtNombre.setId(listaEntrenadores.get(position).getIdentrenador());
        holder.TXTID.setId(listaEntrenadores.get(position).getIdentrenador());
        holder.TXTID.setText(listaEntrenadores.get(position).getIdentrenador());
        /*holder.txtApellidoP.setText(listaEntrenadores.get(position).getApellidop().toString());
        holder.txtApellidoM.setText(listaEntrenadores.get(position).getApellidom().toString());
        holder.txtCorreo.setText(listaEntrenadores.get(position).getCorreo().toString());
        holder.txtContrasenia.setText(listaEntrenadores.get(position).getContrasenia().toString());
        holder.txtNoTelefono.setText(listaEntrenadores.get(position).getNotelefono().toString());
        holder.txtSexo.setText(listaEntrenadores.get(position).getSexo().toString());
        holder.txtFechaActu.setText(listaEntrenadores.get(position).getFechaactualizacion().toString());
        holder.txtfechasNacimiento.setText(listaEntrenadores.get(position).getFechanacimiento().toString());*/

    }

    @Override
    public int getItemCount() {
        return listaEntrenadores.size();
    }

    public class EntrenadoresHolder extends RecyclerView.ViewHolder{

        TextView txtNombre,txtApellidoP,txtApellidoM, txtCorreo,txtContrasenia,txtNoTelefono,txtFechaActu,txtfechasNacimiento,txtSexo,TXTID;

        public EntrenadoresHolder(View itemView) {
            super(itemView);
            txtNombre= (TextView) itemView.findViewById(R.id.etNombreEU);
            txtApellidoP = (TextView) itemView.findViewById(R.id.etapellidoPEU);
            txtApellidoM = (TextView) itemView.findViewById(R.id.etapellidoMEU);
            txtSexo =(TextView)itemView.findViewById(R.id.etSexoEU);
            TXTID=(TextView)itemView.findViewById(R.id.txtid);
            txtCorreo = (TextView) itemView.findViewById(R.id.etCorreoEU);
            txtContrasenia = (TextView) itemView.findViewById(R.id.etPasswordEU);
            txtNoTelefono = (TextView) itemView.findViewById(R.id.etnoTelefonoEU);
            txtFechaActu = (TextView) itemView.findViewById(R.id.etfechaActualizacionEU);
            txtfechasNacimiento = (TextView) itemView.findViewById(R.id.etfechaNacimientoEU);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activitys = (AppCompatActivity) view.getContext();
                    editusuario myFragments = new editusuario();
                    Bundle datas = new Bundle();//Use bundle to pass data
                   // data.putString("numbreid", EntrenadoresHolder.this.TXTID.getText().toString());
                    datas.putString("NombreEntrenador", EntrenadorAdapter.EntrenadoresHolder.this.txtNombre.getText().toString());
                   /* data.putString("ApellidoPE", EntrenadorAdapter.EntrenadoresHolder.this.txtApellidoP.getText().toString());
                    data.putString("ApellidoME", EntrenadorAdapter.EntrenadoresHolder.this.txtApellidoM.getText().toString());
                    data.putString("SexoE", EntrenadoresHolder.this.txtSexo.getText().toString());
                    data.putString("ContraseniaE", EntrenadoresHolder.this.txtContrasenia.getText().toString());
                    data.putString("CorreoE", EntrenadoresHolder.this.txtCorreo.getText().toString());
                    data.putString("FechaNacimientoE", EntrenadorAdapter.EntrenadoresHolder.this.txtfechasNacimiento.getText().toString());
                    data.putString("FechaActualizaciónA", EntrenadoresHolder.this.txtFechaActu.getText().toString());
                    data.putString("NoTelefonoE", EntrenadoresHolder.this.txtNoTelefono.getText().toString());*/
                    myFragments.setArguments(datas);
                    activitys.getSupportFragmentManager().beginTransaction().replace(R.id.llll, myFragments).addToBackStack(null).commit();
                }
            });


        }
    }
}
