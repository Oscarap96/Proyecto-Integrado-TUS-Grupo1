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
    private List<Parada> resultados;
    private RemoteFetch remoteFetchParadas;
    private RemoteFetch remoteFetchParadasActualizar;
    private int identifierLinea;
    private static final String ERROR = "ERROR";
    private Context context;

    private static final String DBTUS = "DBTUS";
    private String dbPath;

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
        this.remoteFetchParadasActualizar = new RemoteFetch();
        this.context = context;
        this.identifierLinea = identifierLinea;
        dbPath = context.getString(R.string.dbPath);
    }// ListLineasPresenter

    /**
     * Comprueba si las paradas de una línea cuyo id se pasa como parámetro están almacenadas en la BBDD
     *
     * @param idLinea identificador de la linea de la cual se quiere saber si sus paradas están en la BBDD
     * @return true si las paradas de dicha línea están en la BBDD
     * o false en caso contrario.
     */
    public boolean isCompleta(int idLinea) {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, DBTUS, null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        if (db != null) {
            List<Parada> listaParada = funciones.obtenerParadasLinea(idLinea, db);
            Log.d("Tamaño", "listaParada:" + listaParada.size());
            if (listaParada.size() > 12) {
                return true;
            }
        }
        return false;
    }

    public boolean obtenParadas() {

        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();

        if (remoteFetchParadas.checkDataBase(dbPath, context) && isCompleta(identifierLinea)) {
            Log.d("BBDD: ", "SI hay base de datos");

            //Si hemos abierto correctamente la base de datos
            if (db != null) {
                //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
                listaParadasBus = funciones.obtenerParadasLinea(identifierLinea, db);
                Log.d("Lista paradasLinea", "Tamano es: " + listaParadasBus.size());
            }
            if (db == null) throw new NullPointerException();
            db.close();
            Log.d("ENTRA", "Obtiene paradas de DB:" + listaParadasBus.size());
            return true;
        } else {
            if (Utilidades.isOnline(context)) {
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

                    if (db == null) throw new NullPointerException();

                    db.close();
                    return true;
                } catch (IOException e) {
                    return false;
                } catch (Exception e) {
                    Log.e(ERROR, "Error en la obtención de las paradas de la linea: " + e.getMessage());
                    return false;
                }
            }
            return false;
        }
    }

    @Override

    public boolean recargaParadas() {

        if (Utilidades.isOnline(context)) {
            MisFuncionesBBDD funciones = new MisFuncionesBBDD();
            TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
            SQLiteDatabase db = tusdbh.getWritableDatabase();

            if (remoteFetchParadas.checkDataBase(dbPath, context) && isCompleta(identifierLinea)) {
                //Si hemos abierto correctamente la base de datos
                if (db != null) {
                    List<Parada> listasecParada = listaParadasBus;

                    try {
                        remoteFetchParadasActualizar.getJSON((RemoteFetch.URL_SECUENCIA_PARADAS));
                        listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetchParadasActualizar.getBufferedData(), identifierLinea);
                        Log.e(ERROR, "EMpezando a reccargar Paradas");
                    } catch (IOException e) {
                        return false;
                    }

                    //SE BORRAN LAS PARADAS ANTIGUAS DE LA BASE DE DATOS
                    funciones.borrarListaParadas(listasecParada, db);

                    //Y SE INSERTAN LAS NUEVAS PARADAS
                    funciones.insertaParadasLinea(listaParadasBus, identifierLinea, db);
                } else return false;

                return true;

            } else {
                if (db != null) {

                    try {

                        remoteFetchParadasActualizar.getJSON((RemoteFetch.URL_SECUENCIA_PARADAS));
                        listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetchParadasActualizar.getBufferedData(), identifierLinea);
                    } catch (IOException e) {
                        return false;
                    }

                    funciones.insertaParadasLinea(listaParadasBus, identifierLinea, db);
                    return true;

                } else return false;
            }
        }
        return false;
    }


    @Override
    public String getTextoParadas() {
        String textoParadas = "";
        if (listaParadasBus != null) {
            for (int i = 0; i < listaParadasBus.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(textoParadas);
                stringBuilder.append(listaParadasBus.get(i).getNombre());
                stringBuilder.append("\n\n");
                textoParadas = stringBuilder.toString();
            }
        } else {
            textoParadas = "";
        }
        return textoParadas;
    }

    @Override
    public List<Parada> getListParadasBus() {
        // retorna la lista correspondiente segun se haya realizado una busqueda o no
        if (resultados == null || resultados.size() == listaParadasBus.size()) {
            return listaParadasBus;
        } else {
            return resultados;
        }
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
                listParadasView.hideErrorMessage();
                listParadasView.showList(listaParadasBus);
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
                Log.d(ERROR, "No hay conexion a Internet");
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

    /**
     * Clase para hacer una tarea asincrona al descargar los datos.
     */
    class RetrieveFeedTask2 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            listParadasView.getDialog().setCancelable(false);
            //Muestra mensaje de cargando datos...
            if (resultados != null) {

                listParadasView.showProgress(false);
                if (resultados.size() == listaParadasBus.size())
                    listParadasView.showProgress(true);

            } else {
                Log.d("mostrado", "DIalogo mostrados");
                listParadasView.showProgress(true);
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listParadasView.hideErrorMessage();
                listParadasView.showList(getListParadasBus());
                listParadasView.showProgress(false);
                //Muestra el toast con el mensaje de que los datos se han actualizado
                Toast toast1 = Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT);
                toast1.show();
            } else {
                listParadasView.showProgress(false);
                //Muestra el toast con el mensaje informando de que no hay conexión a internet
                Toast toast1 = Toast.makeText(context, "No hay Internet", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return recargaParadas();
            } catch (Exception e) {
                Log.e("ERROR", "Error en la obtención de las lineas");
                return false;
            }
        }
    }

    /**
     * Inicia la tarea asincrona.
     */
    public void start1() {
        new RetrieveFeedTask2().execute();
    }// start


    @Override
    public void buscar(String busqueda) {
        if (listaParadasBus != null) {
            resultados = Utilidades.buscarParada(listaParadasBus, busqueda);
            listParadasView.showList(resultados);
            if (resultados.isEmpty()) {
                listParadasView.showSinResultados(true);
            } else {
                listParadasView.showSinResultados(false);
            }
        }
    }
}
