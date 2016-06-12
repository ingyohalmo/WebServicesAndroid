package fia.ues.sv.webservicesandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi")
public class ActualizarMateria extends Activity {
    ControlBDAlumno db;
    Conexion conn;
    static List<Materia> listaMaterias;
    static List<String> nombreMaterias;

    EditText fechaTxt;
    ListView listViewMaterias;

    TextView texto1;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_materia);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        db = new ControlBDAlumno(this);
        conn = new Conexion();
        listaMaterias = new ArrayList<Materia>();
        nombreMaterias = new ArrayList<String>();
        fechaTxt = (EditText) findViewById(R.id.editText_fecha);
        listViewMaterias = (ListView) findViewById(R.id.listView1);
        texto1 = (TextView) findViewById(R.id.textView1);
        //valor default
        fechaTxt.setText("2011/01/01");
    }

    public void servicioPHP(View v) {
        String[] fecha = fechaTxt.getText().toString().split("/");
        String url = "";

        if (v.getId() == R.id.button4)
            url = conn.getURLLocal() + "/ws_db_materia_fecha.php" + "?year=" + fecha[0]  + "&month=" + fecha[1] +  "&day=" + fecha[2];
        if (v.getId() == R.id.button5)
            url = conn.getURLServerLocal() + "/ws_db_materia_fecha.php" + "?year=" + fecha[0]  + "&month=" + fecha[1] +  "&day=" + fecha[2];
        if (v.getId() == R.id.button6)
            url = conn.getURLPublico() + "/ws_db_materia_fecha.php" + "?year=" + fecha[0]  + "&month=" + fecha[1] +  "&day=" + fecha[2];

        String materiasExternas = "";
        materiasExternas = ControladorServicio.obtenerRespuestaPeticion(url, this);
        try {
            listaMaterias.addAll(ControladorServicio.obtenerMateriasExterno(materiasExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardar(View v) {
        db.abrir();
        for (int i = 0; i < listaMaterias.size(); i++) {
            Log.v("guardar", db.insertar(listaMaterias.get(i)));
        }
        db.cerrar();
        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show();
        listaMaterias.removeAll(listaMaterias);
        actualizarListView();
    }

    private void actualizarListView() {
        String dato = "";

        nombreMaterias.clear();
        for (int i = 0; i < listaMaterias.size(); i++) {
            dato = listaMaterias.get(i).getCodMateria() + " " + listaMaterias.get(i).getNombreMateria();
            nombreMaterias.add(dato);
        }

        eliminarElementosDuplicados();

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreMaterias);
        listViewMaterias.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<Materia> conjuntoMateria = new HashSet<Materia>();
        conjuntoMateria.addAll(listaMaterias);
        listaMaterias.clear();
        listaMaterias.addAll(conjuntoMateria);

        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreMaterias);
        nombreMaterias.clear();
        nombreMaterias.addAll(conjuntoNombre);
    }
}




