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
     * Método para obtener todas las lineas de buses
     *
     * @param in InputStream del JSON con las lineas de buses
     * @return Lista con todas las lineas
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
     * Lee una linea
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
    /*public static List<Integer> cogeLineas(InputStream in,List<Parada> identificadorParada)throws IOException{
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Integer>lineas=new ArrayList<Integer>();
        int aux=0;
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object


                if (name.equals("ayto:linea")) {
                    aux = reader.nextInt();
                }
                if (name.equals("dc:identifier")) {
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
        Log.d("Lineas de la parada",lineas.toString());
        return lineas;
    }*/

   /** public static List<Integer> cogeLineas(InputStream in, int identificadorParada) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        List<Integer> listLineasParada = new ArrayList<Integer>();
        int aux = 0;
        reader.beginObject(); //summary y resources
        while (reader.hasNext()) {
            String nombre = reader.nextName();
            if (nombre.equals("resources")) {
                reader.beginArray(); //cada elemento del array es un object
            }
        }
        reader.endObject();

        while (reader.hasNext()) {
            String name = reader.nextName();

            if (name.equals("ayto:linea")) {
                aux = reader.nextInt();
            }
            if (name.equals("dc:identifier"))
                if (reader.nextInt() == identificadorParada) {
                    listLineasParada.add(aux);
                } else {
                    reader.skipValue();
                }
        }
        reader.endObject();

        Log.d("Lineas de la parada", listLineasParada.toString());
        return listLineasParada;
    }

*/
   public static List<Integer> readLineasParadas(InputStream in) throws IOException {
       JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
       List<Integer> listParadasBus = new ArrayList<Integer>();
       reader.beginObject(); //summary y resources
       while (reader.hasNext()) {
           String name = reader.nextName();
           if (name.equals("resources")) {
               reader.beginArray(); //cada elemento del array es un object
               while (reader.hasNext())
                   listParadasBus.add(lineasParadas(reader));


           } else {
               reader.skipValue();
           }
       }
       Log.d("Lineas buses", listParadasBus.toString());
       return listParadasBus;
   }
   public static Integer lineasParadas(JsonReader reader) throws IOException {
       reader.beginObject(); //Leemos un object
       int cont = 1;//Contador de ids para la bd
       List<Integer>aux=new ArrayList<Integer>();
       String nombre = "";
       int identifier = -1;
       while (reader.hasNext()) {
           String n = reader.nextName();
           if (n.equals("ayto:linea")) {
               cont = reader.nextInt();
           } else if (n.equals("dc:identifier")) {
               identifier = reader.nextInt();
               aux.add(cont);
               //  cogeLineas(reader,identifier);
           } else {
               reader.skipValue();
           }

       }
       reader.endObject();


   }
   }
}//ParserJSON
