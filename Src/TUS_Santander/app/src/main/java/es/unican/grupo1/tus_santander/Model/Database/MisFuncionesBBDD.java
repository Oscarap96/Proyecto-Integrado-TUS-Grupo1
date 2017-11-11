package es.unican.grupo1.tus_santander.Model.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
                valores.put("nombreParada", laParada.getNombre());
                valores.put("idLinea", identiLinea);
                db.insert("ParadaLinea", null, valores);
            }
        }
    }

    public List<Parada> obtenerParadasLinea(int identiLinea, SQLiteDatabase db) {
        int identiParada;
        String nombre;
        List<Parada> paradas = new ArrayList<Parada>();

        Cursor c1 = db.rawQuery("SELECT * FROM ParadaLinea where idLinea =" + identiLinea, null);

        if (c1.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                identiParada = c1.getInt(0);
                nombre = c1.getString(1);
                //Cursor c2 = db.rawQuery("SELECT * FROM Parada where identificador = " + identiParada + " limit 1", null);
                //c2.moveToFirst();
                //nombre = c2.getString(1);
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

    //METODOS HEREDADOS DE LAS FUNCIONES ANTERIORES//
    /**
     * Permite anhadir lineas a la bd desde un inputstream. Por ejemplo, para leer un archivo.
     */
    /*
    public static void anhadeLineasFromInputStream(InputStream in, TUSSQLiteHelper baseDatos) throws IOException {
        List<Linea> lista=readArrayLineasBus(in);
        for(int i=1;i<=lista.size();i++){
            baseDatos.modificarParada(i,lista.get(i).getName(),lista.get(i).getIdentifier());
        }
        baseDatos.close();
    }*/
}
