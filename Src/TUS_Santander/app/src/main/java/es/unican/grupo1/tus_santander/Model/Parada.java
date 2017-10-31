package es.unican.grupo1.tus_santander.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrian on 25/10/2017.
 */

public class Parada {

    private String nombre;
    private int identificador;
    private List<Integer> lineas;

    public Parada(String nombre,int identificador){

        this.nombre=nombre;
        this.identificador=identificador;
        lineas=new ArrayList<Integer>();
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

    public List<Integer>getLineas(){return lineas;}

    public void setLinea(Integer i){lineas.add(i);}
}
