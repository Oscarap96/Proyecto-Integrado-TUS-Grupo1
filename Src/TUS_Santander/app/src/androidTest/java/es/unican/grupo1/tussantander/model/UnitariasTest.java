package es.unican.grupo1.tussantander.model;

import android.support.test.InstrumentationRegistry;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.R;

/**
 * Created by lamberto on 29/10/17.
 * Pruebas unitarias para listar lineas.
 */
public class UnitariasTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void obtenerLineasInternet() throws Exception {
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_test_unitarias);
        List<Linea> lineas = ParserJSON.readArrayLineasBus(is);
        Collections.sort(lineas);
        // U1a
        Linea linea = lineas.get(0);
        Assert.assertEquals("1", linea.getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA", linea.getName());
        Assert.assertEquals(1, linea.getIdentifier());
        // U1b
        linea = lineas.get(2);
        Assert.assertEquals("19", linea.getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA", linea.getName());
        Assert.assertEquals(19, linea.getIdentifier());
        // U1c
        linea = lineas.get(lineas.size() - 1);
        Assert.assertEquals("20", linea.getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE", linea.getName());
        Assert.assertEquals(20, linea.getIdentifier());
        // U1d
        List<Linea> lineasBus = lineas;
        Assert.assertEquals("1", lineasBus.get(0).getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA", lineasBus.get(0).getName());
        Assert.assertEquals(1, lineasBus.get(0).getIdentifier());
        Assert.assertEquals("2", lineasBus.get(1).getNumero());
        Assert.assertEquals("CORBAN-CONSUELO BERGES", lineasBus.get(1).getName());
        Assert.assertEquals(2, lineasBus.get(1).getIdentifier());
        Assert.assertEquals("19", lineasBus.get(2).getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA", lineasBus.get(2).getName());
        Assert.assertEquals(19, lineasBus.get(2).getIdentifier());
        Assert.assertEquals("20", lineasBus.get(3).getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE", lineasBus.get(3).getName());
        Assert.assertEquals(20, lineasBus.get(3).getIdentifier());
    }

    @Test
    public void obtenerLineasBaseDatos() throws Exception {
        // TODO rehacer las pruebas con la nueva base de datos
        /*
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_test_unitarias);
        BaseTUS baseDatos = new BaseTUS(getContext());
        FuncionesBBDD.anhadeLineasFromInputStream(is, baseDatos);
        List<Linea> lineas = FuncionesBBDD.obtenerLineas();
        // U2a
        Linea linea = lineas.get(0);
        Assert.assertEquals("1", linea.getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA", linea.getName());
        Assert.assertEquals(1, linea.getIdentifier());
        // U2b
        linea = lineas.get(2);
        Assert.assertEquals("19", linea.getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA", linea.getName());
        Assert.assertEquals(19, linea.getIdentifier());
        // U2c
        linea = lineas.get(lineas.size() - 1);
        Assert.assertEquals("20", linea.getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE", linea.getName());
        Assert.assertEquals(20, linea.getIdentifier());
        // U2d
        List<Linea> lineasBus = lineas;
        Assert.assertEquals("1", lineasBus.get(0).getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA", lineasBus.get(0).getName());
        Assert.assertEquals(1, lineasBus.get(0).getIdentifier());
        Assert.assertEquals("2", lineasBus.get(1).getNumero());
        Assert.assertEquals("CORBAN-CONSUELO BERGES", lineasBus.get(1).getName());
        Assert.assertEquals(2, lineasBus.get(1).getIdentifier());
        Assert.assertEquals("19", lineasBus.get(2).getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA", lineasBus.get(2).getName());
        Assert.assertEquals(19, lineasBus.get(2).getIdentifier());
        Assert.assertEquals("20", lineasBus.get(3).getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE", lineasBus.get(3).getName());
        Assert.assertEquals(20, lineasBus.get(3).getIdentifier());*/
    }

    @Test
    public void insertarYBuscarBaseDatos() throws Exception {
        // id es el de la base de datos
        // identificador es el de la linea
        // creo que es al contrario
        // TODO rehacer las pruebas con la nueva base de datos
        /*
        BaseTUS baseDatos = new BaseTUS(getContext());
        // U3a
        baseDatos.modificarLinea(8, "Línea 25", "39", 10);
        // U3b
        baseDatos.modificarLinea(4, "Línea 12", "25", 14);
        // U3c
        baseDatos.modificarLinea(1, "Línea 1", "20", 15);
        // U3d
        Linea linea = baseDatos.recuperarLinea(8);
        Assert.assertEquals("Línea 25", linea.getName());
        Assert.assertEquals(10, linea.getIdentifier());
        Assert.assertEquals("39", linea.getNumero());
        // U3e
        try {
            linea = baseDatos.recuperarLinea(9);
            Assert.fail();
        } catch (Exception e) {
        }
        // U3f
        linea = baseDatos.recuperarLinea(4);
        Assert.assertEquals("Línea 12", linea.getName());
        Assert.assertEquals(14, linea.getIdentifier());
        Assert.assertEquals("25", linea.getNumero());*/
    }
}