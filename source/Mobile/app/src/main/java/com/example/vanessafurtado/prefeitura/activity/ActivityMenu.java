package com.example.vanessafurtado.prefeitura.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vanessafurtado.prefeitura.R;

public class ActivityMenu extends AppCompatActivity implements View.OnClickListener{

    Button entrar, cadastrar;

    private String baseURL = "https://192.168.0.105:8080/DivulgaEditais";

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String someVariable) {
        this.baseURL = someVariable;
    }

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

//    public int getNavItemIndex() {
//        return navItemIndex;
//    }
//
//    public void setNavItemIndex(int navItemIndex) {
//        this.navItemIndex = navItemIndex;
//    }
//
//    public String getCURRENT_TAG() {
//        return CURRENT_TAG;
//    }
//
//    public void setCURRENT_TAG(String CURRENT_TAG) {
//        this.CURRENT_TAG = CURRENT_TAG;
//    }

    int navItemIndex;
    String CURRENT_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cadastrar = findViewById(R.id.cadastrar);
        entrar = findViewById(R.id.entrar);

        cadastrar.setOnClickListener(this);
        entrar.setOnClickListener(this);
//        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
//        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override
    public void onClick(View view) {
        MainActivity main = new MainActivity();
        Intent i = new Intent();
        if(view == cadastrar){
            i = new Intent(this, ActivityRegister.class);
        }
        if(view == entrar){
            i = new Intent(this, ActivityEntrar.class);
        }
        startActivity(i);
        finish();

    }
//
//    @Override
//    public void uncaughtException(Thread thread, final Throwable throwable) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                BancoDeDados.getInstance().crashNotify(throwable);
//            }
//        }.start();
//    }
}
