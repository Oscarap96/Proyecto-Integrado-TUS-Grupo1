package es.unican.grupo1.tus_santander.Views;

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

import es.unican.grupo1.tus_santander.Model.Linea;
import es.unican.grupo1.tus_santander.R;


/**
 * Created by alejandro on 10/08/17.
 * //http://www.viralandroid.com/2016/04/custom-android-listview-example.html
 */

public class ListLineasAdapter extends ArrayAdapter {
    List<Linea> lineasBus;
    Context context;

    public ListLineasAdapter (Context context, List<Linea> lineasBus){
        super(context, R.layout.custom_list_lineas_layout,lineasBus);
        this.context = context;
        this.lineasBus = lineasBus;
    }// ListLineasAdapter

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.custom_list_lineas_layout,null,true);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        textViewName.setText(lineasBus.get(position).getName().trim());
        textViewNumero.setText(lineasBus.get(position).getNumero().trim());

        switch (lineasBus.get(position).getNumero())
        {
            case "1":
                textViewNumero.setTextColor(Color.parseColor("#FD000A"));
                break;
            case "2":
                textViewNumero.setTextColor(Color.parseColor("#A903CE"));
                break;
            case "3":
                textViewNumero.setTextColor(Color.parseColor("#FFD21D"));
                break;
            case "4":
                textViewNumero.setTextColor(Color.parseColor("#21C4E3"));
                break;
            case "5C1":
                textViewNumero.setTextColor(Color.parseColor("#929292"));
                break;
            case "5C2":
                textViewNumero.setTextColor(Color.parseColor("#929292"));
                break;
            case "6C1":
                textViewNumero.setTextColor(Color.parseColor("#157A3A"));
                break;
            case "6C2":
                textViewNumero.setTextColor(Color.parseColor("#157A3A"));
                break;
            case "7C1":
                textViewNumero.setTextColor(Color.parseColor("#F26319"));
                break;
            case "7C2":
                textViewNumero.setTextColor(Color.parseColor("#F26319"));
                break;
            case "11":
                textViewNumero.setTextColor(Color.parseColor("#070F47"));
                break;
            case "12":
                textViewNumero.setTextColor(Color.parseColor("#9ECA6B"));
                break;
            case "13":
                textViewNumero.setTextColor(Color.parseColor("#9083A9"));
                break;
            case "14":
                textViewNumero.setTextColor(Color.parseColor("#0F69AB"));
                break;
            case "15":
                textViewNumero.setTextColor(Color.parseColor("#FFD61B"));
                break;
            case "16":
                textViewNumero.setTextColor(Color.parseColor("#681433"));
                break;
            case "17":
                textViewNumero.setTextColor(Color.parseColor("#F37E80"));
                break;
            case "18":
                textViewNumero.setTextColor(Color.parseColor("#B4E5D9"));
                break;
            case "19":
                textViewNumero.setTextColor(Color.parseColor("#01848D"));
                break;
            case "20":
                textViewNumero.setTextColor(Color.parseColor("#73F4A7"));
                break;
            case "21":
                textViewNumero.setTextColor(Color.parseColor("#9DCD60"));
                break;
            case "23":
                textViewNumero.setTextColor(Color.parseColor("#C8C8C8"));
                break;
            case "N1":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "N2":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "N3":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E1":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E2":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E3":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E4":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E5":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E9":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E30":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E31":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
        }

        return viewRow;
    }

}
