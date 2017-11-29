package es.unican.grupo1.tussantander.model;

/**
 * Guarda los datos de las paradas, como su nombre, numero o identificador.
 */
public class Parada {
    // Nombre de la parada
    private String nombre;
    // Identificador numerico, valor correcto
    private int identificador;
    // Linea o lineas que pasan por esta parada
    private int linea;

    /**
     * Crea paradas con su nombre, identificador y el numero de una linea.
     *
     * @param nombre        Nombre de la parada
     * @param identificador Identificador numerico de la parada
     * @param numero        Numero de la linea que pasa por la parada
     */
    public Parada(String nombre, int identificador, int numero) {
        this.nombre = nombre;
        this.linea = numero;
        this.identificador = identificador;
    }

    /**
     * Crea paradas usando solo su nombre e identificador numerico.
     *
     * @param nombre        Nombre de la parada
     * @param identificador Identificador numerico de la parada
     */
    public Parada(String nombre, int identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
    }

    /**
     * Observador del nombre de la parada.
     *
     * @return nombre de la parada
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Observador del identificador numerico de la parada.
     *
     * @return identificador numerico de la parada
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Setter del nombre de la parada.
     *
     * @param nombre nombre de la parada
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter del identificador numerico de la parada.
     *
     * @param identificador identificador numerico de la parada
     */
    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    /**
     * Observador de la linea que pasa por la parada.
     *
     * @return numero de la linea
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Setter de la linea que pasa por la parada.
     *
     * @param linea linea que pasa por la parada
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }
}
