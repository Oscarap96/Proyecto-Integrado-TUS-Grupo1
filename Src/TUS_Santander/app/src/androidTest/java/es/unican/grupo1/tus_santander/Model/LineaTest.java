package es.unican.grupo1.tus_santander.Model;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lamberto on 29/10/17.
 * Pruebas unitarias para listar lineas. No solo de la clase Linea.
 */
public class LineaTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void obtenerLineasInternet() throws Exception {
        // TODO llamar a los metodos correspondientes a probar en todos los casos
        // U1a
        Linea linea = null;
        Assert.assertEquals("1",linea.getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA",linea.getName());
        Assert.assertEquals(1,linea.getIdentifier());
        // U1b
        linea = null;
        Assert.assertEquals("19",linea.getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA",linea.getName());
        Assert.assertEquals(19,linea.getIdentifier());
        // U1c
        linea = null;
        Assert.assertEquals("20",linea.getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE",linea.getName());
        Assert.assertEquals(20,linea.getIdentifier());
        // U1d
        List<Linea> lineasBus = null;
        Assert.assertEquals("1",lineasBus.get(0).getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA",lineasBus.get(0).getName());
        Assert.assertEquals(1,lineasBus.get(0).getIdentifier());
        Assert.assertEquals("2",lineasBus.get(1).getNumero());
        Assert.assertEquals("CORBAN-CONSUELO BERGES",lineasBus.get(1).getName());
        Assert.assertEquals(2,lineasBus.get(1).getIdentifier());
        Assert.assertEquals("19",lineasBus.get(2).getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA",lineasBus.get(2).getName());
        Assert.assertEquals(19,lineasBus.get(2).getIdentifier());
        Assert.assertEquals("20",lineasBus.get(3).getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE",lineasBus.get(3).getName());
        Assert.assertEquals(20,lineasBus.get(3).getIdentifier());
        // TODO terminar el metodo
    }

    @Test
    public void obtenerLineasBaseDatos() throws Exception {
        // TODO llamar a los metodos correspondientes a probar en todos los casos
        // U2a
        Linea linea = null;
        Assert.assertEquals("1",linea.getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA",linea.getName());
        Assert.assertEquals(1,linea.getIdentifier());
        // U2b
        linea = null;
        Assert.assertEquals("19",linea.getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA",linea.getName());
        Assert.assertEquals(19,linea.getIdentifier());
        // U2c
        linea = null;
        Assert.assertEquals("20",linea.getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE",linea.getName());
        Assert.assertEquals(20,linea.getIdentifier());
        // U2d
        List<Linea> lineasBus = null;
        Assert.assertEquals("1",lineasBus.get(0).getNumero());
        Assert.assertEquals("PCTCAN-VALDENOJA",lineasBus.get(0).getName());
        Assert.assertEquals(1,lineasBus.get(0).getIdentifier());
        Assert.assertEquals("2",lineasBus.get(1).getNumero());
        Assert.assertEquals("CORBAN-CONSUELO BERGES",lineasBus.get(1).getName());
        Assert.assertEquals(2,lineasBus.get(1).getIdentifier());
        Assert.assertEquals("19",lineasBus.get(2).getNumero());
        Assert.assertEquals("ESTACIONES-RICARDO L. ARANDA",lineasBus.get(2).getName());
        Assert.assertEquals(19,lineasBus.get(2).getIdentifier());
        Assert.assertEquals("20",lineasBus.get(3).getNumero());
        Assert.assertEquals("ESTACIONES-BARRIO LA TORRE",lineasBus.get(3).getName());
        Assert.assertEquals(20,lineasBus.get(3).getIdentifier());
        // TODO terminar el metodo
    }

}