//package com.fyp.halalrestaurants;
//
//import org.ksoap2.SoapEnvelope;
//import org.ksoap2.serialization.PropertyInfo;
//import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.SoapSerializationEnvelope;
//import org.ksoap2.transport.AndroidHttpTransport;
// 
//
//public class WebserviceCall {
//     
//    /**
//     * Variable Decleration................
//     * 
//     */
//    String namespace = "http://localhost:14400/HalalRestaurantClient/sampleRestaurantWebProxy/Input.jsp";
//    private String url = "http://localhost:14400/HalalRestaurantClient/sampleRestaurantWebProxy/Input.jsp?method=13";
//     
//    String SOAP_ACTION;
//    private static final String METHOD_NAME = "sayHelloWorld";
//    SoapObject request = null, objMessages = null;
//    SoapSerializationEnvelope envelope;
//    AndroidHttpTransport androidHttpTransport;
//     
//    WebserviceCall() {
//    }
// 
//     
//    /**
//     * Set Envelope
//     */
//    protected void SetEnvelope() {
//         
//        try {
//             
//            // Creating SOAP envelope           
//            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
//             
//            //You can comment that line if your web service is not .NET one.
//            //envelope.dotNet = true;
//             
//            envelope.setOutputSoapObject(request);
//            androidHttpTransport = new AndroidHttpTransport(url);
//            androidHttpTransport.debug = true;
//             
//        } catch (Exception e) {
//            System.out.println("Soap Exception---->>>" + e.toString());    
//        }
//    }
// 
//    // MethodName variable is define for which webservice function  will call
//    public String getXml(String MethodName) 
//      {
//         
//        try {
//            SOAP_ACTION = namespace + MethodName;
//             
//            //Adding values to request object
//            request = new SoapObject(namespace, MethodName);
//             
//       
//             
//            SetEnvelope();
//             
//            try {
//                 
//                //SOAP calling webservice
//                androidHttpTransport.call(SOAP_ACTION, envelope);
//                 
//                //Got Webservice response
//                String xml = envelope.getResponse().toString();
// 
//                return xml;
//                 
//            } catch (Exception e) {
//                // TODO: handle exception
//                return e.toString();
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            return e.toString();
//        }
// 
//    }
// 
//     
//    /************************************/
// }