package com.example.didaktikapp.sopadeletras;

import android.app.Application;

import com.example.didaktikapp.sopadeletras.di.component.AppComponent;
import com.example.didaktikapp.sopadeletras.di.component.DaggerAppComponent;
import com.example.didaktikapp.sopadeletras.di.modules.AppModule;


public class WordSearchApp extends Application {

    AppComponent mAppComponent;

    private static boolean menujuegos;
    private  static boolean admin;

    // Variables
  //  public static Usuario usuario;
  //  public static ArrayList<Punto> puntos = new ArrayList<Punto>();

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        admin=false;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    // Getters y Setters
    //public static Usuario getUsuario() {
      //  return usuario;
  //  }

   // public static void setUsuario(Usuario usuario) {
    //    WordSearchApp.usuario = usuario;
    //}

    public static boolean isMenujuegos() {
        return menujuegos;
    }

    public static void setMenujuegos(boolean menujuegos) {
        WordSearchApp.menujuegos = menujuegos;
    }

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        WordSearchApp.admin = admin;
    }
}
