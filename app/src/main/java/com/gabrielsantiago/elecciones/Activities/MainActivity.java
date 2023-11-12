package com.gabrielsantiago.elecciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gabrielsantiago.elecciones.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonLogin = findViewById(R.id.buttonLogin);
        EditText dni = findViewById(R.id.editTextLogin);

        botonLogin.setOnClickListener(v -> {

            if (validarDni(String.valueOf(dni.getText()))){
                Intent intent = new Intent(MainActivity.this,SpinnerActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean validarDni(String dni) {
        if (dni.isEmpty() || dni.length()<9) {
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