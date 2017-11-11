package es.unican.grupo1.tus_santander.Model.DataLoaders;

import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

import static es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON.bd;
import static es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON.readArrayLineasBus;
import static es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON.readArrayParadas;

/**
 * Created by Adrian on 30/10/2017.
 */

public class FuncionesBBDD {
    //private RemoteFetch remoteFetchLineas;
    public void anhadeParadas(RemoteFetch remoteFetch) throws IOException {
        List<Parada> lista=readArrayParadas(remoteFetch.getBufferedData());
        for(int i=1;i<=lista.size();i++){
           bd.modificarParada(i,lista.get(i).getNombre(),lista.get(i).getIdentificador());
        }
        bd.close();
    }

    public void anhadeLineas(RemoteFetch remoteFetchLineas) throws IOException {
        List<Linea> lista=readArrayLineasBus(remoteFetchLineas.getBufferedData());
        for(int i=1;i<=lista.size();i++){
            bd.modificarParada(i,lista.get(i).getName(),lista.get(i).getIdentifier());

        }
        bd.close();
    }

    public static List<Parada> obtenerParadas() {
        return bd.recuperarParadas();
    }

    public static List<Linea> obtenerLineas() {
        return bd.recuperarLineas();
    }

    /**
     * Permite anhadir lineas a la bd desde un inputstream. Por ejemplo, para leer un archivo.
     */
    public static void anhadeLineasFromInputStream(InputStream in, BaseTUS baseDatos) throws IOException {
        List<Linea> lista=readArrayLineasBus(in);
        for(int i=1;i<=lista.size();i++){
            baseDatos.modificarParada(i,lista.get(i).getName(),lista.get(i).getIdentifier());
        }
        baseDatos.close();
    }
}
