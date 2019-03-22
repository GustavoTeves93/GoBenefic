package com.example.nicolas.gobenefic_102.Controller;

import com.example.nicolas.gobenefic_102.Services.SoapClient;

import java.util.HashMap;

public class UsuarioFN0007Controller {
    //DECLARACIONES
    public final String SOAP_ACTION ="http://ws.sos.idat.edu.pe/hello";
    public final String OPERATION_NAME ="FN0007";
    public final String WSDL_TARGET_NAMESPACE ="http://ws.sos.idat.edu.pe/";
    //public final String SOAP_ADDRESS = "http://192.168.43.50:8080/WebAlertas/AlertasWS?WSDL";

    SoapClient soapClient = null;
    //CONSTRUCTOR
    public UsuarioFN0007Controller() {
        soapClient = new SoapClient();
    }

    public String FN007(HashMap mapParametros){
        String strRespones = soapClient.Call(WSDL_TARGET_NAMESPACE,
                UsuarioControllerGlobal.SOAP_ADDRESS,OPERATION_NAME,mapParametros);
        return strRespones;
    }
}
