package es.unican.grupo1.tus_santander.Presenter;

/**
 * Created by Oscar Alario Pelaz on 31/10/2017.
 */

import android.app.ProgressDialog;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

public interface IListLineasPresenter {

    // TODO comentario
    boolean obtenLineas();

    // TODO comentario
    List<Linea> getListaLineasBus();

    // TODO comentario
    String getTextoLineas();
}
