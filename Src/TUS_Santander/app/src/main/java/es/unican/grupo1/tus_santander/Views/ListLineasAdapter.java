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
        TextView textViewIdentificador = (TextView) viewRow.findViewById(R.id.textViewIdentificador);
        TextView textViewName = (TextView) viewRow.findViewById(R.id.textViewName);
        TextView textViewNumero = (TextView) viewRow.findViewById(R.id.textViewNumero);
        textViewIdentificador.setText(Integer.toString(lineasBus.get(position).getIdentifier()));
        textViewName.setText(lineasBus.get(position).getName().trim());
        //textViewNumero.setText(lineasBus.get(position).getNumero().trim());

        switch (lineasBus.get(position).getNumero())
        {
            case "1":
                textViewNumero.setTextColor(Color.parseColor("#FD000A"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_1,0,0,0);
                break;
            case "2":
                textViewNumero.setTextColor(Color.parseColor("#A903CE"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_2,0,0,0);
                break;
            case "3":
                textViewNumero.setTextColor(Color.parseColor("#FFD21D"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_3,0,0,0);
                break;
            case "4":
                textViewNumero.setTextColor(Color.parseColor("#21C4E3"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_4,0,0,0);
                break;
            case "5C1":
                textViewNumero.setTextColor(Color.parseColor("#929292"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_5,0,0,0);
                break;
            case "5C2":
                textViewNumero.setTextColor(Color.parseColor("#929292"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_5,0,0,0);
                break;
            case "6C1":
                textViewNumero.setTextColor(Color.parseColor("#157A3A"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_6,0,0,0);
                break;
            case "6C2":
                textViewNumero.setTextColor(Color.parseColor("#157A3A"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_6,0,0,0);
                break;
            case "7C1":
                //textViewNumero.setTextColor(Color.parseColor("#F26319"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_71,0,0,0);
                break;
            case "7C2":
                textViewNumero.setTextColor(Color.parseColor("#F26319"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_71,0,0,0);
                break;
            case "11":
                textViewNumero.setTextColor(Color.parseColor("#070F47"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_11,0,0,0);
                break;
            case "12":
                textViewNumero.setTextColor(Color.parseColor("#9ECA6B"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_12,0,0,0);
                break;
            case "13":
                textViewNumero.setTextColor(Color.parseColor("#9083A9"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_13,0,0,0);
                break;
            case "14":
                textViewNumero.setTextColor(Color.parseColor("#0F69AB"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_14,0,0,0);
                break;
            case "15":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_15,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#FFD61B"));

                break;
            case "16":
                textViewNumero.setTextColor(Color.parseColor("#681433"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_16,0,0,0);
                break;
            case "17":
                textViewNumero.setTextColor(Color.parseColor("#F37E80"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_17,0,0,0);
                break;
            case "18":
                textViewNumero.setTextColor(Color.parseColor("#B4E5D9"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_18,0,0,0);
                break;
            case "19":
                textViewNumero.setTextColor(Color.parseColor("#01848D"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_19,0,0,0);
                break;
            case "20":
                textViewNumero.setTextColor(Color.parseColor("#73F4A7"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_20,0,0,0);
                break;
            case "21":
                textViewNumero.setTextColor(Color.parseColor("#9DCD60"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_21,0,0,0);
                break;
            case "23":
                textViewNumero.setTextColor(Color.parseColor("#C8C8C8"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_23,0,0,0);
                break;
            case "N1":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_n1,0,0,0);
                break;
            case "N2":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_n2,0,0,0);
                break;
            case "N3":
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_n3,0,0,0);
                break;
            case "E1":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e1,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E2":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e2,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E3":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e3,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E4":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e4,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E5":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e5,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E9":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e9,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E30":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e30,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
            case "E31":
                textViewNumero.setCompoundDrawablesWithIntrinsicBounds(R.drawable.e31,0,0,0);
                textViewNumero.setTextColor(Color.parseColor("#010101"));
                break;
        }

        return viewRow;
    }

}
