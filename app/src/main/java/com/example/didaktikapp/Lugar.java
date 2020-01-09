package com.example.didaktikapp;

public class Lugar {

    public int getIdlugar() {
        return Idlugar;
    }

    public void setIdlugar(int idlugar) {
        Idlugar = idlugar;
    }

    int Idlugar;
    String Nombre;
    String Lugar;
    double Latitud;

    public String getLugar() {
        return Lugar;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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

    public Lugar (int Id,String nombre,double Lat,double Long){
        this.Idlugar = Id;
        this.Nombre = nombre;
        this.Latitud = Lat;
        this.Longitud = Long;
    }

}
