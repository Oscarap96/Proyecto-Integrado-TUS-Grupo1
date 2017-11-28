package es.unican.grupo1.tussantander.views;

import android.database.sqlite.SQLiteDatabase;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;
import es.unican.grupo1.tussantander.model.databaseaccess.MisFuncionesBBDD;
import es.unican.grupo1.tussantander.model.databaseaccess.TUSSQLiteHelper;
import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.model.dataloaders.RemoteFetch;

import static android.support.test.InstrumentationRegistry.getTargetContext;

/**
 * Created by Adrian on 27/11/2017.
 */

public class ActualizarIntegracionTest {
    @Test
    public void li1a() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        db.execSQL("delete from Linea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        List<Linea> listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());
        funciones.borrarListaLineas(listaLineasBus,db);
        funciones.insertaListaLineas(listaLineasBus,db);
        Assert.assertEquals(funciones.obtenerLineas(db).size(),33);
    }
    @Test
    public void li1b() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        db.execSQL("delete from Linea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        List<Linea> listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());//NO COGE NADA SIN INTERNET
        funciones.borrarListaLineas(listaLineasBus,db);
        funciones.insertaListaLineas(listaLineasBus,db);
        Assert.assertEquals(listaLineasBus.size(),0); //COMPROBAMOS QUE NO HA COGIDO LAS LINEAS
    }
    @Test
    public void pi2a() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        db.execSQL("delete from ParadaLinea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
        List<Parada> listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetch.getBufferedData(),2);
        System.out.print("PARADAS" + listaParadasBus.size());
        funciones.borrarListaParadas(listaParadasBus,db);
        funciones.insertaParadasLinea(listaParadasBus,2,db);
        System.out.println("PARADA" + funciones.obtenerParadasLinea(2,db).get(0).getIdentificador() + funciones.obtenerParadasLinea(2,db).get(0).getLinea());
        Assert.assertEquals(funciones.obtenerParadasLinea(2,db).size(),listaParadasBus.size());
    }
    @Test
    public void pi2b() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        db.execSQL("delete from ParadaLinea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
        List<Parada> listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetch.getBufferedData(),2);
        Assert.assertEquals(listaParadasBus.size(),0);//SIN INTERNET
    }
}
