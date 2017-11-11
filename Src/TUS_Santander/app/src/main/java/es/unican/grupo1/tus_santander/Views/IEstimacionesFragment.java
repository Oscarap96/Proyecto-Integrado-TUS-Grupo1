package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;


/**
 * Define los metodos del fragment de estimaciones
 */
public interface IEstimacionesFragment {

    // TODO comentario
    void showList(List<Estimacion> estimacionesList);

    // TODO comentario
    void showProgress(boolean state);

    // TODO comentario
    void showErrorMessage();

    // TODO comentario
    ProgressDialog getDialog();
}
