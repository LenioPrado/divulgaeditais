package com.example.vanessafurtado.prefeitura.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Arquivos {
    private static final Arquivos ourInstance = new Arquivos();

    public static Arquivos getInstance() {
        return ourInstance;
    }

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    private Arquivos() {
    }


public void downloadDoArquivo(String nome, final ImageView imagem){

    StorageReference arquivo = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/q-braille.appspot.com/o/PDF%2Fimagem.png?alt=media&token=0facc1b2-f2d1-47af-a4eb-4a031a695a88");
    try {
   //     File arquivao = new File("/imagem/");


        final File localFile = File.createTempFile("images", "png");

     //  final  File fileToWrite = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "arquivo.png");
        final  File fileToWrite = File.createTempFile("images", "png");

      //  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        arquivo.getFile(fileToWrite).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.i("Arquivo", "Deu certo o download do Arquivo");
                Bitmap myBitmap = BitmapFactory.decodeFile(fileToWrite.getAbsolutePath());
                imagem.setImageBitmap(myBitmap);

                File downloads = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "imagem.png");
                fileToWrite.renameTo(downloads);


                Log.i("caminho", fileToWrite.getAbsolutePath());
                Log.i("caminho", downloads.getAbsolutePath());


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("Arquivo", "Deu erro o download do Arquivo");
                Log.i("caminho", fileToWrite.getAbsolutePath());
            }
        });




    } catch (IOException e) {
        e.printStackTrace();
    }


}




}
