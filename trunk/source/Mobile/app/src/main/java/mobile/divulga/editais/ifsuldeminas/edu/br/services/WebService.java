package mobile.divulga.editais.ifsuldeminas.edu.br.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mobile.divulga.editais.ifsuldeminas.edu.br.model.User;

public class WebService<T> {

    private final Class<T> classType;
    private Gson gson;
    private Context context;

    public WebService(Class<T> classType, Context context){
        this.classType = classType;
        this.context = context;
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

    public void query(String endpoint, final ResultCallback<T> callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, endpoint, new Response.Listener<String>() {
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
