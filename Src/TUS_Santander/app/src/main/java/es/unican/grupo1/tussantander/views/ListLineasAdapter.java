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

import es.unican.grupo1.tussantander.model.Linea;
import es.unican.grupo1.tussantander.R;


/**
 * Adapter de la lista de lineas. Determina como se muestran las lineas en cuanto a su aspecto.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */
public class ListLineasAdapter extends ArrayAdapter {
    List<Linea> lineasBus;
    Context context;

    /**
     * Contructor para mostrar las lineas.
     *
     * @param context   contexto de la app
     * @param lineasBus lista de lineas
     */
    public ListLineasAdapter(Context context, List<Linea> lineasBus) {
        super(context, R.layout.custom_list_lineas_layout, lineasBus);
        this.context = context;
        this.lineasBus = lineasBus;
    }// ListLineasAdapter

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout, null, true);
        TextView textViewIdentificador = viewRow.findViewById(R.id.textViewIdentificador);
        TextView textViewName = viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = viewRow.findViewById(R.id.textViewNumero);
        textViewIdentificador.setText(Integer.toString(lineasBus.get(position).getIdentifier()));
        textViewName.setText(lineasBus.get(position).getName().trim());
        // Seleccionar el simbolo de la linea
        switch (lineasBus.get(position).getNumero()) {
            case "1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_1, 0, 0, 0);
                break;
            case "2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_2, 0, 0, 0);
                break;
            case "3":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_3, 0, 0, 0);
                break;
            case "4":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_4, 0, 0, 0);
                break;
            case "5C1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_5, 0, 0, 0);
                textViewIdentificador.setText("5C1");
                break;
            case "5C2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_5, 0, 0, 0);
                textViewIdentificador.setText("5C2");
                break;
            case "6C1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_6, 0, 0, 0);
                textViewIdentificador.setText("6C1");
                break;
            case "6C2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_6, 0, 0, 0);
                textViewIdentificador.setText("6C2");
                break;
            case "7C1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_71, 0, 0, 0);
                textViewIdentificador.setText("7C1");
                break;
            case "7C2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_71, 0, 0, 0);
                textViewIdentificador.setText("7C2");
                break;
            case "11":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_11, 0, 0, 0);
                break;
            case "12":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_12, 0, 0, 0);
                break;
            case "13":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_13, 0, 0, 0);
                break;
            case "14":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_14, 0, 0, 0);
                break;
            case "15":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_15, 0, 0, 0);
                break;
            case "16":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_16, 0, 0, 0);
                break;
            case "17":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_17, 0, 0, 0);
                break;
            case "18":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_18, 0, 0, 0);
                break;
            case "19":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_19, 0, 0, 0);
                break;
            case "20":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_20, 0, 0, 0);
                break;
            case "21":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_21, 0, 0, 0);
                break;
            case "23":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_23, 0, 0, 0);
                break;
            case "N1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_n1, 0, 0, 0);
                textViewIdentificador.setText("N1");
                break;
            case "N2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_n2, 0, 0, 0);
                textViewIdentificador.setText("N2");
                break;
            case "N3":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_n3, 0, 0, 0);
                textViewIdentificador.setText("N3");
                break;
            case "E1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e1, 0, 0, 0);
                textViewIdentificador.setText("E1");
                break;
            case "E2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e2, 0, 0, 0);
                textViewIdentificador.setText("E2");
                break;
            case "E3":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e3, 0, 0, 0);
                textViewIdentificador.setText("E3");
                break;
            case "E4":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e4, 0, 0, 0);
                textViewIdentificador.setText("E4");
                break;
            case "E5":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e5, 0, 0, 0);
                textViewIdentificador.setText("E5");
                break;
            case "E9":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e9, 0, 0, 0);
                textViewIdentificador.setText("E9");
                break;
            case "E30":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e30, 0, 0, 0);
                textViewIdentificador.setText("E30");
                break;
            case "E31":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e31, 0, 0, 0);
                textViewIdentificador.setText("E31");
                break;
            default:
                break;
        }
        return viewRow;
    }
}
