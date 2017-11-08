package es.unican.grupo1.tus_santander.Presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


import es.unican.grupo1.tus_santander.Model.DataLoaders.Data;
import es.unican.grupo1.tus_santander.Model.DataLoaders.FuncionesBBDD;
import es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON;
import es.unican.grupo1.tus_santander.Model.DataLoaders.RemoteFetch;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Views.IListLineasView;

/**
 * Created by alejandro on 11/10/17.
 */

public class ListLineasPresenter implements IListLineasPresenter {
    private IListLineasView listLineasView;
    private List<Linea> listaLineasBus;
    private RemoteFetch remoteFetchLineas;
    private Context context;


    private static String DB_PATH = "/data/data/es.unican.grupo1.tus_santander.Model.DataLoaders/databases/BaseTUS.db";
    //private static String DB_NAME = "";

    public ListLineasPresenter(Context context, IListLineasView listLineasView) {
        this.listLineasView = listLineasView;
        this.remoteFetchLineas = new RemoteFetch();
        this.context = context;
    }// ListLineasPresenter

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
            obtenLineas();
            if(isCancelled()){
                return false;
            }
            return true;
        }

        @Override
        protected void onCancelled(){

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
        try {
            if (remoteFetchLineas.checkDataBase(DB_PATH)) {
                Log.d("BBDD: ", "SI hay base de datos");
                //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
                listaLineasBus = FuncionesBBDD.obtenerLineas();
            } else {
                Log.d("BBDD: ", "NO hay base de datos");
                //SE OBTIENEN LOS DATOS DE INTERNET...
                // remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
                // listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());
                Data data = new Data();
                listaLineasBus = data.descargarLineas();

                //... Y SE METEN EN LA BBDD
                //FuncionesBBDD usuario = new FuncionesBBDD();
                //usuario.anhadeLineas(remoteFetchLineas);
                //usuario.anhadeParadas(remoteFetchLineas);
                /**BaseTUS usuario = new BaseTUS(context);
                 SQLiteDatabase db = usuario.getWritableDatabase();

                 ContentValues valores = new ContentValues();
                 valores.put("_id", "1");
                 valores.put("nombre", "MiLinea");
                 valores.put("identificador", 1);

                 db.insert(usuario.TABLA_LINEAS,null,valores);
                 db.close();*/
            }
            Log.d("ENTRA", "Obten lineas de bus:" + listaLineasBus.size());
            return true;
        } catch (IOException e) {
            return false;
        } catch (Exception e) {
            Log.e("ERROR", "Error en la obtención de las lineas de Bus: " + e.getMessage());

            e.printStackTrace();
            return false;
        }//try
    }//obtenLineas


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
