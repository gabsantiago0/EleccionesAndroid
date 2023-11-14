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

import com.gabrielsantiago.elecciones.Clases.Candidato;
import com.gabrielsantiago.elecciones.Clases.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

            //Insertamos candidatos
            registrarCandidatoBBDD(new CandidatoBBDD(0, 0, "Doña Concha", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(1, 0, "Marisa", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(2, 0, "Vicenta", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(3, 1, "Lucía", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(4, 1, "Belén", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(5, 1, "Bea", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(6, 2, "Juan 'Chorizo' Cuesta", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(7, 2, "Isabel 'Yerbas'", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(8, 2, "Mauri", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(9, 3, "Paco", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(10, 3, "'Josemi' Cuesta", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(11, 3, "Doña Concha", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(12, 4, "Emilio Delgado", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(13, 4, "Mariano Delgado", 0));
            registrarCandidatoBBDD(new CandidatoBBDD(14, 4, "Jose María", 0));


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

    public ArrayList<String> mostrarElecciones() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> candidadoVoto = new ArrayList<>();
        try {
            Cursor cursor = db.query(
                    TABLE_NAME,
                    new String[]{COLUMN_NOMBRE, COLUMN_VOTOS},
                    COLUMN_VOTOS + " > ?",
                    new String[]{"1"},
                    null,
                    null,
                    null
            );

            int indiceNombre = cursor.getColumnIndex(COLUMN_NOMBRE);
            int indiceVotos = cursor.getColumnIndex(COLUMN_VOTOS);

            while (cursor.moveToNext()) {
                if (indiceNombre != -1 && indiceVotos != -1) {
                    String nombreCandidato = cursor.getString(indiceNombre);
                    String votosCandidato = String.valueOf(cursor.getInt(indiceVotos));
                    String candidato = nombreCandidato + ": " + votosCandidato + " votos";
                    candidadoVoto.add(candidato);
                }
            }
            cursor.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return candidadoVoto;
    }


    public boolean registrarCandidatoBBDD(CandidatoBBDD a) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CODCANDIDATO, a.getCodCandidato());
            contentValues.put(COLUMN_CODPARTIDO, a.getCodPartido());
            contentValues.put(COLUMN_NOMBRE, a.getNombre());
            contentValues.put(COLUMN_VOTOS, a.getVotos());

            db.beginTransaction();
            db.insert(TABLE_NAME, null, contentValues);
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

    public boolean marcarDniComoVotado(String dni) {
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

    public boolean votarCandidato(String nombre) {
        boolean estado = false;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_VOTOS + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NOMBRE + " = ?", new String[]{nombre});
        int indiceColumna = 0;
        if (cursor.moveToFirst()) {
            indiceColumna = cursor.getColumnIndex(COLUMN_VOTOS);
        }

        if (indiceColumna != -1) {
            int votosActuales = cursor.getInt(indiceColumna);
            int actualizacionVotos = votosActuales + 1;
            values.put(COLUMN_VOTOS, actualizacionVotos);

            int filasAfectadas = db.update(TABLE_NAME, values, COLUMN_NOMBRE, new String[]{nombre});
            db.close();
            if (filasAfectadas > 0) {
                estado = true;
            }
        }
        cursor.close();
        return false;
    }

}

