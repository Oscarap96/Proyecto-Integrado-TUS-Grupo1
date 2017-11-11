package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;


/**
 * Define los metodos del fragment de lineas.
 */
public interface ILineasFragment {

    // TODO comentario
    void showList(List<Linea> lineaList);

    // TODO comentario
    void showProgress(boolean state);

    // TODO comentario
    void showErrorMessage();

    ProgressDialog getDialog();
}//IListLineasView
