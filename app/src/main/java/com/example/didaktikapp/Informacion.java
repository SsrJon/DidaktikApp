package com.example.didaktikapp;

import android.app.Application;

public class Informacion extends Application {
    public String getUsuarioJugando() {
        return UsuarioJugando;
    }

    public void setUsuarioJugando(String usuarioJugando) {
        UsuarioJugando = usuarioJugando;
    }

    String UsuarioJugando;

    public void onCreate(){
        super.onCreate();
    }

}
