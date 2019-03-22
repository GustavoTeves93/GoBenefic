package com.example.nicolas.gobenefic_102.Services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SoapClient {
    public SoapClient()
    {
    }
    public String Call(String WSDL_TARGET_NAMESPACE, String SOAP_ADDRESS, String OPERATION_NAME, HashMap<String, String> PARAMETERS)
    {
        String SOAP_ACTION = WSDL_TARGET_NAMESPACE + OPERATION_NAME; //SOAP_ACTION

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME); //SOLICITUD

        //CARGA DE PARAMETROS DINAMICAMENTE ITERANDO UN MAP
        Iterator it = PARAMETERS.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            request.addProperty(e.getKey().toString(), e.getValue().toString());

            /* METODO TRADICIONAL
            request.addProperty("name", "123456");
            */

            /* METODO USANDO UN PROPERTYINFO
            //PropertyInfo propertyInfo =new PropertyInfo();
            pi=new PropertyInfo();
            pi.setName(e.getKey().toString());
            pi.setValue(e.getValue().toString());
            pi.setType(String.class);

            request.addProperty(pi);
            */
        }


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false; // true, solo si es servicio es .NET ASMX o WCF
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 60000); //CONEXION

        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope); //ENVIO DE SOLICITUD
            response = envelope.getResponse();
            httpTransport.getServiceConnection().disconnect();
        }
        catch(SocketTimeoutException e){
            response="{\"Result\" : \"-1\", \"Message\" : \"" + e.toString() + "\"}";
            //timeoutexcep=true;
            e.printStackTrace();
        }
        catch(UnknownHostException e){
            response= "{\"Result\" : \"-1\", \"Message\" : \"" + e.toString() + "\"}";
            //httpexcep=true;
            //e.printStackTrace();
        }
        catch (Exception e) {
            response="{\"Result\" : \"-1\", \"Message\" : \"" + e.toString() + "\"}";
            //genexcep=true;
            e.printStackTrace();
        }
        finally {
            try {
                httpTransport.getServiceConnection().disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response.toString();
    }


}
