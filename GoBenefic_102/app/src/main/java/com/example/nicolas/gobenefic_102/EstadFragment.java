package com.example.nicolas.gobenefic_102;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0007Controller;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0008Controller;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EstadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadFragment extends Fragment {

    TextView totalDona,totalDonaCod,historial;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EstadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstadFragment newInstance(String param1, String param2) {
        EstadFragment fragment = new EstadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_estad, container, false);

        String ACCESO = getArguments() != null ? String.valueOf(getArguments().getInt("ACCESO")) : "USUARIO ";

        String NOMBRE = getArguments() != null ? getArguments().getString("TOTAL_NOMBRE") : "USUARIO ";
        String APELLIDO = getArguments() != null ? getArguments().getString("TOTAL_APELLIDO") : "USUARIO ";

        String TOTAL_DONA = getArguments() != null ? String.valueOf(getArguments().getInt("TOTAL_DONA")) : "USUARIO ";
        String TOTAL_CODIGO = getArguments() != null ? String.valueOf(getArguments().getInt("TOTAL_DONA_C")) : "USUARIO ";
        totalDona = (TextView)view.findViewById(R.id.txtDonaRealizadas);
        totalDonaCod = (TextView)view.findViewById(R.id.txtDonaR);
        historial = (TextView)view.findViewById(R.id.txtDonaHistorial);

        totalDona.setText("GoBenefic ha registrado " +TOTAL_DONA+" donaciones desde 10/08/2018 ");
        totalDonaCod.setText(NOMBRE+ " "+ APELLIDO + " has realizado "+TOTAL_CODIGO+" donaciones");


        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(),HistorialActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
