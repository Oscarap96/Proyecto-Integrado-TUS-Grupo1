package es.unican.grupo1.tussantander.views;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import es.unican.grupo1.tussantander.R;


/**
 * Actividad principal desde la que se inicia toda la aplicacion.
 */
public class MainActivity extends AppCompatActivity implements DataCommunication {
    private int lineaIdentifier;
    private int paradaIdentifier;
    // determina si hay que mostrar el boton de refrescar de la action bar
    private boolean mostrarBotonActualizar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineasFragment fragmentLineas = new LineasFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameLayoutElements, fragmentLineas);
        ft.commit();
    }//onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // mustra u oculta el boton de actualizar
        MenuItem item = menu.findItem(R.id.refresh_item);
        item.setVisible(mostrarBotonActualizar);

        return true;
    }

    @Override
    public int getLineaIdentifier() {
        return lineaIdentifier;
    }

    @Override
    public void setLineaIdentifier(int identifier) {
        this.lineaIdentifier = identifier;
    }

    @Override
    public int getParadaIdentifier() {
        return paradaIdentifier;
    }

    @Override
    public void setParadaIdentifier(int paradaIdentifier) {
        this.paradaIdentifier = paradaIdentifier;
    }

    @Override
    public void setMostrarBotonActualizar(boolean mostrar) {
        this.mostrarBotonActualizar = mostrar;
        // vuelve a llamar a onCreateOptionsMenu despues de invalidar la action bar
        invalidateOptionsMenu();
    }
}// MainActivity
