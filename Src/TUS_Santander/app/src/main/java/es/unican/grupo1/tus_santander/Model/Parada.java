package es.unican.grupo1.tus_santander.Model;

/**
 * Created by Adrian on 25/10/2017.
 */

public class Parada {

    private String nombre;
    private int identificador;


    public Parada(String nombre,int identificador){

        this.nombre=nombre;
        this.identificador=identificador;
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
}
