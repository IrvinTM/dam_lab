package com.example.dam;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextUsername = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        Button salirButton = findViewById(R.id.salir_button);

        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        editTextUsername = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        Button ingresarButton = findViewById(R.id.ingresar_button);
        ingresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCredenciales();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nuevo){
            Intent intent = new Intent(MainActivity.this, RegistrarActivity.class);
            startActivity(intent);
        }else if(id == R.id.salir){
            this.finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void verificarCredenciales() {
        String inputUsername = editTextUsername.getText().toString();
        String inputPassword = editTextPassword.getText().toString();

        if (inputUsername.length() < 3){
            Toast.makeText(this, "El usuario tiene que tener mas de 3 caracteres", Toast.LENGTH_SHORT).show();
        }else if(inputPassword.length() < 5){
            Toast.makeText(this, "El password tiene que tener mas de 5 caracteres", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences sharedPref = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            String storedUsername = sharedPref.getString(inputUsername, null);
            String storedPassword = sharedPref.getString(inputPassword, null);

            if (storedUsername != null && storedPassword != null) {
                if (storedUsername.equals(inputUsername) && storedPassword.equals(inputPassword)) {
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Opcional: cerrar MainActivity
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No hay usuario registrado con estas credenciales. Por favor, regístrese primero.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}