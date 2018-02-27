package com.example.vanessafurtado.prefeitura.other;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.vanessafurtado.prefeitura.activity.ActivityMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import info.guardianproject.netcipher.NetCipher;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by vanes on 17/02/2018.
 */

public class Banco {


     public static void banco(Context context, String link, String user) {
//        String response = "";
//        BufferedReader reader = null;
//        HttpURLConnection conn = null;
//        try {
//            Log.d("RequestManager", url + " ");
//            Log.e("data::", " " + user);
//            URL urlObj = new URL(url);
//
//            conn = (HttpURLConnection) urlObj.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setDoOutput(true);
//            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//
//            wr.write(user);
//            wr.flush();
//
//            Log.d("post response code", conn.getResponseCode() + " ");
//
//            int responseCode = conn.getResponseCode();
//
//            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            String line = null;
//
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//
//            response = sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("Error", "error");
//        } finally {
//            try {
//                reader.close();
//                if (conn != null) {
//                    conn.disconnect();
//                }
//            } catch (Exception ex) {
//            }
//        }
//        Log.d("RESPONSE POST", response);
//        return response;
//    }
//
//
//    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
//        HttpURLConnection urlConnection = null;
//        URL url = new URL(urlString);
//        urlConnection = (HttpURLConnection) url.openConnection();
//        urlConnection.setRequestMethod("POST");
//        urlConnection.setReadTimeout(10000 /* milliseconds */ );
//        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
//        urlConnection.setDoOutput(true);
//        urlConnection.connect();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
//        StringBuilder sb = new StringBuilder();
//
//        String line;
//        while ((line = br.readLine()) != null) {
//            sb.append(line + "\n");
//        }
//        br.close();
//
//        String jsonString = sb.toString();
//        System.out.println("JSON: " + jsonString);
//
//        return new JSONObject(jsonString);

         Log.i("####", "Chegou aqui");
         URL url = null;
         try {
             Log.i("####", "ENTROU NO TRY");
             url = new URL(link);
             HttpsURLConnection conn = NetCipher.getHttpsURLConnection(url);
             conn.setReadTimeout(10000);
             conn.setConnectTimeout(15000);
             conn.setRequestMethod("POST");
             conn.setDoInput(true);
             conn.setDoOutput(true);

             OutputStream os = null;
             os = conn.getOutputStream();

             BufferedWriter writer = null;
             writer = new BufferedWriter(
                     new OutputStreamWriter(os, "UTF-8"));
             writer.write(user);
             writer.flush();
             writer.close();
             os.close();

             conn.connect();
             Log.i("####", "CONECTOU");
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

     }


    public static JSONObject foo(String url, JSONObject json) {
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


    private static JSONObject parseJSONStringToJSONObject(final String strr) {

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