package es.unican.grupo1.tussantander.views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tussantander.model.Parada;


/**
 * Define los metodos del fragment de paradas.
 */
public interface IParadasFragment {

    /**
     * Mustra graficamente la lista de paradas haciendo uso del adapter.
     *
     * @param paradasList lista de paradas
     */
    void showList(List<Parada> paradasList);

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
