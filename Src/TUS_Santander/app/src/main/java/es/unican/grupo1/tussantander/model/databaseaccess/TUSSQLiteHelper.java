package es.unican.grupo1.tussantander.model.databaseaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Oscar Alario Pelaz on 07/11/2017.
 */

public class TUSSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Lineas
    String sqlCreateLinea = "CREATE TABLE Linea (idLinea INTEGER PRIMARY KEY, nombre TEXT, numero TEXT, identificador INTEGER)";

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

        db.execSQL(sqlCreateParadaLinea);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Linea");

        db.execSQL("DROP TABLE IF EXISTS ParadaLinea");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateLinea);

        db.execSQL(sqlCreateParadaLinea);
    }
}
