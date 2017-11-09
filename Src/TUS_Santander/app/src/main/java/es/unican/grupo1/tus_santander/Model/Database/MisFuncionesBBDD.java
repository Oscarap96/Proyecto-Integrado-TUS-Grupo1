package es.unican.grupo1.tus_santander.Model.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

/**
 * Created by Oscar Alario Pelaz on 08/11/2017.
 */

public class MisFuncionesBBDD {
    private void insertaLinea(int id, String nom, String num, int identi, SQLiteDatabase db) {
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("idLinea", id);
            valores.put("nombre", nom);
            valores.put("numero", num);
            valores.put("identificador", identi);
            db.insert("Linea", null, valores);
        }
    }

    public void insertaListaLineas(List<Linea> lineas, SQLiteDatabase db) {
        for (int i = 0; i < lineas.size(); i++) {
            Linea linea = lineas.get(i);
            insertaLinea(i, linea.getName(), linea.getNumero(), linea.getIdentifier(), db);
        }
    }

    private void insertaParada(int id, String nom, int identi, SQLiteDatabase db) {
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("idParada", id);
            valores.put("nombre", nom);
            valores.put("identificador", identi);
            db.insert("Parada", null, valores);
        }
    }

    public void insertaListaParadas(List<Parada> paradas, SQLiteDatabase db) {
        for (int i = 0; i < paradas.size(); i++) {
            Parada parada = paradas.get(i);
            insertaParada(i, parada.getNombre(), parada.getIdentificador(), db);
        }
    }

    public void insertaParadasLinea(List<Parada> paradas, int identiLinea, SQLiteDatabase db) {
        if (db != null) {
            ContentValues valores = new ContentValues();
            Parada laParada;
            for (int i = 0; i < paradas.size(); i++) {
                laParada = paradas.get(i);
                valores.put("idParada", laParada.getIdentificador());
                valores.put("idLinea", identiLinea);
                db.insert("ParadaLinea", null, valores);
            }
        }
    }

    public List<Parada> obtenerParadasLinea(int identiLinea, SQLiteDatabase db) {
        String[] args = new String[]{String.valueOf(identiLinea)};
        Cursor c1 = db.rawQuery("SELECT idParada FROM ParadaLinea where idLinea = ?", args);
        int identiParada;
        String nombre;
        int identificador;
        List<Parada> paradas = new ArrayList<Parada>();
        if (c1.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                identiParada = c1.getInt(0);
                String[] args2 = new String[]{String.valueOf(identiParada)};
                Cursor c2 = db.rawQuery("SELECT * FROM Parada where identificador = ? limit 1", args2);

                nombre = c2.getString(1);
                identificador = c2.getInt(2);
                paradas.add(new Parada(nombre, identificador));
            } while (c1.moveToNext());
        }
        Log.d("Lista paradas lineas", "tamano: "+paradas.size());
        return paradas;
    }

    public List<Linea> obtenerLineas(SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT * FROM Linea", null);
        String nombre;
        String numero;
        int identificador;
        List<Linea> lineas = new ArrayList<Linea>();
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                nombre = c.getString(1);
                numero = c.getString(2);
                identificador = c.getInt(3);
                lineas.add(new Linea(nombre, numero, identificador));
            } while (c.moveToNext());
        }
        return lineas;
    }

    public List<Parada> obtenerParadas(SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT * FROM Parada", null);
        String nombre;
        int identificador;
        List<Parada> paradas = new ArrayList<Parada>();
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                nombre = c.getString(1);
                identificador = c.getInt(2);
                paradas.add(new Parada(nombre, identificador));
            } while (c.moveToNext());
        }
        return paradas;
    }

}
