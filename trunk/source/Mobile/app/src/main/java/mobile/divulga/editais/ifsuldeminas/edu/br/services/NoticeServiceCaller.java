package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.content.Context;

import org.json.JSONObject;

import mobile.divulga.editais.ifsuldeminas.edu.br.other.RequestMethods;

public class NoticeServiceCaller extends UnusedWebServiceCaller {
    public NoticeServiceCaller(Context context, String url, RequestMethods requestMethod) {
        super(context, url, requestMethod);
    }

    public NoticeServiceCaller(Context context, String url, RequestMethods requestMethod, JSONObject jsonObject) {
        super(context, url, requestMethod, jsonObject);
    }

    @Override
    protected String getWaitProcessMessage(){
        return "Acessando lista de editais...";
    }

    @Override
    protected String getSuccessMessage(){
        return "Acesso a lista de editais realizada com sucesso!";
    }
}
