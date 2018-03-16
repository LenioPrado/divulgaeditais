package mobile.divulga.editais.ifsuldeminas.edu.br.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vanessafurtado.prefeitura.R;

public class ActivityIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Button cadastrar = findViewById(R.id.cadastrar);
        Button entrar = findViewById(R.id.entrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ActivityRegister.class);
                startActivity(i);
                finish();
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ActivityLogin.class);
                startActivity(i);
                finish();
            }
        });
    }
}
