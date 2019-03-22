package com.example.nicolas.gobenefic_102.Adapters;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.gobenefic_102.Bean.DonacionBean;
import com.example.nicolas.gobenefic_102.MainActivity;
import com.example.nicolas.gobenefic_102.R;
import com.example.nicolas.gobenefic_102.RealizarDonaActivity;
import com.example.nicolas.gobenefic_102.SeguimientoActivity;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class DonacionesAD extends
        RecyclerView.Adapter<DonacionesAD.DonacionesPH>{

    List<DonacionBean> pdata;
    Dialog myDialog;

    public DonacionesAD(List<DonacionBean> pdata) {
        this.pdata = pdata;
    }

    @NonNull
    @Override
    public DonacionesPH onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemmolde,parent,false);
        final DonacionesPH ph = new DonacionesPH(v);
        myDialog = new Dialog(parent.getContext());
        myDialog.setContentView(R.layout.dialog_img);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button llamar = (Button)myDialog.findViewById(R.id.btnllamar);
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+String.valueOf(pdata.get(ph.getAdapterPosition()).getTDON_TELEFONO())));
                if(ActivityCompat.checkSelfPermission(parent.getContext(), Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(parent.getContext(),i,null);
            }
        });
      /*  ph.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom;
                int imagen;
                nom = pdata.get(ph.getAdapterPosition()).getTUSU_NOMBRE();
                imagen = pdata.get((ph.getAdapterPosition())).getIMAGEN();
                Toast.makeText(parent.getContext(),"CLICK " + String.valueOf(ph.getAdapterPosition()) + nom,Toast.LENGTH_LONG).show();
                Intent in = new Intent(parent.getContext(),RealizarDonaActivity.class);
                in.putExtra("nombre",nom);
                in.putExtra("imagen",imagen);
                startActivity(parent.getContext(),in,null);
            }
        });*/
        /*ph.ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        ph.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView dialog_imagen = (ImageView) myDialog.findViewById(R.id.img_dialog);
                Button btn = (Button)myDialog.findViewById(R.id.btnllamar) ;
                dialog_imagen.setImageResource(pdata.get((ph.getAdapterPosition())).getTDON_IMAGEN());
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String numero = pdata.get(ph.getAdapterPosition()).getTDON_TELEFONO();

                        Intent i = new Intent(android.content.Intent.ACTION_CALL,
                                Uri.parse("tel:"+numero+""));
                        startActivity(parent.getContext(),i,null);
                    }
                });
                myDialog.show();
            }
        });
        ph.lupaVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloDona,descriDona,telefDona,estadoDona,depaDona,proviDona,distDona;
                String fechIni,fechFin,cel;
                int codigoDona,imagenDona;
                codigoDona = pdata.get((ph.getAdapterPosition())).getTUSU_CODDON();
                tituloDona = pdata.get(ph.getAdapterPosition()).getTDON_TIPODONAC();
                descriDona = pdata.get(ph.getAdapterPosition()).getTDON_DESCRIPC();
                telefDona = pdata.get(ph.getAdapterPosition()).getTDON_TELEFONO();
                estadoDona = pdata.get(ph.getAdapterPosition()).getTDON_ESTADO();
                depaDona = pdata.get(ph.getAdapterPosition()).getTDON_DEPARTAMENTO();
                proviDona = pdata.get(ph.getAdapterPosition()).getTDON_PROVINCIA();
                distDona = pdata.get(ph.getAdapterPosition()).getTDON_DISTRITO();
                fechIni = pdata.get(ph.getAdapterPosition()).getTDON_FECHAINICIO();
                fechFin = pdata.get(ph.getAdapterPosition()).getTDON_FECHAFIN();
                imagenDona = pdata.get((ph.getAdapterPosition())).getTDON_IMAGEN();
                cel = pdata.get((ph.getAdapterPosition())).getTUSU_CELULAR();
                //Toast.makeText(parent.getContext(),"CLICK " + String.valueOf(ph.getAdapterPosition()) + tituloDona,Toast.LENGTH_LONG).show();
                //
                Toast.makeText(parent.getContext(), tituloDona,Toast.LENGTH_LONG).show();
                Intent in = new Intent(parent.getContext(),RealizarDonaActivity.class);
                in.putExtra("imagenDona",imagenDona);
                in.putExtra("codigoDona",codigoDona);
                in.putExtra("tituloDona",tituloDona);
                in.putExtra("telefDona",telefDona);
                in.putExtra("estadoDona",estadoDona);
                in.putExtra("depaDona",depaDona);
                in.putExtra("proviDona",proviDona);
                in.putExtra("distDona",distDona);
                in.putExtra("fechIni",fechIni);
                in.putExtra("fechFin",fechFin);
                in.putExtra("descriDona",descriDona);
                in.putExtra("celu",cel);
                startActivity(parent.getContext(),in,null);
            }
        });
        ph.lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tituloDona,descriDona,telefDona,estadoDona,depaDona,proviDona,distDona;
                String fechIni,fechFin,cel;
                int codigoDona,imagenDona;
                codigoDona = pdata.get((ph.getAdapterPosition())).getTUSU_CODDON();
                tituloDona = pdata.get(ph.getAdapterPosition()).getTDON_TIPODONAC();
                descriDona = pdata.get(ph.getAdapterPosition()).getTDON_DESCRIPC();
                telefDona = pdata.get(ph.getAdapterPosition()).getTDON_TELEFONO();
                estadoDona = pdata.get(ph.getAdapterPosition()).getTDON_ESTADO();
                depaDona = pdata.get(ph.getAdapterPosition()).getTDON_DEPARTAMENTO();
                proviDona = pdata.get(ph.getAdapterPosition()).getTDON_PROVINCIA();
                distDona = pdata.get(ph.getAdapterPosition()).getTDON_DISTRITO();
                fechIni = pdata.get(ph.getAdapterPosition()).getTDON_FECHAINICIO();
                fechFin = pdata.get(ph.getAdapterPosition()).getTDON_FECHAFIN();
                imagenDona = pdata.get((ph.getAdapterPosition())).getTDON_IMAGEN();
                cel = pdata.get((ph.getAdapterPosition())).getTUSU_CELULAR();
                //Toast.makeText(parent.getContext(),"CLICK " + String.valueOf(ph.getAdapterPosition()) + nom,Toast.LENGTH_LONG).show();
                //
                Toast.makeText(parent.getContext(),  tituloDona,Toast.LENGTH_LONG).show();
                Intent in = new Intent(parent.getContext(),RealizarDonaActivity.class);
                in.putExtra("imagenDona",imagenDona);
                in.putExtra("codigoDona",codigoDona);
                in.putExtra("tituloDona",tituloDona);
                in.putExtra("telefDona",telefDona);
                in.putExtra("estadoDona",estadoDona);
                in.putExtra("depaDona",depaDona);
                in.putExtra("proviDona",proviDona);
                in.putExtra("distDona",distDona);
                in.putExtra("fechIni",fechIni);
                in.putExtra("fechFin",fechFin);
                in.putExtra("descriDona",descriDona);
                in.putExtra("celu",cel);
                startActivity(parent.getContext(),in,null);

            }
        });
        ph.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigoDona;
                codigoDona = pdata.get((ph.getAdapterPosition())).getTUSU_CODDON();

                //alertNoDono("No ha realizado donaciones",parent.getContext());
                Intent in = new Intent(parent.getContext(),SeguimientoActivity.class);
                in.putExtra("codigoDona",codigoDona);
                startActivity(parent.getContext(),in,null);
            }
        });
        ph.likeSeguimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigoDona;
                codigoDona = pdata.get((ph.getAdapterPosition())).getTUSU_CODDON();

                //alertNoDono("No ha realizado donaciones",parent.getContext());
                Intent in = new Intent(parent.getContext(),SeguimientoActivity.class);
                in.putExtra("codigoDona",codigoDona);
                startActivity(parent.getContext(),in,null);
            }
        });


        ph.comparte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String des = pdata.get(ph.getAdapterPosition()).getTDON_DESCRIPC();
                String te = pdata.get(ph.getAdapterPosition()).getTDON_TELEFONO();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //intentetPackage("com.whatsapp"); <-- si es que quieres que sea ccon una aplicacion en especifico
                intent.putExtra(Intent.EXTRA_TEXT, pdata.get(ph.getAdapterPosition()).getTDON_TIPODONAC() +
                        " " + des +" Comunicate: "+te);
                startActivity(parent.getContext(),Intent.createChooser(intent, "GOBENEFIC - COMPARTIR POR"),null);
            }
        });
        ph.comparteV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String des = pdata.get(ph.getAdapterPosition()).getTDON_DESCRIPC();
                String te = pdata.get(ph.getAdapterPosition()).getTDON_TELEFONO();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //intentetPackage("com.whatsapp"); <-- si es que quieres que sea ccon una aplicacion en especifico
                //intent.putExtra(Intent.EXTRA_TEXT, "Aquí va el mensaje que quieres enviar o text.gettext()");
                intent.putExtra(Intent.EXTRA_TEXT, pdata.get(ph.getAdapterPosition()).getTDON_TIPODONAC() +
                        " " + des +" Comunicate: "+te);
                startActivity(parent.getContext(),Intent.createChooser(intent, "GOBENEFIC - COMPARTIR POR"),null);
            }
        });
        return ph;
    }

    @Override
    public void onBindViewHolder(@NonNull DonacionesPH holder, int position) {
        holder.ti.setText(pdata.get(position).getTDON_TIPODONAC());
        holder.img.setImageResource(pdata.get(position).getTDON_IMAGEN());
    }

    @Override
    public int getItemCount() {
        return pdata.size();
    }

    public static class DonacionesPH extends RecyclerView.ViewHolder {

        private LinearLayout item_contact;
        private CardView cv;
        private TextView ti,lupaVisualizar,likeSeguimiento,comparteV;
        private ImageView img;
        private ImageView lupa,like,comparte;


        public DonacionesPH(View itemView) {
            super(itemView);

            item_contact = (LinearLayout) itemView.findViewById(R.id.contact_item_id);
            cv =(CardView) itemView.findViewById(R.id.cv);
            ti =(TextView)itemView.findViewById(R.id.txtDescriMolde);
            img = (ImageView)itemView.findViewById(R.id.imgMolde);

            lupa = (ImageView)itemView.findViewById(R.id.imageView2);
            lupaVisualizar =(TextView)itemView.findViewById(R.id.txtBuscar);

            like = (ImageView)itemView.findViewById(R.id.imageView3);
            likeSeguimiento =(TextView)itemView.findViewById(R.id.txtSeguimiento);

            comparte = (ImageView)itemView.findViewById(R.id.imageView);
            comparteV  =(TextView)itemView.findViewById(R.id.txtComparte);
        }
    }
    private void alertNoDono(String msg,Context context){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle("Seguimiento de donaciones realizadas")
                .setMessage(msg)

                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

}