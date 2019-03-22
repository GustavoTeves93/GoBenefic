package com.example.nicolas.gobenefic_102;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0007Controller;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0008Controller;

import java.util.HashMap;

public class RealizarDonaActivity extends AppCompatActivity {
    Button btnDoDinero, btnDoOtro;
    SharedPreferences read;
    SharedPreferences.Editor write;
    int accesoCanal = 0;
    Bundle datos;
    UsuarioFN0007Controller usuarioFN0007Controller = new UsuarioFN0007Controller();
    UsuarioFN0008Controller usuarioFN0008Controller = new UsuarioFN0008Controller();

    @Override
    protected void onStart() {
        new RealizarDonaActivity.UserDonacionesTask().execute();
        new RealizarDonaActivity.UserDonacionesCodigoTask().execute();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_dona);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        read = getSharedPreferences("usuarioGuardado",MODE_PRIVATE);
        write = read.edit();

        TextView ttitul = (TextView)findViewById(R.id.txttitulo);
        TextView tDescri = (TextView)findViewById(R.id.txtDescri);
        TextView tContactenos = (TextView)findViewById(R.id.txtContactenos);
        TextView tDepartamento = (TextView)findViewById(R.id.txtDepartamento);
        TextView tProvincia = (TextView)findViewById(R.id.txtProvincia);
        TextView tDistrito = (TextView)findViewById(R.id.txtDistrito);
        TextView tfechaI = (TextView)findViewById(R.id.txtfechaI);
        TextView tfechaF = (TextView)findViewById(R.id.txtfechaF);
        ImageView imageView = (ImageView)findViewById(R.id.imgDonador);

        datos = this.getIntent().getExtras();

        ttitul.setText("Titulo      :  " + datos.getString("tituloDona"));
        tDescri.setText("Descripcion      :  " + datos.getString("descriDona"));
        tContactenos.setText("Contactenos      :  " + datos.getString("telefDona"));
        tDepartamento.setText("Departamento      :  " + datos.getString("depaDona"));
        tProvincia.setText("Provincia      :  " + datos.getString("proviDona"));
        tDistrito.setText("Distrito      :  " + datos.getString("distDona"));
        tfechaI.setText("Fecha Inicio      :  " + datos.getString("fechIni"));
        tfechaF.setText("Fecha Fin      :  " + datos.getString("fechFin"));
        imageView.setImageResource(datos.getInt("imagenDona"));

        accesoCanal = read.getInt("TACC_CODACC",0);

        btnDoDinero = (Button)findViewById(R.id.btnDonarDinero);
        btnDoDinero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accesoCanal != 0){
                    alertTrabajando("Servicio no disponible temporalmente");
                }else{
                    alertBloquado("Usuario sin acceso al servicio");
                }
            }
        });
        btnDoOtro = (Button)findViewById(R.id.btnDonarOtros);
        btnDoOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accesoCanal != 0){
                    Intent x = new Intent(getApplicationContext(),ConfirmarDonaActivity.class);
                    x.putExtra("codigo",datos.getInt("codigoDona"));
                    x.putExtra("titulo",datos.getString("tituloDona"));
                    x.putExtra("celular",datos.getString("celu"));
                    startActivity(x);
                }else{
                    alertBloquado("Usuario sin acceso al servicio");
                }
            }
        });

    }
    private void alertTrabajando(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(RealizarDonaActivity.this);
        dialog.setTitle("Disculpe la molestia")
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }
    private void alertBloquado(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(RealizarDonaActivity.this);
        dialog.setTitle("BLOQUEADO")
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }
    /***********************************************************************/
    public class UserDonacionesTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String cod ="";
            cod = String.valueOf(read.getInt("TUSU_CODUSUR",0));
            HashMap mapParametros = new HashMap();
            //mapParametros.put("codigo",cod);
            return usuarioFN0007Controller.FN007(mapParametros);
        }

        @Override
        protected void onPostExecute(String s) {
            int c1 = Integer.parseInt(s);
            Log.i("EXITO TOTAL DONACIONES", String.valueOf(c1));
            write.putInt("TOTAL_DONA",c1).commit();
        }
    }
    /***********************************************************************/
    public class UserDonacionesCodigoTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String cod ="";
            cod = String.valueOf(read.getInt("TACC_CODACC",0));
            Log.i("EXITO POR CODIGO", cod);
            HashMap mapParametros = new HashMap();
            mapParametros.put("codigo",cod);
            return usuarioFN0008Controller.FN008(mapParametros);
        }

        @Override
        protected void onPostExecute(String s) {
            int c1 = Integer.parseInt(s);
            Log.i("EXITO POR CODIGO", String.valueOf(c1));
            write.putInt("TOTAL_DONA_C",c1).commit();
        }
    }
}
