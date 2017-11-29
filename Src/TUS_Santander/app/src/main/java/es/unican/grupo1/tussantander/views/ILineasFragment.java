package es.unican.grupo1.tussantander.views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tussantander.model.Linea;


/**
 * Define los metodos del fragment de lineas.
 */
public interface ILineasFragment {

    /**
     * Mustra graficamente la lista de lineas haciendo uso del adapter.
     *
     * @param lineaList lista de lineas
     */
    void showList(List<Linea> lineaList);

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
     * Esconde el mensaje de error por falta de Internet
     */
    void hideErrorMessage();

    /**
     * Observador del dialogo de carga.
     *
     * @return dialogo de carga
     */
    ProgressDialog getDialog();
}//IListLineasView
