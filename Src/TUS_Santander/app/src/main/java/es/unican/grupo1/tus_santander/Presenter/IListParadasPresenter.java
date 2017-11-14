package es.unican.grupo1.tus_santander.Presenter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Define los metodos principales del presenter de paradas.
 */
public interface IListParadasPresenter {

    /**
     * Almacena los datos en el atributo listaParadasBus. Para ello utiliza la base de datos si
     * esta disponible o descarga los datos de Internet si no esta disponible.
     *
     * @return true en caso de que lo haya obtenido con exito, false en caso contrario
     */
    boolean obtenParadas();

    /**
     * Observador de listaParadasBus.
     *
     * @return lista de paradas
     */
    List<Parada> getListParadasBus();

    /**
     * Metodo para obtener un cadena de texto con todas las paradas.
     *
     * @return String con todas las paradas
     */
    String getTextoParadas();
}
