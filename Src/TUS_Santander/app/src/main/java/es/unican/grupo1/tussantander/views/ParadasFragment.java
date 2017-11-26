package es.unican.grupo1.tussantander.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import es.unican.grupo1.tussantander.model.Parada;
import es.unican.grupo1.tussantander.presenter.ListParadasPresenter;
import es.unican.grupo1.tussantander.R;


/**
 * Fragment de paradas.
 */
public class ParadasFragment extends ListFragment implements IParadasFragment {
    private DataCommunication dataCommunication;
    private ProgressDialog dialog;
    private TextView textViewMensajeError;

    private ListParadasPresenter listParadasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paradas_list, container, false);
        textViewMensajeError = view.findViewById(R.id.textViewMensajeError);
        TextView buscarParadas = view.findViewById(R.id.editText_search);
        buscarParadas.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, 0, 0);

        // TODO
        dataCommunication = (DataCommunication) getContext();
        int identifierLinea = dataCommunication.getLineaIdentifier();
        this.listParadasPresenter = new ListParadasPresenter(getContext(), this, identifierLinea);
        this.dialog = new ProgressDialog(getContext());
        // this.listParadasPresenter.start();
        ((DataCommunication) getContext()).setMostrarBotonActualizar(true);
        dataCommunication.setParadasPresenter(listParadasPresenter);
        EditText buscar = (EditText) view.findViewById(R.id.editText_search);
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO aqui meto lo que pasa cuando modifican el texto
                DataCommunication dataCommunication = (DataCommunication) getContext();
                ListParadasPresenter presenter = dataCommunication.getParadasPresenter();
                presenter.buscar(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        this.listParadasPresenter.start();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*
        dataCommunication = (DataCommunication) getContext();
        int identifierLinea = dataCommunication.getLineaIdentifier();
        this.listParadasPresenter = new ListParadasPresenter(getContext(), this, identifierLinea);
        this.dialog = new ProgressDialog(getContext());
        this.listParadasPresenter.start();

        ((DataCommunication) getContext()).setMostrarBotonActualizar(true);
        */
        // TODO ver que hago con ese codigo comentado
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", "" + position);
        // cambio a la interfaz de estimaciones
        EstimacionesFragment fragmentEstimaciones = new EstimacionesFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutElements, fragmentEstimaciones);
        dataCommunication = (DataCommunication) getContext();
        // Apunta la parada pulsada
        dataCommunication.setParadaIdentifier(listParadasPresenter.getListParadasBus().get(position).getIdentificador());
        ft.addToBackStack(null);
        ft.commit();
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showList(List<Parada> paradasList) {
        ListParadasAdapter listParadasAdapter = new ListParadasAdapter(getContext(), paradasList);
        getListView().setAdapter(listParadasAdapter);
    }

    @Override
    public void showProgress(boolean state) {
        if (state) {
            dialog.setMessage(getString(R.string.mensajeCargando));
            dialog.show();
        } else {
            dialog.cancel();
        }
    }

    @Override
    public void showErrorMessage() {
        textViewMensajeError.setVisibility(View.VISIBLE);
        textViewMensajeError.setText(getString(R.string.noHayInternet));
    }

    @Override
    public ProgressDialog getDialog() {
        return dialog;
    }
}