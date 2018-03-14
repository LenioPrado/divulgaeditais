package com.example.vanessafurtado.prefeitura.other;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.vanessafurtado.prefeitura.activity.ActivityIndex;


public class Permissao {
    private static final Permissao ourInstance = new Permissao();
    final int permissaoEscritaDeArquivo = 1;

    public static Permissao getInstance() {
        return ourInstance;
    }

    private Permissao() {
    }


    public void tentarPermissaoDeArquivo(Activity activity) {

        int permissao = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);


        Log.i("Permissao ", "Permissao " + permissao);
        Log.i("Permissao ", "Arquivo " + permissaoEscritaDeArquivo);
        if (permissao == 0) {
            permissoesConcedidas(activity);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    permissaoEscritaDeArquivo);
        }
    }

    public boolean tentarPermissaoInternet(Activity activity) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        } else
            return false;

    }

    public void permissoesConcedidas(final Activity activity){

        Log.i("Permissao", "Permissao concedida");

        if (tentarPermissaoInternet(activity)) {
            Log.i("InternetAcesso", "Conectado");

        } else {
            Log.i("InternetAcesso", "Nao conectado");

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(activity, ActivityIndex.class);

                activity.startActivity(mainIntent);
                activity.finish();
            }
        }, 3000);
    }
}