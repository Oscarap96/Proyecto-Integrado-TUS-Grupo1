package es.unican.grupo1.tus_santander.Model.DataLoaders;

import java.io.IOException;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

/**
 * Created by anon on 7/11/17.
 */

public interface IData {
    // estas dos de descargar usaran la clase remoteFetch y parserJson

    // TODO comentario, tambien ordenarlas
    List<Linea> descargarLineas() throws IOException;

    // TODO comentario
    List<Parada> descargarParadas(int identifierLinea) throws IOException;

    // TODO comentario
    List<Estimacion> descargarEstimaciones(int paradaId) throws IOException;

    // segun donde vaya la logica de primero descargar y luego usar la base de datos, a lo mejor hay que anhadir mas metodos aqui
}
