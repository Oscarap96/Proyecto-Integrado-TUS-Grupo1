package es.unican.grupo1.tussantander;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;

import es.unican.grupo1.tussantander.views.MainActivity;
import es.unican.grupo1.tussantander.views.TarifasFragment;

/**
 * Created by ALberto de Castro on 24/11/2017.
 */

public class UnitariasTarifasTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void compruebaTituloInterfaz(){
        Assert.assertEquals("Tarifas 2017",mActivityTestRule.getActivity().getResources().getString(R.string.tituloTarifas));
    }
    @Test
    public void compruebaPrecioBilleteOrdinario(){

        Assert.assertEquals("1.30€",mActivityTestRule.getActivity().getResources().getString(R.string.precioBillete));

    }
    @Test
    public void compruebaPrecioTarjetaRecargable(){
        Assert.assertEquals("0.66€", mActivityTestRule.getActivity().getResources().getString(R.string.precioTarjeta));

    }
    @Test
    public void compruebaPrecioTarjetaFamiliaNumerosa(){
        Assert.assertEquals("Gratis", mActivityTestRule.getActivity().getResources().getString(R.string.precioFamNumerosa));

    }
    @Test
    public void compruebaPrecioNinhos(){
        Assert.assertEquals("Gratis", mActivityTestRule.getActivity().getResources().getString(R.string.precioNinhos));

    }
    @Test
    public void compruebaPrecioDiscapacitados(){
        Assert.assertEquals("Gratis", mActivityTestRule.getActivity().getResources().getString(R.string.precioDiscapacitados));

    }
    @Test
    public void compruebaTextoBotonMasInformacion(){
        Assert.assertEquals("Más Información", mActivityTestRule.getActivity().getResources().getString(R.string.masInfo));
    }

}
