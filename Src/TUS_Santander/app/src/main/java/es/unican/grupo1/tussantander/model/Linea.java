package es.unican.grupo1.tussantander.model;


/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */
public class Linea implements Comparable {
    // Nombre de la linea
    private String name;
    // Numero alfanumerico de la linea, ej "7C1"
    private String numero;
    // Identificador numerico, ej "71"
    private int identifier;

    /**
     * Crea una nueva linea con todos sus atributos.
     *
     * @param name       nombre de la linea
     * @param numero     numero alfanumerico, ej "7C1"
     * @param identifier numero, ej "71"
     */
    public Linea(String name, String numero, int identifier) {
        this.name = name;
        this.numero = numero;
        this.identifier = identifier;
    }

    /**
     * Observador del nombre.
     *
     * @return Nombre de la linea
     */
    public String getName() {
        return name;
    }

    /**
     * Setter del nombre.
     *
     * @param name nombre de la linea
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Observador del numero
     *
     * @return identificador alfanumerico o numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Setter del numero o identificador alfanumerico.
     *
     * @param numero numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Observador del identificador numerico.
     *
     * @return identificador numerico
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Setter del identificador numerico.
     *
     * @param identifier identificador numerico
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public int compareTo(Object o) {
        Linea linea = (Linea) o;
        int resultado = 0;
        if (this.identifier < linea.identifier) {
            resultado = -1;
        } else if (this.identifier > linea.identifier) {
            resultado = 1;
        } else {
            resultado = 0;
        }
        return resultado;
    }
}
