package fia.ues.sv.webservicesandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lanzarActividad(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_materia:
                i = new Intent(this, ActualizarMateria.class);
                break;
            case R.id.button_alumno:
                i = new Intent(this, PromedioAlumno.class);
                break;
            case R.id.button_nota:
                i = new Intent(this, IngresarNota.class);
                break;
        }
            if (i != null)
                startActivity(i);
        }
    }



