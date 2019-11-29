package com.example.didaktikapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorJuegos extends RecyclerView.Adapter <AdaptadorJuegos.ViewHolder> {
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;
    private LayoutInflater inflador; //crea layout a partir de xml
    protected ArrayList<Juegos> lista;//libros a visualizar
    private Context contexto;
    public int contador = 0;

    public AdaptadorJuegos(ArrayList<Juegos> juegos) {
        this.lista = juegos;
        this.contexto = contexto;
    }

    //creamos nuestro ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView portada;
        public TextView textoJuego;
        public ViewHolder(View itemView) {
            super(itemView);
            portada = itemView.findViewById(R.id.ImagenJuego);
            textoJuego = itemView.findViewById(R.id.textoJuego);
        }
    }
    //creamos el viewholder con la vista de un elemento sin personalizar

    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        //inflamos vista desde XML
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselectojuegos, parent, false);
        v.setId(contador);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Aqui rellenaremos la foto que queremos poner el elemento del recicler view

        String nombrejuego = lista.get(position).getNombre();
        Drawable foto = lista.get(position).caratula;
        System.out.println("dentro de onbindholder");
        holder.portada.setImageDrawable(foto);
        holder.textoJuego.setText(nombrejuego);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

