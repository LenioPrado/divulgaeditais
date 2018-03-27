package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Debug;
import android.os.Environment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.List;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.User;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Utils;

public class WebService<T> {

    private Context context;
    private Class<T> classType;
    private Gson gson;
    private ProgressDialog processDialog;

    public WebService(Class<T> classType, Context context){
        this.context = context;
        this.classType = classType;
        createGsonBuilder();
        createDialog(context);
    }

    private void createGsonBuilder(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        gson = gsonBuilder.create();
    }

    private void createDialog(Context context){
        processDialog = new ProgressDialog(context);
        processDialog.setMax(100);
        processDialog.setMessage("Efetuando download...");
        processDialog.setTitle("Por favor, aguarde!");
        processDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    public void querySingle(String endpoint, JSONObject jsonObject, RequestMethods requestMethod, final ResultCallback<T> callback){

        String url = Utils.getBaseUrl(context) + endpoint;
        Log.i("Download Endpoint: ", url);

        processDialog.show();

        JsonObjectRequest request = new JsonObjectRequest(requestMethod.getValue(), url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                processOnResponse(response.toString(), callback);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processOnErrorResponse(error, callback);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }
        };

        processRequestQueue(request);
    }

    public void queryList(String endpoint, JSONArray jsonArray, RequestMethods requestMethod, final ResultCallback<T> callback){

        String url = Utils.getBaseUrl(context) + endpoint;
        Log.i("Download Endpoint: ", url);

        processDialog.show();

        JsonArrayRequest request = new JsonArrayRequest(requestMethod.getValue(), url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                processOnResponse(response.toString(), callback);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processOnErrorResponse(error, callback);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }
        };

        processRequestQueue(request);
    }

    public void download(final String fileName, String endpoint, RequestMethods requestMethod, final ResultCallback<T> callback){

        String url = Utils.getBaseUrl(context) + endpoint;
        Log.i("Download Endpoint: ", url);

        processDialog.show();

        InputStreamVolleyRequest request = new InputStreamVolleyRequest(requestMethod.getValue(), url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                processOnResponse(response, fileName, callback);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                processOnErrorResponse(error, callback);
            }
        }, null)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }
        };

        processRequestQueue(request);
    }

    private void processRequestQueue(Request request){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        processDialog.show();
        requestQueue.add(request);
    }

    private void processOnResponse(byte[] response, String fileName, ResultCallback<T> callback){
        try{
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(response);
            outputStream.close();
            Log.i("File Saved: ", fileName);
            processDialog.setProgress(100);
            processDialog.dismiss();
            callback.onSuccess(null);
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }
    }

    private void processOnResponse(String response, ResultCallback<T> callback){
        Log.i("#### PostLoaded: ", response);
        try {
            T entity = gson.fromJson(response, classType);
            processDialog.setProgress(100);
            processDialog.dismiss();
            callback.onSuccess(entity);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            callback.onError(e);
        }
    }

    private void processOnErrorResponse(VolleyError error, ResultCallback<T> callback){
        Log.e("#### PostError", error.toString());
        processDialog.setProgress(100);
        processDialog.dismiss();
        callback.onVolleyError(error);
    }

    private Map<String, String> getRequestHeaders(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        return params;
    }
}