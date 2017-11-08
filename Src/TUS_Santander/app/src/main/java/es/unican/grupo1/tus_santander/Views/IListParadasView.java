package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

/**
 * Created by Adrian on 25/10/2017.
 */

public interface IListParadasView {

    // TODO comentario
    void showList(List<Parada> paradasList);

    // TODO comentario
    void showProgress(boolean state);


    // TODO comentario
    // TODO comprobar si es necesario
    void showErrorMessage();


    ProgressDialog getDialog();

}
