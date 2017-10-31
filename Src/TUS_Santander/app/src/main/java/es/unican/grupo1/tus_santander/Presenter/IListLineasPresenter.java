package es.unican.grupo1.tus_santander.Presenter;

/**
 * Created by Oscar Alario Pelaz on 31/10/2017.
 */

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

public interface IListLineasPresenter {

    public boolean obtenLineas();
    public List<Linea> getListaLineasBus();
    public String getTextoLineas();
    public List<Parada> getListParadasBus();
}
