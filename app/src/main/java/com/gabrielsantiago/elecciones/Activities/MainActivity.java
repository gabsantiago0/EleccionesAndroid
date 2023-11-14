package com.gabrielsantiago.elecciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabrielsantiago.elecciones.BBDD.DBHelper;
import com.gabrielsantiago.elecciones.R;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    String dniString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        Button botonLogin = findViewById(R.id.buttonLogin);
        EditText dniEditText = findViewById(R.id.editTextLogin);


        //Boton que te manda a SpinnerActivity
        botonLogin.setOnClickListener(v -> {
            dniString = dniEditText.getText().toString();

            if (validarDni(dniString)) {
                //Si el dni es valido:
                if (dbHelper.existeUsuario(dniString)) {
                    if (dbHelper.hasVotado(dniString)) {
                        // Usuario existe y ha votado
                        Toast.makeText(this, "EL usuario con DNI "+dniString+"" +
                                " ya ha votado.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                        intent.putExtra("dni",dniString);
                        startActivity(intent);
                    } else {
                        // Usuario existe pero no ha votado
                        Toast.makeText(this, "EL usuario con DNI "+dniString+"" +
                                " todavia no ha votado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                        intent.putExtra("dni",dniString);
                        startActivity(intent);
                    }
                } else {
                    // Usuario no existe, registrarlo
                    dbHelper.registrarUsuario(dniString);
                    Toast.makeText(this, "EL usuario con DNI "+dniString+"" +
                            " ha sido registrado, vota por favor", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                    intent.putExtra("dni",dniString);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean validarDni(String dni) {
        if (dni.isEmpty() || dni.length() < 9) {
            Toast.makeText(this, "El campo DNI está vacío o es demasiado corto", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Character.isLetter(dni.charAt(8))) {
            Toast.makeText(this, "El DNI no es válido", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            String numerosDni = dni.substring(0, 8);

            if (numerosDni.matches("\\d+")) {
                return true;
            } else {
                Toast.makeText(this, "Los primeros 8 caracteres del DNI deben ser numéricos", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }


}