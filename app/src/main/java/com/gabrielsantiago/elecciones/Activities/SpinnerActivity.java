package com.gabrielsantiago.elecciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.gabrielsantiago.elecciones.Adapter.spinnerAdapter;
import com.gabrielsantiago.elecciones.R;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {

    public final int MAX_VOTOS = 3;
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
    Button botonVotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinnerCandidatos = findViewById(R.id.spinnerElecciones);
        spinnerAdapter adapter = new spinnerAdapter(getApplicationContext(),listaNombres,listaFotos,listaPartidos);
        spinnerCandidatos.setAdapter(adapter);

        botonVotos= findViewById(R.id.buttonVotos);

        ArrayList<String>votos = new ArrayList<>();
        spinnerCandidatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==1){
                    String seleccion = parent.getSelectedItem().toString();
                    votos.add(seleccion);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        botonVotos.setOnClickListener(new View.OnClickListener() {
            int contador = 0;
            @Override
            public void onClick(View v) {
                contador++;
                ArrayList<String> votos = new ArrayList<>();
                    if (contador==0){
                        botonVotos.setText("Votos 0/3");
                    } else if (contador==1){
                        botonVotos.setText("Votos 1/3");
                    } else if (contador==2) {
                        botonVotos.setText("Votos 2/3");
                    } else if (contador==3) {
                        botonVotos.setEnabled(false);
                        botonVotos.setText("Votos 3/3");
                    }
                }
        });

    }
}