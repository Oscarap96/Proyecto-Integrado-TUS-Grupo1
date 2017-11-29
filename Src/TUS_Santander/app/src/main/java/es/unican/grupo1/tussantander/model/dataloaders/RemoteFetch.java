package es.unican.grupo1.tussantander.model.dataloaders;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Clase en la que se realizan la descarga de los datos desde el servicio "REST"
 */
public class RemoteFetch {

    //URL para obtener un listado de las líneas de Bus de Santander (pero no de las sublineas)
    public static final String URL_LINEAS_BUS = "http://datos.santander.es/api/rest/datasets/lineas_bus.json";

    //URL para obtener un listado de la secuencia de paradas
    //http://datos.santander.es/resource/?ds=lineas-bus&id=bbfe898c-715b-4dfd-a418-a878d276f9fc&ft=JSON
    public static final String URL_SECUENCIA_PARADAS = "http://datos.santander.es/api/rest/datasets/lineas_bus_secuencia.json?items=5000";

    //URL para obtener un listado de todas las paradas de autobus
    public static final String URL_PARADAS_BUS = "http://datos.santander.es/api/rest/datasets/lineas_bus_paradas.json?items=2000";

    //URL para obtener información extendida sobre las paradas
    public static final String URL_PARADAS_INFO = "http://datos.santander.es/api/rest/datasets/paradas_bus.json?items=2300";

    //Estimacion del tiempo de llegada
    public static final String URL_ESTIMACION = "http://datos.santander.es/api/rest/datasets/control_flotas_estimaciones.json?items=5000";

    private BufferedInputStream bufferedData;

    /**
     * Metodo que a través de una dirección URL obtiene el bufferedInputStream correspondiente
     * al JSON alojado en el servidor y lo almacena en el atributo bufferedDataGasolineras de la
     * clase
     *
     * @throws IOException en caso de que no pueda descargar el json de internet
     */
    public void getJSON(String urlJSON) throws IOException {
        URL url = new URL(urlJSON);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.addRequestProperty("Accept", "application/json");
        bufferedData = new BufferedInputStream(urlConnection.getInputStream());
    }//getJSON

    /**
     * Comprueba si existe la base de datos en la ruta especificada.
     *
     * @param Database_path ruta a la base de datos
     * @param contexto      contexto
     * @return verdadero en caso de que exista
     */
    public boolean checkDataBase(String databasePath, Context contexto) {
        File database = contexto.getDatabasePath(databasePath);
        if (!database.exists()) {
            // Database does not exist so copy it from assets here
            Log.i("Database", "Not Found");
            return false;
        } else {
            Log.i("Database", "Found");
            return true;
        }
    }

    /**
     * Retorna el BufferedInputStream con el JSON, pero para que el objeto no este vacío debemos de
     * llamar antes a getJSON
     *
     * @return BufferedInputStream con el JSON
     */
    public BufferedInputStream getBufferedData() {
        return bufferedData;
    }//getBufferedData
}//RemoteFetch
