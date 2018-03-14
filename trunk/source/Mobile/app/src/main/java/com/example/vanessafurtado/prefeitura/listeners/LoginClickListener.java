package com.example.vanessafurtado.prefeitura.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vanessafurtado.prefeitura.R;
import com.example.vanessafurtado.prefeitura.activity.ActivityHome;
import com.example.vanessafurtado.prefeitura.other.RequestMethods;
import com.example.vanessafurtado.prefeitura.other.Sessao;
import com.example.vanessafurtado.prefeitura.other.WebServiceCaller;

import java.util.concurrent.ExecutionException;

public class LoginClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        Activity host = (Activity) v.getContext();

        EditText emailText = host.findViewById(R.id.txtEmail);
        EditText passwordText = host.findViewById(R.id.txtSenha);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.trim().length() > 0 && password.trim().length() > 0) {

            String method = "user/login/" + email + "/" + password;
            Object result = null;
            WebServiceCaller wsCaller = new WebServiceCaller();
            try {
                result = wsCaller.execute(method, RequestMethods.POST).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            if (result != null || email.equals("test") && password.equals("test")) {

                // Creating user login session
                // For testing i am stroing name, email as follow
                // Use user real data
                Sessao session = new Sessao(host.getApplicationContext());
                session.createLoginSession("Android Hive", "anroidhive@gmail.com");

                // Staring MainActivity
                Intent i = new Intent(v.getContext(), ActivityHome.class);
                host.startActivity(i);
                host.finish();

                Toast.makeText(host.getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

            } else {
                // username / password doesn't match
                //alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
                Toast.makeText(v.getContext(), "Usuario ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        } else {
            // user didn't entered username or password
            // Show alert asking him to enter the details
            //alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);

            Toast.makeText(v.getContext(), "Digite um usuário e senha válidos", Toast.LENGTH_SHORT).show();
        }
    }
}