package es.unican.grupo1.tussantander.model.dataloaders;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;


/**
 * Created by alejandro on 27/09/16.
 * Clase que contiene los metodos necesarios para parsear el JSON que devuelve el servicio "REST" con
 * los diferentes datos del TUS de Santander.
 */
public class ParserJSON {
    private static final String UTF = "UTF-8";
    private static final String RESOURCES = "resources";
    private static final String LINEAS_BUSES = "Lineas buses";

    /**
     * Constructor
     */
    private ParserJSON(){
        //
    }
    /**
     * Método para obtener todas las lineas de buses
     *
     * @param in InputStream del JSON con las lineas de buses
     * @return Lista con todas las lineas
     * @throws IOException en caso de que no pueda leer el inputstream
     */
    public static List<Linea> readArrayLineasBus(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF));
        List<Linea> listLineasBus = new ArrayList<>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(RESOURCES)) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext())
                    listLineasBus.add(readLinea(reader));
            } else {
                reader.skipValue();
            }
        }
        Log.d(LINEAS_BUSES, listLineasBus.toString());
        Collections.sort(listLineasBus);
        return listLineasBus;
    }

    /**
     * Método que se encarga de leer una linea
     *
     * @param reader para leer uno de los objetos
     * @return nueva linea
     * @throws IOException en caso de que no pueda leer los datos
     */
    private static Linea readLinea(JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        String name = "";
        String numero = "";
        int identifier = -1;
        while (reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:numero")) {
                numero = reader.nextString();
            } else if (n.equals("dc:name")) {
                name = reader.nextString();
            } else if (n.equals("dc:identifier")) {
                identifier = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Linea(name, numero, identifier);
    }

    /**
     * Método para obtener todas las paradas de buses.
     *
     * @param in InputStream del JSON con las paradas de buses
     * @return Lista con todas las paradas
     * @throws IOException en caso de que no pueda leer las paradas
     */
    public static List<Parada> readArrayParadas(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF));
        List<Parada> listParadasBus = new ArrayList<>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(RESOURCES)) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext())
                    listParadasBus.add(readParada(reader));
            } else {
                reader.skipValue();
            }
        }
        Log.d(LINEAS_BUSES, listParadasBus.toString());
        return listParadasBus;
    }

    /**
     * Lee una parada.
     *
     * @param reader para leer el objeto
     * @return nueva parada
     * @throws IOException en caso de que no pueda leer la parada
     */
    private static Parada readParada(JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        String nombre = "";
        int identifier = -1;
        while (reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:parada")) {
                nombre = reader.nextString();
            } else if (n.equals("dc:identifier")) {
                identifier = reader.nextInt();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Parada(nombre, identifier);
    }

    /**
     * Genera una lista de paradas ordenadas segun las paradas que visita el autobus.
     *
     * @param in              json
     * @param identifierLinea linea de la que queremos obtener paradas
     * @return lista de paradas
     * @throws IOException en caso de que no pueda leerlo
     */
    public static List<Parada> readArraySecuenciaParadas(InputStream in, int identifierLinea) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF));
        List<Parada> listParadasBus = new ArrayList<>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(RESOURCES)) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext()) {
                    Parada p = readParadaSecuencia(reader, identifierLinea);
                    if (p != null)
                        listParadasBus.add(p);
                }
            } else {
                reader.skipValue();
            }
        }
        Log.d(LINEAS_BUSES, listParadasBus.toString());
        return listParadasBus;
    }

    /**
     * Lee una parada para generar la lista de paradas.
     *
     * @param reader          objeto a procesar
     * @param identifierLinea linea de la que queremos obtener paradas
     * @return una nueva parada o null en caso de que no pueda
     * @throws IOException en caso de que no pueda leerlo
     */
    private static Parada readParadaSecuencia(JsonReader reader, int identifierLinea) throws IOException {
        //Leemos un object
        String nombre = "";
        int identifier = -1;
        int linea = -1;
        reader.beginObject();
        boolean flag = false;
        while (reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:Linea")) {
                linea = reader.nextInt();
                if (linea != identifierLinea)
                    flag = true;
            } else if (n.equals("ayto:NParada")) {
                identifier = reader.nextInt();
            } else if (n.equals("ayto:NombreParada")) {
                nombre = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!flag)
            return new Parada(nombre, identifier, linea);
        else
            return null;
    }

    /**
     * Obtiene una lista de estimaciones de un InputStream.
     *
     * @param in       InputStream correspondiente a leer
     * @param paradaId parada de la que queremos obtener sus estimaciones
     * @return lista ordenada de todas las estimaciones
     * @throws IOException en caso de que no pueda leer correctamente
     */
    public static List<Estimacion> readArrayEstimaciones(InputStream in, int paradaId) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, UTF));
        List<Estimacion> listEstimaciones = new ArrayList<>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(RESOURCES)) {
                reader.beginArray(); //cada elemento del array es un object
                // recorre los elementos del resources
                while (reader.hasNext()) {
                    Estimacion estimacion = readEstimacion(reader, paradaId);
                    if (estimacion != null)
                        listEstimaciones.add(estimacion);
                }
            } else {
                reader.skipValue();
            }
        }
        Collections.sort(listEstimaciones);
        return listEstimaciones;
    }

    /**
     * Lee un campo para una estimacion.
     *
     * @param reader   json a procesar
     * @param paradaId parada de la que queremos conocer sus estimaciones
     * @return estimacion con sus correspondientes campos
     * @throws IOException en caso de que no pueda leer correctamente
     */
    private static Estimacion readEstimacion(JsonReader reader, int paradaId) throws IOException {
        String etiquetaLinea = "";
        int tiempo1Seg = -1;
        int tiempo2Seg = -1;
        // parada para comprobar que es una estimacion a esa parada
        int parada = -1;
        // flag para descartar los campos que no me interesen
        boolean flag = false;
        // recorrer los campos de cada objeto
        reader.beginObject();
        while (reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:tiempo1")) {
                tiempo1Seg = reader.nextInt();
            } else if (n.equals("ayto:tiempo2")) {
                try {
                    tiempo2Seg = reader.nextInt();
                } catch (Exception e) {
                    reader.skipValue();
                    tiempo2Seg = -1;
                }
            } else if (n.equals("ayto:etiqLinea")) {
                etiquetaLinea = reader.nextString();
            } else if (n.equals("ayto:paradaId")) {
                // comprobar que es una estiamcion para la parada correspondiente
                parada = reader.nextInt();
                if (parada != paradaId) {
                    flag = true;
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!flag) {
            if (tiempo2Seg == -1) {
                return new Estimacion(etiquetaLinea, tiempo1Seg);
            } else {
                return new Estimacion(etiquetaLinea, tiempo1Seg, tiempo2Seg);
            }
        } else {
            return null;
        }
    }
}//ParserJSON
