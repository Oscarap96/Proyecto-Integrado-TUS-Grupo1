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
import es.unican.grupo1.tus_santander.Views.ILineasFragment;

/**
 * Created by alejandro on 11/10/17.
 */

public class ListLineasPresenter implements IListLineasPresenter {
    private ILineasFragment listLineasView;
    private List<Linea> listaLineasBus;
    private RemoteFetch remoteFetchLineas;
    private Context context;

    private RemoteFetch remoteFetchParadas;
    private List<Parada> listaParadasBus;

    private static String DB_PATH = "/data/data/es.unican.grupo1.tus_santander/databases/DBTUS";

    public ListLineasPresenter(Context context, ILineasFragment listLineasView) {
        this.listLineasView = listLineasView;
        this.remoteFetchLineas = new RemoteFetch();
        this.context = context;

        this.remoteFetchParadas = new RemoteFetch();
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;

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
                Toast toast1 = Toast.makeText(context, "Datos obtenidos con éxito", Toast.LENGTH_SHORT);
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

    public void start() {
        new RetrieveFeedTask().execute();

    }// start

    /**
     * Método a través del cual se almacenan las lineas de buses en el atributo listaLineasBus
     * de esta clase. Para realizar esto internamente realiza una llamada a la función
     * getJSON (RemoteFetch) para seguidamente parsear el JSON obtenido con la llamada
     * a readArrayLineasBus (ParserJSON)
     *
     * @return
     */
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
                        funciones.insertaParadasLinea(paradasDeLinea,identiLinea,db);
                    }
                }

                // Asignar paradas a lineas


                //Si hemos abierto correctamente la base de datos
                if (db != null) {
                    Log.d("DB Creada","creada la base de datos");
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
                e.printStackTrace();
                return false;
            }
        }
    }


    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus


    /**
     * Método para obtener un cadena de texto con todas las lineas. En esta cadena
     * se muestra unicamente el nombre de la linea
     *
     * @return String con todas las gasolineras separadas por un doble salto de línea
     */

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
