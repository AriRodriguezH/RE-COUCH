package com.example.proyectoi;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    public final String INTRO = "intro";
    public final String NAME = "nombre";
    public final String HOBBY = "identrenador";
    public final String APP = "apellidop";
    public final String APM = "apellidom";
    public final String FECHANA = "fechanacimiento";
    public final String TELEFONO ="notelefono";
    public final String SEXO="sexo";
    public final String CORREO="correo";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putName(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NAME, loginorout);
        edit.commit();
    }
    public String getName() {
        return app_prefs.getString(NAME, "");
    }

    public void putHobby(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(HOBBY, loginorout);
        edit.commit();
    }
    public String getHobby() {
        return app_prefs.getString(HOBBY, "");
    }

    public void putApellidoP(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(APP, loginorout);
        edit.commit();
    }

    public String getAPP(){return  app_prefs.getString(APP,"");}

    public void putApellidoM(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(APM, loginorout);
        edit.commit();
    }

    public String getAPM(){return  app_prefs.getString(APM,"");}

    public void putFechaNa(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(FECHANA, loginorout);
        edit.commit();
    }

    public String getFECHANA(){return  app_prefs.getString(FECHANA,"");}

    public void putTelefono(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(TELEFONO, loginorout);
        edit.commit();
    }

    public String getTELEFONO(){return  app_prefs.getString(TELEFONO,"");}

    public void putSexo(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(SEXO, loginorout);
        edit.commit();
    }

    public String getSEXO(){return  app_prefs.getString(SEXO,"");}

    public void putCorreo(String loginorout){
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(CORREO, loginorout);
        edit.commit();
    }

    public String getCORREO(){return  app_prefs.getString(CORREO,"");}

}
