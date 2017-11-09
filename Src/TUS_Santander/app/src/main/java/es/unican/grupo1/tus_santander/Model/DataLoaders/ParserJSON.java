package es.unican.grupo1.tus_santander.Model.DataLoaders;

import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;

import static android.R.attr.id;


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
        String nombre = "";
        int identifier = -1;
        int linea = -1;
        reader.beginObject();
        boolean flag = false;
        while (reader.hasNext()) {
            String n = reader.nextName();
            //System.out.println(n);
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


    public static List<Parada> readArraySecuenciaParadas(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Parada> listParadasBus = new ArrayList<Parada>();
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object
                while (reader.hasNext()) {
                    Parada p = readParadaSecuencia(reader);
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

    private static Parada readParadaSecuencia(JsonReader reader) throws IOException {
        //Leemos un object
        String nombre = "";
        int identifier = -1;
        int linea = -1;
        reader.beginObject();
        while (reader.hasNext()) {
            String n = reader.nextName();
            //System.out.println(n);
            if (n.equals("ayto:Linea")) {
                linea = reader.nextInt();
            } else if (n.equals("ayto:NParada")) {
                identifier = reader.nextInt();
            } else if (n.equals("ayto:NombreParada")) {
                nombre = reader.nextString();
            } else {
                reader.skipValue();
            }

        }
        reader.endObject();

        return new Parada(nombre, identifier, linea);
    }
}//ParserJSON
