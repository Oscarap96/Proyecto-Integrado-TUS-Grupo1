package es.unican.grupo1.tussantander.presenter;

import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;


/**
 * Define los metodos minimos necesarios en el presenter de Estimaciones.
 */
public interface IListEstimacionesPresenter {

    /**
     * Almacena los datos en el atributo listaEstimaciones. Para ello siempre descarga los datos de
     * Internet y los procesa con el parser del json.
     *
     * @return true en caso de que lo haya obtenido con exito, false en caso contrario
     */
    boolean obtenEstimaciones();

    /**
     * Observador de listaEstimaciones.
     *
     * @return lista de estimaciones
     */
    List<Estimacion> getListaEstimacionesBus();

    /**
     * Metodo para obtener un cadena de texto con todas las estimaciones.
     *
     * @return String con todas las estimaciones
     */
    String getTextoEstimaciones();
}
