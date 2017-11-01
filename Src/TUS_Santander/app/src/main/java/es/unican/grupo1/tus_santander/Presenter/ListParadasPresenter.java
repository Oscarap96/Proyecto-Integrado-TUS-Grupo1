package es.unican.grupo1.tus_santander.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON;
import es.unican.grupo1.tus_santander.Model.DataLoaders.RemoteFetch;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Views.IListParadasView;


/**
 * Created by Adrian on 25/10/2017.
 */

public class ListParadasPresenter implements IListParadasPresenter{
    private IListParadasView listParadasView;
    private List<Parada> listParadasBus;
    private List<Parada> lineasDeParadas;
    private RemoteFetch remoteFetchParadas;
    private int identifierLinea;
    private Context context;
    public ListParadasPresenter(Context context, IListParadasView listParadasView, int identifierLinea){
        this.listParadasView = listParadasView;
        this.remoteFetchParadas = new RemoteFetch();
        this.context = context;
        this.identifierLinea = identifierLinea;
    }// ListLineasPresenter
    public boolean obtenParadas(){
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
            listParadasBus = ParserJSON.readArraySecuenciaParadas(remoteFetchParadas.getBufferedData(), identifierLinea);
            //remoteFetchParadas.getJSON(RemoteFetch.URL_SECUENCIA_PARADAS);
           // lineasDeParadas=ParserJSON.readArraySecuenciaParadas(remoteFetchParadas.getBufferedData(), identifierLinea);
            return true;
        }catch(Exception e){
            Log.e("ERROR","Error en la obtención de las paradas de la linea: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public String getTextoParadas(){
        String textoParadas="";
        if(listParadasBus!=null){
            for (int i=0; i<listParadasBus.size(); i++){
                textoParadas=textoParadas+listParadasBus.get(i).getNombre()+"\n\n";
            }
        }else{
            textoParadas="";
        }
        return textoParadas;
    }
    public List<Parada>getListLineasParadas(){return lineasDeParadas;}

    public List<Parada> getListParadasBus(){
        return listParadasBus;
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
