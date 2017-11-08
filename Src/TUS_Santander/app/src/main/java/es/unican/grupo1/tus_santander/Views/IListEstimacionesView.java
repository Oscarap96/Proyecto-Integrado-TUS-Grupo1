package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;

/**
 * Created by anon on 7/11/17.
 */

public interface IListEstimacionesView {

    // TODO comentario
    void showList(List<Estimacion> estimacionesList);

    // TODO comentario
    void showProgress(boolean state);

    // TODO comentario
    // TODO comprobar si es necesario
    void showErrorMessage();

    // TODO comentario
    ProgressDialog getDialog();
}
