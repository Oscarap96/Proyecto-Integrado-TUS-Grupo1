package es.unican.grupo1.tussantander;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tussantander.model.Parada;
import es.unican.grupo1.tussantander.utils.Utilidades;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Clase con las pruebas unitarias correspondientes
 * a buscar paradas.
 * Created by pma11 on 22/11/2017.
 */
public class BuscarParadaUnitariasTest {
    /**
     * Test que comprueba si al introducir el
     * texto "LOS CIRUELOS", devuelve las paradas correctas.
     */
    @Test
    public void u1a1() {
        List<Parada> paradasLinea1 = new ArrayList<>();
        Parada p1 = new Parada("LOS CIRUELOS 26", 52, 1);
        Parada p2 = new Parada("LOS CIRUELOS 47", 1, 1);
        Parada p3 = new Parada("LOS CIRUELOS 27", 2, 1);
        Parada p4 = new Parada("LOS CIRUELOS 26", 52, 1);
        Parada p5 = new Parada("LOS CIRUELOS 47", 1, 1);
        Parada p6 = new Parada("LOS CIRUELOS 27", 2, 1);
        paradasLinea1.add(p1);
        paradasLinea1.add(p2);
        paradasLinea1.add(p3);
        paradasLinea1.add(p4);
        paradasLinea1.add(p5);
        paradasLinea1.add(p6);

        assertEquals(52, Utilidades.buscarParada(paradasLinea1, "LOS CIRUELOS").get(0).getIdentificador());
        assertEquals(1, Utilidades.buscarParada(paradasLinea1, "LOS CIRUELOS").get(1).getIdentificador());
        assertEquals(2, Utilidades.buscarParada(paradasLinea1, "LOS CIRUELOS").get(2).getIdentificador());
        assertEquals(52, Utilidades.buscarParada(paradasLinea1, "LOS CIRUELOS").get(3).getIdentificador());
        assertEquals(1, Utilidades.buscarParada(paradasLinea1, "LOS CIRUELOS").get(4).getIdentificador());
        assertEquals(2, Utilidades.buscarParada(paradasLinea1, "LOS CIRUELOS").get(5).getIdentificador());
    }

    /**
     * Test que comprueba si al introducir el
     * texto "VALDENOJA 25", devuelve las paradas correctas.
     */
    @Test
    public void u1a2() {
        List<Parada> paradasLinea1 = new ArrayList<>();
        Parada p1 = new Parada("VALDENOJA 25", 168, 1);
        Parada p2 = new Parada("VALDENOJA 25", 168, 1);
        paradasLinea1.add(p1);
        paradasLinea1.add(p2);

        assertEquals(168, Utilidades.buscarParada(paradasLinea1, "VALDENOJA 25").get(0).getIdentificador());
        assertEquals(168, Utilidades.buscarParada(paradasLinea1, "VALDENOJA 25").get(1).getIdentificador());
    }

    /**
     * Test que comprueba si al introducir el
     * texto "168 VALDENOJA", devuelve las paradas correctas.
     */
    @Test
    public void u1a3() {
        List<Parada> paradasLinea1 = new ArrayList<>();
        Parada p1 = new Parada("VALDENOJA 25", 168, 1);
        Parada p2 = new Parada("VALDENOJA 25", 168, 1);
        paradasLinea1.add(p1);
        paradasLinea1.add(p2);

        assertEquals(168, Utilidades.buscarParada(paradasLinea1, " 168 VALDENOJA ").get(0).getIdentificador());
        assertEquals(168, Utilidades.buscarParada(paradasLinea1, "168 VALDENOJA ").get(1).getIdentificador());
    }

    /**
     * Test que comprueba si al introducir
     * el texto los, devuelve las paradas correctas.
     */
    @Test
    public void u1a4() {
        List<Parada> paradasLinea1 = new ArrayList<>();
        Parada p1 = new Parada("LOS AGUSTINOS", 28, 1);
        Parada p2 = new Parada("LOS CIRUELOS 26", 52, 1);
        Parada p3 = new Parada("LOS CIRUELOS 47", 1, 1);
        Parada p4 = new Parada("LOS CIRUELOS 27", 2, 1);
        Parada p5 = new Parada("LOS AGUSTINOS", 196, 1);
        Parada p6 = new Parada("LLOS AGUSTINOS", 28, 1);
        Parada p7 = new Parada("LOS CIRUELOS 26", 52, 1);
        Parada p8 = new Parada("LOS CIRUELOS 47", 1, 1);
        Parada p9 = new Parada("LOS CIRUELOS 27", 2, 1);
        Parada p10 = new Parada("LOS AGUSTINO", 196, 1);
        paradasLinea1.add(p1);
        paradasLinea1.add(p2);
        paradasLinea1.add(p3);
        paradasLinea1.add(p4);
        paradasLinea1.add(p5);
        paradasLinea1.add(p6);
        paradasLinea1.add(p7);
        paradasLinea1.add(p8);
        paradasLinea1.add(p9);
        paradasLinea1.add(p10);

        assertEquals(28, Utilidades.buscarParada(paradasLinea1, "los").get(0).getIdentificador());
        assertEquals(52, Utilidades.buscarParada(paradasLinea1, "los").get(1).getIdentificador());
        assertEquals(1, Utilidades.buscarParada(paradasLinea1, "los").get(2).getIdentificador());
        assertEquals(2, Utilidades.buscarParada(paradasLinea1, "los").get(3).getIdentificador());
        assertEquals(196, Utilidades.buscarParada(paradasLinea1, "los").get(4).getIdentificador());
        assertEquals(28, Utilidades.buscarParada(paradasLinea1, "los").get(5).getIdentificador());
        assertEquals(52, Utilidades.buscarParada(paradasLinea1, "los").get(6).getIdentificador());
        assertEquals(1, Utilidades.buscarParada(paradasLinea1, "los").get(7).getIdentificador());
        assertEquals(2, Utilidades.buscarParada(paradasLinea1, "los").get(8).getIdentificador());
        assertEquals(196, Utilidades.buscarParada(paradasLinea1, "los").get(9).getIdentificador());
    }

    /**
     * Test que comprueba si al introducir el
     * texto "tomates", no devuelve paradas.
     */
    @Test
    public void u1a5() throws Exception{
        List<Parada> paradasLinea1 = new ArrayList<>();
        Parada p1 = new Parada("LOS CIRUELOS 26", 52, 1);
        Parada p2 = new Parada("LOS CIRUELOS 47", 1, 1);
        Parada p3 = new Parada("LOS CIRUELOS 27", 2, 1);
        paradasLinea1.add(p1);
        paradasLinea1.add(p2);
        paradasLinea1.add(p3);

        assertTrue(Utilidades.buscarParada(paradasLinea1, "tomates").isEmpty());
    }
}
