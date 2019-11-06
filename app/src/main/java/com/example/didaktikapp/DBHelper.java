package com.example.didaktikapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 0;
    public static final String DATABASE_NAME = "DidaktikAPP.db";



    public static class entidadUsuario implements BaseColumns {
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_NOMBRE = "nUsuario" ;
    }

    private static final String SQL_CREATE_TABLE_USUARIO =
            "CREATE TABLE " + entidadUsuario.TABLE_NAME + " (" +
                    entidadUsuario._ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                    entidadUsuario.COLUMN_NAME_NOMBRE + " TEXT)";

    private static final String SQL_DELETE_TABLE_USUARIO =
            "DROP TABLE IF EXISTS " + entidadUsuario.TABLE_NAME;

    public static class entidadLugares implements BaseColumns {
        public static final String TABLE_NAME = "Lugares";
        public static final String COLUMN_NAME_NOMBRE = "nLugar" ;
        public static final String COLUMN_NAME_LATITUD = "Latitud" ;
        public static final String COLUMN_NAME_LONGITUD = "Longitud" ;
    }

    private static final String SQL_CREATE_TABLE_LUGARES =
            "CREATE TABLE " + entidadLugares.TABLE_NAME + " (" +
                    entidadLugares._ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                    entidadLugares.COLUMN_NAME_NOMBRE + " TEXT," +
                    entidadLugares.COLUMN_NAME_LATITUD + " TEXT," +
                    entidadLugares.COLUMN_NAME_LONGITUD + " TEXT)";

    private static final String SQL_DELETE_TABLE_LUGARES =
            "DROP TABLE IF EXISTS " + entidadLugares.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade (SQLiteDatabase db,int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
