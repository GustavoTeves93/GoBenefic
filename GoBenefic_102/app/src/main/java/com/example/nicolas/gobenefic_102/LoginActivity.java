package com.example.nicolas.gobenefic_102;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.gobenefic_102.Bean.UsuarioBean;
import com.example.nicolas.gobenefic_102.Controller.UsuarioController;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0003Controller;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0004Controller;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0007Controller;
import com.example.nicolas.gobenefic_102.Controller.UsuarioFN0008Controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    //private UserLoginTask mAuthTask = null;
    UsuarioController usuarioController = new UsuarioController();
    UsuarioFN0003Controller usuarioFN0003Controller = new UsuarioFN0003Controller();
    UsuarioFN0004Controller usuarioFN0004Controller = new UsuarioFN0004Controller();
    //TRAE CANTIDAD DE DONACIONES
    UsuarioFN0007Controller usuarioFN0007Controller = new UsuarioFN0007Controller();
    UsuarioFN0008Controller usuarioFN0008Controller = new UsuarioFN0008Controller();

    /*AGREGO 25-07-2018*/
    ArrayList<UsuarioBean> aryUsuario = null;
    SharedPreferences read;
    SharedPreferences.Editor write;
    Bundle args;
    /*AGREGO 25-07-2018*/
    private ImageView imgFacebook;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /*AGREGO 25-07-2018*/
        read = getSharedPreferences("usuarioGuardado",MODE_PRIVATE);
        write = read.edit();
        /*AGREGO 25-07-2018*/
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);
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
        Button bton = (Button) findViewById(R.id.btnRegistroNuevo);
        bton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(x);
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        imgFacebook = (ImageView)findViewById(R.id.imageFacebook);
        imgFacebook.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(),MainfaceActivity.class);
                startActivity(x);
            }
        });

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
        mEmailView.setError(null);
        mPasswordView.setError(null);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
        //    mAuthTask = new UserLoginTask(email, password);
        //    mAuthTask.execute((Void) null);
            new UserLoginDatos().execute(email,password);
            new UserLoginTask().execute(email,password);
            new UserAccesoTask().execute();

        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
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
                // Retrieve data rows for the device user's 'profile' contact.
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
                new ArrayAdapter<>(LoginActivity.this,
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

    public class UserLoginTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            HashMap mapParametros = new HashMap();
            mapParametros.put("correo",strings[0]);
            mapParametros.put("clave",strings[1]);

            return usuarioController.FN001(mapParametros);
        }
        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            int c1 = Integer.parseInt(s);
            Log.i("EXITO LOGIN", String.valueOf(c1));
            if (c1 == 1) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }else{
                alertLogin("VERIFIQUE SUS DATOS");
            }
        }
        @Override
        protected void onCancelled() {
            showProgress(false);
        }
    }
    private void alertLogin(String msg){
        final AlertDialog.Builder dialog=new AlertDialog.Builder(LoginActivity.this);
        dialog.setTitle("Informacion")
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                }).show();
    }
    /***********************************************************************/

    public class UserLoginDatos extends AsyncTask<String,Integer,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            HashMap mapParametros = new HashMap();
            mapParametros.put("correo1",strings[0]);
            mapParametros.put("clave1",strings[1]);
            /*AGREGO 25-07-2018*/
            //guardarUsuario();
            /*AGREGO 25-07-2018*/
            return usuarioFN0003Controller.FN003(mapParametros);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if(jsonObject.getBoolean("status")) {
                    Log.i("EXITO1", "se conecto a la data");
                    /*AGREGO 25-07-2018*/
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    aryUsuario = new ArrayList<>();
                    JSONObject j = jsonArray.getJSONObject(0);
                    write.putInt("TUSU_CODUSUR",j.getInt("TUSU_CODUSUR")).commit();
                    write.putString("TUSU_CODIDENT",j.getString("TUSU_CODIDENT")).commit();
                    write.putString("TUSU_NOMBRE",j.getString("TUSU_NOMBRE")).commit();
                    write.putString("TUSU_APELLIDO",j.getString("TUSU_APELLIDO")).commit();
                    write.putString("TUSU_CORREO",j.getString("TUSU_CORREO")).commit();
                    write.putString("TUSU_CLAVIDENT",j.getString("TUSU_CLAVIDENT")).commit();
                    write.putString("TUSU_FECHNACI",j.getString("TUSU_FECHNACI")).commit();
                    write.putInt("TUSU_CELULAR",j.getInt("TUSU_CELULAR")).commit();
                    write.putString("TUSU_DIRECCION",j.getString("TUSU_DIRECCION")).commit();
                    write.putString("TUSU_PRESECRE",j.getString("TUSU_PRESECRE")).commit();
                    write.putString("TUSU_RESSECRE",j.getString("TUSU_RESSECRE")).commit();
                    write.putString("TUSU_ESTADO",j.getString("TUSU_ESTADO")).commit();
                    write.putString("TUSU_TARJETA",j.getString("TUSU_TARJETA")).commit();
                    write.putString("TUSU_TARJCLAV",j.getString("TUSU_TARJCLAV")).commit();
                    /*AGREGO 25-07-2018*/
                    Log.i("BIENVENIDO", String.valueOf(read.getInt("TUSU_CODUSUR",0)) +" "+read.getString("TUSU_NOMBRE",""));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    /***********************************************************************/
    public class UserAccesoTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String cod ="";
            cod = String.valueOf(read.getInt("TUSU_CODUSUR",0));
            HashMap mapParametros = new HashMap();
            mapParametros.put("codigo",cod);
            return usuarioFN0004Controller.FN004(mapParametros);
        }

        @Override
        protected void onPostExecute(String s) {
            int c1 = Integer.parseInt(s);
            Log.i("EXITO ACCESO", String.valueOf(c1));
            write.putInt("TACC_CODACC",c1).commit();
            new UserDonacionesTask().execute();
            new UserDonacionesCodigoTask().execute();
        }
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

