package es.unican.grupo1.tussantander.model.databaseaccess;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;

/**
 * Created by Oscar Alario Pelaz and Alberto de Castro on 08/11/2017.
 */

public class MisFuncionesBBDD {
    private static final String IDLINEA="idLinea";
    private static final String PARADALINEA="ParadaLinea";

    public void insertaLinea(int id, String nom, String num, int identi, SQLiteDatabase db) {
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put(IDLINEA, id);
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

    public void borrarLinea(int id,SQLiteDatabase db){
        if (db!=null){
            db.delete("Linea","idLinea="+id,null);
        }else
            Log.d("Error: ", "ERROR DELETE");
    }
    public void borrarListaLineas(List<Linea> lineas,SQLiteDatabase db){
        for (int i=0; i<lineas.size();i++) {
            borrarLinea(i, db);
        }
    }
    public void insertaParada(int id, String nom, int idLinea, SQLiteDatabase db) {
        if (db != null) {
            ContentValues valores = new ContentValues();
            valores.put("idParada", id);
            valores.put("nombreParada", nom);
            valores.put("idLinea", idLinea);
            db.insert(PARADALINEA, null, valores);
        }
    }
    public void borraParada(int id,SQLiteDatabase db){
        if(db!=null){
            db.delete(PARADALINEA,"idParada="+id,null);
        }
    }

    public void insertaListaParadas(List<Parada> paradas, SQLiteDatabase db) {
        for (int i = 0; i < paradas.size(); i++) {
            Parada parada = paradas.get(i);
            insertaParada(i, parada.getNombre(), parada.getIdentificador(), db);
        }
    }

    public void borrarListaParadas(List<Parada> paradas,SQLiteDatabase db){
        Parada parada;
        for (int i=0; i< paradas.size();i++){
            parada=paradas.get(i);

            Log.d("Error: ", "ERROR DELETE");
            db.delete(PARADALINEA,"idParada="+parada.getIdentificador(),null);

        }
    }

    public void insertaParadasLinea(List<Parada> paradas, int identiLinea, SQLiteDatabase db) {
        if (db != null) {
            ContentValues valores = new ContentValues();
            Parada laParada;
            for (int i = 0; i < paradas.size(); i++) {
                laParada = paradas.get(i);
                valores.put("idParada", laParada.getIdentificador());
                valores.put("nombreParada", laParada.getNombre());
                valores.put(IDLINEA, identiLinea);
                db.insert("ParadaLinea", null, valores);
            }
        }
    }

    public List<Parada> obtenerParadasLinea(int identiLinea, SQLiteDatabase db) {
        int identiParada;
        String nombre;
        List<Parada> paradas = new ArrayList<>();

        Cursor c1 = db.rawQuery("SELECT * FROM ParadaLinea where idLinea =" + identiLinea, null);

        if (c1.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                identiParada = c1.getInt(0);
                nombre = c1.getString(1);
                paradas.add(new Parada(nombre, identiParada));
            } while (c1.moveToNext());
        }
        return paradas;
    }

    public List<Linea> obtenerLineas(SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT * FROM Linea", null);
        String nombre;
        String numero;
        int identificador;
        List<Linea> lineas = new ArrayList<>();
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
        Cursor c = db.rawQuery("SELECT * FROM ParadaLinea", null);
        String nombre;
        int identificador;
        List<Parada> paradas = new ArrayList<>();
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
