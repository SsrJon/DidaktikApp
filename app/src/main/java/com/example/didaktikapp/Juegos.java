package com.example.didaktikapp;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Juegos {
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Drawable getCaratula() {
        return caratula;
    }

    public void setCaratula(Drawable caratula) {
        this.caratula = caratula;
    }

    public static ArrayList<Juegos> getJuegosArrayList() {
        return juegosArrayList;
    }

    public void setJuegosArrayList(ArrayList<Juegos> juegosArrayList) {
        this.juegosArrayList = juegosArrayList;
    }

    String Nombre;
    Drawable caratula;
    static ArrayList<Juegos> juegosArrayList= new ArrayList<Juegos>();

    public Juegos(String nombre , Drawable DRW){
        this.Nombre = nombre;
        this.caratula = DRW;
    }

}
