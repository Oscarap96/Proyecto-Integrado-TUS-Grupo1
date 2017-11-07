package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

/**
 * Created by Oscar Alario Pelaz on 31/10/2017.
 */

public interface IListParadasPresenter {

    // TODO comentario
    boolean obtenParadas();

    // TODO comentario
    List<Parada> getListParadasBus();

    // TODO comentario
    String getTextoParadas();
}
