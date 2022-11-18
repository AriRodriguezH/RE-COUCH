package com.example.proyectoi.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoi.R;
import com.example.proyectoi.entidades.ConsultaHistoricoAsesorado;

import java.util.List;

public class HistoricoAsesoradoAdapter extends RecyclerView.Adapter<HistoricoAsesoradoAdapter.HistoricoAsesoradoHolder>{
    List<ConsultaHistoricoAsesorado> listaConsultaHistorico;
    CardView CHA;

    public HistoricoAsesoradoAdapter(List<ConsultaHistoricoAsesorado> listaConsultaHistorico) {
        this.listaConsultaHistorico = listaConsultaHistorico;
    }

    @Override
    public HistoricoAsesoradoHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_historicoasesorado,parent,false);
        CHA = vista.findViewById(R.id.CvHistoricoA);
        CHA.setAnimation(AnimationUtils.loadAnimation(vista.getContext(), R.anim.slide));
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new HistoricoAsesoradoAdapter.HistoricoAsesoradoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricoAsesoradoAdapter.HistoricoAsesoradoHolder holder, int position) {
        holder.txtNombreHA.setText(listaConsultaHistorico.get(position).getNombreHA().toString());
        holder.txtApellidoPHA.setText(listaConsultaHistorico.get(position).getApellidopHA().toString());
        holder.txtApellidoMHA.setText(listaConsultaHistorico.get(position).getApellidomHA().toString());
        holder.txtalturaHA.setText(listaConsultaHistorico.get(position).getAlturaHA().toString());
        holder.txtpesoHA.setText(listaConsultaHistorico.get(position).getPesoHA().toString());
        holder.txttallaHA.setText(listaConsultaHistorico.get(position).getTallaHA().toString());
        holder.txtfechasCreacionHA.setText(listaConsultaHistorico.get(position).getFechaCreacionHA().toString());
    }

    @Override
    public int getItemCount() {
        return listaConsultaHistorico.size();
    }

    public class HistoricoAsesoradoHolder extends RecyclerView.ViewHolder{

        TextView txtNombreHA,txtApellidoPHA,txtApellidoMHA, txtalturaHA,txtpesoHA,txttallaHA,txtfechasCreacionHA;

        public HistoricoAsesoradoHolder(View itemView) {
            super(itemView);
            //txtIdAsesorado = (TextView) itemView.findViewById(R.id.txtidasesorado);
            txtNombreHA= (TextView) itemView.findViewById(R.id.txtNombreHA);
            txtApellidoPHA = (TextView) itemView.findViewById(R.id.txtapeliidopHA);
            txtApellidoMHA = (TextView) itemView.findViewById(R.id.txtapellidomHA);
            txtalturaHA = (TextView) itemView.findViewById(R.id.txtalturaHA);
            txtpesoHA= (TextView) itemView.findViewById(R.id.txtPesoHA);
            txttallaHA = (TextView) itemView.findViewById(R.id.txttallaHA);
            txtfechasCreacionHA = (TextView) itemView.findViewById(R.id.txtFechaCreacionHA);

        }
    }
}
