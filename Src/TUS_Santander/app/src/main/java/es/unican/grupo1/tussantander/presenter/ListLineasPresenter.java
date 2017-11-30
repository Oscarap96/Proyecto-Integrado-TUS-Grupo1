package es.unican.grupo1.tussantander.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import java.util.Collections;
import java.util.List;

import es.unican.grupo1.tussantander.model.dataloaders.ParserJSON;
import es.unican.grupo1.tussantander.model.dataloaders.RemoteFetch;
import es.unican.grupo1.tussantander.model.databaseaccess.MisFuncionesBBDD;
import es.unican.grupo1.tussantander.model.databaseaccess.TUSSQLiteHelper;
import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.model.Parada;
import es.unican.grupo1.tussantander.R;
import es.unican.grupo1.tussantander.utils.Utilidades;
import es.unican.grupo1.tussantander.views.ILineasFragment;


/**
 * Presenter de lineas. Se encarga de la logica entre la interfaz de lineas y el modelo.
 */
public class ListLineasPresenter implements IListLineasPresenter {
    private ILineasFragment listLineasView;
    private List<Linea> listaLineasBus;
    private RemoteFetch remoteFetchLineas;
    private RemoteFetch remoteFetchActualizar;
    private Context context;
    private static final String ERROR= "ERROR";
    private RemoteFetch remoteFetchParadas;
    private static final String ENTRA = "ENTRA";

    private static final String DBTUS="DBTUS";
    private String dbPath;

    /**
     * Constructor.
     *
     * @param context        contexto de la app
     * @param listLineasView view para las lineas
     */
    public ListLineasPresenter(Context context, ILineasFragment listLineasView) {
        this.listLineasView = listLineasView;
        this.remoteFetchLineas = new RemoteFetch();
        this.remoteFetchActualizar = new RemoteFetch();
        this.context = context;
        this.remoteFetchParadas = new RemoteFetch();
        dbPath = context.getString(R.string.dbPath);
    }

