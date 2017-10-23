package es.unican.alejandro.tus_practica3.Views;

import java.util.List;

import es.unican.alejandro.tus_practica3.Model.Linea;

/**
 * Created by alejandro on 11/10/17.
 */

public interface IListLineasView {
    void showList(List<Linea> lineaList);
    void showProgress(boolean state);
}//IListLineasView
