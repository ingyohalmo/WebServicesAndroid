package fia.ues.sv.webservicesandroid;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class IngresarNota extends Activity {

    EditText carnetTxt;
    EditText codMateriaTxt;
    EditText cicloTxt;
    EditText notaTxt;
    Conexion conn;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_nota);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        conn = new Conexion();

        carnetTxt = (EditText) findViewById(R.id.editText_notaCarnet);
        codMateriaTxt = (EditText) findViewById(R.id.editText_notaMateria);
        cicloTxt = (EditText) findViewById(R.id.editText_notaCiclo);
        notaTxt = (EditText) findViewById(R.id.editText_notaFinal);

        carnetTxt.setText("LL00001");
        codMateriaTxt.setText("PRN115");
        cicloTxt.setText("10");
        notaTxt.setText("5");
    }

    public void insertarNota(View v) {
        String carnet = carnetTxt.getText().toString();
        String codMateria = codMateriaTxt.getText().toString();
        String ciclo = cicloTxt.getText().toString();
        String notaFinal = notaTxt.getText().toString();
        String url = null;

        JSONObject datosNota = new JSONObject();
        JSONObject nota = new JSONObject();

        if (v.getId() == R.id.button4 || v.getId() == R.id.button5 || v.getId() == R.id.button6) {
            url = "";
            if (v.getId() == R.id.button4)
                url = conn.getURLLocal() + "/ws_nota_insert.php" + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo=" + ciclo + "&notafinal=" + notaFinal;
            if (v.getId() == R.id.button5)
                url = conn.getURLServerLocal() + "/ws_nota_insert.php" + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo=" + ciclo + "&notafinal=" + notaFinal;
            if (v.getId() == R.id.button6)
                url = conn.getURLPublico() + "/ws_nota_insert.php" + "?carnet=" + carnet + "&codmateria=" + codMateria + "&ciclo=" + ciclo + "&notafinal=" + notaFinal;
            ControladorServicio.insertarNotaPHP(url, this);
        }
    }
}



