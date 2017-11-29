package es.unican.grupo1.tussantander.model.dataloaders;

import java.io.IOException;
import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;

/**
 * Implementa los metodos principales de los DataLoaders.
 */
public class Data implements IData {
    private RemoteFetch remoteFetch = new RemoteFetch();

    @Override
    public List<Linea> descargarLineas() throws IOException {
        remoteFetch.getJSON(RemoteFetch.URL_LINEAS_BUS);
        return ParserJSON.readArrayLineasBus(remoteFetch.getBufferedData());
    }

    @Override
    public List<Parada> descargarParadas(int identifierLinea) throws IOException {
        remoteFetch.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
        return ParserJSON.readArraySecuenciaParadas(remoteFetch.getBufferedData(), identifierLinea);
    }

    @Override
    public List<Estimacion> descargarEstimaciones(int paradaId) throws IOException {
        remoteFetch.getJSON(RemoteFetch.URL_ESTIMACION);
        return ParserJSON.readArrayEstimaciones(remoteFetch.getBufferedData(), paradaId);
    }
}
