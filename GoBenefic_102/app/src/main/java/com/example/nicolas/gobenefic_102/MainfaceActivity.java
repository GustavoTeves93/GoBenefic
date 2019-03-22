package com.example.nicolas.gobenefic_102;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainfaceActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    TextView txtEmai,txtCumple,txtFriend;
    ProgressDialog mDialog;
    ImageView imgAvatar;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        callbackManager = CallbackManager.Factory.create();

        txtEmai = (TextView)findViewById(R.id.txtEmail);
        txtCumple = (TextView)findViewById(R.id.txtCumpleaños);
        txtFriend = (TextView)findViewById(R.id.txtFriends);

        imgAvatar = (ImageView)findViewById(R.id.avatar);

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(MainfaceActivity.this);
                mDialog.setMessage("Retrieving data");
                mDialog.show();

                String accesstoken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response",response.toString());
                        getData(object);
                    }
                });
                //Request
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,first_name,last_name,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {

            }
        });


        //If already login
        if (AccessToken.getCurrentAccessToken() != null){
            txtEmai.setText(AccessToken.getCurrentAccessToken().getUserId());
            txtCumple.setText(AccessToken.getCurrentAccessToken().getApplicationId());
        }
        //printKeyHash();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void getData(JSONObject object) {
        try{
            URL profile_picture = new URL("https://graph.facebook.com/" + object.getString("id")+"/picture?width=350&height=350");
            Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);

            txtEmai.setText(object.getString("email") + " " +object.getString("first_name")+ " " +object.getString("last_name"));
            txtCumple.setText(object.getString("birthday"));
            txtFriend.setText("Friends:"+object.getJSONObject("friends").getJSONObject("summary").getString("total_count"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().
                    getPackageInfo("com.example.nicolas.gobenefic_102", PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException e){e.printStackTrace();}
        catch (NoSuchAlgorithmException e){e.printStackTrace();}
    }

}
