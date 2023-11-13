package com.gabrielsantiago.elecciones.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gabrielsantiago.elecciones.Clases.Candidato;
import com.gabrielsantiago.elecciones.R;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<Candidato> {
    private Context context;
    private ArrayList<Candidato> listaCandidatos;
    private ArrayList<String> listaPp;

    public SpinnerAdapter(Context context, ArrayList<Candidato> listaCandidatos) {
        super(context, 0, listaCandidatos);
        this.context = context;
        this.listaCandidatos = listaCandidatos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_row_style, parent, false);
        }

        TextView candidateTextView = convertView.findViewById(R.id.candidateName);
        ImageView candidateImageView = convertView.findViewById(R.id.candidateImage);
        TextView candidateTextppView = convertView.findViewById(R.id.candidatePp);

        // Obtener el objeto Candidato de la lista
        Candidato candidato = listaCandidatos.get(position);

        // Establecer el texto y la imagen en los elementos de la vista
        candidateTextView.setText(candidato.getNombre());
        candidateImageView.setImageResource(candidato.getIdDrawable());
        candidateTextppView.setText(candidato.getPartido());
        return convertView;
    }
}

