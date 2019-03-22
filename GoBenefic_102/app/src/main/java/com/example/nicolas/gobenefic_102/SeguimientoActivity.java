package com.example.nicolas.gobenefic_102;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nicolas.gobenefic_102.Adapters.SeguimientoAD;
import com.example.nicolas.gobenefic_102.Bean.SeguimientoBean;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0006Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SeguimientoActivity extends AppCompatActivity {
    Bundle datos;
    UsuarioFN0006Controller usuarioFN0006Controller = new UsuarioFN0006Controller();
    ArrayList<SeguimientoBean> arySeguimiento = null;
    String codigo= "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);
        datos = this.getIntent().getExtras();
        Log.i("CODIGO ********** ",String.valueOf(datos.getInt("codigoDona"))) ;
        new SeguimientoDona().execute(String.valueOf(datos.getInt("codigoDona")) );
    }

    public class SeguimientoDona extends AsyncTask<String,Integer,JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            HashMap mapParametros = new HashMap();
            mapParametros.put("codDona",strings[0]);
            return usuarioFN0006Controller.FN006(mapParametros);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if(jsonObject.getBoolean("status")) {
                    Log.i("EXITO", "se conecto a la data seguimiento");
                    /*AGREGO 25-07-2018*/
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    arySeguimiento = new ArrayList<>();

                    for (int x=0; x < jsonArray.length();x++){
                        JSONObject j = jsonArray.getJSONObject(x);
                        SeguimientoBean s = new SeguimientoBean();
                        s.setTUSU_NOMBRE(j.getString("TUSU_NOMBRE"));
                        Log.i("NOMBRE", j.getString("TUSU_NOMBRE"));
                        s.setTUSU_APELLIDO(j.getString("TUSU_APELLIDO"));
                        Log.i("APELLIDO", j.getString("TUSU_APELLIDO"));
                        s.setTDDO_ANONIMO(j.getString("TDDO_ANONIMO"));
                        s.setTDDO_ESTADO(j.getString("TDDO_ESTADO"));
                        s.setTLDO_FECHALTA(j.getString("TLDO_FECHALTA"));
                        s.setTLDO_FECHMODI(j.getString("TLDO_FECHMODI"));
                        s.setTDDO_MONTO(j.getDouble("TDDO_MONTO"));
                        s.setTDDO_DESCRIPC(j.getString("TDDO_DESCRIPC"));
                        arySeguimiento.add(s);
                    }

                    SeguimientoAD ad= new SeguimientoAD(arySeguimiento);
                    RecyclerView rc = (RecyclerView)findViewById(R.id.listaSeguimiento);
                    rc.setHasFixedSize(true);
                    RecyclerView.LayoutManager ly =
                            new LinearLayoutManager(SeguimientoActivity.this);
                    rc.setLayoutManager(ly);
                    rc.setAdapter(ad);
                    /*JSONObject j = jsonArray.getJSONObject(0);
                    write.putInt("TUSU_CODUSUR",j.getInt("TUSU_CODUSUR")).commit();
                    write.putString("TUSU_CODIDENT",j.getString("TUSU_CODIDENT")).commit();*/

                    /*AGREGO 25-07-2018*/
                    //Log.i("BIENVENIDO", String.valueOf(read.getInt("TUSU_CODUSUR",0)) +" "+read.getString("TUSU_NOMBRE",""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
