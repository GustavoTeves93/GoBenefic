package com.example.nicolas.gobenefic_102;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0005Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class ConfirmarDonaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int REQUEST_READ_CONTACTS = 0;

    SharedPreferences read;
    SharedPreferences.Editor write;
    int accesoCanal = 0,codigoDona;
    TextView txtTit,txtcel;
    String titulo,nombre,apellido,celular;
    private CheckBox chkAnoni;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView,mNombreView,mApellidoView,mCelularView,mDireccionView,mFechaNaView,
            mPreguntaView,mRespuestaView;
    private EditText mtxtOtros,mtxtEfectivo,mtxtcontacto,mtxtdir,mtxtcomentario;
    private View mProgressView;
    private View mLoginFormView;
    String estadoAnonimo,otros,efectivo,contacto,direc,comentario;
    UsuarioFN0005Controller usuarioFN0005Controller = new UsuarioFN0005Controller();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_dona);

        read = getSharedPreferences("usuarioGuardado",MODE_PRIVATE);
        write = read.edit();
        accesoCanal = read.getInt("TACC_CODACC",0);
        nombre = read.getString("TUSU_NOMBRE","");
        apellido = read.getString("TUSU_APELLIDO","");
        celular = read.getString("TUSU_APELLIDO","");
        Bundle datos = this.getIntent().getExtras();
        codigoDona = datos.getInt("codigo");
        titulo = datos.getString("titulo");
        celular = datos.getString("celular");


        txtTit = (TextView)findViewById(R.id.txtTituloDonacion);
        chkAnoni = (CheckBox)findViewById(R.id.chkAnonimo);
        txtcel = (TextView)findViewById(R.id.txtcontacto);


        estadoAnonimo = "I";

        txtTit.setText("Gracias por apoyar a : "  + titulo);
        txtcel.setText(celular);
        mtxtOtros = (EditText) findViewById(R.id.txtOtros);
        mtxtOtros.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mtxtEfectivo = (EditText) findViewById(R.id.txtEfectivo);
        mtxtcontacto = (EditText) findViewById(R.id.txtcontacto);
        mtxtdir = (EditText) findViewById(R.id.txtdir);
        mtxtcomentario = (EditText)findViewById(R.id.txtcomentario);

        Button mEmailSignInButton = (Button) findViewById(R.id.button_donar);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkAnoni.isChecked()){
                    estadoAnonimo = "A";
                }else{
                    estadoAnonimo = "I";
                }
                attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form1);
        mProgressView = findViewById(R.id.login_progress1);

    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }
    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    private void attemptLogin() {
        /*if (mAuthTask != null) {
            return;
        }*/
        mtxtOtros.setError(null);
        mtxtEfectivo.setError(null);
        mtxtcontacto.setError(null);
        mtxtdir.setError(null);
        mtxtcomentario.setError(null);

        otros = mtxtOtros.getText().toString();
        efectivo = mtxtEfectivo.getText().toString();
        contacto = mtxtcontacto.getText().toString();
        direc = mtxtdir.getText().toString();
        comentario = mtxtcomentario.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(otros)) {
            mtxtOtros.setError(getString(R.string.error_incorrect_otros));
            focusView = mtxtOtros;
            cancel = true;
        }
        if (!isMenor(otros)) {
            mtxtOtros.setError(getString(R.string.error_incorrect_otros));
            focusView = mtxtOtros;
            cancel = true;
        }

        if (TextUtils.isEmpty(efectivo)) {
            mtxtEfectivo.setError(getString(R.string.error_incorrect_efectivo));
            focusView = mtxtEfectivo;
            cancel = true;
        }

        if (TextUtils.isEmpty(contacto) || !isCelularValid(contacto)) {
            mtxtcontacto.setError(getString(R.string.error_incorrect_cel));
            focusView = mtxtcontacto;
            cancel = true;
        }

        if (!isMenor50(direc)) {
            mtxtdir.setError(getString(R.string.error_incorrect_dir));
            focusView = mtxtdir;
            cancel = true;
        }

        if (!isMenor(comentario)) {
            mtxtcomentario.setError(getString(R.string.error_incorrect_comen));
            focusView = mtxtcomentario;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            //showProgress(true);
            alertConfirmar( nombre+" "+ apellido+" "+ ", declaras que la información proporcionada es legitima.                                                             Nos pondremos en contacto con el celular proporcionado, gracias");
           /* mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);*/
            //new RegistroActivity.UserRegistrarTask().execute(nombre,apellido,email,password,celular,direccion,pregunta,respuesta);
        }
    }
    private boolean isMenor(String password) {
        //TODO: Replace this with your own logic
        return password.length() < 300;
    }
    private boolean isMenor50(String password) {
        //TODO: Replace this with your own logic
        return password.length() < 100;
    }
    private boolean isCelularValid(String celular) {
        //TODO: Replace this with your own logic
        return celular.length() < 10;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
    }
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(ConfirmarDonaActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }
    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };
        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    public class UserRegistrarDonaTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            HashMap mapParametros = new HashMap();
            mapParametros.put("codDona",strings[0]);
            mapParametros.put("codAcc",strings[1]);
            mapParametros.put("descDona",strings[2]);
            mapParametros.put("montoDona",strings[3]);
            mapParametros.put("celDona",strings[4]);
            mapParametros.put("direcDona",strings[5]);
            mapParametros.put("anonDona",strings[6]);
            mapParametros.put("estaDona",strings[7]);
            mapParametros.put("desc2Dona",strings[8]);
            return usuarioFN0005Controller.FN005(mapParametros);
        }
        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            int c1 = Integer.parseInt(s);
            Log.i("EXITO REGISTRO:", String.valueOf(c1));
            if (c1 == 1) {
                //alertRegistro("REGISTRADO CORRECTAMENTE");
                finish();
                /*Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);*/
            }else{
                alertRegistro("NO SE PUDO REGISTRAR - ERROR FN0005");
            }
        }
        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }

    private void alertConfirmar(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(ConfirmarDonaActivity.this);
        dialog.setTitle("Declaración :")
                .setMessage(msg)

                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new UserRegistrarDonaTask()
                                .execute(String.valueOf(codigoDona),String.valueOf(accesoCanal),otros,efectivo,contacto,direc,estadoAnonimo,"A",comentario);

                    }
                }).show();
    }
    private void alertRegistro(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(ConfirmarDonaActivity.this);
        dialog.setTitle("Información")
                .setMessage(msg)
                .setPositiveButton("Si", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                }).show();
    }
}
