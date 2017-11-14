package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;


/**
 * Define los metodos minimos necesarios en el presenter de Estimaciones.
 */
public interface IListEstimacionesPresenter {


    boolean obtenEstimaciones();


    List<Estimacion> getListaEstimacionesBus();


    String getTextoEstimaciones();
}
