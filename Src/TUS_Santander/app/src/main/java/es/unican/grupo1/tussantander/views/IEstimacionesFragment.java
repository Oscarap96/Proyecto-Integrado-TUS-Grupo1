package es.unican.grupo1.tussantander.views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;


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

    /**
     * Muestra un dialogo de carga.
     *
     * @param state true para mostrarlo, false para quitarlo
     */
    void showProgress(boolean state);

    /**
     * Muestra el mensaje de error por falta de Internet.
     */
    void showErrorMessage();

    /**
     * Observador del dialogo de carga.
     *
     * @return dialogo de carga
     */
    ProgressDialog getDialog();
}
