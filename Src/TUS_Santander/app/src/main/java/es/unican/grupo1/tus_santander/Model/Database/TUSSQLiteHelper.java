package es.unican.grupo1.tus_santander.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Oscar Alario Pelaz on 07/11/2017.
 */

public class TUSSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Lineas
    String sqlCreateLinea = "CREATE TABLE Linea" + "(idLinea INTEGER, nombre TEXT, numero TEXT, identificador INTEGER)";

    //Sentencia SQL para crear la tabla de Paradas
    String sqlCreateParada = "CREATE TABLE Parada" + "(idParada INTEGER, nombre TEXT, identificador INTEGER)";

    public TUSSQLiteHelper(Context contexto, String nombre,
                           SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateLinea);
        db.execSQL(sqlCreateParada);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Linea");
        db.execSQL("DROP TABLE IF EXISTS Parada");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreateLinea);
        db.execSQL(sqlCreateParada);
    }
}
