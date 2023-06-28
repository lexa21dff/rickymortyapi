package com.example.rickymortyapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class ListaRickyMortyAdapter extends RecyclerView.Adapter<ListaRickyMortyAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private ListaRickyMortyAdapter listaRickyMortyAdapter;
    private ArrayList<RickyMorty> dataset;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView fotoImageView;
        private TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            fotoImageView=itemView.findViewById(R.id.fotoImageView);
        }
    }

    public ListaRickyMortyAdapter(Context context) {
        this.context = context;
        dataset=new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return dataset.size(); //tamaño del arreglo
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RickyMorty rick=dataset.get(position);
        holder.name.setText(rick.getName());//envio del nombre al textview


        String url ="https://rickandmortyapi.com/api/character/avatar/"+rick.getId()+ ".jpeg";

        Glide.with(context)
                .load(url)
                .error(R.drawable.error)
                .placeholder(R.drawable.placeholder)
                .into(holder.fotoImageView);
    }



    public void add(ArrayList<RickyMorty> listaRick){
        dataset.addAll(listaRick);
        notifyDataSetChanged(); //reseteo de la vista
    }


}