package es.unican.grupo1.tus_santander;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.grupo1.tus_santander.Views.EstimacionesActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by pma11 on 08/11/2017.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class EstimacionesTestIntegracion {
    @Rule
    public ActivityTestRule<EstimacionesActivity> mActivityTestRule = new ActivityTestRule<>(EstimacionesActivity.class);

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
     * a una parada de diferentes lineas
     */
    @Test
    public void I1b(){
        //Comprobación para la línea 1 y la parada Arsensio Odriozola
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacion1("1")));
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacion2("17")));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacion1("8")));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacion2("-1")));
        //Comprobación para la línea 2 y la parada Corbán
        onData(anything()).atPosition(1).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacion1("4")));
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacion2("-1")));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacion1("14")));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacion2("34")));
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkEstimacion1("18")));
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkEstimacion2("-1")));
        //Comprobación para la línea 2 y la parada Los Ciruelos 27
        onData(anything()).atPosition(1).perform(click());
        onData(anything()).atPosition(5).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacion1("0")));
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacion2("-1")));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacion1("0")));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacion2("-1")));
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkEstimacion1("2")));
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkEstimacion2("21")));
        onData(anything()).atPosition(3).check(ViewAssertions.matches(checkEstimacion1("29")));
        onData(anything()).atPosition(3).check(ViewAssertions.matches(checkEstimacion2("44")));
        onData(anything()).atPosition(4).check(ViewAssertions.matches(checkEstimacion1("59")));
        onData(anything()).atPosition(4).check(ViewAssertions.matches(checkEstimacion2("-79")));
    }

    /**
     * Test que comprueba que las estimaciones salen
     * en orden decreciente en relación al tiempo
     */
    @Test
    public void I1c() throws Exception{
        //Comprobación para la línea 1 y la parada Arsenio Odriozola
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacionOrden()));
        //Comprobación para la línea 2 y la parada Corbán
        onData(anything()).atPosition(1).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkEstimacionOrden()));
        //Comprobación para la línea 2 y la parada Los Ciruelos 27
        onData(anything()).atPosition(1).perform(click());
        onData(anything()).atPosition(5).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(3).check(ViewAssertions.matches(checkEstimacionOrden()));
        onData(anything()).atPosition(4).check(ViewAssertions.matches(checkEstimacionOrden()));
    }

    /**
     * Método auxiliar que comprueba si el primer tiempo esperado
     * del primer bus es igual al original.
     * @param esperado tiempo que se espera obtener
     * @return true si el tiempo esperado es correcto
     */
    private static Matcher<View> checkEstimacion1(final String esperado) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                LinearLayout layout = (LinearLayout) item;
                TextView campo = (TextView) layout.findViewById(R.id.textView_tiempoBus1);
                String time = campo.getText().toString();
                String resultadoEsperado = esperado;
                return time.equals(resultadoEsperado);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El mensaje de error no coincide.");
            }
        };
    }

    /**
     * Método auxiliar que comprueba si el segundo tiempo esperado
     * del primer bus es igual al original.
     * @param esperado tiempo que se espera obtener
     * @return true si el tiempo esperado es correcto
     */
    private static Matcher<View> checkEstimacion2(final String esperado) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                LinearLayout layout = (LinearLayout) item;
                TextView campo = (TextView) layout.findViewById(R.id.textView_tiempoBus2);
                String time = campo.getText().toString();
                String resultadoEsperado = esperado;
                return time.equals(resultadoEsperado);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El mensaje de error no coincide.");
            }
        };
    }

    /**
     * Método auxiliar que comprueba si las estimaciones
     * de llegada de los autobuses salen en orden.
     */
    private static Matcher<View> checkEstimacionOrden() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                LinearLayout layout = (LinearLayout) item;
                TextView campo1 = (TextView) layout.findViewById(R.id.textView_tiempoBus1);
                TextView campo2 = (TextView) layout.findViewById(R.id.textView_tiempoBus2);
                String time1 = campo1.getText().toString();
                String time2 = campo2.getText().toString();
                if(time1.compareTo(time2)<=0 || time2.isEmpty()){
                  return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El mensaje de error no coincide.");
            }
        };
    }
}
