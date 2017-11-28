package es.unican.grupo1.tussantander;

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

import es.unican.grupo1.tussantander.views.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * Created by Alberto on 08/11/2017.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class EstimacionesTestAceptacion {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Test que comprueba que se muestra un mensaje de error en caso
     * de que se intenten consultar las estimaciones de llegada
     * de un bus a una parada sin Internet.
     */
    @Test
    public void a1() {
        // Descomentarlo para probarlo.
        onView(withId(R.id.textViewMensajeError)).check(ViewAssertions.matches(checkMensajeError()));
    }

    /**
     * Método auxiliar que comprueba si el mensaje que sale
     * en la pantalla es igual al esperado.
     *
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
     * Test que comprueba que se muestra correctamente la interfaz al pulsar el boton atras
     * del dispositivo.
     */
    @Test
    public void a2(){
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(0).perform(click());
        pressBack();
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkParadas("328")));
    }
    private static Matcher<View> checkParadas(final String esperado) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                LinearLayout layout = (LinearLayout) item;
                TextView campo = (TextView) layout.findViewById(R.id.textView_idParada);
                String line = campo.getText().toString();
                String resultadoEsperado = esperado;
                return line.equals(resultadoEsperado);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El mensaje de error no coincide.");
            }
        };
    }
}