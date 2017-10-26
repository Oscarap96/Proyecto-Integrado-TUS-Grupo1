package es.unican.grupo1.tus_santander.Views;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

/**
 * Created by Adrian on 25/10/2017.
 */

public interface IListParadasView {
    void showList(List<Parada> paradasList);
    void showProgress(boolean state);
}
