package com.example.nicolas.gobenefic_102;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0002Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class RegistroActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    private static final int REQUEST_READ_CONTACTS = 0;

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    //private UserLoginTask mAuthTask = null;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView,mNombreView,mApellidoView,mCelularView,mDireccionView,mFechaNaView,
                    mPreguntaView,mRespuestaView;
    private View mProgressView;
    private View mLoginFormView;
    UsuarioFN0002Controller usuarioFN0002Controller = new UsuarioFN0002Controller();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        // Set up the login form.
        mNombreView = (EditText) findViewById(R.id.txtnombre);
        mApellidoView = (EditText) findViewById(R.id.txtapellido);
        mCelularView = (EditText) findViewById(R.id.txtcelular);
        mDireccionView = (EditText)findViewById(R.id.txtdireccion);
        mFechaNaView = (EditText) findViewById(R.id.txtfechanacimiento);
        mPreguntaView = (EditText) findViewById(R.id.txtpregunta);
        mRespuestaView  = (EditText) findViewById(R.id.txtrespuesta);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.txtemail);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.txtpassword);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
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
                    .setAction(android.R.string.ok, new OnClickListener() {
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
        mNombreView.setError(null);
        mApellidoView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mCelularView.setError(null);
        mDireccionView.setError(null);
        mFechaNaView.setError(null);
        mPreguntaView.setError(null);
        mRespuestaView.setError(null);

        String nombre = mNombreView.getText().toString();
        String apellido = mApellidoView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String celular = mCelularView.getText().toString();
        String direccion = mDireccionView.getText().toString();
        String fechaNaci = mFechaNaView.getText().toString();
        String pregunta = mPreguntaView.getText().toString();
        String respuesta = mRespuestaView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(nombre)) {
            mNombreView.setError(getString(R.string.error_invalid_nombre));
            focusView = mNombreView;
            cancel = true;
        }
        if (TextUtils.isEmpty(apellido)) {
            mApellidoView.setError(getString(R.string.error_invalid_apellido));
            focusView = mApellidoView;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password_corta));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(celular) || !isCelularValid(celular)) {
            mCelularView.setError(getString(R.string.error_invalid_celular));
            focusView = mCelularView;
            cancel = true;
        }
        if (TextUtils.isEmpty(direccion)) {
            mDireccionView.setError(getString(R.string.error_invalid_direccion));
            focusView = mDireccionView;
            cancel = true;
        }

        if (TextUtils.isEmpty(fechaNaci) || !isFechaNaciValid(fechaNaci)
                || !isFechaNaciTValid(fechaNaci)) {
            mFechaNaView.setError(getString(R.string.error_invalid_fecNaci));
            focusView = mFechaNaView;
            cancel = true;
        }

        if (TextUtils.isEmpty(pregunta) ) {
            mPreguntaView.setError(getString(R.string.error_invalid_respuesta));
            focusView = mPreguntaView;
            cancel = true;
        }
        if (TextUtils.isEmpty(respuesta) ) {
            mRespuestaView.setError(getString(R.string.error_invalid_pregunta));
            focusView = mRespuestaView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email) || !isEmailValidP(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
           /* mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);*/
           new UserRegistrarTask().execute(nombre,apellido,email,password,celular,direccion,pregunta,respuesta);
        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    private boolean isEmailValidP(String email) {
        //TODO: Replace this with your own logic
        return email.contains(".");
    }
    private boolean isFechaNaciValid(String fecNac) {
        //TODO: Replace this with your own logic
        return fecNac.contains("/");
    }
    private boolean isFechaNaciTValid(String fecNac) {
        //TODO: Replace this with your own logic
        return fecNac.length() == 10;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    private boolean isCelularValid(String celular) {
        //TODO: Replace this with your own logic
        return celular.length() < 11;
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
                new ArrayAdapter<>(RegistroActivity.this,
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
    public class UserRegistrarTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            HashMap mapParametros = new HashMap();
            mapParametros.put("nombre",strings[0]);
            mapParametros.put("apellido",strings[1]);
            mapParametros.put("correo",strings[2]);
            mapParametros.put("clave",strings[3]);
            mapParametros.put("celular",strings[4]);
            mapParametros.put("direccion",strings[5]);
            mapParametros.put("pregunta",strings[6]);
            mapParametros.put("respuesta",strings[7]);
            return usuarioFN0002Controller.FN002(mapParametros);
        }
        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
                int c1 = Integer.parseInt(s);
                Log.i("EXITO REGISTRO:", String.valueOf(c1));
                if (c1 == 1) {
//                alertRegistro("USUARIO REGISTRADO CORRECTAMENTE");
                    /*Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);*/
                    finish();
                }else{
                    alertRegistro("NO SE PUDO REGISTRAR");
                }
        }
        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }
    private void alertRegistro(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(RegistroActivity.this);
        dialog.setTitle("Información")
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                }).show();
    }
}

