package es.unican.grupo1.tus_santander.Model.DataLoaders;

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
    List<Linea> descargarLineas();

    // TODO comentario
    List<Parada> descargarParadas();

    // TODO comentario
    // TODO investigar si le hace falta pasar una parada como parametro
    List<Estimacion> descargarEstimaciones();

    // segun donde vaya la logica de primero descargar y luego usar la base de datos, a lo mejor hay que anhadir mas metodos aqui
}
