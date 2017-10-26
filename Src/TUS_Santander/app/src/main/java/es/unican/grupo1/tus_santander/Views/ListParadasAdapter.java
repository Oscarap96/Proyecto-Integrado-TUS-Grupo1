package es.unican.grupo1.tus_santander.Views;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.Model.Parada;
import es.unican.grupo1.tus_santander.R;

/**
 * Created by Adrian on 26/10/2017.
 */

public class ListParadasAdapter extends ArrayAdapter {
    List<Parada> paradasBus;
    Context context;

    public ListParadasAdapter (Context context, List<Parada> paradasBus){
        super(context, R.layout.custom_list_paradas_layout,paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;
    }// ListLineasAdapter
}
