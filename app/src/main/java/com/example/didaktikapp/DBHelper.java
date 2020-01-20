package com.example.didaktikapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static com.example.didaktikapp.DBHelper.entidadUsuario.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

   ArrayList <Lugar> lugares = new ArrayList<>();

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DidaktikAPP.db";



    public static class entidadUsuario implements BaseColumns {
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_NOMBRE = "nUsuario" ;
    }

    private static final String SQL_CREATE_TABLE_USUARIO =
            "CREATE TABLE " + entidadUsuario.TABLE_NAME + " (" +
                    entidadUsuario.COLUMN_NAME_NOMBRE + " TEXT PRIMARY KEY)";

    private static final String SQL_DELETE_TABLE_USUARIO =
            "DROP TABLE IF EXISTS " + TABLE_NAME;



    public static class entidadProgreso implements BaseColumns {
        public static final String TABLE_NAME = "Progreso";
        public static final String COLUMN_NAME_ID_USUARIO = "IDusuario" ;
        public static final String COLUMN_NAME_PROGRESO = "Puntos" ;
    }

    private static final String SQL_CREATE_TABLE_PROGRESO =
            "CREATE TABLE " + entidadProgreso.TABLE_NAME + " (" +
                    //entidadProgreso._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"  +
                    entidadProgreso.COLUMN_NAME_PROGRESO + " INTEGER,"+
                    entidadProgreso.COLUMN_NAME_ID_USUARIO +" TEXT,"+
                    "FOREIGN KEY('"+  entidadProgreso.COLUMN_NAME_ID_USUARIO + "') REFERENCES '" + entidadUsuario.TABLE_NAME + "'('" + entidadUsuario.COLUMN_NAME_NOMBRE + "'))";

    private static final String SQL_DELETE_TABLE_PROGRESO =
            "DROP TABLE IF EXISTS " + entidadProgreso.TABLE_NAME;

    public static class entidadLugares implements BaseColumns {
        public static final String TABLE_NAME = "Lugares";
        public static final String COLUMN_NAME_NOMBRE = "nLugar" ;
        public static final String COLUMN_NAME_LATITUD = "Latitud" ;
        public static final String COLUMN_NAME_LONGITUD = "Longitud" ;
    }

    private static final String SQL_CREATE_TABLE_LUGARES =
            "CREATE TABLE " + entidadLugares.TABLE_NAME + " (" +
                    entidadLugares._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    entidadLugares.COLUMN_NAME_NOMBRE + " TEXT," +
                    entidadLugares.COLUMN_NAME_LATITUD + " TEXT," +
                    entidadLugares.COLUMN_NAME_LONGITUD + " TEXT)";

    private static final String SQL_DELETE_TABLE_LUGARES =
            "DROP TABLE IF EXISTS " + entidadLugares.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_DELETE_TABLE_LUGARES);
        //db.execSQL(SQL_CREATE_TABLE_PROGRESO);
        //db.execSQL(SQL_DELETE_TABLE_USUARIO);
        db.execSQL(SQL_CREATE_TABLE_USUARIO);
        db.execSQL(SQL_CREATE_TABLE_PROGRESO);
        db.execSQL(SQL_CREATE_TABLE_LUGARES);
        crearLugares();
        rellenarPuntos(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_USUARIO);
        db.execSQL(SQL_DELETE_TABLE_PROGRESO);
        db.execSQL(SQL_DELETE_TABLE_LUGARES);
        onCreate(db);
    }

    public void onDowngrade (SQLiteDatabase db,int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void crearLugares(){
        Lugar L1 = new Lugar(1,"Larrea eskultura",43.211583,-2.886917);
        Lugar L2 = new Lugar(2,"Arrigorriagako Udaletxea",43.205978,-2.887869);
        Lugar L3 = new Lugar(3,"Maria Magdalena eliza",43.205548,-2.888705);
        Lugar L4 = new Lugar(4,"Hiltegi Zaharra",43.204889,-2.887833);
        Lugar L5 = new Lugar(5,"Landaederreagako Santo Kristo baseliza",43.209306,-2.893722);
        Lugar L6 = new Lugar(6,"Abrisketako San Pedro baseleizea",43.210500,-2.909417);
        lugares.add(L1);
        lugares.add(L2);
        lugares.add(L3);
        lugares.add(L4);
        lugares.add(L5);
        lugares.add(L6);
    }

    public void rellenarPuntos(SQLiteDatabase db){
        for (int i = 0;lugares.size()>i;i++) {
            ContentValues values = new ContentValues();
            values.put(entidadLugares._ID,lugares.get(i).Idlugar);
            values.put(DBHelper.entidadLugares.COLUMN_NAME_NOMBRE,lugares.get(i).Nombre);
            values.put(DBHelper.entidadLugares.COLUMN_NAME_LATITUD, lugares.get(i).Latitud);
            values.put(DBHelper.entidadLugares.COLUMN_NAME_LONGITUD, lugares.get(i).Longitud);
            db.insert(DBHelper.entidadLugares.TABLE_NAME, null, values);
        }
    }
}
