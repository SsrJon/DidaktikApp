package com.example.didaktikapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import static com.example.didaktikapp.DBHelper.entidadUsuario.TABLE_NAME;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 0;
    public static final String DATABASE_NAME = "DidaktikAPP.db";



    public static class entidadUsuario implements BaseColumns {
        public static final String TABLE_NAME = "Usuario";
        public static final String COLUMN_NAME_NOMBRE = "nUsuario" ;
    }

    private static final String SQL_CREATE_TABLE_USUARIO =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    entidadUsuario._ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                    entidadUsuario.COLUMN_NAME_NOMBRE + " TEXT)";

    private static final String SQL_DELETE_TABLE_USUARIO =
            "DROP TABLE IF EXISTS " + TABLE_NAME;



    public static class entidadProgreso implements BaseColumns {
        public static final String TABLE_NAME = "Progreso";
        public static final String COLUMN_NAME_ID_USUARIO = "ID_usuario" ;
        public static final String COLUMN_NAME_PTO_0 = "PTO_0" ;
        public static final String COLUMN_NAME_PTO_1 = "PTO_1" ;
        public static final String COLUMN_NAME_PTO_2 = "PTO_2" ;
        public static final String COLUMN_NAME_PTO_3 = "PTO_3" ;
        public static final String COLUMN_NAME_PTO_4 = "PTO_4" ;
        public static final String COLUMN_NAME_PTO_5 = "PTO_5" ;
        public static final String COLUMN_NAME_PTO_6 = "PTO_6" ;
    }

    private static final String SQL_CREATE_TABLE_PROGRESO =
            "CREATE TABLE " + entidadProgreso.TABLE_NAME + " (" +
                    entidadProgreso._ID + " TEXT PRIMARY KEY AUTOINCREMENT," +
                    entidadProgreso.COLUMN_NAME_ID_USUARIO + " TEXT,"
                    + "FOREIGN KEY('" + entidadProgreso.COLUMN_NAME_ID_USUARIO + "') REFERENCES '" + entidadUsuario.TABLE_NAME + "'('" + entidadUsuario._ID + "'),"+
                    entidadProgreso.COLUMN_NAME_PTO_0+ "BOOLEAN,"+
                    entidadProgreso.COLUMN_NAME_PTO_1+ "BOOLEAN,"+
                    entidadProgreso.COLUMN_NAME_PTO_2+ "BOOLEAN,"+
                    entidadProgreso.COLUMN_NAME_PTO_3+ "BOOLEAN,"+
                    entidadProgreso.COLUMN_NAME_PTO_4+ "BOOLEAN,"+
                    entidadProgreso.COLUMN_NAME_PTO_5+ "BOOLEAN,"+
                    entidadProgreso.COLUMN_NAME_PTO_6+ "BOOLEAN)";

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
