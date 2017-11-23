package es.unican.grupo1.tussantander.model;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Busca sobre una lista de paradas segun los criterios especificados en los tests de aceptacion
     * de Buscar parada de una linea.
     *
     * @param paradas  lista de paradas sobre la que buscar
     * @param busqueda texto de busqueda introducido
     * @return lista de resultados
     */
    public static List<Parada> buscarParada(List<Parada> paradas, String busqueda) {
        // al comprobar nombre tendre que quitar los acentos tanto de la parada como de la busqueda
        // procesar el texto de busqueda para quitarle detalles que dificulten la busqueda
        String textoBuscado = busqueda.toUpperCase();
        textoBuscado = quitarSimbolosInnecesarios(textoBuscado);
        textoBuscado = quitarAcentos(textoBuscado);
        // si la parada es un resultado de busqueda, lo anhade como resultado
        List<Parada> resultado = new ArrayList<>();
        for (Parada parada : paradas) {
            if (comprobarResultadoParada(parada, textoBuscado)) {
                resultado.add(parada);
            }
        }
        return resultado;
    }

    /**
     * Dada una parada y un texto de busqueda, comprueba si la parada seria un resultado a esa
     * busqueda. Comprueba el criterio de buscar tanto en el campo de id como en el nombre de la
     * parada de manera unificada y ademas, que la parada contenga todos las palabras buscadas.
     *
     * @param parada   parada a comprobar si satisface la busqueda
     * @param busqueda texto buscado
     * @return true en caso de que parada sea un resultado de busqueda
     */
    private static boolean comprobarResultadoParada(Parada parada, String busqueda) {
        boolean resultado = true;
        // obtener texto a comprobar de la parada
        String textoParada = parada.getNombre() + " " + Integer.toString(parada.getIdentificador());
        textoParada = quitarAcentos(textoParada);
        // obtener palabras de busqueda
        // comprobar que todas las palabras de busqueda estan contenidas en el texto de la parada
        for (String palabra : busqueda.split(" ")) {
            if (!textoParada.contains(palabra)) {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    /**
     * Cambia las vocales con acento por su correspondiente sin acento.
     *
     * @param busqueda texto buscado
     * @return texto buscado sin acentos
     */
    private static String quitarAcentos(String busqueda) {
        String resultado = busqueda.replace("Á", "A");
        resultado = resultado.replace("É", "E");
        resultado = resultado.replace("Í", "I");
        resultado = resultado.replace("Ó", "O");
        resultado = resultado.replace("Ú", "U");
        return resultado;
    }

    /**
     * Quita los caracteres que puede haber anhadido el usuario por equivocarse con la interfaz
     * mostrada debajo.
     *
     * @param busqueda texto buscado
     * @return texto buscado sin simbolos innecesarios
     */
    private static String quitarSimbolosInnecesarios(String busqueda) {
        /*
        no quitar los puntos porque en ese caso no tienen que salir resultados, seria culpa del
        usuario, no hay razon para que ponga un punto sin querer
         */
        return busqueda.replace("-", "");
    }
}
