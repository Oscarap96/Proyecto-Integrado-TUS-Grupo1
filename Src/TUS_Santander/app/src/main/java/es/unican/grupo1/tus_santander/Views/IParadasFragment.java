package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Define los metodos del fragment de paradas.
 */
public interface IParadasFragment {


    void showList(List<Parada> paradasList);


    void showProgress(boolean state);


    void showErrorMessage();


    ProgressDialog getDialog();
}
