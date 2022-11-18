package com.example.proyectoi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoi.R;
import com.example.proyectoi.entidades.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EditUserAdapter extends RecyclerView.Adapter<EditUserAdapter.EditUsersHolder> implements View.OnClickListener{
    List<Usuario> listaEditarUsuarios;

    public EditUserAdapter(List<Usuario> listaEditarUsuarios) {
        this.listaEditarUsuarios = listaEditarUsuarios;
    }

    @Override
    public EditUsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_editusuario,parent,false);
        return new EditUsersHolder(vista);
    }

    @Override
    public void onBindViewHolder(EditUsersHolder holder, int position) {

        holder.txtNombre.setText(listaEditarUsuarios.get(position).getNombre().toString());
        holder.txtApellidoP.setText(listaEditarUsuarios.get(position).getApellidop().toString());
        holder.txtApellidoM.setText(listaEditarUsuarios.get(position).getApellidom().toString());
        holder.txtaltura.setText(listaEditarUsuarios.get(position).getAltura().toString());
        holder.txtpeso.setText(listaEditarUsuarios.get(position).getPeso().toString());
        holder.txttalla.setText(listaEditarUsuarios.get(position).getTalla().toString());
        holder.txtsexo.setText(listaEditarUsuarios.get(position).getSexo().toString());
        holder.txtfechasNacimiento.setText(listaEditarUsuarios.get(position).getFechanacimiento().toString());
       // holder.MIDasesad.setId(listaEditarUsuarios.get(position).getIdasesorado());
        holder.btninfo.setId(listaEditarUsuarios.get(position).getIdasesorado());
        holder.txtEstado.setText(listaEditarUsuarios.get(position).getEstado().toString());
        holder.txtEntrenador.setText(listaEditarUsuarios.get(position).getNombreEntrenador().toString());
        holder.txtEdad.setText(listaEditarUsuarios.get(position).getEdad().toString());

    }

    @Override
    public int getItemCount() {
        return listaEditarUsuarios.size();
    }


    @Override
    public void onClick(View view) {

    }


    public class EditUsersHolder extends RecyclerView.ViewHolder{

        TextView txtNombre,txtApellidoP,txtApellidoM, txtaltura,txtpeso,txttalla,txtsexo,txtfechasNacimiento,txtEstado,txtEntrenador,txtEdad;
        ImageView MIDasesad;
        FloatingActionButton btninfo;

        public EditUsersHolder(View itemView) {
            super(itemView);
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
          //  MIDasesad= (ImageView) itemView.findViewById(R.id.vvv);

        }
    }
}
