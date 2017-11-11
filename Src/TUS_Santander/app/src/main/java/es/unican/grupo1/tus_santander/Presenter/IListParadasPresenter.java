package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Define los metodos principales del presenter de paradas.
 */
public interface IListParadasPresenter {

    // TODO comentario
    boolean obtenParadas();

    // TODO comentario
    List<Parada> getListParadasBus();

    // TODO comentario
    String getTextoParadas();
}
