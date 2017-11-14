package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;


/**
 * Define los metodos del fragment de lineas.
 */
public interface ILineasFragment {


    void showList(List<Linea> lineaList);


    void showProgress(boolean state);


    void showErrorMessage();

    ProgressDialog getDialog();
}//IListLineasView
