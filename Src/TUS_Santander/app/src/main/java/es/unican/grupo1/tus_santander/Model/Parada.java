package es.unican.grupo1.tus_santander.Model;

/**
 * Guarda los datos de las paradas, como su nombre, numero o identificador.
 */
public class Parada {
    private String nombre;
    private int identificador;
    private int linea;

    public Parada(String nombre, int identificador, int numero) {
        this.nombre = nombre;
        this.linea = numero;
        this.identificador = identificador;
    }

    public Parada(String nombre, int identificador) {
        this.nombre = nombre;
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
}
