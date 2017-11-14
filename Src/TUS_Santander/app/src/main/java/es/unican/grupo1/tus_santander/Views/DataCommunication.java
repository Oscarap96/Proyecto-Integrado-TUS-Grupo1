package es.unican.grupo1.tus_santander.Views;


/**
 * Interfaz que implementará la activitidad MainActivity para tener métodos que nos permitan comunicar
 * los distintos fragments de la app.
 */
public interface DataCommunication {

    /**
     * Observador del identificador de la linea.
     *
     * @return identificador numerico de la linea
     */
    int getLineaIdentifier();

    /**
     * Setter de la linea.
     *
     * @param identifier identificador numerico de la linea
     */
    void setLineaIdentifier(int identifier);

    /**
     * Observador del numero de la parada.
     *
     * @return numero de la parada
     */
    int getParadaIdentifier();

    /**
     * Setter del numero de la parada.
     *
     * @param paradaIdentifier numero de la parada
     */
    void setParadaIdentifier(int paradaIdentifier);

    /**
     * Permite ocultar o mostrar el boton de recargar de la action bar. Solo con llamar a este
     * metodo se refresca la action bar.
     *
     * @param mostrar true para mostrar el boton, false para ocultarlo
     */
    void setMostrarBotonActualizar(boolean mostrar);
}
