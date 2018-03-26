package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.model.User;

public class WebService<T> {

    private String baseUrl = "http://%s:8080/DivulgaEditais/rest/";
    private Gson gson;
    private Context context;
    private Class<T> classType;

    public WebService(Class<T> classType, Context context){
        this.context = context;
        this.classType = classType;
        String serverAddress = context.getResources().getString(R.string.serverAddress);
        baseUrl = String.format(baseUrl, serverAddress);

        createGsonBuilder();
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

    public void query(String endpoint, JSONObject jsonObject, RequestMethods requestMethod, final ResultCallback<T> callback){
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = baseUrl + endpoint;

        JsonObjectRequest request = new JsonObjectRequest(requestMethod.getValue(), url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("#### PostLoaded: ", response.toString());
                try {
                    T entity = gson.fromJson(response.toString(), classType);
                    callback.onSuccess(entity);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    callback.onError(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("#### PostError", error.toString());
                callback.onVolleyError(error);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        requestQueue.add(request);
    }

    public void query(String endpoint, RequestMethods requestMethod, final ResultCallback<T> callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String url = baseUrl + endpoint;

        StringRequest request = new StringRequest(requestMethod.getValue(), url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("#### PostLoaded: ", response);
                try {
                    T entity = gson.fromJson(response, classType);
                    callback.onSuccess(entity);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    callback.onError(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("#### PostError", error.toString());
                callback.onVolleyError(error);
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        requestQueue.add(request);
    }
}
