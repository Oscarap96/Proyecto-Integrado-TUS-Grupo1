package es.unican.grupo1.tus_santander.Model.DataLoaders;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Estimacion;
import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;


/**
 * Created by alejandro on 27/09/16.
 * Clase que contiene los metodos necesarios para parsear el JSON que devuelve el servicio "REST" con
 * los diferentes datos del TUS de Santander
 */
public class ParserJSON {

    static BaseTUS bd;

    /**
     * Método para obtener todas las lineas de buses
     *
     * @param in InputStream del JSON con las lineas de buses
     * @return Lista con todas las lineas
     * @throws IOException
     */
    public static List<Linea> readArrayLineasBus(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Linea> listLineasBus = new ArrayList<Linea>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext())
                    listLineasBus.add(readLinea(reader));
            } else {
                reader.skipValue();
            }
        }
        Log.d("Lineas buses", listLineasBus.toString());
        Collections.sort(listLineasBus);
        return listLineasBus;
    }

    /**
     * Método que se encarga de leer una parada
     *
     * @param reader
     * @return
     * @throws IOException
     */
    private static Linea readLinea(JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        int cont = 1;//Contador de ids
        String name = "", numero = "";
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
            cont++;
        }
        reader.endObject();
        return new Linea(name, numero, identifier);
    }

    /**
     * Método para obtener todas las paradas de buses
     *
     * @param in InputStream del JSON con las paradas de buses
     * @return Lista con todas las paradas
     * @throws IOException
     */
    public static List<Parada> readArrayParadas(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Parada> listParadasBus = new ArrayList<Parada>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext())
                    listParadasBus.add(readParada(reader));
            } else {
                reader.skipValue();
            }
        }
        Log.d("Lineas buses", listParadasBus.toString());
        return listParadasBus;
    }

    /**
     * Lee una parada
     *
     * @param reader
     * @return
     * @throws IOException
     */
    private static Parada readParada(JsonReader reader) throws IOException {
        reader.beginObject(); //Leemos un object
        int cont = 1;//Contador de ids para la bd
        String nombre = "";
        int identifier = -1;
        while (reader.hasNext()) {
            String n = reader.nextName();
            if (n.equals("ayto:parada")) {
                nombre = reader.nextString();
            } else if (n.equals("dc:identifier")) {
                identifier = reader.nextInt();
                //  cogeLineas(reader,identifier);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Parada(nombre, identifier);
    }

    public static List<Integer> cogeLineas(InputStream in, List<Parada> identificadorParada) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Integer> lineas = new ArrayList<Integer>();
        int aux = 0;
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object
                if (name.equals("ayto:linea")) {
                    aux = reader.nextInt();
                }
                if (name.equals("ayto:parada")) {
                    for (int i = 0; i < identificadorParada.size(); i++) {
                        if (reader.nextInt() == identificadorParada.get(i).getIdentificador()) {
                            aux = reader.nextInt();
                            lineas.add(aux);
                            identificadorParada.get(i).setLinea(aux);
                        }
                    }
                } else {
                    reader.skipValue();
                }
            }
        }
        Log.d("Lineas de la parada", lineas.toString());
        return lineas;
    }

    public static List<Parada> readArraySecuenciaParadas(InputStream in, int identifierLinea) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Parada> listParadasBus = new ArrayList<Parada>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
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
        Log.d("Lineas buses", listParadasBus.toString());
        return listParadasBus;
    }

    private static Parada readParadaSecuencia(JsonReader reader, int identifierLinea) throws IOException {
        //Leemos un object
        int cont = 1;//Contador de ids para la bd
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
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Estimacion> listEstimaciones = new ArrayList<Estimacion>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
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
                    // en caso de que no pueda leer el campo porque esta vacio
                    e.printStackTrace();
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
