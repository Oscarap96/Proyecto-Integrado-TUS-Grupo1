package es.unican.grupo1.tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.DataLoaders.Data;
import es.unican.grupo1.tus_santander.Model.DataLoaders.RemoteFetch;
import es.unican.grupo1.tus_santander.Model.Estimacion;
import es.unican.grupo1.tus_santander.Views.IListEstimacionesView;

/**
 * Created by anon on 8/11/17.
 */

public class ListEstimacionesPresenter implements IListEstimacionesPresenter {
    private IListEstimacionesView listEstimacionesView;
    private List<Estimacion> listaEstimaciones;
    private RemoteFetch remoteFetch;
    private Context context;
    private int paradaId;

    // TODO
    public ListEstimacionesPresenter(Context context, IListEstimacionesView listEstimacionesView, int paradaId) {
        this.listEstimacionesView = listEstimacionesView;
        this.remoteFetch = new RemoteFetch();
        this.context = context;
        this.paradaId = paradaId;
    }

    // TODO
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
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listEstimacionesView.showList(getListaEstimacionesBus());
                listEstimacionesView.showProgress(false);
                Toast.makeText(context, "Datos obtenidos con éxito", Toast.LENGTH_SHORT).show();
            } else {
                listEstimacionesView.showProgress(false);
                listEstimacionesView.showErrorMessage();
            }
        }
    }

    // TODO
    public void start() {
        new RetrieveFeedTask().execute();
    }

    @Override
    public boolean obtenEstimaciones() {
        try {
            Data data = new Data();
            listaEstimaciones = data.descargarEstimaciones(paradaId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Estimacion> getListaEstimacionesBus() {
        return listaEstimaciones;
    }

    @Override
    public String getTextoEstimaciones() {
        // TODO
        return null;
    }
}
