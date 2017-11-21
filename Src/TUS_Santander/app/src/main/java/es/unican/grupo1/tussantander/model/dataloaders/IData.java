package es.unican.grupo1.tussantander.model.dataloaders;

import java.io.IOException;
import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;

/**
 * Define los metodos que ofrece el paquete DataLoaders.
 */
public interface IData {

    /**
     * Descarga de Internet la lista de lineas.
     *
     * @return lista de lineas
     * @throws IOException en caso de que no tenga conexion a internet
     */
    List<Linea> descargarLineas() throws IOException;

    /**
     * Descarga la lista de paradas de Internet.
     *
     * @param identifierLinea linea de la que queremos obtener sus paradas
     * @return lista de paradas de una lina
     * @throws IOException en caso de que no tenga conexion a internet
     */
    List<Parada> descargarParadas(int identifierLinea) throws IOException;

    /**
     * Descarga una lista de estimaciones de Internet.
     *
     * @param paradaId parada de la que queremos obtener sus estimaciones
     * @return lista de estimaciones
     * @throws IOException en caso de que no tenga conexion a internet
     */
    List<Estimacion> descargarEstimaciones(int paradaId) throws IOException;
}
