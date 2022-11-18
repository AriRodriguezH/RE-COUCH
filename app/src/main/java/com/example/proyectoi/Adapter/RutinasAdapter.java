package com.example.proyectoi.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoi.R;
import com.example.proyectoi.detallesRutina;
import com.example.proyectoi.entidades.ConsultarRutinas;

import java.util.List;

public class RutinasAdapter extends RecyclerView.Adapter<RutinasAdapter.RutinasHolder>{
    List<ConsultarRutinas> listaConsultaRutinas;
    CardView CVRD;

    public RutinasAdapter(List<ConsultarRutinas> listaConsultaRutinas) {
        this.listaConsultaRutinas = listaConsultaRutinas;
    }

    @Override
    public RutinasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_rutinas,parent,false);
        CVRD = vista.findViewById(R.id.miCARDRUTINAD);
        CVRD.setAnimation(AnimationUtils.loadAnimation(vista.getContext(),R.anim.fade_transition));
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new RutinasAdapter.RutinasHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull RutinasHolder holder, int position) {
        holder.txtNombreRutina.setText(listaConsultaRutinas.get(position).getNombreRutina().toString());
        holder.txtNivelR.setText(listaConsultaRutinas.get(position).getNivel().toString());
        holder.txtNombreEjercicioR.setText(listaConsultaRutinas.get(position).getNombreEjercicio().toString());
        holder.txtDiaSemanaR.setText(listaConsultaRutinas.get(position).getDiaSemana().toString());
        holder.txtParteTrabajarR.setText(listaConsultaRutinas.get(position).getParteATrabajar().toString());
        holder.txtIDRutina.setId(listaConsultaRutinas.get(position).getIdrutina());
        holder.txtIDRutina.setText(listaConsultaRutinas.get(position).getIdrutina().toString());
      /*  holder.txtPesoAgregadoR.setText(listaConsultaRutinas.get(position).getPesoAgregado().toString());
        holder.txtSeriesR.setText(listaConsultaRutinas.get(position).getSeries().toString());
        holder.txtNumRepeticionR.setText(listaConsultaRutinas.get(position).getNumRepeticion().toString());
        holder.txtNombreMaquinaR.setText(listaConsultaRutinas.get(position).getNombreMaquina().toString());*/

    }

    @Override
    public int getItemCount() {
        return listaConsultaRutinas.size();
    }

    public class RutinasHolder extends RecyclerView.ViewHolder{

        TextView txtNombreRutina,txtNivelR,txtNombreEjercicioR, txtDiaSemanaR,txtParteTrabajarR,txtIDRutina,
        txtSeriesR, txtNumRepeticionR, txtNombreMaquinaR;

        public RutinasHolder(View itemView) {
            super(itemView);
            //txtIdAsesorado = (TextView) itemView.findViewById(R.id.txtidasesorado);
            txtNombreRutina= (TextView) itemView.findViewById(R.id.txtNombreRutina);
            txtNivelR = (TextView) itemView.findViewById(R.id.txtNivelRutina);
            txtNombreEjercicioR = (TextView) itemView.findViewById(R.id.txtnombreEjercicio);
            txtDiaSemanaR = (TextView) itemView.findViewById(R.id.txtdiasemanda);
            txtParteTrabajarR= (TextView) itemView.findViewById(R.id.txtParteaTrabajar);
            txtIDRutina=(TextView) itemView.findViewById(R.id.txtidRutina);
         /*   txtPesoAgregadoR = (TextView) itemView.findViewById(R.id.txtPesoR);
            txtSeriesR = (TextView) itemView.findViewById(R.id.txtSeriesR);
            txtNumRepeticionR = (TextView) itemView.findViewById(R.id.txtRepeticionesR);
            txtNombreMaquinaR = (TextView) itemView.findViewById(R.id.txtnombreMaquina);*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    detallesRutina myFragment = new detallesRutina();
                    Bundle data = new Bundle();//Use bundle to pass data
                    data.putString("Rutinaid", RutinasHolder.this.txtIDRutina.getText().toString());
                    data.putString("NombreRutina", RutinasHolder.this.txtNombreRutina.getText().toString());
                    data.putString("NivelRutina", RutinasHolder.this.txtNivelR.getText().toString());
                    myFragment.setArguments(data);//Finally set argument bundle to fragment
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerRutinas, myFragment).addToBackStack(null).commit();
                }
            });

        }
    }
}
