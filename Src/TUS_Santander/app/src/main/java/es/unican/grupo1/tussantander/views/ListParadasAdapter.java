package es.unican.grupo1.tussantander.views;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import es.unican.grupo1.tussantander.model.Parada;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tussantander.R;


/**
 * Determinan como se muestran graficamente las paradas.
 */
public class ListParadasAdapter extends ArrayAdapter {
    List<Parada> paradasBus;
    Context context;

    /**
     * Constructor para mostrar paradas.
     *
     * @param context    context de la app
     * @param paradasBus lista de paradas
     */
    public ListParadasAdapter(Context context, List<Parada> paradasBus) {
        super(context, R.layout.custom_list_paradas_layout, paradasBus);
        this.context = context;
        this.paradasBus = paradasBus;
    }// ListLineasAdapter

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_paradas_layout, null, true);
        TextView nombreParada = viewRow.findViewById(R.id.textView_nombreParada);
        TextView idParada = viewRow.findViewById(R.id.textView_idParada);
        TextView separador = viewRow.findViewById(R.id.textView_separador);

        idParada.setText(String.valueOf(paradasBus.get(position).getIdentificador()));
        separador.setText(" - ");
        nombreParada.setText(String.valueOf(paradasBus.get(position).getNombre()).trim());
        return viewRow;
    }
}
