package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Define los metodos del fragment de paradas.
 */
public interface IParadasFragment {

    // TODO comentario
    void showList(List<Parada> paradasList);

    // TODO comentario
    void showProgress(boolean state);

    // TODO comentario
    void showErrorMessage();

    // TODO comentario
    ProgressDialog getDialog();
}
