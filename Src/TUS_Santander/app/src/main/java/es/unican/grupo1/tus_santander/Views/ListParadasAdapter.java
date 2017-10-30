package es.unican.grupo1.tus_santander.Views;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import es.unican.grupo1.tus_santander.Model.Parada;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    public ListParadasAdapter(Context context, List<Parada> paradasBus) {
        super(context, R.layout.custom_list_paradas_layout, paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;
    }// ListLineasAdapter
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_paradas_layout,null,true);
        TextView lineasParada=(TextView) viewRow.findViewById(R.id.textView_lineasParada);
        TextView nombreParada=(TextView) viewRow.findViewById(R.id.textView_nombreParada);
        TextView idParada=(TextView) viewRow.findViewById(R.id.textView_idParada);
        TextView separador=(TextView) viewRow.findViewById(R.id.textView_separador);
        idParada.setText(paradasBus.get(position).getIdentificador());
        separador.setText(paradasBus.get(position).getIdentificador());
        nombreParada.setText(paradasBus.get(position).getIdentificador());
        return viewRow;
    }
}
