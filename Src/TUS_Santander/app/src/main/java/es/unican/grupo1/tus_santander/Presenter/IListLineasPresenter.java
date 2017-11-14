package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;


/**
 * Define los metodos basicos qeu tiene que implementar el presenter de lineas.
 */
public interface IListLineasPresenter {


    boolean obtenLineas();


    List<Linea> getListaLineasBus();


    String getTextoLineas();
}
