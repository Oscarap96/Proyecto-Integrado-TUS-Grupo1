package es.unican.grupo1.tussantander.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tussantander.model.Parada;

/**
 * Utilidades contiene metodos estaticos referentes a la logica de negocio. Se pueden utilizar
 * donde hagan falta.
 */
public class Utilidades {

    /**
     * Constructor privado para evitar que se creen objetos de esta clase, ya que es una clase de
     * metodos estaticos.
     */
    private Utilidades() {
        throw new IllegalStateException("Utility class");
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

    /**
     * Método que comprueba si el teléfono tiene conexión a internet.
     * @param context contexto
     * @return true si el teléfono tiene conexión a internet
     * @return false si el teléfono no tiene conexión a internet
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}