package es.unican.grupo1.tussantander.model.dataloaders;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.List;

import es.unican.grupo1.tussantander.model.Parada;
import es.unican.grupo1.tussantander.R;

/**
 * Created by pma11 on 30/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class ParadaTestUnitario {
    @Test
    public void leerArrayParadas() throws Exception {
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.paradas_test);
        List<Parada> paradasBus = ParserJSON.readArrayParadas(is);

        Assert.assertEquals(paradasBus.get(0).getNombre(), "Camarreal Peñacastillo");
        Assert.assertEquals(paradasBus.get(0).getIdentificador(), 42063);
        Assert.assertEquals(paradasBus.get(11).getNombre(), "La Albericia 18");
        Assert.assertEquals(paradasBus.get(11).getIdentificador(), 364);
        Assert.assertEquals(paradasBus.get(442).getNombre(), "Nuevo Parque");
        Assert.assertEquals(paradasBus.get(442).getIdentificador(), 100);
    }

   /*@Test
    public void obtenParadasTest() throws Exception{
        ParadasFragment p = new mock(ParadasFragment.class);
        ListParadasPresenter listParadas = new ListParadasPresenter(InstrumentationRegistry.getTargetContext(),p);
        Assert.assertEquals(listParadas.obtenParadas(),true);
        Assert.assertEquals(listParadas.getListParadasBus().size(),443);
        Assert.assertEquals(listParadas.getListParadasBus().get(0).getNombre(),"Camarreal Peñacastillo");
        Assert.assertEquals(listParadas.getListParadasBus().get(11).getNombre(),"La Albericia 18");
        Assert.assertEquals(listParadas.getListParadasBus().get(442).getNombre(),"Nuevo Parque");

        Assert.assertEquals(listParadas.getTextoParadas().length(),listParadas.getListParadasBus().size());
    }*/
}
