package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceCaller extends AsyncTask<Object, Integer, Object> {

    private static String baseUrl = "http://192.168.0.107:8080/DivulgaEditais/rest/";
    private String url;
    private RequestMethods requestMethod;
    private JSONObject jsonObject;

    public WebServiceCaller(String url, RequestMethods requestMethod){
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public WebServiceCaller(String url, RequestMethods requestMethod, JSONObject jsonObject){
        this(url, requestMethod);
        this.jsonObject = jsonObject;
    }

    public static String getBaseUrl(){
        return baseUrl;
    }

    @Override
    protected void onPreExecute(){
        // load = ProgressDialog.show(ActivityRegister.this, "Por favor Aguarde ...", "Acessando Servidor...");
    }

    @Override
    protected Object doInBackground(Object... parameters) {
        return callService(url, requestMethod, jsonObject);
    }

    private String callService(String urlAddress, RequestMethods requestMethod, JSONObject jsonObject) {
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
            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
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