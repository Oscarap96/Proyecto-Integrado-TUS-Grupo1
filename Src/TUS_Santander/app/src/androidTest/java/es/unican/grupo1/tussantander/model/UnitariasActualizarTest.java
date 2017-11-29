package es.unican.grupo1.tussantander.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.io.LittleEndianDataInputStream;
import android.support.test.espresso.core.deps.guava.reflect.Parameter;

import org.junit.Assert;
import org.junit.Test;

import es.unican.grupo1.tussantander.R;
import es.unican.grupo1.tussantander.model.databaseaccess.MisFuncionesBBDD;
import es.unican.grupo1.tussantander.model.databaseaccess.TUSSQLiteHelper;
import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.model.dataloaders.RemoteFetch;
import es.unican.grupo1.tussantander.presenter.IListLineasPresenter;
import es.unican.grupo1.tussantander.presenter.ListLineasPresenter;
import es.unican.grupo1.tussantander.views.ILineasFragment;
import es.unican.grupo1.tussantander.views.LineasFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getContext;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static es.unican.grupo1.tussantander.model.dataloaders.ParserJSON.readArrayLineasBus;

/**
 * Created by Adrian on 23/11/2017.
 */

/**
 * LAS LINEAS QUE BORRAN CONTENIDO DE TABLAS DE LA BASE DE DATOS ESTAN COMENTADAS Y DEBERAN SER
 * INTRODUCIDAS PARA HACER LAS PRUEBAS. ESTÁN COMENTADAS PARA CONSEGUIR UN CORRECTO FUNCIONAMIENTO DE
 * LA APLICACION Y LA BASE DE DATOS.
 */
public class UnitariasActualizarTest {




