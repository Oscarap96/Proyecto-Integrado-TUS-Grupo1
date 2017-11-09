package es.unican.grupo1.tus_santander.Presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON;
import es.unican.grupo1.tus_santander.Model.DataLoaders.RemoteFetch;
import es.unican.grupo1.tus_santander.Model.Database.MisFuncionesBBDD;
import es.unican.grupo1.tus_santander.Model.Database.TUSSQLiteHelper;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Views.IListParadasView;


/**
 * Created by Adrian on 25/10/2017.
 */

public class ListParadasPresenter implements IListParadasPresenter {
    private IListParadasView listParadasView;
    private List<Parada> listaParadasBus;
    private List<Parada> lineasDeParadas;
    private RemoteFetch remoteFetchParadas;
    private int identifierLinea;
    private Context context;

    private static String DB_PATH = "/data/data/es.unican.grupo1.tus_santander/databases/DBTUS";

    public ListParadasPresenter(Context context, IListParadasView listParadasView, int identifierLinea) {
        this.listParadasView = listParadasView;
        this.remoteFetchParadas = new RemoteFetch();
        this.context = context;
        this.identifierLinea = identifierLinea;
    }// ListLineasPresenter

    public boolean obtenParadas() {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();

        if (remoteFetchParadas.checkDataBase(DB_PATH, context)) {
            Log.d("BBDD: ", "SI hay base de datos");

            //Si hemos abierto correctamente la base de datos
            if (db != null) {
                //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
                listaParadasBus = funciones.obtenerParadasLinea(identifierLinea, db);
                Log.d("Lista paradasLinea", "Tamano es: "+listaParadasBus.size());
            }
            db.close();
            Log.d("ENTRA", "Obtiene paradas de DB:" + listaParadasBus.size());
            return true;
        } else {
            try {
                Log.d("BBDD: ", "NO hay base de datos");
                //SE OBTIENEN LOS DATOS DE INTERNET...
                remoteFetchParadas.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
                listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetchParadas.getBufferedData(), identifierLinea);
                Log.d("ENTRA", "Obtiene paradas de linea de JSON:" + listaParadasBus.size());
                //Si hemos abierto correctamente la base de datos
                if (db != null) {
                    funciones.insertaListaParadas(listaParadasBus, db);
                }

                db.close();
                return true;
            } catch (IOException e) {
                return false;
            } catch (Exception e) {
                Log.e("ERROR", "Error en la obtención de las paradas de la linea: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    public String getTextoParadas() {
        String textoParadas = "";
        if (listaParadasBus != null) {
            for (int i = 0; i < listaParadasBus.size(); i++) {
                textoParadas = textoParadas + listaParadasBus.get(i).getNombre() + "\n\n";
            }
        } else {
            textoParadas = "";
        }
        return textoParadas;
    }

    public List<Parada> getListLineasParadas() {
        return lineasDeParadas;
    }

    public List<Parada> getListParadasBus() {
        return listaParadasBus;
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;

        @Override
        protected void onPreExecute() {
            //Muestra mensaje de cargando datos...
            listParadasView.showProgress(true);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            listParadasView.showList(getListParadasBus());
            listParadasView.showProgress(false);
            //Muestra el toast con el mensaje
            Toast toast1 = Toast.makeText(context, "Datos obtenidos con éxito", Toast.LENGTH_SHORT);
            toast1.show();
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return obtenParadas();
            } catch (Exception e) {
                this.exception = e;
                return null;
            }

        }

    }

    public void start() {
        new ListParadasPresenter.RetrieveFeedTask().execute();

    }// start


}
