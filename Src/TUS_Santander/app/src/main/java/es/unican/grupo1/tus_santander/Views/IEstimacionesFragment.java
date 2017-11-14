package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;


/**
 * Define los metodos del fragment de estimaciones
 */
public interface IEstimacionesFragment {


    void showList(List<Estimacion> estimacionesList);


    void showProgress(boolean state);


    void showErrorMessage();


    ProgressDialog getDialog();
}
