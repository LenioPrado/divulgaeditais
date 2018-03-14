package com.example.vanessafurtado.prefeitura.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.vanessafurtado.prefeitura.R;
import com.example.vanessafurtado.prefeitura.listeners.LoginClickListener;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        Button entrar = findViewById(R.id.entrar);
        entrar.setOnClickListener(new LoginClickListener());
    }

    @Override
    public void onBackPressed()
    {
        Intent i  = new Intent(this, ActivityIndex.class);
        startActivity(i);
        finish();
        super.onBackPressed();  // optional depending on your needs
    }
}
