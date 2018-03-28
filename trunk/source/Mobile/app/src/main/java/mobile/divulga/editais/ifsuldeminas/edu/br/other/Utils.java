package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;

public final class Utils {

    private static final String MASK_DATE = "dd/MM/yyyy";
    private static String urlToFormat = "http://%s:8080/DivulgaEditais/rest/";
    private static String baseUrl = "";

    private static String SUBSCRIBE = "SUBSCRIBE";
    private static String UNSUBSCRIBE = "UNSUBSCRIBE";

    private static String CURRENT_TAG = "CURRENT_TAG";
    private static String TAG_SCREEN_ALL_NOTICES = "ALL_NOTICES";
    private static String TAG_SCREEN_REGISTERED_NOTICES = "REGISTERED_NOTICES";

    public static String createDateFormatddMMyyyy(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(MASK_DATE);
        String ret = null;
        ret = sdf.format(date);
        return ret;
    }

    public static String getBaseUrl(Context context){
        if("".equals(baseUrl)){
            String serverAddress = context.getResources().getString(R.string.serverAddress);
            baseUrl = String.format(urlToFormat, serverAddress);
        }

        return baseUrl;
    }

    public static JSONObject toJson(Object model){
        Gson gson = new Gson();
        String modelString = gson.toJson(model);
        try {
            JSONObject request = new JSONObject(modelString);
            Log.i("Object to JSONObject", request.toString());
            return request;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentTagKey(){
        return CURRENT_TAG;
    }

    public static String getTagScreenAllNotices(){
        return TAG_SCREEN_ALL_NOTICES;
    }

    public static String getTagScreenRegisteredNotices(){
        return TAG_SCREEN_REGISTERED_NOTICES;
    }

    public static Boolean isTagScreenAllNotices(String currentTag){
        return TAG_SCREEN_ALL_NOTICES.equals(currentTag);
    }

    public static String getPathToSaveFile(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    public static String getSubscribeAction(){
        return SUBSCRIBE;
    }

    public static String getUnsubscribeAction(){
        return UNSUBSCRIBE;
    }

    public static Boolean isSubscribeAction(String action){
        return SUBSCRIBE.equals(action);
    }
}