package com.example.nicolas.gobenefic_102;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0007Controller;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0008Controller;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //alertSinAcceso("Usted no ha cumplido con las politicas acordadas por la fundación, pongase en contacto 968 468 315");
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onStart() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        Log.i("ONSTART","******************");
        super.onStart();
    }

    @Override
    protected void onResume() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //android.os.Process.killProcess(android.os.Process.myPid());
            return true;
        }

        if (id == R.id.action_salir) {
            //android.os.Process.killProcess(android.os.Process.myPid());
            alertSalir("Confirmar");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position){
                case 0:
                    DonaFragment donaFragment = new DonaFragment();
                    SharedPreferences read = getSharedPreferences("usuarioGuardado",MODE_PRIVATE);
                    Bundle args = new Bundle();
                    args.putInt("T_CODUSUR",read.getInt("TUSU_CODUSUR",0));
                    args.putString("T_CODIDENT",read.getString("TUSU_CODIDENT",""));
                    args.putString("T_NOMBRE",read.getString("TUSU_NOMBRE",""));
                    args.putString("T_APELLIDO",read.getString("TUSU_APELLIDO",""));
                    args.putString("T_CORREO",read.getString("TUSU_CORREO",""));
                    args.putString("T_CLAVIDENT",read.getString("TUSU_CLAVIDENT",""));
                    args.putString("T_FECHNACI",read.getString("TUSU_FECHNACI",""));
                    args.putInt("T_CELULAR",read.getInt("TUSU_CELULAR",0));
                    args.putString("T_DIRECCION",read.getString("TUSU_DIRECCION",""));
                    args.putString("T_PRESECRE",read.getString("TUSU_PRESECRE",""));
                    args.putString("T_RESSECRE",read.getString("TUSU_RESSECRE",""));
                    args.putString("T_ESTADO",read.getString("TUSU_ESTADO",""));
                    args.putString("T_TARJETA",read.getString("TUSU_TARJETA",""));
                    args.putString("T_TARJCLAV",read.getString("TUSU_TARJCLAV",""));

                    donaFragment.setArguments(args);
                    return donaFragment;
                case 1:

                    EstadFragment estadFragment = new EstadFragment();
                    SharedPreferences read2 = getSharedPreferences("usuarioGuardado",MODE_PRIVATE);
                    Bundle args2 = new Bundle();
                    args2.putInt("ACCESO",read2.getInt("TACC_CODACC",0));
                    args2.putString("TOTAL_NOMBRE",read2.getString("TUSU_NOMBRE",""));
                    args2.putString("TOTAL_APELLIDO",read2.getString("TUSU_APELLIDO",""));
                    args2.putInt("TOTAL_DONA",read2.getInt("TOTAL_DONA",0));
                    args2.putInt("TOTAL_DONA_C",read2.getInt("TOTAL_DONA_C",0));
                    estadFragment.setArguments(args2);
                    return estadFragment;
              /*  case 2:
                    return PlaceholderFragment.newInstance(3);*/
            }
            return null;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    @Override
    public void onBackPressed() {
        alertSalir("Confirmar");
    }

    private void alertSalir(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Seguro que desea salir")
                .setMessage(msg)

                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
    }
    private void alertSinAcceso(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("USUARIO BLOQUEADO")
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }



}
