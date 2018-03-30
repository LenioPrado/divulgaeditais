package mobile.divulga.editais.ifsuldeminas.edu.br.other;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityIndex;

public class Session {

    SharedPreferences pref;

    Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidUserPreferences";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_ID = "userId";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    public Session(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(int userId, String name, String email) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_ID, userId);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public int getUserId() {
        return pref.getInt(KEY_ID, 0);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        redirectToIndex();
    }

    private void redirectToIndex(){
        Intent i = new Intent(context, ActivityIndex.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