    /**
     * Clase para hacer una tarea asincrona al descargar los datos.
     */
    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            listLineasView.getDialog().setCancelable(false);
            //Muestra mensaje de cargando datos...
            listLineasView.showProgress(true);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listLineasView.showList(getListaLineasBus());
                listLineasView.showProgress(false);
                //Muestra el toast con el mensaje
                Toast toast1 = Toast.makeText(context, R.string.mensajeToast1, Toast.LENGTH_SHORT);
                toast1.show();
            } else {
                listLineasView.showProgress(false);
                listLineasView.showErrorMessage();
            }
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return obtenLineas();
            } catch (Exception e) {

                Log.e(ERROR,"Error en la obtención de las lineas");

                return false;
            }
        }
    }

    /**
     * Clase para hacer una tarea asincrona al descargar los datos.
     */
    class RetrieveFeedTask2 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            listLineasView.getDialog().setCancelable(false);
            //Muestra mensaje de cargando datos...
            listLineasView.showProgress(true);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                listLineasView.hideErrorMessage();
                listLineasView.showList(getListaLineasBus());
                listLineasView.showProgress(false);
                //Muestra el toast con el mensaje
                Toast toast1 = Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT);
                toast1.show();
            } else {
                listLineasView.showProgress(false);
                //Muestra el toast con el mensaje
                Toast toast1 = Toast.makeText(context, "No hay Internet", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                return recargaLineas();
            } catch (Exception e) {

                Log.e(ERROR,"Error en la obtención de las lineas");

                return false;
            }
        }
    }

    /**
     * Inicia la tarea asincrona.
     */
    public void start() {
        new RetrieveFeedTask().execute();
    }// start

    /**
     * Inicia la tarea asincrona.
     */
    public void start1() {
        new RetrieveFeedTask2().execute();
    }// start

    /**
     * Comprueba si las líneas están almacenadas en la BBDD
     *
     * @return true si las líneas están en la BBDD
     * o false en caso contrario.
     */
    public boolean isCompleta() {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, DBTUS, null, 1);
        SQLiteDatabase db = tusdbh.getWritableDatabase();
        if (db != null) {
            List<Linea> listaLinea = funciones.obtenerLineas(db);
            if (listaLinea.size() > 30) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean obtenLineas() {
        MisFuncionesBBDD funciones = new MisFuncionesBBDD();
        if (remoteFetchLineas.checkDataBase(dbPath, context) && isCompleta()) {

            TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, DBTUS, null, 1);
            SQLiteDatabase db = tusdbh.getWritableDatabase();
            Log.d("BBDD: ", "SI hay base de datos");
            //Si hemos abierto correctamente la base de datos
            if (db != null) {
                //SE OBTIENEN LOS DATOS DE LA BASE DE DATOS
                listaLineasBus = funciones.obtenerLineas(db);
            } else {
                throw new NullPointerException();
            }

            db.close();

            Collections.sort(listaLineasBus); //ordenación de las lineas de buses
            return true;

        } else {
            try {
                Log.d("BBDD: ", "NO hay base de datos");

                //SE OBTIENEN LOS DATOS DE INTERNET...
                remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
                listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());
                Log.d(ENTRA, "Obtiene lineas de JSON:" + listaLineasBus.size());

                TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, DBTUS, null, 1);

                SQLiteDatabase db = tusdbh.getWritableDatabase();

                // Asignar paradas a lineas
                Linea laLinea;
                int identiLinea;
                List<Parada> paradasDeLinea;

                for (int i = 0; i < listaLineasBus.size(); i++) {
                    laLinea = listaLineasBus.get(i);
                    identiLinea = laLinea.getIdentifier();
                    Log.d("ENTRA EN EL BUCLE", "Casi obtiene paradas de linea de JSON");
                    Log.d("IdentLinea", "Identiline" + identiLinea);
                    remoteFetchParadas.getJSON((RemoteFetch.URL_SECUENCIA_PARADAS));
                    paradasDeLinea = ParserJSON.readArraySecuenciaParadas(remoteFetchParadas.getBufferedData(), identiLinea);
                    Log.d(ENTRA, "Obtiene paradas de linea de JSON:" + paradasDeLinea.size());
                    if (db != null) {
                        funciones.insertaParadasLinea(paradasDeLinea, identiLinea, db);
                    }
                }

                // Insertar lineas
                // Si hemos abierto correctamente la base de datos

                if (db != null) {
                    Log.d("DB Creada", "creada la base de datos");
                    funciones.insertaListaLineas(listaLineasBus, db);
                }

                if (db == null) throw new NullPointerException();
                db.close();
                Collections.sort(listaLineasBus);
                Log.d("ordenadas", "lineas ordenadas");
                return true;
            } catch (IOException e) {
                return false;
            } catch (Exception e) {
                Log.e(ERROR, "Error en la obtención de las lineas de Bus: " + e.getMessage());
                return false;
            }
        }
    }

    @Override
    public boolean recargaLineas() {
        // Se comprueba si hay internet: si lo hay se intentan actualizar las líneas,
        // si no hay internet no se intentan actualizar y se retorna false
        if (Utilidades.isOnline(context)) {
            Log.d("HAY INTERNET","HAY INTERNET");
            //SE CARGA LA DB Y HABILITA SUS FUNCIONES
            MisFuncionesBBDD funciones = new MisFuncionesBBDD();

            if (remoteFetchLineas.checkDataBase(dbPath, context) && isCompleta()) {
                TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, "DBTUS", null, 1);
                SQLiteDatabase db = tusdbh.getWritableDatabase();

                //Si hemos abierto correctamente la base de datos
                if (db != null) {
                    List<Linea> listasec = listaLineasBus;
                    //SE BORRAN LAS LINEAS PARA ACTUALIZAR LAS LINNEAS
                    //funciones.borrarListaLineas(listaLineasBus, db);

                    try {
                        remoteFetchActualizar.getJSON(RemoteFetch.URL_LINEAS_BUS);
                        listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetchActualizar.getBufferedData());
                        //InputStream is = context.getResources().openRawResource(R.raw.lineas_test);
                        //listaLineasBus = ParserJSON.readArrayLineasBus(is);
                        Collections.sort(listaLineasBus);
                    } catch (IOException e) {
                        return false;

                    }
                    funciones.borrarListaLineas(listasec, db);

                    funciones.insertaListaLineas(listaLineasBus, db);

                } else {
                    throw new NullPointerException();
                }
            } else {
                try {
                    //SE OBTIENEN LOS DATOS DE INTERNET...
                    remoteFetchLineas.getJSON(RemoteFetch.URL_LINEAS_BUS);
                    listaLineasBus = ParserJSON.readArrayLineasBus(remoteFetchLineas.getBufferedData());

                    TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(context, DBTUS, null, 1);
                    SQLiteDatabase db = tusdbh.getWritableDatabase();

                    funciones.insertaListaLineas(listaLineasBus, db);
                } catch (Exception e) {
                    return false;
                }
            }
            return true;
        }
        Log.d("NO HAY INTERNET","NO HAY INTERNET");
        return false;
}

    @Override
    public List<Linea> getListaLineasBus() {
        return listaLineasBus;
    }//getListaLineasBus

    @Override
    public String getTextoLineas() {
        String textoLineas = "";
        if (listaLineasBus != null) {
            for (int i = 0; i < listaLineasBus.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(textoLineas);
                stringBuilder.append(listaLineasBus.get(i).getNumero());
                stringBuilder.append("\n\n");
                textoLineas = stringBuilder.toString();
            }//for
        } else {
            textoLineas = "Sin lineas";
        }//if
        return textoLineas;
    }//getTextoLineas
    public Context getContexto(){
        return context;
    }
}// ListLineasPresenter
