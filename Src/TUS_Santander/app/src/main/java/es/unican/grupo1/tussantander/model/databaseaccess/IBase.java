package es.unican.grupo1.tussantander.model.databaseaccess;

import java.util.List;

import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;

/**
 * Interfaz que tiene que cumplir la base de datos.
 */
public interface IBase {
    /**
     * Anhade una nueva linea a la base de datos.
     *
     * @param nuevaLinea
     * @return true en caso de que lo anhada correctamente, false en caso contrario
     */
    boolean anhadeLinea(Linea nuevaLinea);

    /**
     * Anhade una nueva parada a la base de datos.
     *
     * @param nuevaParada
     * @return true en caso de que lo anhada correctamente, false en caso contrario
     */
    boolean anhadeParada(Parada nuevaParada);


    List<Linea> obtenerLineas();


    List<Parada> obtenerParadas();
}
