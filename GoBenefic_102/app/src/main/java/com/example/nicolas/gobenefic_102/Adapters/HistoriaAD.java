package com.example.nicolas.gobenefic_102.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Bean.SeguimientoBean;
import com.example.nicolas.gobenefic_102.R;

import java.util.List;

public class HistoriaAD  extends RecyclerView.Adapter<HistoriaAD.HistoriaPH> {
    List<SeguimientoBean> pData;

    public HistoriaAD(List<SeguimientoBean> pData) {
        this.pData = pData;
    }

    @NonNull
    @Override
    public HistoriaPH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemmoldehistoria,parent,false);

        HistoriaPH ph = new HistoriaPH(v);
        return ph;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriaPH holder, int position) {
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

    public static class HistoriaPH extends RecyclerView.ViewHolder{
        CardView cv;
        ImageView im;
        TextView txtanonimo,txtDescri,txtefectivo,txtVerificacion;
        TextView txtfechaR,txtfechaA;


        public HistoriaPH(View itemView) {
            super(itemView);
            txtanonimo = (TextView)itemView.findViewById(R.id.txtAnonimo3);
            txtfechaR = (TextView)itemView.findViewById(R.id.txtFechaR3);
            txtfechaA = (TextView)itemView.findViewById(R.id.txtFechaA3);
            txtDescri = (TextView)itemView.findViewById(R.id.txtSeguimiento3);
            txtefectivo = (TextView)itemView.findViewById(R.id.txtefecti3);
            txtVerificacion = (TextView)itemView.findViewById(R.id.txtVerificacion3);

        }
    }
}
