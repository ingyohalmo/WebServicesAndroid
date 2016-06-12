package fia.ues.sv.webservicesandroid;


import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

@SuppressLint("NewApi")
public class PromedioAlumno extends Activity {
    EditText carnetTxt;
    TextView notaPromedioTxt;
    ControlBDAlumno db;
    Conexion conn;
    Button button0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promedio_alumno);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        carnetTxt = (EditText) findViewById(R.id.editText_alumnoCarnet);
        notaPromedioTxt = (TextView) findViewById(R.id.textView_alumnoNota);
        button0 = (Button) findViewById(R.id.button0);
        conn = new Conexion();
        //valor default
        button0.setText("L");
        carnetTxt.setText("DR12004");
    }

    public void cambiovalor(View v) {
        if (button0.getText() == "L") {
            carnetTxt.setText("DR12004");
            button0.setText("N");
        } else {
            carnetTxt.setText("NN00001");
            button0.setText("L");
        }
    }

    public void consultarPromedioPHP(View v) {
        String carnet = carnetTxt.getText().toString();
        String url = "";
        if (v.getId() == R.id.button4)
            url = conn.getURLLocal() + "/ws_db_carnet_group.php?carnet=" + carnet;
        if (v.getId() == R.id.button5)
            url = conn.getURLServerLocal() + "/ws_db_carnet_group.php?carnet=" + carnet;
        if (v.getId() == R.id.button6)
            url = conn.getURLPublico() + "/ws_db_carnet_group.php?carnet=" + carnet;
        //url="http://138.255.154.43/ws_db_carnet_group.php?carnet=NN00001";
        String notaPromedioJSON = ControladorServicio.obtenerRespuestaPeticion(url, this);
        notaPromedioTxt.setText("Nota Promedio Externo: " + ControladorServicio.obtenerPromedioJSON(notaPromedioJSON, this));
    }
}


