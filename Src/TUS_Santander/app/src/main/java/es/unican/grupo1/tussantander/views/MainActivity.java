package es.unican.grupo1.tussantander.views;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import es.unican.grupo1.tussantander.R;
import es.unican.grupo1.tussantander.presenter.ListParadasPresenter;

import static java.security.AccessController.getContext;


/**
 * Actividad principal desde la que se inicia toda la aplicacion.
 */
public class MainActivity extends AppCompatActivity implements DataCommunication {
    private int lineaIdentifier;
    private int paradaIdentifier;
    // determina si hay que mostrar el boton de refrescar de la action bar
    private boolean mostrarBotonActualizar = true;
    // presenter de paradas
    private ListParadasPresenter paradasPresenter;

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
        // muestra u oculta el boton de actualizar
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

    @Override
    public void setParadasPresenter(ListParadasPresenter paradasPresenter) {
        this.paradasPresenter = paradasPresenter;
    }

    @Override
    public ListParadasPresenter getParadasPresenter() {
        return paradasPresenter;
    }

    /**
     * Método que en función del item del menú que se pulse realiza su acción correspondiente
     * @param item es el item que ha sido pulsado
     * @return true si el item se controla correctamente, false si no es así.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.tarifas_item:
                TarifasFragment fragmentTarifas = new TarifasFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayoutElements, fragmentTarifas);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}// MainActivity
