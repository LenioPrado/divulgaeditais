package com.example.vanessafurtado.prefeitura.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vanessafurtado.prefeitura.R;
import com.example.vanessafurtado.prefeitura.other.Sessao;

public class ActivityEntrar extends AppCompatActivity implements View.OnClickListener {

    Button entrar;
    EditText email, senha;

    Sessao session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        session = new Sessao(getApplicationContext());

        email = findViewById(R.id.txtEmail);
        senha = findViewById(R.id.txtSenha);
        entrar = findViewById(R.id.entrar);
        entrar.setOnClickListener(this);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();




    }

    @Override
    public void onClick(View view) {
        if (view == entrar) {
            String username = email.getText().toString();
            String password = senha.getText().toString();

            // Check if username, password is filled
            if (username.trim().length() > 0 && password.trim().length() > 0) {
                // For testing puspose username, password is checked with sample data
                // username = test
                // password = test
                if (username.equals("test") && password.equals("test")) {

                    // Creating user login session
                    // For testing i am stroing name, email as follow
                    // Use user real data
                    session.createLoginSession("Android Hive", "anroidhive@gmail.com");

                    // Staring MainActivity
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    // username / password doesn't match
                    //alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
                    Toast.makeText(this, "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            } else {
                // user didn't entered username or password
                // Show alert asking him to enter the details
                //alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);

                Toast.makeText(this, "Digite um usuário e senha válidos", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityMenu.class);
        startActivity(i);
        this.finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