    @Test
    public void u1a() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from Linea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        List<Linea> listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());
        funciones.borrarListaLineas(listaLineasBus,db);
        funciones.insertaListaLineas(listaLineasBus,db);
        Assert.assertEquals(funciones.obtenerLineas(db).size(),33);
    }
    @Test
    public void u1b() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from Linea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        List<Linea> listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());//NO COGE NADA SIN INTERNET
        funciones.borrarListaLineas(listaLineasBus,db);
        funciones.insertaListaLineas(listaLineasBus,db);
        Assert.assertEquals(listaLineasBus.size(),0); //COMPROBAMOS QUE NO HA COGIDO LAS LINEAS
    }
    @Test
    public void u2a() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from ParadaLinea");
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
    public void u2b() throws IOException {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
       // db.execSQL("delete from ParadaLinea");
        RemoteFetch remoteFetch=new RemoteFetch();
        remoteFetch.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
        List<Parada> listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetch.getBufferedData(),2);
        Assert.assertEquals(listaParadasBus.size(),0);//SIN INTERNET
    }


    @Test
    public void u4a() throws IOException {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        funcionesBBDD.insertaLinea(1,"","1",2555,db);
        funcionesBBDD.insertaLinea(2,"","2",2547,db);
        int i=funcionesBBDD.obtenerLineas(db).size();
        List<Linea>lin=  funcionesBBDD.obtenerLineas(db);
        System.out.println("LAS LINEAS SON" + lin);
        funcionesBBDD.borrarLinea(1,db);
        int j=funcionesBBDD.obtenerLineas(db).size();
        funcionesBBDD.borrarLinea(2,db);
        Assert.assertEquals(i-1,j);
    }
    @Test
    public void u4b() throws IOException {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        funcionesBBDD.insertaLinea(1,"","1",2555,db);
        funcionesBBDD.insertaLinea(2,"","2",2547,db);
        int i=funcionesBBDD.obtenerLineas(db).size();
        List<Linea>lin=  funcionesBBDD.obtenerLineas(db);
        System.out.println("LAS LINEAS SON" + lin);
        funcionesBBDD.borrarLinea(40,db); //QUITA PORQUE NO EXISTE
        int j=funcionesBBDD.obtenerLineas(db).size();
        funcionesBBDD.borrarLinea(1,db);
        funcionesBBDD.borrarLinea(2,db);
        Assert.assertEquals(i,j);
    }
    @Test
    public void u5a() throws IOException {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from ParadaLinea");
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        funcionesBBDD.insertaParada(1,"parada1",2,db);
        funcionesBBDD.insertaParada(2,"parada2",2,db);
        funcionesBBDD.insertaParada(3,"parada3",2,db);
        System.out.println("HOLA" + funcionesBBDD.obtenerParadasLinea(2,db).size());
        int i=funcionesBBDD.obtenerParadasLinea(2,db).size();
        funcionesBBDD.borraParada(1,db);
        int j=funcionesBBDD.obtenerParadasLinea(2,db).size();
        funcionesBBDD.borraParada(3,db);
        funcionesBBDD.borraParada(2,db);
        System.out.println("HOLA" + funcionesBBDD.obtenerParadasLinea(2,db).size());


        Assert.assertEquals(i-1,j);
    }

    @Test
    public void u5b() throws IOException {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        funcionesBBDD.insertaParada(1,"parada1",2,db);
        funcionesBBDD.insertaParada(2,"parada2",2,db);
        funcionesBBDD.insertaParada(3,"parada3",2,db);
        System.out.println("HOLA" + funcionesBBDD.obtenerParadasLinea(2,db).size());
        int i=funcionesBBDD.obtenerParadasLinea(2,db).size();
        funcionesBBDD.borraParada(4000,db);
        int j=funcionesBBDD.obtenerParadasLinea(2,db).size();
        System.out.println("HOLA" + funcionesBBDD.obtenerParadasLinea(2,db).size());
        funcionesBBDD.borraParada(1,db);
        funcionesBBDD.borraParada(2,db);
        funcionesBBDD.borraParada(3,db);


        Assert.assertEquals(i,j);
    }
    @Test
    public void u6a() {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from Linea");
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        Linea l=new Linea("1","1",1);
        Linea l1=new Linea ("2","2",2);
        Linea l2=new Linea("3","3",3);
        List<Linea>lineas=new ArrayList<>();
        lineas.add(l);
        lineas.add(l1);
        lineas.add(l2);
        funcionesBBDD.insertaLinea(1,"1","1",1,db);
        funcionesBBDD.insertaLinea(2,"2","2",2,db);
        funcionesBBDD.insertaLinea(3,"3","3",3,db);
        System.out.println("TAMAÑOINI" + funcionesBBDD.obtenerLineas(db).size());
        funcionesBBDD.borrarListaLineas(lineas,db);
        Assert.assertEquals(funcionesBBDD.obtenerLineas(db).size(),0);

    }
    @Test
    public void u6b() {

        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from Linea");
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        Linea l=new Linea("12","24",12);
        Linea l1=new Linea ("22","22",23);
        Linea l2=new Linea("32","35",30);
        List<Linea>lineas=new ArrayList<>();
        lineas.add(l);
        lineas.add(l1);
        lineas.add(l2);
        funcionesBBDD.insertaLinea(1000,"1","1",1,db);
        funcionesBBDD.insertaLinea(2000,"2","2",2,db);
        funcionesBBDD.insertaLinea(3000,"3","3",3,db);
        funcionesBBDD.borrarListaLineas(lineas,db);
        List<Linea> lineas1=funcionesBBDD.obtenerLineas(db);
        funcionesBBDD.borrarListaLineas(lineas1,db);
        int j=funcionesBBDD.obtenerLineas(db).size();
        funcionesBBDD.borrarLinea(1000,db);
        funcionesBBDD.borrarLinea(2000,db);
        funcionesBBDD.borrarLinea(3000,db);
        Assert.assertEquals(j,3);


    }
    @Test
    public void u7a() {

        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from ParadaLinea");
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        Parada l=new Parada("12",1,2);
        Parada l1=new Parada ("22",2,3);
        Parada l2=new Parada("32",3,1);
        List<Parada>paradas=new ArrayList<>();
        paradas.add(l);
        paradas.add(l1);
        paradas.add(l2);
        funcionesBBDD.insertaParada(1,"1",1,db);
        funcionesBBDD.insertaParada(2,"2",2,db);
        funcionesBBDD.insertaParada(3,"3",3,db);
        funcionesBBDD.borrarListaParadas(paradas,db);
       /// List<Parada> paradas1=funcionesBBDD.obtenerParadas(db);
        //funcionesBBDD.borrarListaParadas(paradas1,db);
        Assert.assertEquals(funcionesBBDD.obtenerParadas(db).size(),0);

    }
    @Test
    public void u7b() {

        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(getTargetContext(), "DBTUSPRUEBA", null, 2);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        //db.execSQL("delete from ParadaLinea");
        MisFuncionesBBDD funcionesBBDD=new MisFuncionesBBDD();
        Parada l=new Parada("12",1,2);
        Parada l1=new Parada ("22",2,3);
        Parada l2=new Parada("32",3,1);
        List<Parada>paradas=new ArrayList<>();
        paradas.add(l);
        paradas.add(l1);
        paradas.add(l2);
        funcionesBBDD.insertaParada(20,"1",1,db);
        funcionesBBDD.insertaParada(25,"2",2,db);
        funcionesBBDD.insertaParada(34,"3",3,db);
        funcionesBBDD.borrarListaParadas(paradas,db);
        /// List<Parada> paradas1=funcionesBBDD.obtenerParadas(db);
        //funcionesBBDD.borrarListaParadas(paradas1,db);
        int j=funcionesBBDD.obtenerParadas(db).size();
        funcionesBBDD.borraParada(20,db);
        funcionesBBDD.borraParada(25,db);
        funcionesBBDD.borraParada(34,db);
        Assert.assertEquals(j,3);


    }

}
