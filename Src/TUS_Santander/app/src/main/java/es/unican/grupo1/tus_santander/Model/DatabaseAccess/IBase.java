package es.unican.grupo1.tus_santander.Model.DatabaseAccess;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

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

    // TODO comentario
    List<Linea> obtenerLineas();

    // TODO comentario
    List<Parada> obtenerParadas();

    // TODO considerar poner metodos para anhadir listas de paradas y de lineas
    // TODO considerar poner metodos para modificar lineas y paradas

    // TODO quitar esta clase si es innecesaria o modificarla con los métodos correctos
}
