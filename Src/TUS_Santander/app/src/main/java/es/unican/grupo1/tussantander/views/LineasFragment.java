package es.unican.grupo1.tussantander.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.presenter.ListLineasPresenter;
import es.unican.grupo1.tussantander.R;


/**
 * A fragment representing a list of Items.
 */
public class LineasFragment extends ListFragment implements ILineasFragment {
    private ProgressDialog dialog;
    private ListLineasPresenter listLineasPresenter;
    private TextView textViewMensajeError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lineas_list, container, false);
        textViewMensajeError = view.findViewById(R.id.textViewMensajeError);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        this.listLineasPresenter = new ListLineasPresenter(getContext(), this);
        this.dialog = new ProgressDialog(getContext());
        this.listLineasPresenter.start();
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        Log.d("pulsado", Integer.toString(position));
        //AQUI SE DEBE HACER EL CAMBIO DE FRAGMENTS
        ParadasFragment fragmentParadas = new ParadasFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutElements, fragmentParadas);
        DataCommunication dataCommunication = (DataCommunication) getContext();
        dataCommunication.setLineaIdentifier(listLineasPresenter.getListaLineasBus().get(position).getIdentifier());
        ft.addToBackStack(null);
        ft.commit();
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.refresh_item)
        {
            listLineasPresenter.start1();
            return(true);
        }
        return(super.onOptionsItemSelected(item));
    }


    @Override
    public void showList(List<Linea> lineaList) {
        ListLineasAdapter listLineasAdapter = new ListLineasAdapter(getContext(), lineaList);
        getListView().setAdapter(listLineasAdapter);
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
}
