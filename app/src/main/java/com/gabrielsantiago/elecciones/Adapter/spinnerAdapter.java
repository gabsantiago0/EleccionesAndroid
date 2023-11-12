package com.gabrielsantiago.elecciones.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gabrielsantiago.elecciones.R;

public class spinnerAdapter extends BaseAdapter {

    Context context;
    String listaNombres[];
    String listaPartidos[];
    int listaFotos[];
    LayoutInflater inflater;

    public spinnerAdapter(Context context, String[] listaNombres, int[] listaFotos, String[] listaPartidos) {
        this.context = context;
        this.listaNombres = listaNombres;
        this.listaPartidos = listaPartidos;
        this.listaFotos = listaFotos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaNombres.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.spinner_row_style, null);
        ImageView candidateImageView = convertView.findViewById(R.id.candidateImage);
        TextView candidateTextView = convertView.findViewById(R.id.candidateName);
        TextView partidoTextView = convertView.findViewById(R.id.candidatePp);
        candidateImageView.setImageResource(listaFotos[position]);
        candidateTextView.setText(listaNombres[position]);

        if (listaNombres[position].toString() == "Doña Concha" ||
                listaNombres[position].toString() == "Marisa" ||
                listaNombres[position].toString() == "Vicenta") {
            partidoTextView.setText(listaPartidos[0]);

        } else if (listaNombres[position].toString() == "Lucía" ||
                listaNombres[position].toString() == "Belén" ||
                listaNombres[position].toString() == "Bea") {
            partidoTextView.setText(listaPartidos[1]);

        } else if (listaNombres[position].toString() == "Juan 'Chorizo' Cuesta" ||
                listaNombres[position].toString() == "Isabel 'Yerbas'" ||
                listaNombres[position].toString() == "Mauri") {
            partidoTextView.setText(listaPartidos[2]);

        } else if (listaNombres[position].toString() == "Paco" ||
                listaNombres[position].toString() == "'Josemi' Cuesta" ||
                listaNombres[position].toString() == "Roberto") {
            partidoTextView.setText(listaPartidos[3]);

        } else {
            partidoTextView.setText(listaPartidos[4]);
        }

        return convertView;
    }
}
