package es.unican.grupo1.tus_santander.Views;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;


import es.unican.grupo1.tus_santander.Model.Database.TUSSQLiteHelper;
import es.unican.grupo1.tus_santander.R;


public class MainActivity extends AppCompatActivity implements DataCommunication {


    private int lineaIdentifier;
    private int paradaIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TUSSQLiteHelper tusdbh = new TUSSQLiteHelper(this, "DBTUS", null, 2);

        SQLiteDatabase db = tusdbh.getWritableDatabase();

        //Si hemos abierto correctamente la base de datos
        if(db != null)
        {
            //Insertamos 5 usuarios de ejemplo
            for(int i=1; i<=5; i++)
            {
                Log.d("Entraaaaaa","Entra inserción de datos"+i);
                ContentValues valores = new ContentValues();

                //Generamos los datos
                int codigo = i;
                String nombre = "Linea" + i;
                String numero = "Numero" + i;
                int identificador = i;

                int codParada = i;
                String nombreP = "Parada" + i;
                int identificadorP = i;

                valores.put("idLinea", codigo);
                valores.put("nombre", nombre);
                valores.put("numero", numero);
                valores.put("identificador", identificador);
                db.insert("Linea", null, valores);
                //db.close();

                //Insertamos los datos en la tabla Linea
               // db.execSQL("INSERT INTO Linea (idLinea, nombre, numero, identificador) " +
                 //       "VALUES (" + codigo + ", '" + nombre +"', '" + numero +"', " + identificador + ")");


//                db.execSQL("INSERT INTO Parada (idParada, nombre, identificador) " +
  //                      "VALUES (" + codParada + ", '" + nombreP +"', " + identificadorP + ")");
            }

            //Cerramos la base de datos
            db.close();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineasFragment fragmentLineas = new LineasFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =  fm.beginTransaction();
        ft.add(R.id.frameLayoutElements,fragmentLineas);
        ft.commit();
    }//onCreate



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }



    @Override
    public int getLineaIdentifier() {
        return lineaIdentifier;
    }

    @Override
    public void setLineaIdentifier(int identifier) {
        this.lineaIdentifier=identifier;
    }

    @Override
    public int getParadaIdentifier() { return paradaIdentifier; }

    @Override
    public void setParadaIdentifier(int paradaIdentifier) { this.paradaIdentifier = paradaIdentifier; }

}// MainActivity
