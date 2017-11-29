package es.unican.grupo1.tussantander.model.dataloaders;

import android.support.test.InstrumentationRegistry;

import junit.framework.Assert;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.R;


/**
 * Created by alejandro on 12/09/17.
 */

public class ParserUnitTest {
    /**
     * Test para comprobar el parseo del JSON correspondiente a las lineas de TUS Santander
     * @throws Exception
     */
    @Test
    public void testParserLineas() throws Exception {
        //Obtenemos el InputStream para el json almacenado en la carpeta raw del proyecto
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.lineas_test);

        List<Linea> listaLineas = ParserJSON.readArrayLineasBus(is);

        Assert.assertEquals(listaLineas.get(0).getNumero(),"20");
        Assert.assertEquals(listaLineas.get(0).getName(),"ESTACIONES-BARRIO LA TORRE");
        Assert.assertEquals(listaLineas.get(0).getIdentifier(),20);

        Assert.assertEquals(listaLineas.get(1).getNumero(),"19");
        Assert.assertEquals(listaLineas.get(1).getName(),"ESTACIONES-RICARDO L. ARANDA");
        Assert.assertEquals(listaLineas.get(1).getIdentifier(),19);

    }// testParserLineas
}// ParserUnitTest
