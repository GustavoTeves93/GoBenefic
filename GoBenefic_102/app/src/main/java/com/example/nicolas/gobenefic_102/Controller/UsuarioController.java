package com.example.nicolas.gobenefic_102.Controller;

import com.example.nicolas.gobenefic_102.Services.SoapClient;

import java.util.HashMap;

public class UsuarioController {
    //DECLARACIONES
    public final String SOAP_ACTION ="http://ws.sos.idat.edu.pe/hello";
    public final String OPERATION_NAME ="FN0001";
    public final String WSDL_TARGET_NAMESPACE ="http://ws.sos.idat.edu.pe/";
    public final String SOAP_ADDRESS = "http://192.168.43.50:8080/WebAlertas/AlertasWS?WSDL";

    SoapClient soapClient = null;
    //CONSTRUCTOR
    public UsuarioController() {
        soapClient = new SoapClient();
    }

    public String FN001(HashMap mapParametros){
        String strRespones = soapClient.Call(WSDL_TARGET_NAMESPACE,
                SOAP_ADDRESS,OPERATION_NAME,mapParametros);
        return strRespones;
    }

}
