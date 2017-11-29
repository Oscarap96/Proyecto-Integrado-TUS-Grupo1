package es.unican.grupo1.tussantander.views;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import es.unican.grupo1.tussantander.R;

/**
 * Created by Oscar Alario Pelaz on 21/11/2017.
 */

/**
 * Fragment utilizado para mostrar las tarifas de los autobuses de TUS.
 */
public class TarifasFragment extends Fragment {

    /**
     * Método que crea el fragment que se utiliza para mostrar las tarifas
     * @param inflater LayoutInflater utilizado para las tarifas
     * @param container ViewGroup utilizado para las tarifas
     * @param savedInstanceState Bundle utilizado para las tarifas
     * @return view correspondiente a las tarifas
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_tarifas, container, false);

        //Se busca el botón "MasInfo" y se guarda en una variable
        Button info = view.findViewById(R.id.buttonMasInfo);

        //Se asigna un método al botón para cuando sea pulsado
        info.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //Se guarda la url a la que se desea acceder en un objeto Uri...
                Uri uri = Uri.parse("http://www.tusantander.es/billetes-abonos");
                //...y se hace un intento de conectarse a ella
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }

        });
        return view;
    }
}
