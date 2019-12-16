package com.example.didaktikapp;

public class Lugar {
    String Lugar;
    double Latitud;

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    double Longitud;

    public Lugar (String nombre,double Lat,double Long){
        this.Lugar = nombre;
        this.Latitud = Lat;
        this.Longitud = Long;
    }

}
