package com.example.proyectoi.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoi.R;
import com.example.proyectoi.detallesAsesorado;
import com.example.proyectoi.entidades.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuariosHolder> implements View.OnClickListener {
    List<Usuario> listaUsuarios;
    CardView cv;
    private View.OnClickListener listener;

    public UsuarioAdapter(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public UsuariosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        cv = vista.findViewById(R.id.cardcv);
        cv.setAnimation(AnimationUtils.loadAnimation(vista.getContext(),R.anim.fade_transition));
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        vista.setOnClickListener(this);
        return new UsuariosHolder(vista);
    }

    @Override
    public void onBindViewHolder(UsuariosHolder holder, int position) {

        holder.txtNombre.setText(listaUsuarios.get(position).getNombre().toString());
        holder.txtApellidoP.setText(listaUsuarios.get(position).getApellidop().toString());
        holder.txtApellidoM.setText(listaUsuarios.get(position).getApellidom().toString());
        holder.txtaltura.setText(listaUsuarios.get(position).getAltura().toString());
        holder.txtpeso.setText(listaUsuarios.get(position).getPeso().toString());
        holder.txttalla.setText(listaUsuarios.get(position).getTalla().toString());
        holder.txtsexo.setText(listaUsuarios.get(position).getSexo().toString());
        holder.txtfechasNacimiento.setText(listaUsuarios.get(position).getFechanacimiento().toString());
        holder.txtID.setId(listaUsuarios.get(position).getIdasesorado());
        holder.txtID.setText(listaUsuarios.get(position).getIdasesorado().toString());
        holder.txtEstado.setText(listaUsuarios.get(position).getEstado().toString());
        holder.txtEntrenador.setText(listaUsuarios.get(position).getNombreEntrenador().toString());
        holder.txtEdad.setText(listaUsuarios.get(position).getEdad().toString());
        holder.txtRutinaName.setText(listaUsuarios.get(position).getNombreRutina().toString());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {

    }

    public class UsuariosHolder extends RecyclerView.ViewHolder{

        TextView txtNombre,txtApellidoP,txtApellidoM, txtaltura,txtpeso,txttalla,
                txtsexo,txtfechasNacimiento,txtEstado,txtEntrenador,txtEdad,txtID,txtRutinaName, txtidEntrenar;
        ImageView MIDasesad;

        public UsuariosHolder(View itemView) {
            super(itemView);
            txtidEntrenar=(TextView)itemView.findViewById(R.id.identrenar);
            txtNombre= (TextView) itemView.findViewById(R.id.txtNombre);
            txtApellidoP = (TextView) itemView.findViewById(R.id.txtapeliidop);
            txtApellidoM = (TextView) itemView.findViewById(R.id.txtapellidom);
            txtaltura = (TextView) itemView.findViewById(R.id.txtaltura);
            txtpeso = (TextView) itemView.findViewById(R.id.txtPeso);
            txttalla = (TextView) itemView.findViewById(R.id.txttalla);
            txtsexo = (TextView) itemView.findViewById(R.id.txtsexo);
            txtEstado= (TextView) itemView.findViewById(R.id.txtEstados);
            txtEntrenador=(TextView)itemView.findViewById(R.id.txtEntrenador);
            txtfechasNacimiento = (TextView) itemView.findViewById(R.id.txtFechaNacimiento);
            txtEdad=(TextView) itemView.findViewById(R.id.txtEdad);
            txtID=(TextView)itemView.findViewById(R.id.txtid);
            txtRutinaName=(TextView) itemView.findViewById(R.id.txtNombreRutinaL);
            MIDasesad= (ImageView) itemView.findViewById(R.id.imgid);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    detallesAsesorado myFragment = new detallesAsesorado();
                    Bundle data = new Bundle();//Use bundle to pass data
                    data.putString("numbreid", UsuariosHolder.this.txtID.getText().toString());
                    data.putString("NombreDetalle", UsuariosHolder.this.txtNombre.getText().toString());
                    data.putString("NombreApellidoP", UsuariosHolder.this.txtApellidoP.getText().toString());
                    data.putString("NombreApellidoM", UsuariosHolder.this.txtApellidoM.getText().toString());
                    data.putString("Sexo", UsuariosHolder.this.txtsexo.getText().toString());
                    data.putString("Altura", UsuariosHolder.this.txtaltura.getText().toString());
                    data.putString("Peso", UsuariosHolder.this.txtpeso.getText().toString());
                    data.putString("Talla", UsuariosHolder.this.txttalla.getText().toString());
                    data.putString("FechaNacimiento", UsuariosHolder.this.txtfechasNacimiento.getText().toString());
                    data.putString("Estado", UsuariosHolder.this.txtEstado.getText().toString());
                    data.putString("idEntrenador", UsuariosHolder.this.txtEntrenador.getText().toString());
                    data.putString("Edad", UsuariosHolder.this.txtEdad.getText().toString());
                    data.putString("NombreR", UsuariosHolder.this.txtRutinaName.getText().toString());
                    myFragment.setArguments(data);//Finally set argument bundle to fragment
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
                }
            });


        }
    }
}
