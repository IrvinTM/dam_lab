package com.example.dam;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private  EditText editTextEmail;
    private EditText editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextConfirmPassword = findViewById(R.id.editTextPasswordConfirm);

    }

    public void goBack(View view){
        getOnBackPressedDispatcher().onBackPressed();
    }

    public void guardarDatos(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString();
        String passworConfirm = editTextConfirmPassword.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()) {
            if(username.length() < 3){
                Toast.makeText(this, "El usuario tiene que tener mas de 3 caracteres", Toast.LENGTH_SHORT).show();
            }else{
                if(!isValidEmail(email)){
                    Toast.makeText(this, "El email no es un email valido", Toast.LENGTH_SHORT).show();
                }else {
                    if( !password.equals(passworConfirm)){
                        Toast.makeText(this, "Los passwords no coinciden", Toast.LENGTH_SHORT).show();
                    }else{
                        if (password.length() < 5){
                            Toast.makeText(this, "El password debe de tener mas de 5 caracteres", Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPreferences sharedPref = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(username, username);
                            editor.putString(password, password);
                            editor.apply();

                            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                            editTextUsername.setText("");
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                            editTextConfirmPassword.setText("");
                        }

                    }

                }

            }
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(regex);
    }
}

