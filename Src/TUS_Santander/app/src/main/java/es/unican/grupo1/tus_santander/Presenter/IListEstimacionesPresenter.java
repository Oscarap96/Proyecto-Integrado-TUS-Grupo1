package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;

/**
 * Created by anon on 7/11/17.
 */

public interface IListEstimacionesPresenter {

    // TODO comentario
    boolean obtenEstimaciones();

    // TODO comentario
    List<Estimacion> getListaEstimacionesBus();

    // TODO comentario
    String getTextoEstimaciones();
}
