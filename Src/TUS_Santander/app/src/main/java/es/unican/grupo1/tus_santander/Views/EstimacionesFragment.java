package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;
import es.unican.grupo1.tus_santander.Presenter.ListEstimacionesPresenter;
import es.unican.grupo1.tus_santander.R;

/**
 * Fragment para las estimaciones.
 */
public class EstimacionesFragment extends ListFragment implements IEstimacionesFragment {
    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private ListEstimacionesPresenter listEstimacionesPresenter;
    private TextView textViewMensajeErrorEstimaciones;
    private int paradaId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estimaciones_list, container, false);
        textViewMensajeErrorEstimaciones = (TextView) view.findViewById(R.id.textViewMensajeErrorEstimaciones);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataCommunication = (DataCommunication) getContext();
        paradaId = dataCommunication.getParadaIdentifier();
        this.listEstimacionesPresenter = new ListEstimacionesPresenter(getContext(), this, paradaId);
        this.dialog = new ProgressDialog(getContext());
        this.listEstimacionesPresenter.start();
        // oculta el boton de refrescar
        ((DataCommunication) getContext()).setMostrarBotonActualizar(false);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        // no hace nada al pulsar una estimacion
    }

    @Override
    public void showList(List<Estimacion> estimacionesList) {
        ListEstimacionesAdapter listEstimacionesAdapter = new ListEstimacionesAdapter(getContext(), estimacionesList);
        getListView().setAdapter(listEstimacionesAdapter);
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
    public ProgressDialog getDialog() {
        return dialog;
    }
}
