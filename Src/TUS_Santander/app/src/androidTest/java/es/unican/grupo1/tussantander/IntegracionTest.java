package es.unican.grupo1.tussantander;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unican.grupo1.tussantander.views.MainActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

/**
 * Created by lamberto on 1/11/17.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntegracionTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Solo pasa este test en caso de que el movil no tenga conexion a internet.
     */
    @Test
    public void i1a() {
        // Descomentarlo para probarlo.
        // onView(withId(R.id.textViewMensajeError)).check(ViewAssertions.matches(checkMensajeError()));
    }

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

    @Test
    public void i1b0() {
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkLineas(0, 1)));
    }

    @Test
    public void i1b1() {
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkLineas(0, 2)));
    }

    @Test
    public void i1b2() {
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkLineas(0, 3)));
    }

    @Test
    public void i1b3() {
        onData(anything()).atPosition(3).check(ViewAssertions.matches(checkLineas(0, 4)));
    }

    @Test
    public void i1b4() {
        onData(anything()).atPosition(4).check(ViewAssertions.matches(checkLineas(0, 11)));
    }

    @Test
    public void i1b5() {
        onData(anything()).atPosition(5).check(ViewAssertions.matches(checkLineas(0, 12)));
    }

    @Test
    public void i1b6() {
        onData(anything()).atPosition(6).check(ViewAssertions.matches(checkLineas(0, 13)));
    }

    @Test
    public void i1b7() {
        onData(anything()).atPosition(7).check(ViewAssertions.matches(checkLineas(0, 14)));
    }

    @Test
    public void i1b8() {
        onData(anything()).atPosition(8).check(ViewAssertions.matches(checkLineas(0, 15)));
    }

    @Test
    public void i1b9() {
        onData(anything()).atPosition(9).check(ViewAssertions.matches(checkLineas(0, 16)));
    }

    @Test
    public void i1b10() {
        onData(anything()).atPosition(10).check(ViewAssertions.matches(checkLineas(0, 17)));
    }

    @Test
    public void i1b11() {
        onData(anything()).atPosition(11).check(ViewAssertions.matches(checkLineas(0, 18)));
    }

    @Test
    public void i1b12() {
        onData(anything()).atPosition(12).check(ViewAssertions.matches(checkLineas(0, 19)));
    }

    @Test
    public void i1b13() {
        onData(anything()).atPosition(13).check(ViewAssertions.matches(checkLineas(0, 20)));
    }

    @Test
    public void i1b14() {
        onData(anything()).atPosition(14).check(ViewAssertions.matches(checkLineas(0, 21)));
    }

    @Test
    public void i1b15() {
        onData(anything()).atPosition(15).check(ViewAssertions.matches(checkLineas(0, 23)));
    }

    @Test
    public void i1b16() {
        onData(anything()).atPosition(16).check(ViewAssertions.matches(checkLineas(0, 30)));
    }

    @Test
    public void i1b17() {
        onData(anything()).atPosition(17).check(ViewAssertions.matches(checkLineas(0, 31)));
    }

    @Test
    public void i1b18() {
        onData(anything()).atPosition(18).check(ViewAssertions.matches(checkLineas(0, 41)));
    }

    @Test
    public void i1b19() {
        onData(anything()).atPosition(19).check(ViewAssertions.matches(checkLineas(0, 42)));
    }

    @Test
    public void i1b20() {
        onData(anything()).atPosition(20).check(ViewAssertions.matches(checkLineas(0, 43)));
    }

    @Test
    public void i1b21() {
        onData(anything()).atPosition(21).check(ViewAssertions.matches(checkLineas(0, 44)));
    }

    @Test
    public void i1b22() {
        onData(anything()).atPosition(22).check(ViewAssertions.matches(checkLineas(0, 45)));
    }

    @Test
    public void i1b23() {
        onData(anything()).atPosition(23).check(ViewAssertions.matches(checkLineas(0, 49)));
    }

    @Test
    public void i1b24() {
        onData(anything()).atPosition(24).check(ViewAssertions.matches(checkLineas(0, 51)));
    }

    @Test
    public void i1b25() {
        onData(anything()).atPosition(25).check(ViewAssertions.matches(checkLineas(0, 52)));
    }

    @Test
    public void i1b26() {
        onData(anything()).atPosition(26).check(ViewAssertions.matches(checkLineas(0, 61)));
    }

    @Test
    public void i1b27() {
        onData(anything()).atPosition(27).check(ViewAssertions.matches(checkLineas(0, 62)));
    }

    @Test
    public void i1b28() {
        onData(anything()).atPosition(28).check(ViewAssertions.matches(checkLineas(0, 71)));
    }

    @Test
    public void i1b29() {
        onData(anything()).atPosition(29).check(ViewAssertions.matches(checkLineas(0, 72)));
    }

    @Test
    public void i1b30() {
        onData(anything()).atPosition(30).check(ViewAssertions.matches(checkLineas(0, 101)));
    }

    @Test
    public void i1b31() {
        onData(anything()).atPosition(31).check(ViewAssertions.matches(checkLineas(0, 102)));
    }

    @Test
    public void i1b32() {
        onData(anything()).atPosition(32).check(ViewAssertions.matches(checkLineas(0, 103)));
    }

    public static Matcher<View> checkLineas(final int pos, final int esperado) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                RelativeLayout layout = (RelativeLayout) item;
                TextView campo = (TextView) layout.getChildAt(pos);
                String line = campo.getText().toString();
                String resultadoEsperado = "" + esperado;
                return line.equals(resultadoEsperado);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("El mensaje de error no coincide.");
            }
        };
    }
}
