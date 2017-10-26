package es.unican.grupo1.tus_santander.Model.DataLoaders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PageRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

public class BaseTUS extends SQLiteOpenHelper {
    private static final int VERISION_BASE=1;

    private static final String NOMBREBASE="BaseTUS.db";

    private static final String TABLA_LINEAS="create table linea" + "(_id INT PRIMARY KEY," +
            "nombre TEXT, numero TEXT, identificador INT";
    private static final String TABLA_PARADAS_A_LINEAS="create table paradasALineas" +
            "parada TEXT not null, linea TEXT not null" +
            "PRIMARY KEY(parada,linea)" +
            " FOREIGN KEY (parada) references parada(_id), FOREIGN KEY (linea) references linea(_id)";
    private static final String TABLA_PARADAS="create table parada" + "(_id INT PRIMARY KEY," +
            "nombre TEXT, identificador INT";
public BaseTUS(Context context){
    super(context,NOMBREBASE,null,VERISION_BASE);
}
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_LINEAS);
        db.execSQL(TABLA_PARADAS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_LINEAS);
        db.execSQL("DROP TABLE IF EXISTS" + TABLA_PARADAS);
        onCreate(db);
    }
    public void modificarLinea(int id, String nombre, String numero, int identificador){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id",id);
        valores.put("nombre",nombre);
        valores.put("numero",numero);
        valores.put("identificador",identificador);
        db.update("Lineas",valores,"_id=" + id,null);
    }
    public void borrarLinea(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("linea", "_id="+id, null);
        db.close();
    }
    public Linea recuperaLinea(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", "nombre", "numero", "identificador"};
        Cursor c = db.query("lineas", valores_recuperar, "_id=" + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Linea linea = new Linea(c.getString(1), c.getString(2), c.getInt(3));
        db.close();
        c.close();
        return linea;
    }
    public List<Linea> recuperarLineas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Linea> lista_lineas = new ArrayList<Linea>();
        String[] valores_recuperar = {"_id", "nombre", "numero", "identifiacor"};
        Cursor c = db.query("lineas", valores_recuperar,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Linea lineas = new Linea(c.getString(1),
                    c.getString(2), c.getInt(3));
            lista_lineas.add(lineas);
    } while (c.moveToNext());
        db.close();
        c.close();
        return lista_lineas;
    }
    public void modificarParada(int id, String nombre, int identificador) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put("nombre", nombre);
        valores.put("identificador", identificador);
        db.update("Lineas", valores, "_id=" + id, null);
    }
    public void borrarParada(int id) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("parada", "_id="+id, null);
            db.close();
    }
    public Parada recuperaParada(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", "nombre", "identificador"};
        Cursor c = db.query("Parada", valores_recuperar, "_id=" +id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Parada parada = new Parada( c.getString(1),
                c.getInt(2));
        db.close();
        c.close();
        return parada;
    }
    public List<Parada> recuperarParadas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Parada> lista_lineas = new ArrayList<Parada>();
        String[] valores_recuperar = {"_id", "nombre", "identificador"};
        Cursor c = db.query("Paradas", valores_recuperar,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Parada paradas = new Parada(c.getString(1),
                    c.getInt(2));
            lista_lineas.add(paradas);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista_lineas;
    }

}
