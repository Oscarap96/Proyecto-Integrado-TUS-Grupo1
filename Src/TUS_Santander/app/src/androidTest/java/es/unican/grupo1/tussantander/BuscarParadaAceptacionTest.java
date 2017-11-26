package es.unican.grupo1.tussantander;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.grupo1.tussantander.views.MainActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static org.hamcrest.Matchers.anything;

/**
 * Created by lamberto on 26/11/17.
 * Pruebas de aceptacion siguiendo el plan de pruebas de Buscar parada de una linea.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class BuscarParadaAceptacionTest {
    private static int a1a5NumIni;
    private static int a1a5NumFin;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void a1a5() {
        onData(anything()).atPosition(0).perform(click());
        onView(encontrarBusqueda()).perform(typeText("PCTCAN"));
        onView(encontrarLista()).perform(click());
        onView(encontrarBusqueda()).perform(typeText(" 1"));
        onView(encontrarListaFin()).perform(click());
        Assert.assertTrue(a1a5NumIni != a1a5NumFin);
    }

    /**
     * Encuentra el campo de busqueda.
     *
     * @return TypeSafeMatcher
     */
    private static Matcher<View> encontrarBusqueda() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof EditText) {
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    /**
     * Encuentra la lista y anota el numerod e elementos inicial.
     *
     * @return TypeSafeMatcher
     */
    private static Matcher<View> encontrarLista() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof ListView) {
                    ListView lista = (ListView) item;
                    a1a5NumIni = lista.getAdapter().getCount();
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    /**
     * Encuentra la lista y anota el numero de elementos final.
     *
     * @return TypeSafeMatcher
     */
    private static Matcher<View> encontrarListaFin() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof ListView) {
                    ListView lista = (ListView) item;
                    a1a5NumFin = lista.getAdapter().getCount();
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void a1a6() {
        // No he podido acceder a menu item del boton de actualizar, por tanto no lo puedo pulsar.
        // Esta prueba se realizara de manera visual.
    }

    @Test
    public void a1a7() {
        onData(anything()).atPosition(0).perform(click());
        onView(encontrarBusqueda()).perform(typeText("CIRUELOS"));
        pressBack();
        // Compruebo uno de los elementos qeu tienen que estar en list view para comprobar que estoy en esa view
        onView(ViewMatchers.withId(R.id.textViewMensajeError)).check(ViewAssertions.matches(comprobarListView()));
    }

    /**
     * Ayuda a comrpobar si estamos en la list view.
     *
     * @return TypeSafeMatcher
     */
    private static Matcher<View> comprobarListView() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (((TextView) item).getText().equals("")) {
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void a1a8() {
        onData(anything()).atPosition(0).perform(click());
        onView(encontrarBusqueda()).perform(typeText("CIRUELOS"));
        onData(anything()).atPosition(0).perform(click());
        // compruebo uno de los elementos para saber que esta en la view de estimaciones
        onView(ViewMatchers.withId(R.id.textViewMensajeErrorEstimaciones)).check(ViewAssertions.matches(comprobarListEstimaciones()));
    }

    /**
     * Comprueba que estamos en la lista de estimaciones.
     *
     * @return TypeSafeMatcher
     */
    private static Matcher<View> comprobarListEstimaciones() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (((TextView) item).getText().equals("")) {
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void a1b2() {
        onData(anything()).atPosition(0).perform(click());
        onView(encontrarBusqueda()).perform(typeText("tomate"));
        onView(ViewMatchers.withId(R.id.textViewSinResultados)).check(ViewAssertions.matches(comprobarSinResultados()));
    }

    /**
     * Comprueba que aparece el mensaje de sin resultados.
     *
     * @return TypeSafeMatcher
     */
    private static Matcher<View> comprobarSinResultados() {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (((TextView) item).getText().equals("Sin resultados")) {
                    return true;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
