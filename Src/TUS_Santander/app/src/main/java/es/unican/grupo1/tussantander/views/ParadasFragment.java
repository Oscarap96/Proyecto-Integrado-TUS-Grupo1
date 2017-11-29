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
import android.view.MenuItem;
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
    private TextView textViewSinResultados;

    private ListParadasPresenter listParadasPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paradas_list, container, false);
        setHasOptionsMenu(true);
        textViewMensajeError = view.findViewById(R.id.textViewMensajeError);
        textViewSinResultados = view.findViewById(R.id.textViewSinResultados);
        TextView buscarParadas = view.findViewById(R.id.editText_search);
        buscarParadas.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_black_24dp, 0, 0, 0);
        /*
        el siguiente codigo estaba en onActivityCreated y se ha movido para poder definir el
        campo de buscar adecuadamente
        */
        dataCommunication = (DataCommunication) getContext();
        int identifierLinea = dataCommunication.getLineaIdentifier();
        this.listParadasPresenter = new ListParadasPresenter(getContext(), this, identifierLinea);
        this.dialog = new ProgressDialog(getContext());
        ((DataCommunication) getContext()).setMostrarBotonActualizar(true);
        // anhadir el listener al campo de buscar
        dataCommunication.setParadasPresenter(listParadasPresenter);
        EditText buscar = view.findViewById(R.id.editText_search);
        buscar.addTextChangedListener(generarTextWatcher());
        // iniciar el presenter
        this.listParadasPresenter.start();
        return view;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", Integer.toString(position));
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
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.refresh_item)
        {
            listParadasPresenter.start1();
            Log.d("Pulsado","ActualizarParadas");
            return(true);
        }
        return(super.onOptionsItemSelected(item));
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
    public void hideErrorMessage() {
        textViewMensajeError.setVisibility(View.INVISIBLE);
    }

    @Override
    public ProgressDialog getDialog() {
        return dialog;
    }

    /**
     * Genera el TextWatcher del campo de busqueda, encargado de volver a buscar cuando cambie
     * el texto del campo de busqueda.
     *
     * @return TextWatcher del campo de busqueda.
     */
    private TextWatcher generarTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // aqui no hace nada porque detecta todos los cambios en onTextChanged
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // aqui meto lo que pasa cuando modifican el texto
                ListParadasPresenter presenter = ((DataCommunication) getContext()).getParadasPresenter();
                presenter.buscar(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // aqui no hace nada porque detecta todos los cambios en onTextChanged
            }
        };
    }

    @Override
    public void showSinResultados(boolean mostrar) {
        if (mostrar) {
            textViewSinResultados.setVisibility(View.VISIBLE);
        } else {
            textViewSinResultados.setVisibility(View.INVISIBLE);
        }
    }
}