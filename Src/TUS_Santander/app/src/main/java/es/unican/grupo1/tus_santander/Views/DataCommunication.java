package es.unican.grupo1.tus_santander.Views;


/**
 * Interfaz que implementará la activitidad MainActivity para tener métodos que nos permitan comunicar
 * los distintos fragments de la app.
 */
public interface DataCommunication {

    // TODO comentario
    int getLineaIdentifier();

    // TODO comentario
    void setLineaIdentifier(int identifier);

    // TODO comentario
    int getParadaIdentifier();

    // TODO comentario
    void setParadaIdentifier(int paradaIdentifier);

    /**
     * Permite ocultar o mostrar el boton de recargar de la action bar.
     *
     * @param mostrar true para mostrar el boton, false para ocultarlo
     */
    void setMostrarBotonActualizar(boolean mostrar);
}
