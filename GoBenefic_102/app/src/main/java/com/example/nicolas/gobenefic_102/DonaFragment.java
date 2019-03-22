package com.example.nicolas.gobenefic_102;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.gobenefic_102.Adapters.DonacionesAD;
import com.example.nicolas.gobenefic_102.Bean.DonacionBean;

import java.util.ArrayList;

public class DonaFragment extends Fragment {
    RecyclerView rc;
    TextView txtBien;
    Bundle dona;
    SharedPreferences read;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dona, container, false);
        //ArrayList<UsuarioBean> usuarioBean = new ArrayList<>();
        ArrayList<DonacionBean> donacionBean = new ArrayList<>();

        txtBien = (TextView) view.findViewById(R.id.txtBienvenido);

        String NOMBRE = getArguments() != null ? getArguments().getString("T_NOMBRE") : "USUARIO ";
        String APELLIDO = getArguments() != null ? getArguments().getString("T_APELLIDO") : "USUARIO ";
        String CELULAR = getArguments() != null ? String.valueOf(getArguments().getInt("T_CELULAR")) : "USUARIO ";
//        read =  context.getSharedPreferences("usuarioGuardado",MODE_PRIVATE);

        //txtBien.setText(read.getString("TUSU_NOMBRE","Bienvenido:"));
        txtBien.setText(" BIENVENIDO : " + NOMBRE + " "+ APELLIDO);
        rc = (RecyclerView) view.findViewById(R.id.listaRecycler);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        /*usuarioBean.add(new UsuarioBean("Se necesita voluntarios",R.drawable.tele5));
        usuarioBean.add(new UsuarioBean("Ayudanos a seguir apoyando",R.drawable.teleton));
        usuarioBean.add(new UsuarioBean("Por un mejor mañana ayudanos hoy",R.drawable.tele2));
        usuarioBean.add(new UsuarioBean("Siempre juntos por una solo causa",R.drawable.tele3));
        usuarioBean.add(new UsuarioBean("Avanzemos juntos ayudando a otros",R.drawable.tele4));*/

        donacionBean.add(new DonacionBean(1,"Campaña de Voluntarios 2018!!","Buscamos, crear espacios de " +
                "integración entre voluntarios, niños, familias y la comunidad en general, a través de proyectos " +
                "participativos y de la comunicación de la obra Teletón. Tenemos una visión orientada a la plena " +
                "integración de las personas que viven con alguna discapacidad en nuestro país. " +
                "Registrate en DONA OTROS, suscribete !","012607008","A",
                "Lima","Lima","Villa María del Triunfo",
                "2018-01-01","2019-01-01",R.drawable.tele5,CELULAR));
        donacionBean.add(new DonacionBean(2,"Taller de arte - TeletónArt!!","Buscamos, crear espacios " +
                "de integración para los niños con discapacidad, a través de este taller, para que nuestros niños realizen figuras " +
                "con plastilina y utilizen acuarelas para colorear diferentes dibujos. Tenemos una visión orientada a la " +
                "plena integración de los niños que viven con alguna discapacidad en nuestro país, por lo cual solicitamos " +
                "todo tipo de materiales educativos, material lúdico y juegos didacticos para el aprendizaje, la diversión " +
                "y la estimulación de los niños.","012607008","A",
                "Lima","Lima","Lima",
                "2018-01-01","2019-01-01",R.drawable.tele6,CELULAR));
        donacionBean.add(new DonacionBean(3,"Atención Chiclayo - Voluntarios 2018!!","Tienes vocación de servicio " +
                "y espíritu solidario, esta es tu oportunidad, únete al gran equipo de voluntarios para Teletón 2018. " +
                "Registrate en DONA OTROS, suscribete !","012708008","A",
                "Lambayeque","Chiclayo","Chiclayo",
                "2018-08-01","2018-10-01",R.drawable.tele7,CELULAR));
        donacionBean.add(new DonacionBean(4,"Centro de Rehabilitación Pediátrica de Piura!!",
                "Tu solidaridad y la unión de todo el Perú están haciendo realidad el sueño de miles de niños " +
                        "y jóvenes con discapacidad. Por lo cual se solicita todo tipo de donaciones ","012801417","A",
                "Piura","Piura","Piura",
                "2018-08-01","2019-10-01",R.drawable.tele8,CELULAR));

        DonacionesAD ad = new DonacionesAD(donacionBean);
        rc.setHasFixedSize(true);
        //RecyclerView.LayoutManager ly = new LinearLayoutManager(cont);
        rc.setAdapter(ad);
        rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"sad",Toast.LENGTH_SHORT);
            }
        });
        return view;
    }


}
