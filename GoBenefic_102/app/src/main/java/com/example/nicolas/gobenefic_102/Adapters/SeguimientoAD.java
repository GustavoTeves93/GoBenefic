package com.example.nicolas.gobenefic_102.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Bean.DonarBean;
import com.example.nicolas.gobenefic_102.Bean.SeguimientoBean;
import com.example.nicolas.gobenefic_102.R;

import java.util.List;

public class SeguimientoAD extends  RecyclerView.Adapter<SeguimientoAD.SeguimientoPH>{

    List<SeguimientoBean> pData;

    public SeguimientoAD(List<SeguimientoBean> pData) {
        this.pData = pData;
    }

    @NonNull
    @Override
    public SeguimientoPH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemmoldesegui,parent,false);
        SeguimientoPH ph = new SeguimientoPH(v);
        return  ph;
    }

    @Override
    public void onBindViewHolder(@NonNull SeguimientoPH holder, int position) {
        String anonimo = null, estado = null;

        anonimo = pData.get(position).getTDDO_ANONIMO().toString();
        estado  = pData.get(position).getTDDO_ESTADO().toString();

        if(anonimo.equals("A")){
            holder.txtanonimo.setText("ANÓNIMO");
        }else{
            holder.txtanonimo.setText(pData.get(position).getTUSU_NOMBRE() + " " + pData.get(position).getTUSU_APELLIDO());
        }

        holder.txtfechaR.setText(pData.get(position).getTLDO_FECHALTA());
        holder.txtfechaA.setText(pData.get(position).getTLDO_FECHMODI());
        holder.txtDescri.setText(pData.get(position).getTDDO_DESCRIPC());
        holder.txtefectivo.setText(String.valueOf(pData.get(position).getTDDO_MONTO()));

        if(estado.equals("A")) {
            holder.txtVerificacion.setText("REALIZADO");
        }else {
            holder.txtVerificacion.setText("PENDIENTE");
        }
    }

    @Override
    public int getItemCount() {
        return pData.size();
    }


    public static class SeguimientoPH extends RecyclerView.ViewHolder{
        CardView cv;
        ImageView im;
        TextView txtanonimo,txtDescri,txtefectivo,txtVerificacion;
        TextView txtfechaR,txtfechaA;


        public SeguimientoPH(View itemView) {
            super(itemView);
            txtanonimo = (TextView)itemView.findViewById(R.id.txtAnonimo);
            txtfechaR = (TextView)itemView.findViewById(R.id.txtFechaR);
            txtfechaA = (TextView)itemView.findViewById(R.id.txtFechaA);
            txtDescri = (TextView)itemView.findViewById(R.id.txtSeguimiento);
            txtefectivo = (TextView)itemView.findViewById(R.id.txtefecti);
            txtVerificacion = (TextView)itemView.findViewById(R.id.txtVerificacion);

        }
    }

}
