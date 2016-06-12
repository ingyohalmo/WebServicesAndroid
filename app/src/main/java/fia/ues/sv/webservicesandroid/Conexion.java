package fia.ues.sv.webservicesandroid;

/**
 * Created by yohalmo on 06-11-16.
 */
public class Conexion {

    public String URLLocal = "http://192.168.1.8/WebServices";
    public String URLServerLocal = "http://192.168.1.8/WebServices";
    public String URLPublico = "http://190.62.44.208/WebServices";

    public Conexion() {
    }

    public String getURLLocal() {
        return URLLocal;
    }

    public String getURLServerLocal() {
        return URLServerLocal;
    }

    public String getURLPublico() {
        return URLPublico;
    }
}
