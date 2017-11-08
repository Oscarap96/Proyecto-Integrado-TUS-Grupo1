package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;

/**
 * Created by anon on 8/11/17.
 */

public class ListEstimacionesPresenter implements IListEstimacionesPresenter {

    @Override
    public boolean obtenEstimaciones() {
        return false;
    }

    @Override
    public List<Estimacion> getListaEstimacionesBus() {
        return null;
    }

    @Override
    public String getTextoEstimaciones() {
        return null;
    }
}
