package es.unican.grupo1.tus_santander.Presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import es.unican.grupo1.tus_santander.Model.DataLoaders.BaseTUS;
import es.unican.grupo1.tus_santander.Model.DataLoaders.FuncionesBBDD;
import es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON;
import es.unican.grupo1.tus_santander.Model.DataLoaders.RemoteFetch;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Views.IListLineasView;

import static es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON.readArrayParadas;
import static java.security.AccessController.getContext;

/**
 * Created by alejandro on 11/10/17.
 */

public class ListLineasPresenter implements IListLineasPresenter{
    private IListLineasView listLineasView;
    private List<Linea> listaLineasBus;
    private RemoteFetch remoteFetchLineas;
    private RemoteFetch remoteFetchParadas;
    private List<Parada>listParadasBus;
    private Context context;

    private static String DB_PATH = "/data/data/Views/databases/";
    //private static String DB_NAME = "";

    public ListLineasPresenter(Context context, IListLineasView listLineasView){
        this.listLineasView = listLineasView;
        this.remoteFetchLineas = new RemoteFetch();
        this.context = context;
    }// ListLineasPresenter

    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        private Exception exception;

        @Override
        protected void onPreExecute() {
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
                this.exception = e;
                return null;
            }

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
     * @return
     */
    public boolean obtenLineas(){
        try {
           // if(remoteFetchLineas.checkDataBase(DB_PATH)) {
             //   //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
               // listaLineasBus = FuncionesBBDD.obtenerLineas();
            //} else {
                //SE OBTIENEN LOS DATOS DE INTERNET
                remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
                listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());
            //}
            Collections.sort(listaLineasBus); //ordenación de las lineas de buses
            Log.d("ENTRA", "Obten lineas de bus:" + listaLineasBus.size());
            return true;
        } catch(IOException e) {
            return false;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las lineas de Bus: "+e.getMessage());
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
     *  @return String con todas las gasolineras separadas por un doble salto de línea
     */
    public String getTextoLineas(){
        String textoLineas="";
        if(listaLineasBus!=null){
            for (int i=0; i<listaLineasBus.size(); i++){
                textoLineas=textoLineas+listaLineasBus.get(i).getNumero()+"\n\n";
            }//for
        }else{
            textoLineas="Sin lineas";
        }//if
        return textoLineas;
    }//getTextoLineas



    public List<Parada> getListParadasBus(){
        return listParadasBus;
    }
}// ListLineasPresenter
