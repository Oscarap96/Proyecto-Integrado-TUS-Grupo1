package es.unican.grupo1.tus_santander.Model.DataLoaders;

import java.io.InputStream;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

/**
 * Created by anon on 7/11/17.
 */

public interface IParserJSON {

    // TODO comentario
    List<Linea> readArrayLineasBus(InputStream in);

    // TODO comentario
    List<Parada> readArrayParadas(InputStream in);

    // TODO comentario
    List<Parada> readArraySecuenciaParadas(InputStream in, int identifierLinea);

    // TODO comentario
    List<Estimacion> readArrayEstimaciones(InputStream in);
}
