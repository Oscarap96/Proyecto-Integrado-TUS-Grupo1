package es.unican.grupo1.tus_santander.Views;

import android.support.v4.app.ListFragment;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.Presenter.ListParadasPresenter;

/**
 * Created by Adrian on 25/10/2017.
 */

public class ParadasFragment extends ListFragment implements IListParadasView {
    private ListParadasPresenter listParadasPresenter;

    @Override
    public void showList(List<Parada> paradasList) {

    }

    @Override
    public void showProgress(boolean state) {

    }
}
