package es.unican.grupo1.tus_santander.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.DataLoaders.ParserJSON;
import es.unican.grupo1.tus_santander.Model.DataLoaders.RemoteFetch;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Views.IListParadasView;


/**
 * Created by Adrian on 25/10/2017.
 */

public class ListParadasPresenter {
    private IListParadasView listParadasView;
    private List<Parada> listParadasBus;
    private RemoteFetch remoteFetchParadas;
    private Context context;
    public ListParadasPresenter(Context context, IListParadasView listParadasView){
        this.listParadasView = listParadasView;
        this.remoteFetchParadas = new RemoteFetch();
        this.context = context;
    }// ListLineasPresenter
    public boolean obtenParadas(){
        try {
            remoteFetchParadas.getJSON(RemoteFetch.URL_PARADAS_INFO);
            listParadasBus = ParserJSON.readArrayParadas(remoteFetchParadas.getBufferedData());
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

    public List<Parada> getListParadasBus(){
        return listParadasBus;
    }
    public void start() {
       // new ListParadasPresenter.RetrieveFeedTask().execute();

    }// start





}
