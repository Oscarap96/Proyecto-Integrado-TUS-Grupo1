package es.unican.grupo1.tussantander.presenter;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import es.unican.grupo1.tussantander.R;
import es.unican.grupo1.tussantander.model.dataloaders.Data;
import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.utils.Utilidades;
import es.unican.grupo1.tussantander.views.IEstimacionesFragment;


/**
 * Presenter de estimaciones. Se encarga de la logica entre la interfaz de las estimaciones y el
 * modelo.
 */
public class ListEstimacionesPresenter implements IListEstimacionesPresenter {
    private IEstimacionesFragment listEstimacionesView;
    private List<Estimacion> listaEstimaciones;
    private Context context;
    // Numero de la parada correspondiente a las estimaciones
    private int paradaId;

    /**
     * Constructor.
     *
     * @param context              contexto de la app
     * @param listEstimacionesView view utilizada en las estimaciones
     * @param paradaId             numero de parada de la que queremos obtener sus estimaciones
     */
    public ListEstimacionesPresenter(Context context, IEstimacionesFragment listEstimacionesView, int paradaId) {
        this.listEstimacionesView = listEstimacionesView;
        this.context = context;
        this.paradaId = paradaId;
    }

    /**
     * Clase privada para hacer una tarea asincrona al descargar los datos.
     */
    private class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            listEstimacionesView.getDialog().setCancelable(false);
            listEstimacionesView.showProgress(true);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return obtenEstimaciones();
            } catch (Exception e) {
                Log.e("ERROR","Error en la obtención de las estimaciones");
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listEstimacionesView.hideErrorMessage();
                listEstimacionesView.showList(listaEstimaciones); // Se muestra la lista de estimaciones
                listEstimacionesView.showProgress(false);
                Toast.makeText(context, "Datos obtenidos con éxito", Toast.LENGTH_SHORT).show();
            } else {
                listEstimacionesView.hideList(); // Se esconde la lista de estimaciones anterior
                listEstimacionesView.showProgress(false);
                listEstimacionesView.showErrorMessage();

                Toast toast = Toast.makeText(context,"No hay Internet",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    /**
     * Inicia la tarea asincrona.
     */
    public void start() {
        new RetrieveFeedTask().execute();
    }

    @Override
    public boolean obtenEstimaciones() {
        if(Utilidades.isOnline(context)) {
            try {
                Data data = new Data();
                Log.e("descargando", "descargando estimaciones");
                listaEstimaciones = data.descargarEstimaciones(paradaId);
                Log.e("NO ERROR", "SIN error en la descarga de estimaciones");
                return true;
            } catch (Exception e) {
                Log.e("ERROR", "Error en la descarga de estimaciones");
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Estimacion> getListaEstimacionesBus() {
        return listaEstimaciones;
    }

    @Override
    public String getTextoEstimaciones() {

        return null;
    }
}
