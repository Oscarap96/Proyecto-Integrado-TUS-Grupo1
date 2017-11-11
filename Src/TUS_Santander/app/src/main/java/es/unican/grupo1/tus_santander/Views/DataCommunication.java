package es.unican.grupo1.tus_santander.Views;

/**
 * Created by alejandro on 16/08/17.
 */

/**
 * Interfaz que implementará la activitidad MainActivity para tener métodos que nos permitan comunicar
 * los distintos fragments de la app
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

    // TODO anhadir metodos cuando necesite comunicarme entre fragments

    // TODO
    void setMostrarBotonActualizar(boolean mostrar);
}
