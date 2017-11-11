package es.unican.grupo1.tus_santander.Views;


import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import es.unican.grupo1.tus_santander.R;


public class MainActivity extends AppCompatActivity implements DataCommunication {
    private int lineaIdentifier;
    private int paradaIdentifier;
    // TODO
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
        // TODO
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
        invalidateOptionsMenu();
    }
}// MainActivity
