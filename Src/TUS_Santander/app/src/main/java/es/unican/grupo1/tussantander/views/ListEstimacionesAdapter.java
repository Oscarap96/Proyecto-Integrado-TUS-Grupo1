package es.unican.grupo1.tussantander.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import es.unican.grupo1.tussantander.model.Estimacion;
import es.unican.grupo1.tussantander.R;


/**
 * Adapter para las estimaciones. Determina como se muestran graficamente las estimaciones.
 */
public class ListEstimacionesAdapter extends ArrayAdapter {
    List<Estimacion> estimaciones;
    Context context;

    /**
     * Contructor para mostrar las estimaciones.
     *
     * @param context      contexto
     * @param estimaciones lista de estimaciones
     */
    public ListEstimacionesAdapter(Context context, List<Estimacion> estimaciones) {
        super(context, R.layout.custom_list_estimaciones_layout, estimaciones);
        this.context = context;
        this.estimaciones = estimaciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_estimaciones_layout, null, true);
        TextView linea = viewRow.findViewById(R.id.textView_nombreLinea);
        TextView tiempo1 = viewRow.findViewById(R.id.textView_tiempoBus1);
        TextView tiempo2 = viewRow.findViewById(R.id.textView_tiempoBus2);
        linea.setText(String.valueOf(estimaciones.get(position).getEtiquetaLinea().trim()));
        tiempo1.setText(String.valueOf(estimaciones.get(position).getTiempo1min()));
        // Oculta la segunda linea en caso de que no tenga valor (-1)
        if (estimaciones.get(position).getTiempo2min() == -1) {
            viewRow.findViewById(R.id.linearLayout_terceraFila).setVisibility(View.GONE);
        } else {
            tiempo2.setText(String.valueOf(estimaciones.get(position).getTiempo2min()));
        }
        return viewRow;
    }
}
