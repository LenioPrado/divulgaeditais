package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;

public final class Utils {

    private static final String MASK_DATE = "dd/MM/yyyy";
    private static String urlToFormat = "http://%s:8080/DivulgaEditais/rest/";
    private static String baseUrl = "";

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

    public static String getPathToSaveFile(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
}