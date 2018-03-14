package com.example.vanessafurtado.prefeitura.other;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by CristianoFagundes on 23/01/2018.
 */

public class GravarEmDisco extends AsyncTask {

    String sBody;
    String NOME_ARQUIVO;



    public GravarEmDisco(String arquivo, String nome){

        this.sBody = arquivo;
        this.NOME_ARQUIVO = nome;


    }


    @Override
    protected Object doInBackground(Object[] objects) {

        Log.i("EscreverArquivo", sBody);

        try {
            File root = new File(String.valueOf(Environment.getExternalStorageDirectory()));
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, NOME_ARQUIVO);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();

            Log.i("arquivo", "Escrita no arquivo com sucesso");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("EscreverArquivo", "finalizou");

        return null;
    }
}