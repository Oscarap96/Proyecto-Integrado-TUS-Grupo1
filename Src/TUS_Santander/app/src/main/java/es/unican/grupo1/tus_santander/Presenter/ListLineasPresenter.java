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
import es.unican.grupo1.tus_santander.Model.DatabaseAccess.MisFuncionesBBDD;
import es.unican.grupo1.tus_santander.Model.DatabaseAccess.TUSSQLiteHelper;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.R;
import es.unican.grupo1.tus_santander.Views.ILineasFragment;


/**
 * Presenter de lineas. Se encarga de la logica entre la interfaz de lineas y el modelo.
 */
public class ListLineasPresenter implements IListLineasPresenter {
    private ILineasFragment listLineasView;
    private List<Linea> listaLineasBus;
    private RemoteFetch remoteFetchLineas;
    private Context context;
    private RemoteFetch remoteFetchParadas;

    private static String DB_PATH = "/data/data/es.unican.grupo1.tus_santander/databases/DBTUS";

    /**
     * Constructor.
     *
     * @param context        contexto de la app
     * @param listLineasView view para las lineas
     */
    public ListLineasPresenter(Context context, ILineasFragment listLineasView) {
        this.listLineasView = listLineasView;
        this.remoteFetchLineas = new RemoteFetch();
        this.context = context;
        this.remoteFetchParadas = new RemoteFetch();
    }

    /**
     * Clase para hacer una tarea asincrona al descargar los datos.
     */
    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            listLineasView.getDialog().setCancelable(false);
            //Muestra mensaje de cargando datos...
            listLineasView.showProgress(true);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listLineasView.showList(getListaLineasBus());
                listLineasView.showProgress(false);
                //Muestra el toast con el mensaje
                Toast toast1 = Toast.makeText(context, R.string.mensajeToast1, Toast.LENGTH_SHORT);
                toast1.show();
            } else {
                listLineasView.showProgress(false);
                listLineasView.showErrorMessage();
            }
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return obtenLineas();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onCancelled() {
        }
    }

    /**
     * Inicia la tarea asincrona.
     */
    public void start() {
        new RetrieveFeedTask().execute();
    }// start

    @Override
    public boolean obtenLineas() {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();

        if (remoteFetchLineas.checkDataBase(DB_PATH, context)) {
            TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
            SQLiteDatabase db = tusdbh.getWritableDatabase();
            Log.d("BBDD: ", "SI hay base de datos");

            //Si hemos abierto correctamente la base de datos
            if (db != null) {
                //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
                listaLineasBus = funciones.obtenerLineas(db);
            }
            db.close();
            Collections.sort(listaLineasBus); //ordenación de las lineas de buses
            Log.d("ENTRA", "Obtiene lineas de DB:" + listaLineasBus.size());
            return true;
        } else {
            try {
                Log.d("BBDD: ", "NO hay base de datos");

                //SE OBTIENEN LOS DATOS DE INTERNET...
                remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
                listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());

                //remoteFetchParadas.getJSON((RemoteFetch.URL_SECUENCIA_PARADAS));
                //listaParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetchParadas.getBufferedData());

                Log.d("ENTRA", "Obtiene lineas de JSON:" + listaLineasBus.size());
                //Log.d("ENTRA", "Obtiene paradas de JSON:" + listaParadasBus.size());

                TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
                SQLiteDatabase db = tusdbh.getWritableDatabase();

                // Asignar paradas a lineas

                Linea laLinea;
                int identiLinea;
                List<Parada> paradasDeLinea;

                for (int i = 0; i < listaLineasBus.size(); i++) {
                    laLinea = listaLineasBus.get(i);
                    identiLinea = laLinea.getIdentifier();
                    Log.d("ENTRA EN EL BUCLE", "Casi obtiene paradas de linea de JSON");
                    remoteFetchParadas.getJSON((RemoteFetch.URL_SECUENCIA_PARADAS));
                    paradasDeLinea = ParserJSON.readArraySecuenciaParadas(remoteFetchParadas.getBufferedData(), identiLinea);
                    Log.d("ENTRA", "Obtiene paradas de linea de JSON:" + paradasDeLinea.size());
                    if (db != null) {
                        funciones.insertaParadasLinea(paradasDeLinea, identiLinea, db);
                    }
                }

                // Asignar paradas a lineas


                //Si hemos abierto correctamente la base de datos
                if (db != null) {
                    Log.d("DB Creada", "creada la base de datos");
                    funciones.insertaListaLineas(listaLineasBus, db);
                    //funciones.insertaListaParadas(listaParadasBus, db);
                }

                db.close();
                Collections.sort(listaLineasBus);
                Log.d("ordenadas", "lineas ordenadas");
                return true;
            } catch (IOException e) {
                return false;
            } catch (Exception e) {
                Log.e("ERROR", "Error en la obtención de las lineas de Bus: " + e.getMessage());
                return false;
            }
        }
    }

    @Override
    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus

    @Override
    public String getTextoLineas() {
        String textoLineas = "";
        if (listaLineasBus != null) {
            for (int i = 0; i < listaLineasBus.size(); i++) {
                textoLineas = textoLineas + listaLineasBus.get(i).getNumero() + "\n\n";
            }//for
        } else {
            textoLineas = "Sin lineas";
        }//if
        return textoLineas;
    }//getTextoLineas
}// ListLineasPresenter
