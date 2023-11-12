package com.gabrielsantiago.elecciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.gabrielsantiago.elecciones.Adapter.spinnerAdapter;
import com.gabrielsantiago.elecciones.R;

public class SpinnerActivity extends AppCompatActivity {

    String[] listaNombres = {
            "Doña Concha","Marisa","Vicenta",
            "Lucía","Belén", "Bea",
            "Juan 'Chorizo' Cuesta","Isabel 'Yerbas'","Mauri",
            "Paco","'Josemi' Cuesta","Roberto",
            "Emilio Delgado","Mariano Delgado","Jose María"
    };

    String[] listaPartidos = {
            "Radiopactos",
            "La pija y amigas",
            "Esta, nuestra Comunidad",
            "Los pibes del videoclub",
            "Un poquito de porfavoh"
    };

    int[] listaFotos = {
            R.drawable.concha,R.drawable.marisa,R.drawable.vicenta,
            R.drawable.lucia,R.drawable.belen,R.drawable.bea,
            R.drawable.juan,R.drawable.yerbas,R.drawable.mauri,
            R.drawable.paco,R.drawable.josemi,R.drawable.roberto,
            R.drawable.emilio,R.drawable.mariano,R.drawable.josemaria
    };

    Spinner spinnerCandidatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinnerCandidatos = findViewById(R.id.spinnerElecciones);
        spinnerAdapter adapter = new spinnerAdapter(getApplicationContext(),listaNombres,listaFotos,listaPartidos);
        spinnerCandidatos.setAdapter(adapter);

    }
}