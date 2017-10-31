package es.unican.grupo1.tus_santander.Views;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Created by alejandro on 11/10/17.
 */

public interface IListLineasView {
    void showList(List<Linea> lineaList);
    void showProgress(boolean state);
}//IListLineasView
