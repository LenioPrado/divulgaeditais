package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.content.Context;

import org.json.JSONObject;

import mobile.divulga.editais.ifsuldeminas.edu.br.other.RequestMethods;

public class UserServiceCaller extends UnusedWebServiceCaller {
    public UserServiceCaller(Context context, String url, RequestMethods requestMethod) {
        super(context, url, requestMethod);
    }

    public UserServiceCaller(Context context, String url, RequestMethods requestMethod, JSONObject jsonObject) {
        super(context, url, requestMethod, jsonObject);
    }

    @Override
    protected String getWaitProcessMessage(){
        return "Processando dados do usuário...";
    }

    @Override
    protected String getSuccessMessage(){
        return "Operação realizada com sucesso!";
    }
}
