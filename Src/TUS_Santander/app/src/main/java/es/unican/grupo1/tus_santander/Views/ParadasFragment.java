package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Presenter.ListParadasPresenter;
import es.unican.grupo1.tus_santander.R;

/**
 * Created by Adrian on 25/10/2017.
 */
public class ParadasFragment extends ListFragment implements IListParadasView {
    private DataCommunication dataCommunication;
    private int identifierLinea;
   // private ProgressDialog dialog;
    private ListParadasPresenter listParadasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paradas_list, container, false);
        TextView buscarParadas=(TextView) view.findViewById(R.id.editText_search);
        buscarParadas.setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.ic_search_black_24dp);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataCommunication=(DataCommunication)getContext();
        identifierLinea = dataCommunication.getLineaIdentifier();
        this.listParadasPresenter = new ListParadasPresenter(getContext(), this, identifierLinea);
        //this.dialog = new ProgressDialog(getContext());
        this.listParadasPresenter.start();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", ""+position);
        //Haciendo uso de la interfaz DataCommunication podemos enviar los datos entre fragmentos
        //Ejemplo:
        //dataCommunication = (DataCommunication) getContext();
        //dataCommunication.setLineaIdentifier(datosBuses.getListaLineasBus().get(position).getIdentifier());
    }

    @Override
    public void showList(List<Parada> paradasList) {
        ListParadasAdapter listParadasAdapter = new ListParadasAdapter(getContext(), paradasList );
        getListView().setAdapter(listParadasAdapter);
    }

    @Override
    public void showProgress(boolean state) {
        if (state) {
          //  dialog.setMessage("Cargando datos");
            //dialog.show();

        } else {
            //dialog.cancel();
        }
    }

}