package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;


/**
 * Define los metodos basicos qeu tiene que implementar el presenter de lineas.
 */
public interface IListLineasPresenter {

    // TODO comentario
    boolean obtenLineas();

    // TODO comentario
    List<Linea> getListaLineasBus();

    // TODO comentario
    String getTextoLineas();
}
