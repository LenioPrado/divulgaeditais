package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.RequestMethods;

public abstract class UnusedWebServiceCaller extends AsyncTask<Object, Integer, AsyncTaskResult<Object>> {

    private String baseUrl = "http://%s:8080/DivulgaEditais/rest/";
    private String url;
    private RequestMethods requestMethod;
    private JSONObject jsonObject;
    private Object result;
    private Exception error;
    private ProgressDialog load;
    private Context context;

    public UnusedWebServiceCaller(Context context, String url, RequestMethods requestMethod){
        this.url = url;
        this.requestMethod = requestMethod;
        this.context = context;

        String serverAddress = context.getResources().getString(R.string.serverAddress);
        baseUrl = String.format(baseUrl, serverAddress);
    }

    public UnusedWebServiceCaller(Context context, String url, RequestMethods requestMethod, JSONObject jsonObject){
        this(context, url, requestMethod);
        this.jsonObject = jsonObject;
    }

    @Override
    protected void onPreExecute(){
        load = ProgressDialog.show(context, getWaitProcessTitle(), getWaitProcessMessage());
    }

    @Override
    protected AsyncTaskResult<Object> doInBackground(Object... parameters) {
        return callService(url, requestMethod, jsonObject);
    }

    @Override
    protected void onPostExecute(AsyncTaskResult<Object> result) {
        load.dismiss();
        if ( result.getError() != null ) {
            Toast.makeText(context, getErrorMessage(result.getError()), Toast.LENGTH_SHORT).show();
        }  else if ( isCancelled()) {
            Toast.makeText(context, getCancelMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, getSuccessMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    protected String getWaitProcessMessage(){
        return "Acessando Servidor...";
    }

    protected String getWaitProcessTitle(){
        return "Por favor aguarde ...";
    }

    protected String getCancelMessage(){
        return "Ação cancelada!";
    }

    protected String getSuccessMessage(){
        return "Conexão efetuada com sucesso!";
    }

    protected String getErrorMessage(Exception e){
        return String.format("%s -- %s", e.getMessage(), e.getLocalizedMessage());
    }

    private AsyncTaskResult<Object> callService(String urlAddress, RequestMethods requestMethod, JSONObject jsonObject) {
        String results = "";
        try {
            URL url = new URL(baseUrl + urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestMethod.toString());
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            if (jsonObject != null) {
                Log.i("Json Object: ", jsonObject.toString());
                os.writeBytes(jsonObject.toString());
            }

            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            Log.i("STATUS", String.valueOf(responseCode));
            results = getResults(conn.getInputStream());

            conn.disconnect();
            return new AsyncTaskResult<Object>(results);

        } catch (Exception e) {
            e.printStackTrace();
            return new AsyncTaskResult<Object>(e);
        }
    }

    private String getResults(InputStream inputStream){
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer response = new StringBuffer();

        try {
            while ((inputLine = in.readLine()) != null) {
                Log.i("RESULT" , String.valueOf(inputLine));
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}