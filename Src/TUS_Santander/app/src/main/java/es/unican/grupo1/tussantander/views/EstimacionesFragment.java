package es.unican.grupo1.tussantander.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.presenter.ListEstimacionesPresenter;
import es.unican.grupo1.tussantander.R;

/**
 * Fragment para las estimaciones.
 */
public class EstimacionesFragment extends ListFragment implements IEstimacionesFragment {
    private ProgressDialog dialog;
    private TextView textViewMensajeErrorEstimaciones;
    ListEstimacionesPresenter listEstimacionesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estimaciones_list, container, false);
        textViewMensajeErrorEstimaciones = view.findViewById(R.id.textViewMensajeErrorEstimaciones);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        DataCommunication dataCommunication = (DataCommunication) getContext();
        int paradaId = dataCommunication.getParadaIdentifier();
        this.listEstimacionesPresenter = new ListEstimacionesPresenter(getContext(), this, paradaId);
        this.dialog = new ProgressDialog(getContext());
        listEstimacionesPresenter.start();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        // no hace nada al pulsar una estimacion
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.refresh_item)
        {
            this.listEstimacionesPresenter.start();
            Log.d("Pulsado","Actualizar");
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void showList(List<Estimacion> estimacionesList) {
        ListEstimacionesAdapter listEstimacionesAdapter = new ListEstimacionesAdapter(getContext(), estimacionesList);
        getListView().setAdapter(listEstimacionesAdapter);
    }

    @Override
    public void hideList() {
        getListView().setAdapter(null);
    }

    @Override
    public void showProgress(boolean state) {
        if (state) {
            dialog.setMessage("Cargando datos...");
            dialog.show();
        } else {
            dialog.cancel();
        }
    }

    @Override
    public void showErrorMessage() {
        textViewMensajeErrorEstimaciones.setVisibility(View.VISIBLE);
        textViewMensajeErrorEstimaciones.setText("Internet no disponible. Inténtalo más tarde.");
    }

    @Override
    public void hideErrorMessage() {
        textViewMensajeErrorEstimaciones.setVisibility(View.INVISIBLE);
    }

    @Override
    public ProgressDialog getDialog() {
        return dialog;
    }
}
