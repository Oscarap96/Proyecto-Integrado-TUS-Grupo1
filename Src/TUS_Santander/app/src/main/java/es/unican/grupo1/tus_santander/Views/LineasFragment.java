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
import es.unican.grupo1.tus_santander.Presenter.ListLineasPresenter;
import es.unican.grupo1.tus_santander.R;


/**
 * A fragment representing a list of Items.
 */
public class LineasFragment extends ListFragment implements IListLineasView {

    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private ListLineasPresenter listLineasPresenter;
    private TextView textViewMensajeError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lineas_list, container, false);
        textViewMensajeError = (TextView)view.findViewById(R.id.textViewMensajeError);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listLineasPresenter = new ListLineasPresenter(getContext(),this);
        this.dialog = new ProgressDialog(getContext());
        this.listLineasPresenter.start();
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
    public void showList(List<Linea> lineaList) {
        ListLineasAdapter listLineasAdapter = new ListLineasAdapter(getContext(), lineaList);
        getListView().setAdapter(listLineasAdapter);

    }

    /**
     * Este método cuando es llamado se encarga de mostrar un progressDialog
     * @param state si es true pone el progressDialog en la interfaz, si es false lo cancela
     */
    @Override
    public void showProgress (boolean state){
        if (state) {
            dialog.setMessage("Cargando datos");
            dialog.show();
        } else {
            dialog.cancel();
        }
    }

    public void showErrorMessage (){
        textViewMensajeError.setVisibility(View.VISIBLE);
        textViewMensajeError.setText("Internet no disponible. Inténtalo más tarde.");
    }
}
