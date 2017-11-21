package es.unican.grupo1.tussantander.views;


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

import es.unican.grupo1.tussantander.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ParadaTestIntegracion {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void paradaTestIntegracion0() {
        onData(anything()).atPosition(29).perform(click());
        onData(anything()).atPosition(0).check(ViewAssertions.matches(checkParadas("224")));
    }

    @Test
    public void paradaTestIntegracion1() {
        onData(anything()).atPosition(29).perform(click());
        onData(anything()).atPosition(1).check(ViewAssertions.matches(checkParadas("225")));
    }

    @Test
    public void paradaTestIntegracion2() {
        onData(anything()).atPosition(29).perform(click());
        onData(anything()).atPosition(2).check(ViewAssertions.matches(checkParadas("9")));
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
