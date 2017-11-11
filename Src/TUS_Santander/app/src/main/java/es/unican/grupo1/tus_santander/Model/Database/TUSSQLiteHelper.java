package es.unican.grupo1.tus_santander.Model.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;


/**
 * Created by Oscar Alario Pelaz on 07/11/2017.
 */

public class TUSSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Lineas
    String sqlCreateLinea = "CREATE TABLE Linea (idLinea INTEGER PRIMARY KEY, nombre TEXT, numero TEXT, identificador INTEGER)";

    //Sentencia SQL para crear la tabla de Paradas
    //String sqlCreateParada = "CREATE TABLE Parada (idParada INTEGER PRIMARY KEY, nombre TEXT, identificador INTEGER)";

    //Sentencia SQL para crear la tabla de ParadaLinea
    String sqlCreateParadaLinea = "CREATE TABLE ParadaLinea (idParada INTEGER, nombreParada TEXT, idLinea INTEGER)";

    public TUSSQLiteHelper(Context contexto, String nombre,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateLinea);
        //db.execSQL(sqlCreateParada);
        db.execSQL(sqlCreateParadaLinea);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Linea");
        //db.execSQL("DROP TABLE IF EXISTS Parada");
        db.execSQL("DROP TABLE IF EXISTS ParadaLinea");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateLinea);
        //db.execSQL(sqlCreateParada);
        db.execSQL(sqlCreateParadaLinea);
    }

    //METODOS HEREDADOS DE LAS FUNCIONES ANTERIORES//
    /**
    public void modificarParada(int id, String nombre, int identificador) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put(NOMBRE, nombre);
        valores.put(IDENTIFICADOR, identificador);
        db.update(PARADAS, valores, "_id=" + id, null);
    }*/
}
