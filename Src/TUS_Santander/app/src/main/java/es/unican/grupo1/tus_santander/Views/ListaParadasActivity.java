package es.unican.grupo1.tus_santander.Views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import es.unican.grupo1.tus_santander.R;

/**
 * Created by Adrian on 30/10/2017.
 */

public class ListaParadasActivity extends FragmentActivity implements DataCommunication{
    private int lineaIdentifier;
    private int paradaIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paradas);
        ParadasFragment fragmentLineas = new ParadasFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =  fm.beginTransaction();
        ft.add(R.id.frameLayoutElements,fragmentLineas);
        ft.commit();
    }//onCreate

    @Override
    public int getLineaIdentifier() {
       return lineaIdentifier;
    }

    @Override
    public void setLineaIdentifier(int identifier) {
        lineaIdentifier=identifier;
    }

    @Override
    public int getParadaIdentifier() {
        return paradaIdentifier;
    }

    @Override
    public void setParadaIdentifier(int paradaIdentifier) {
        this.paradaIdentifier= paradaIdentifier;
    }

    @Override
    public void setMostrarBotonActualizar(boolean mostrar) {
        // TODO
    }
}
