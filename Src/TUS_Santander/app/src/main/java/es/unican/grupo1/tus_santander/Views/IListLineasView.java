package es.unican.grupo1.tus_santander.Views;

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Created by alejandro on 11/10/17.
 */

public interface IListLineasView {

    // TODO comentario
    void showList(List<Linea> lineaList);

    // TODO comentario
    void showProgress(boolean state);
<<<<<<< HEAD

    // TODO comentario
    void showErrorMessage();
=======
    void showErrorMessage ();
    ProgressDialog getDialog();
>>>>>>> 245550-ArreglarBotonAtras
}//IListLineasView
