package es.unican.grupo1.tussantander.model;

import android.support.test.InstrumentationRegistry;

import junit.framework.Assert;

import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.grupo1.tussantander.model.dataloaders.Data;
import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.R;


/**
 * Created by Adrian on 09/11/2017.
 */

public class UnitariasEstimacionesTest {
    private static String dbPath = "/data/data/es.unican.grupo1.tus_santander/databases/DBTUS";

    @Test
    public void readArrayEstimacionesTest() throws IOException {
        InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
        List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is,1);
        //U1.A
        for(int i=0;i<estimaciones.size();i++){
            System.out.print("Las estimaciones " + i + "son" + estimaciones.get(i).getTiempo1min() + "y" + estimaciones.get(i).getTiempo2min());
        }
        Assert.assertEquals(estimaciones.get(0).getTiempo1min(),0);//27
        Assert.assertEquals(estimaciones.get(0).getTiempo2min(),-1);//42
        Assert.assertEquals(estimaciones.get(estimaciones.size()-1).getTiempo1min(),27);//0
        Assert.assertEquals(estimaciones.get(estimaciones.size()-1).getTiempo2min(),42);//59
        //U1.B
        is=InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
       List<Estimacion> estimaciones1 = ParserJSON.readArrayEstimaciones(is,2);
        Assert.assertEquals(estimaciones1.get(0).getTiempo1min(),0);//27
        Assert.assertEquals(estimaciones1.get(0).getTiempo2min(),-1);//42
        Assert.assertEquals(estimaciones1.get(1).getTiempo1min(),0);//27
        Assert.assertEquals(estimaciones1.get(1).getTiempo2min(),-1);
        Assert.assertEquals(estimaciones1.get(estimaciones1.size()-1).getTiempo1min(),59);//0
        Assert.assertEquals(estimaciones1.get(estimaciones1.size()-1).getTiempo2min(),78);//59

        //U1.C
        is=InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
        List<Estimacion>estimaciones2 = ParserJSON.readArrayEstimaciones(is,4);
        Assert.assertEquals(estimaciones2.get(0).getTiempo1min(),0);//27
        Assert.assertEquals(estimaciones2.get(0).getTiempo2min(),-1);//42
        Assert.assertEquals(estimaciones2.get(1).getTiempo1min(),0);//27
        Assert.assertEquals(estimaciones2.get(1).getTiempo2min(),-1);
        Assert.assertEquals(estimaciones2.get(estimaciones2.size()-1).getTiempo1min(),59);//0
        Assert.assertEquals(estimaciones2.get(estimaciones2.size()-1).getTiempo2min(),79);//59
        //U1.D
        is=InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
        List<Estimacion>estimaciones3 = ParserJSON.readArrayEstimaciones(is,26);
        Assert.assertEquals(estimaciones3.get(0).getTiempo1min(),4);//27
        Assert.assertEquals(estimaciones3.get(0).getTiempo2min(),22);//42
        //U1.E
        is=InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
        List<Estimacion>estimaciones4 = ParserJSON.readArrayEstimaciones(is,87);
        Assert.assertEquals(estimaciones4.get(0).getTiempo1min(),0);//27
        Assert.assertEquals(estimaciones4.get(0).getTiempo2min(),-1);//42
        Assert.assertEquals(estimaciones4.get(1).getTiempo1min(),16);//27
        Assert.assertEquals(estimaciones4.get(1).getTiempo2min(),37);
        Assert.assertEquals(estimaciones4.get(estimaciones4.size()-1).getTiempo1min(),80);//0
        Assert.assertEquals(estimaciones4.get(estimaciones4.size()-1).getTiempo2min(),-1);//59
    }

    @Test
    public void descargaEstimacionesTest() throws IOException {
        List<Estimacion>estimaciones;
        Data data= new Data();
        estimaciones=data.descargarEstimaciones(1);
        Assert.assertEquals(estimaciones.size(),5);
        estimaciones=data.descargarEstimaciones(2);
        Assert.assertEquals(estimaciones.size(),5);
        estimaciones=data.descargarEstimaciones(3);
        Assert.assertEquals(estimaciones.size(),5);
        estimaciones=data.descargarEstimaciones(101);
        Assert.assertEquals(estimaciones.size(),2);
        estimaciones=data.descargarEstimaciones(200);
        Assert.assertEquals(estimaciones.size(),7);
        List<Estimacion>estimaciones1;
        estimaciones1=data.descargarEstimaciones(20000);
        Assert.assertEquals(estimaciones1.size(),0);
    }
}