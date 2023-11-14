package com.gabrielsantiago.elecciones.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gabrielsantiago.elecciones.Adapter.SpinnerAdapter;
import com.gabrielsantiago.elecciones.BBDD.DBHelper;
import com.gabrielsantiago.elecciones.Clases.Candidato;
import com.gabrielsantiago.elecciones.R;

import java.util.ArrayList;


public class SpinnerActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Spinner spinnerCandidatos;
    ArrayList<Candidato> listaCandidatos;
    ArrayList<String> votos;
    TextView textViewVotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // Obtener datos del intent
        Bundle bundle = getIntent().getExtras();
        String dniRecibidoMain = bundle.getString("dni");

        // Inicializar la lista de candidatos antes de crear el adaptador
        listaCandidatos = new ArrayList<>();
        listaCandidatos.add(new Candidato("Doña Concha", R.drawable.concha, "Radiopactos"));
        listaCandidatos.add(new Candidato("Marisa", R.drawable.marisa, "Radiopactos"));
        listaCandidatos.add(new Candidato("Vicenta", R.drawable.vicenta, "Radiopactos"));
        listaCandidatos.add(new Candidato("Lucía", R.drawable.lucia, "La pija y amigas"));
        listaCandidatos.add(new Candidato("Belén", R.drawable.belen, "La pija y amigas"));
        listaCandidatos.add(new Candidato("Bea", R.drawable.bea, "La pija y amigas"));
        listaCandidatos.add(new Candidato("Juan 'Chorizo' Cuesta", R.drawable.juan, "Esta, nuestra Comunidad"));
        listaCandidatos.add(new Candidato("Isabel 'Yerbas'", R.drawable.yerbas, "Esta, nuestra Comunidad"));
        listaCandidatos.add(new Candidato("Mauri", R.drawable.mauri, "Esta, nuestra Comunidad"));
        listaCandidatos.add(new Candidato("Paco", R.drawable.paco, "Los pibes del videoclub"));
        listaCandidatos.add(new Candidato("'Josemi' Cuesta", R.drawable.josemi, "Los pibes del videoclub"));
        listaCandidatos.add(new Candidato("Roberto", R.drawable.roberto, "Los pibes del videoclub"));
        listaCandidatos.add(new Candidato("Emilio Delgado", R.drawable.emilio, "Un poquito de porfavoh"));
        listaCandidatos.add(new Candidato("Mariano Delgado", R.drawable.mariano, "Un poquito de porfavoh"));
        listaCandidatos.add(new Candidato("Jose María", R.drawable.josemaria, "Un poquito de porfavoh"));

        // Crear el adaptador después de inicializar la lista de candidatos
        SpinnerAdapter adapter = new SpinnerAdapter(getApplicationContext(), listaCandidatos);

        spinnerCandidatos = findViewById(R.id.spinnerElecciones);

        // Asignar el adaptador al Spinner
        spinnerCandidatos.setAdapter(adapter);
        votos = new ArrayList<>();
        // Resto de tu código...
        Button botonBBDD = findViewById(R.id.buttonVotosFinal);
        Button botonVotos = findViewById(R.id.buttonVotos);

        textViewVotos = findViewById(R.id.textVdatos);




        try {
            dbHelper = new DBHelper(this);

            if (!dbHelper.hasVotado(dniRecibidoMain)) { // Si el usuario con este dni, no ha votado, procede a votar
                botonVotos.setOnClickListener(new View.OnClickListener() {
                    int contador = 0;

                    @Override
                    public void onClick(View v) {
                        if (contador < 3) {
                            // Obtiene el candidato seleccionado del Spinner
                            Candidato candidatoSeleccionado = (Candidato) spinnerCandidatos.getSelectedItem();

                            // Verifica si el nombre del candidato ya está en la lista de votos
                            if (!votos.contains(candidatoSeleccionado.getNombre())) {
                                Toast.makeText(getApplicationContext(), "Has elegido a " + candidatoSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
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

                botonBBDD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Separar nombres de la lista
                        String nombre1 = votos.get(0);
                        String nombre2 = votos.get(1);
                        String nombre3 = votos.get(2);

                        dbHelper.votarCandidato(nombre1);
                        dbHelper.votarCandidato(nombre2);
                        dbHelper.votarCandidato(nombre3);
                        dbHelper.marcarDniComoVotado(dniRecibidoMain);
                        mostrarVotos(textViewVotos);

                    }
                });

                //AQUIIIIIIIIIIIIII
            } else { // Si el dni está marcado como votado
                // Bloqueamos los botones y mostramos como va el recuento de datos
                botonVotos.setEnabled(false);
                botonBBDD.setEnabled(false);
                mostrarVotos(textViewVotos);
            }

        } catch (Exception e) {

        }

    }
                                    //AQUIIIIIII
    public void mostrarVotos(TextView textView) {
        ArrayList<String> listaResultados = dbHelper.mostrarElecciones();
        StringBuilder resultadoF = new StringBuilder();
        for (String resultado : listaResultados) {
            resultadoF.append(resultado).append("\n");
        }
        textView.setText(resultadoF.toString());
    }


}







