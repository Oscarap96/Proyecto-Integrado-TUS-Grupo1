package es.unican.grupo1.tussantander.views;

import android.content.Context;
import android.graphics.Color;
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
    private static final String COLORGRIS = "#929292";
    private static final String COLORVERDE = "#157A3A";
    private static final String COLORNARANJA = "#F26319";
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
        TextView campoLinea = viewRow.findViewById(R.id.textView_linea);
        TextView linea = viewRow.findViewById(R.id.textView_nombreLinea);
        TextView tiempo1 = viewRow.findViewById(R.id.textView_tiempoBus1);
        TextView tiempo2 = viewRow.findViewById(R.id.textView_tiempoBus2);
        linea.setText(String.valueOf(estimaciones.get(position).getEtiquetaLinea().trim()));
        tiempo1.setText(String.valueOf(estimaciones.get(position).getTiempo1min()));
        //Con esto nos aseguramos de que el texto (p.e. Línea 1)
        //aparece con el color oficial de TUS.
        switch (estimaciones.get(position).getEtiquetaLinea()){
            case "1":
                linea.setTextColor(Color.parseColor("#FD000A"));
                campoLinea.setTextColor(Color.parseColor("#FD000A"));
                break;
            case "2":
                linea.setTextColor(Color.parseColor("#A903CE"));
                campoLinea.setTextColor(Color.parseColor("#A903CE"));
                break;
            case "3":
                linea.setTextColor(Color.parseColor("#FFD21D"));
                campoLinea.setTextColor(Color.parseColor("#FFD21D"));
                break;
            case "4":
                linea.setTextColor(Color.parseColor("#21C4E3"));
                campoLinea.setTextColor(Color.parseColor("#21C4E3"));
                break;
            case "5C1":
                linea.setTextColor(Color.parseColor(COLORGRIS));
                campoLinea.setTextColor(Color.parseColor(COLORGRIS));
                break;
            case "5C2":
                break;
            case "6C1":
                linea.setTextColor(Color.parseColor(COLORVERDE));
                campoLinea.setTextColor(Color.parseColor(COLORVERDE));
                break;
            case "6C2":
                break;
            case "7C1":
                linea.setTextColor(Color.parseColor(COLORNARANJA));
                campoLinea.setTextColor(Color.parseColor(COLORNARANJA));
                break;
            case "7C2":
                break;
            case "11":
                linea.setTextColor(Color.parseColor("#070F47"));
                campoLinea.setTextColor(Color.parseColor("#070F47"));
                break;
            case "12":
                linea.setTextColor(Color.parseColor("#9ECA6B"));
                campoLinea.setTextColor(Color.parseColor("#9ECA6B"));
                break;
            case "13":
                linea.setTextColor(Color.parseColor("#9083A9"));
                campoLinea.setTextColor(Color.parseColor("#9083A9"));
                break;
            case "14":
                linea.setTextColor(Color.parseColor("#0F69AB"));
                campoLinea.setTextColor(Color.parseColor("#0F69AB"));
                break;
            case "15":
                linea.setTextColor(Color.parseColor("#FFD61B"));
                campoLinea.setTextColor(Color.parseColor("#FFD61B"));
                break;
            case "16":
                linea.setTextColor(Color.parseColor("#681433"));
                campoLinea.setTextColor(Color.parseColor("#681433"));
                break;
            case "17":
                linea.setTextColor(Color.parseColor("#F37E80"));
                campoLinea.setTextColor(Color.parseColor("#F37E80"));
                break;
            case "18":
                linea.setTextColor(Color.parseColor("#B4E5D9"));
                campoLinea.setTextColor(Color.parseColor("#B4E5D9"));
                break;
            case "19":
                linea.setTextColor(Color.parseColor("#01848D"));
                campoLinea.setTextColor(Color.parseColor("#01848D"));
                break;
            case "20":
                linea.setTextColor(Color.parseColor("#73F4A7"));
                campoLinea.setTextColor(Color.parseColor("#73F4A7"));
                break;
            case "21":
                linea.setTextColor(Color.parseColor("#9DCD60"));
                campoLinea.setTextColor(Color.parseColor("#9DCD60"));
                break;
            case "23":
                linea.setTextColor(Color.parseColor("#C8C8C8"));
                campoLinea.setTextColor(Color.parseColor("#C8C8C8"));
                break;
            default:
                break;
        }
        //Utilizamos esto para las lineas con dos autobuses(p.e. 5C1 y 5C2)
       if(estimaciones.get(position).getEtiquetaLinea().equals("5C2")){
           linea.setTextColor(Color.parseColor(COLORGRIS));
           campoLinea.setTextColor(Color.parseColor(COLORGRIS));
       }else if(estimaciones.get(position).getEtiquetaLinea().equals("6C2")){
           linea.setTextColor(Color.parseColor(COLORVERDE));
           campoLinea.setTextColor(Color.parseColor(COLORVERDE));
       }else if(estimaciones.get(position).getEtiquetaLinea().equals("7C2")){
           linea.setTextColor(Color.parseColor(COLORNARANJA));
           campoLinea.setTextColor(Color.parseColor(COLORNARANJA));
       }
        // Oculta la segunda linea en caso de que no tenga valor (-1)
        if (estimaciones.get(position).getTiempo2min() == -1) {
            viewRow.findViewById(R.id.linearLayout_terceraFila).setVisibility(View.GONE);
        } else {
            tiempo2.setText(String.valueOf(estimaciones.get(position).getTiempo2min()));
        }
        return viewRow;
    }
}
