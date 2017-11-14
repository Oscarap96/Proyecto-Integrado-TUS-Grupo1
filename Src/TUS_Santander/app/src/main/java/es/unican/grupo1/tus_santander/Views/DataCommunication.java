package es.unican.grupo1.tus_santander.Views;


/**
 * Interfaz que implementará la activitidad MainActivity para tener métodos que nos permitan comunicar
 * los distintos fragments de la app.
 */
public interface DataCommunication {


    int getLineaIdentifier();


    void setLineaIdentifier(int identifier);


    int getParadaIdentifier();


    void setParadaIdentifier(int paradaIdentifier);

    /**
     * Permite ocultar o mostrar el boton de recargar de la action bar.
     *
     * @param mostrar true para mostrar el boton, false para ocultarlo
     */
    void setMostrarBotonActualizar(boolean mostrar);
}
