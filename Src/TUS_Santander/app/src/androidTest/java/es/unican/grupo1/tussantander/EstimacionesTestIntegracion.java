package es.unican.grupo1.tussantander;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.views.MainActivity;

import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.anything;

/**
 * Created by pma11 on 08/11/2017.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class EstimacionesTestIntegracion {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test que comprueba que se muestra un mensaje de error en caso
     * de que se intenten consultar las estimaciones de llegada
     * de un bus a una parada sin Internet.
     */
    @Test
    public void I1a() {
        // Descomentarlo para probarlo.
        //onView(withId(R.id.textViewMensajeError)).check(ViewAssertions.matches(checkMensajeError()));
    }

    /**
     * Método auxiliar que comprueba si el mensaje que sale
     * en la pantalla es igual al esperado.
     * @return true si el mensaje que aparece en la pantalla
     * es igual al mensaje esperado
     */
    public static Matcher<View> checkMensajeError() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                TextView campo = (TextView) item;
                String line = campo.getText().toString();
                String resultadoEsperado = "Internet no disponible. Inténtalo más tarde.";
                return line.equals(resultadoEsperado);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El mensaje de error no coincide.");
            }
        };
    }


    /**
     * Test que comprueba que se muestran los tiempos de llegada
     * a la parada Arsenio Odriozola 16 de la línea 1.
     */
    @Test
    public void I1ba() {
        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 328);
            Assert.assertEquals(0, estimaciones.get(0).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 328);
            Assert.assertEquals(16, estimaciones.get(0).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 328);
            Assert.assertEquals(7, estimaciones.get(1).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 328);
            Assert.assertEquals(-1, estimaciones.get(1).getTiempo2min());
        }catch(IOException e){

        }
    }

    /**
     * Test que comprueba que se muestran los tiempos de llegada
     * a la parada Corbán de la línea 2
     */
    @Test
    public void I1bb() {
        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(3, estimaciones.get(0).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(-1, estimaciones.get(0).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(13, estimaciones.get(1).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(33, estimaciones.get(1).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(17, estimaciones.get(2).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(-1, estimaciones.get(2).getTiempo2min());
        }catch(IOException e){

        }
    }

    /**
     * Test que comprueba que se muestran los tiempos de llegada
     * a la parada Los Ciruelos 27 de la línea 2.
     */
    @Test
    public void I1bc() {
        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(0, estimaciones.get(0).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(-1, estimaciones.get(0).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(0, estimaciones.get(1).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(-1, estimaciones.get(1).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(2, estimaciones.get(2).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(20, estimaciones.get(2).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(28, estimaciones.get(3).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(44, estimaciones.get(3).getTiempo2min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(59, estimaciones.get(4).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(78, estimaciones.get(4).getTiempo2min());
        }catch(IOException e){

        }
    }

    /**
     * Test que comprueba que las estimaciones de la línea 1
     * y la parada Arsenio Odriozola 16 salen
     * en orden decreciente en relación al tiempo.
     */
    @Test
    public void I1ca() throws Exception {
        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 328);
            Assert.assertEquals(0, estimaciones.get(0).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 328);
            Assert.assertEquals(7, estimaciones.get(1).getTiempo1min());
        }catch(IOException e){

        }
    }

    /**
     * Test que comprueba que las estimaciones de la línea 2
     * y la parada Corbán salen en orden decreciente en
     * relación al tiempo.
     */
    @Test
    public void I1cb() throws Exception {
        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(3, estimaciones.get(0).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(13, estimaciones.get(1).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 331);
            Assert.assertEquals(17, estimaciones.get(2).getTiempo1min());
        }catch(IOException e){

        }
    }
    /**
     * Test que comprueba que las estimaciones de la línea 2
     * y la parada Los Ciruelos 27 salen en orden decreciente
     * en relación al tiempo.
     */
    @Test
    public void I1cc() throws Exception {
        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(0, estimaciones.get(0).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(0, estimaciones.get(1).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(2, estimaciones.get(2).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(28, estimaciones.get(3).getTiempo1min());
        }catch(IOException e){

        }

        try {
            InputStream is = InstrumentationRegistry.getTargetContext().getResources().openRawResource(R.raw.control_flotas_estimaciones);
            List<Estimacion> estimaciones = ParserJSON.readArrayEstimaciones(is, 2);
            Assert.assertEquals(59, estimaciones.get(4).getTiempo1min());
        }catch(IOException e){

        }
    }
}
