package com.gabrielsantiago.elecciones.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gabrielsantiago.elecciones.Adapter.SpinnerAdapter;
import com.gabrielsantiago.elecciones.Clases.Candidato;
import com.gabrielsantiago.elecciones.R;

import java.util.ArrayList;


public class SpinnerActivity extends AppCompatActivity {


    Spinner spinnerCandidatos;
    Button botonVotos;
    ArrayList<Candidato> listaCandidatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        listaCandidatos = new ArrayList<>();
        listaCandidatos.add(new Candidato("Doña Concha", R.drawable.concha,"Radiopactos"));
        listaCandidatos.add(new Candidato("Marisa", R.drawable.marisa,"Radiopactos"));
        listaCandidatos.add(new Candidato("Vicenta", R.drawable.vicenta,"Radiopactos"));
        listaCandidatos.add(new Candidato("Lucía", R.drawable.lucia,"La pija y amigas"));
        listaCandidatos.add(new Candidato("Belén", R.drawable.belen,"La pija y amigas"));
        listaCandidatos.add(new Candidato("Bea", R.drawable.bea,"La pija y amigas"));
        listaCandidatos.add(new Candidato("Juan 'Chorizo' Cuesta", R.drawable.juan,"Esta, nuestra Comunidad"));
        listaCandidatos.add(new Candidato("Isabel 'Yerbas'", R.drawable.yerbas,"Esta, nuestra Comunidad"));
        listaCandidatos.add(new Candidato("Mauri", R.drawable.mauri,"Esta, nuestra Comunidad"));
        listaCandidatos.add(new Candidato("Paco", R.drawable.paco,"Los pibes del videoclub"));
        listaCandidatos.add(new Candidato("'Josemi' Cuesta", R.drawable.josemi,"Los pibes del videoclub"));
        listaCandidatos.add(new Candidato("Roberto", R.drawable.roberto,"Los pibes del videoclub"));
        listaCandidatos.add(new Candidato("Emilio Delgado", R.drawable.emilio,"Un poquito de porfavoh"));
        listaCandidatos.add(new Candidato("Mariano Delgado", R.drawable.mariano,"Un poquito de porfavoh"));
        listaCandidatos.add(new Candidato("Jose María", R.drawable.josemaria,"Un poquito de porfavoh"));


        spinnerCandidatos = findViewById(R.id.spinnerElecciones);
        SpinnerAdapter adapter = new SpinnerAdapter(getApplicationContext(), listaCandidatos);
        spinnerCandidatos.setAdapter(adapter);

        botonVotos= findViewById(R.id.buttonVotos);
        ArrayList<String> votos = new ArrayList<>();
        Button botonVotos = findViewById(R.id.buttonVotos);

        botonVotos.setOnClickListener(new View.OnClickListener() {
            int contador = 0;
            @Override
            public void onClick(View v) {
                if (contador < 3) {
                    // Obtiene el candidato seleccionado del Spinner
                    Candidato candidatoSeleccionado = (Candidato) spinnerCandidatos.getSelectedItem();

                    // Verifica si el nombre del candidato ya está en la lista de votos
                    if (!votos.contains(candidatoSeleccionado.getNombre())) {
                        Toast.makeText(getApplicationContext(), "Has elegido a"+candidatoSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
                        votos.add(candidatoSeleccionado.getNombre());

                        contador++;

                        botonVotos.setText("Votos " + contador + "/3");

                        if (contador == 3) {
                            botonVotos.setEnabled(false);
                            botonVotos.setVisibility(View.GONE);

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Ya has votado por este candidato.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button botonBBDD = findViewById(R.id.buttonVotosFinal);
        botonBBDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Separar nombres De la lista
                String nombre1 = votos.get(0);
                String nombre2 = votos.get(1);
                String nombre3 = votos.get(3);

            }
        });



    }
}