package es.unican.grupo1.tussantander.model;

import android.support.annotation.NonNull;


/**
 * Guarda la informacion de una estimacion, con el autobus al que corresponde y los tiempos.
 */
public class Estimacion implements Comparable {
    // Autobus correspondiente a esta estimacion, ej: "7C1"
    private String etiquetaLinea;

    // primera y segunda estimacion de llegada del autobus
    private int tiempo1min;
    // la segunda estimacion puede no estar disponible, se representa con -1
    private int tiempo2min;

    /**
     * Constructor de una estimacion. Redondea los tiempos a la baja.
     *
     * @param autobus    etiqueta del autobus correspondiente a la estimacion, ej: "7C1"
     * @param tiempo1seg primera estimacion en segundos
     * @param tiempo2seg segunda estimacion en segundos
     */
    public Estimacion(String autobus, int tiempo1seg, int tiempo2seg) {
        this.etiquetaLinea = autobus;
        this.tiempo1min = (int) (Math.floor(tiempo1seg / 60.0));
        this.tiempo2min = (int) (Math.floor(tiempo2seg / 60.0));
    }

    /**
     * Constructor de una estimacion cuando no esta disponible la segudna estimacion.
     * Redondea los tiempos a la baja.
     *
     * @param autobus    etiqueta del autobus correspondiente a la estimacion, ej: "7C1"
     * @param tiempo1seg primera estimacion en segundos
     */
    public Estimacion(String autobus, int tiempo1seg) {
        this.etiquetaLinea = autobus;
        this.tiempo1min = (int) (Math.floor(tiempo1seg / 60.0));
        this.tiempo2min = -1;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Estimacion estimacion = (Estimacion) o;
        int resultado = 0;
        // primero se ordenan segun el primer tiempo
        if (this.tiempo1min < estimacion.tiempo1min) {
            resultado = -1;
        } else if (this.tiempo1min > estimacion.tiempo1min) {
            resultado = 1;
        } else {
            // si aun asi son iguales, se ordenan segun el tiempo2
            if (this.tiempo2min < estimacion.tiempo2min) {
                resultado = -1;
            } else if (this.tiempo2min > estimacion.tiempo2min) {
                resultado = 1;
            } else {
                // si aun asi son iguales, se ordenan segun el nombre de la lina
                resultado = this.etiquetaLinea.compareTo(estimacion.etiquetaLinea);
            }
        }
        return resultado;
    }

    /**
     * Observador de la estiqueta de la linea correspondiente a esta estimacion.
     *
     * @return etiqueta de la linea, ej: "7C1"
     */
    public String getEtiquetaLinea() {
        return etiquetaLinea;
    }

    /**
     * Observador de la primera estimacion en minutos.
     *
     * @return tiempo en minutos para que llegue el primer bus
     */
    public int getTiempo1min() {
        return tiempo1min;
    }

    /**
     * Observador de la segunda estimacion en minutos.
     *
     * @return tiempo en minutos para que llegue el segundo bus
     */
    public int getTiempo2min() {
        return tiempo2min;
    }
}
