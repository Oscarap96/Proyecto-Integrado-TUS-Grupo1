package es.unican.grupo1.tussantander.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import es.unican.grupo1.tussantander.utils.Utilidades;
import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.model.dataloaders.RemoteFetch;
import es.unican.grupo1.tussantander.model.databaseaccess.MisFuncionesBBDD;
import es.unican.grupo1.tussantander.model.databaseaccess.TUSSQLiteHelper;
import es.unican.grupo1.tussantander.model.Parada;
import es.unican.grupo1.tussantander.R;
import es.unican.grupo1.tussantander.views.IParadasFragment;


/**
 * Presenter de paradas. Se encarga de la logica entre la interfaz de paradas y el modelo.
 */
public class ListParadasPresenter implements IListParadasPresenter {
    private IParadasFragment listParadasView;
    private List<Parada> listaParadasBus;
    private RemoteFetch remoteFetchParadas;
    private int identifierLinea;
    private Context context;

    private static String dbPath = "/data/data/es.unican.grupo1.tus_santander/databases/DBTUS";

    /**
     * Constructor.
     *
     * @param context         contexto de la app
     * @param listParadasView view de las paradas
     * @param identifierLinea numero de la linea correspondiente a estas paradas
     */
    public ListParadasPresenter(Context context, IParadasFragment listParadasView, int identifierLinea) {
        this.listParadasView = listParadasView;
        this.remoteFetchParadas = new RemoteFetch();
        this.context = context;
        this.identifierLinea = identifierLinea;
    }// ListLineasPresenter

    @Override
    public boolean obtenParadas() {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();

        if (remoteFetchParadas.checkDataBase(dbPath, context)) {
            Log.d("BBDD: ", "SI hay base de datos");

            //Si hemos abierto correctamente la base de datos
            if (db != null) {
                //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
                listaParadasBus = funciones.obtenerParadasLinea(identifierLinea, db);
                Log.d("Lista paradasLinea", "Tamano es: " + listaParadasBus.size());
            }
            if(db == null) throw new NullPointerException();

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
                if(db == null) throw new NullPointerException();

                db.close();
                return true;
            } catch (IOException e) {
                return false;
            } catch (Exception e) {
                Log.e("ERROR", "Error en la obtención de las paradas de la linea: " + e.getMessage());
                return false;
            }
        }
    }

    @Override
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

    @Override
    public List<Parada> getListParadasBus() {
        return listaParadasBus;
    }

    /**
     * Clase para hacer la tarea asincrona y obtener los datos correspondientes.
     */
    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            listParadasView.getDialog().setCancelable(false);
            //Muestra mensaje de cargando datos...
            listParadasView.showProgress(true);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listParadasView.showList(getListParadasBus());
                listParadasView.showProgress(false);
                //Muestra el toast con el mensaje
                Toast toast1 = Toast.makeText(context, R.string.mensajeToast1, Toast.LENGTH_SHORT);
                toast1.show();
            } else {
                listParadasView.showProgress(false);
                listParadasView.showErrorMessage();
            }
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return obtenParadas();
            } catch (Exception e) {
                Log.d("ERROR", "No hay conexion a Internet");
                return false;
            }
        }
    }

    /**
     * Inicia la tarea asincrona.
     */
    public void start() {
        new RetrieveFeedTask().execute();
    }// start

    // TODO
    public void buscar(String busqueda) {
        List<Parada> resultados = Utilidades.buscarParada(listaParadasBus, busqueda);
        listParadasView.showList(resultados);
    }
}
