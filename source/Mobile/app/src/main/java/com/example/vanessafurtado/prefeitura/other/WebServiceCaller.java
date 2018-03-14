package com.example.vanessafurtado.prefeitura.other;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import info.guardianproject.netcipher.NetCipher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class WebServiceCaller extends AsyncTask<Object, Integer, Object> {

    private static String baseUrl = "http://192.168.0.104:8080/DivulgaEditais/rest/";

    @Override
    protected void onPreExecute(){
        // load = ProgressDialog.show(ActivityRegister.this, "Por favor Aguarde ...", "Acessando Servidor...");
    }

    @Override
    protected Object doInBackground(Object... parameters) {

        if(parameters != null && parameters.length > 0){
            String url = parameters[0].toString();
            RequestMethods requestMethod = (RequestMethods)parameters[1];
            JSONObject jsonObject = null;
            if(parameters.length > 2){
                jsonObject = (JSONObject)parameters[2];
            }
            return callService(url, requestMethod, jsonObject);
        }
        return false;
    }

    public static String getBaseUrl(){
        return baseUrl;
    }

    private String callService(String urlAddress, RequestMethods requestMethod){
        return callService(urlAddress, requestMethod, null);
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
            results = showResults(conn.getInputStream());

            conn.disconnect();
            return results;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private String showResults(InputStream inputStream){
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

    private JSONObject foo(String url, JSONObject json) {
        JSONObject jsonObjectResp = null;

        try {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();

            okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
            okhttp3.Request request = new okhttp3.Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();

            String networkResp = response.body().string();
            if (!networkResp.isEmpty()) {
                jsonObjectResp = parseJSONStringToJSONObject(networkResp);
            }
        } catch (Exception ex) {
            String err = String.format("{\"result\":\"false\",\"error\":\"%s\"}", ex.getMessage());
            jsonObjectResp = parseJSONStringToJSONObject(err);
        }

        return jsonObjectResp;
    }

    private JSONObject parseJSONStringToJSONObject(final String strr) {

        JSONObject response = null;
        try {
            response = new JSONObject(strr);
        } catch (Exception ex) {
            //  Log.e("Could not parse malformed JSON: \"" + json + "\"");
            try {
                response = new JSONObject();
                response.put("result", "failed");
                response.put("data", strr);
                response.put("error", ex.getMessage());
            } catch (Exception exx) {
            }
        }
        return response;
    }
}