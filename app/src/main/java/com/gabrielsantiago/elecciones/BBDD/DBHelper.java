package com.gabrielsantiago.elecciones.BBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.gabrielsantiago.elecciones.Clases.Usuario;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "elecciones.db";
    public static final int DATABASE_VERSION = 1;
    //----------------------------------------------------------------------------
    public static final String TABLE_NAME = "candidatos";
    public static final String COLUMN_CODCANDIDATO = "codCandidato";
    public static final String COLUMN_CODPARTIDO = "codPartido";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_VOTOS = "votos";
    //----------------------------------------------------------------------------
    public static final String TABLE_NAME1 = "partidos";
    public static final String COLUMN_CODPARTIDO1 = "codPartido";
    public static final String COLUMN_NOMBRE1 = "nombre";
    public static final String COLUMN_COLOR = "color";
    //----------------------------------------------------------------------------
    public static final String TABLE_NAME2 = "usuarios";
    public static final String COLUMN_NIF = "nif";
    public static final String COLUMN_HAVOTADO = "haVotado";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBHelper", "Creando BBDD " + DATABASE_NAME + " v" + DATABASE_VERSION);
        try {
            db.beginTransaction();

            //TABLA CANDIDATOS
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                    COLUMN_CODCANDIDATO + " INTEGER PRIMARY KEY," +
                    COLUMN_CODPARTIDO + " INTEGER," +
                    COLUMN_NOMBRE + " TEXT," +
                    COLUMN_VOTOS + " INTEGER," +
                    "FOREIGN KEY (" + COLUMN_CODPARTIDO + ") REFERENCES " + TABLE_NAME1 + "(" + COLUMN_CODPARTIDO1 + "));");

            //TABLA PARTIDOS
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + "(" +
                    COLUMN_CODPARTIDO1 + " INTEGER PRIMARY KEY," +
                    COLUMN_NOMBRE1 + " TEXT," +
                    COLUMN_COLOR + " INTEGER)");

            //TABLA USUARIOS
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + "(" +
                    COLUMN_NIF + " TEXT," +
                    COLUMN_HAVOTADO + " TINYINT)");

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager.onCreate", e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DBManager", "ACTUALIZANDO LA BBDD DE LA VERSION " + oldVersion
                + " A LA VERSION " + newVersion);
    }

    //----------------------------------------------------------------------------


    //Metodo para saber si alguien ha votado anteriormente.
    public boolean hasVotado(String dni) {
        boolean estado = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME2,
                new String[]{COLUMN_HAVOTADO},
                COLUMN_NIF + " = ?",
                new String[]{dni},
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            int resultado = cursor.getColumnIndex(COLUMN_HAVOTADO);
            resultado = cursor.getInt(resultado);
            cursor.close();
            if (!(resultado == 0)) {
                estado = true;
            }
        }
        return estado;
    }

    public boolean existeUsuario(String dni) {
        boolean estado = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME2,
                new String[]{COLUMN_NIF},
                COLUMN_NIF + " = ?",
                new String[]{dni},
                null,
                null,
                null);

        if (cursor.moveToNext()) {
            return true;
        }
        cursor.close();
        return false;
    }


    public boolean registrarUsuario(String dni) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_NIF, dni);
            contentValues.put(COLUMN_HAVOTADO, 0);

            db.beginTransaction();
            db.insert(TABLE_NAME2, null, contentValues);
            db.setTransactionSuccessful();
            return true;
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        } finally {
            if (db != null && db.isOpen()) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    public boolean Votar(String dni) {
        boolean estado = false;
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_HAVOTADO, 1);

        int filasAfectadas = db.update(TABLE_NAME2, values, COLUMN_NIF + " = ?", new String[]{dni});
        db.close();

        if (filasAfectadas > 0) {
            return true;
        }
        return estado;
    }

    public boolean VotarCandidato(String nombre) {
        boolean estado = false;
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        int filasAfectadas = db.update(TABLE_NAME2, values, COLUMN_NIF + " = ?", new String[]{dni});
        db.close();

        if (filasAfectadas > 0) {
            return true;
        }
        return estado;
    }



}

