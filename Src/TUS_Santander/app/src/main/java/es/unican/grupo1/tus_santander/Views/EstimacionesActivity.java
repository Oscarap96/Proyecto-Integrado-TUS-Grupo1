package es.unican.grupo1.tus_santander.Views;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import es.unican.grupo1.tus_santander.R;

/**
 * Created by pma11 on 07/11/2017.
 */

public class EstimacionesActivity extends FragmentActivity implements DataCommunication {
    private int lineaIdentifier;
    private int paradaIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimaciones);
        EstimacionesFragment fragmentEstimaciones = new EstimacionesFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameLayoutElements, fragmentEstimaciones);
        ft.commit();
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
}
