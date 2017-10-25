package com.example.diana.escenarios;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diana.escenarios.models.Escenarios;

import java.util.List;

/**
 * Created by diana on 18/10/17.
 */

public class EscAdapter extends RecyclerView.Adapter <EscAdapter.AdapterViewHolder> {
    private List<Escenarios> items;

    public static class AdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public TextView municipio;
        public ImageView imagen;

        public AdapterViewHolder(View v){
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            municipio = (TextView) v.findViewById(R.id.municipio);
            imagen = (ImageView) v.findViewById(R.id.imagen);
        }
    }

    public EscAdapter(List<Escenarios> items){
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view, viewGroup, false);
        return new AdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.municipio.setText(items.get(i).getMunicipio());
        Picasso.with(this).load(items.get(i).getUrlimagen()).into(viewHolder.imagen);
    }
}
