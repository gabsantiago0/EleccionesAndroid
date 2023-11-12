package com.gabrielsantiago.elecciones.BBDD;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "elecciones.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "candidatos";
    public static final String COLUMN_CODCANDIDATO = "codCandidato";
    public static final String COLUMN_CODPARTIDO = "codPartido";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_VOTOS = "votos";





    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBHelper","Creando BBDD "+DATABASE_NAME+" v"+DATABASE_VERSION);
        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(" +
                    COLUMN_CODCANDIDATO+" INTEGER," +
                    COLUMN_CODPARTIDO+" INTEGER," +
                    COLUMN_NOMBRE+" TEXT," +
                    COLUMN_VOTOS+" INTEGER)"); //FALTA FOREING KEY
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager.onCreate",e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
