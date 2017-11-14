package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;


/**
 * Define los metodos del fragment de estimaciones
 */
public interface IEstimacionesFragment {

    /**
     * Mustra graficamente la lista de estimaciones haciendo uso del adapter.
     *
     * @param estimacionesList lista de estimaciones
     */
    void showList(List<Estimacion> estimacionesList);

    // TODO comentario
    void showProgress(boolean state);

    // TODO comentario
    void showErrorMessage();

    // TODO comentario
    ProgressDialog getDialog();
}
