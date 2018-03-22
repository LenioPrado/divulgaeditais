package mobile.divulga.editais.ifsuldeminas.edu.br.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityHome;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.User;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.ResultCallback;
import mobile.divulga.editais.ifsuldeminas.edu.br.services.WebService;

public class LoginClickListener implements View.OnClickListener{

    @Override
    public void onClick(final View v) {

        final Activity host = (Activity) v.getContext();

        EditText emailText = host.findViewById(R.id.txtEmail);
        EditText passwordText = host.findViewById(R.id.txtSenha);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.trim().length() > 0 && password.trim().length() > 0) {
            String endpoint = "user/login/" + email + "/" + password;

            new WebService<User>(User.class, v.getContext()).query(endpoint, new ResultCallback<User>() {
                @Override
                public void onSuccess(User user) {
                    if (user != null) {
                        Session session = new Session(host.getApplicationContext());
                        session.createLoginSession(user.getSocialName(), user.getEmail());

                        Toast.makeText(v.getContext(), "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(v.getContext(), ActivityHome.class);
                        host.startActivity(i);
                        host.finish();

                    } else {
                        Toast.makeText(v.getContext(), "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Exception e) {
                    String error = String.format("Erro desconhecido: %s", e.getMessage());
                    Toast.makeText(v.getContext(), error, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onVolleyError(VolleyError e) {
                    String error = String.format("Erro ao trabalhar com o resultado: %s", e.getMessage());
                    Toast.makeText(v.getContext(), error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(v.getContext(), "Digite um usuário e senha válidos", Toast.LENGTH_SHORT).show();
        }
    }
}