package es.unican.grupo1.tus_santander.Model;

import java.util.List;

/**
 * Clase que almacena la información referente a una línea de TUS
 * Created by alejandro on 4/08/17.
 */

public class Linea implements Comparable{


    private String name;
    private String numero;
    private int identifier;



    public Linea(String name, String numero, int identifier){
        this.name = name;
        this.numero = numero;
        this.identifier = identifier;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getIdentifier() {
        return identifier;
    }

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
