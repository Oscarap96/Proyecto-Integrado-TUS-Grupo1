package es.unican.grupo1.tus_santander.Model.DataLoaders;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

import static android.R.attr.id;

public class BaseTUS extends SQLiteOpenHelper {
    private static final int VERSION_BASE = 1;

    private static final String NOMBREBASE = "BaseTUS.db";

    private static final String TABLA_LINEAS = "create table linea" + "(_id INT PRIMARY KEY," +
            "nombre TEXT, numero TEXT, identificador INT";
    private static final String TABLA_PARADAS_A_LINEAS = "create table ParadaALinea" +
            "parada TEXT not null, linea TEXT not null" +
            "PRIMARY KEY(parada,linea)" +
            " FOREIGN KEY (parada) references parada(_id), FOREIGN KEY (linea) references linea(_id)";
    private static final String TABLA_PARADAS = "create table parada" + "(_id INT PRIMARY KEY," +
            "nombre TEXT, identificador INT";
    private static final String NOMBRE="nombre";
    private static final String NUMERO="numero";
    private static final String IDENTIFICADOR="identificador";
    private static final String LINEAS="Lineas";
    private static final String PARADAS="paradas";


    public BaseTUS(Context context) {
        super(context, NOMBREBASE, null, VERSION_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_LINEAS);
        db.execSQL(TABLA_PARADAS);
        db.execSQL(TABLA_PARADAS_A_LINEAS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLA_LINEAS");
        db.execSQL(TABLA_LINEAS);
        db.execSQL("DROP TABLE IF EXISTS TABLA_PARADAS");
        db.execSQL(TABLA_PARADAS);
        db.execSQL("DROP TABLE IF EXISTS TABLA_PARADAS_A_LINEAS");
        db.execSQL(TABLA_PARADAS_A_LINEAS);
    }

    public void modificarLinea(int id, String nombre, String numero, int identificador) {
        SQLiteDatabase db = getWritableDatabase();
        //db.execSQL();
        /*ContentValues valores = new ContentValues();
        valores.put("_id", id);
        valores.put(NOMBRE, nombre);
        valores.put(NUMERO, numero);
        valores.put(IDENTIFICADOR, identificador);
        db.update(LINEAS, valores, "_id=" + id, null);*/
    }

    public void modificarLineas(List<Linea> lista) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 1; i <= lista.size(); i++) {
            valores.put("_id", i);
            valores.put(IDENTIFICADOR, lista.get(i).getIdentifier());
            valores.put(NOMBRE, lista.get(i).getName());
            valores.put(NUMERO, lista.get(i).getNumero());
            db.update(LINEAS, valores, "_id" + id, null);
        }
        db.update(LINEAS, valores, "_id" + id, null);
        db.close();
    }

    public void borrarLinea(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("linea", "_id=" + id, null);
        db.close();
    }

    public Linea recuperarLinea(int id) throws NullPointerException {
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", NOMBRE, NUMERO, IDENTIFICADOR};
        Cursor c = db.query(LINEAS, valores_recuperar, "_id=" + id,
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        if (c==null) throw new  NullPointerException();
        Linea linea = new Linea(c.getString(1), c.getString(2), c.getInt(3));
        db.close();
        c.close();
        return linea;
    }

    public List<Linea> recuperarLineas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Linea> lista_lineas = new ArrayList<Linea>();
        String[] valores_recuperar = {"_id", NOMBRE, NUMERO, IDENTIFICADOR};
        Cursor c = db.query(LINEAS, valores_recuperar,
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
        valores.put(NOMBRE, nombre);
        valores.put(IDENTIFICADOR, identificador);
        db.update(PARADAS, valores, "_id=" + id, null);
    }

    public void modificarParadas(List<Parada> lista) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        for (int i = 1; i <= lista.size(); i++) {
            valores.put("_id", i);
            valores.put(NOMBRE, lista.get(i).getNombre());
            valores.put("identificador", lista.get(i).getIdentificador());

        }
        db.update("Paradas", valores, "_id" + id, null);
        db.close();
    }

    public void borrarParada(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("parada", "_id=" + id, null);
        db.close();
    }

    public Parada recuperarParada(int id) throws NullPointerException{
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"_id", NOMBRE, IDENTIFICADOR};
        Cursor c = db.query("Parada", valores_recuperar, "_id=" + id,
                null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        if (c==null) throw new NullPointerException();
        Parada parada = new Parada(c.getString(1),
                c.getInt(2));
        db.close();
        c.close();
        return parada;
    }

    public List<Parada> recuperarParadas() {
        SQLiteDatabase db = getReadableDatabase();
        List<Parada> lista_lineas = new ArrayList<Parada>();
        String[] valores_recuperar = {"_id", NOMBRE, IDENTIFICADOR};
        Cursor c = db.query(PARADAS, valores_recuperar,
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
