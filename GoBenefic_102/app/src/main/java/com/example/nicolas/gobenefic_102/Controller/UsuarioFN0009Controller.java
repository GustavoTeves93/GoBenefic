package com.example.nicolas.gobenefic_102.Controller;

import android.util.Log;

import com.example.nicolas.gobenefic_102.Services.SoapClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UsuarioFN0009Controller {
    //DECLARACIONES
    public final String SOAP_ACTION ="http://ws.sos.idat.edu.pe/FN0009";
    public final String OPERATION_NAME ="FN0009";
    public final String WSDL_TARGET_NAMESPACE ="http://ws.sos.idat.edu.pe/";
    public final String SOAP_ADDRESS = "http://192.168.43.50:8080/WebAlertas/AlertasWS?WSDL";

     String strResult = null;
     JSONObject jsonObject = null;

    SoapClient soapClient = null;
    //CONSTRUCTOR
    public UsuarioFN0009Controller() {
        soapClient = new SoapClient();
    }

    public JSONObject FN009(HashMap mapParametros){
        try {
            SoapClient soapClient = new SoapClient();
            String strRespones = soapClient.Call(WSDL_TARGET_NAMESPACE,
                    SOAP_ADDRESS,OPERATION_NAME,mapParametros);
            Log.e("RESULTADO",strRespones);
            jsonObject = new JSONObject(strRespones);
        }catch (Exception e){
            Log.e("ERROR",e.getMessage());
            try {
                jsonObject = new JSONObject();
                jsonObject.put("status", false);
                jsonObject.put("message", e.getMessage());
            }catch(JSONException ex){
                Log.e("ERROR",ex.getMessage());
            }
        }
        return jsonObject;
    }
}
