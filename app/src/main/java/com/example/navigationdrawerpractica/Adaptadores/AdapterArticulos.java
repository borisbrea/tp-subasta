package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class AdapterArticulos extends RecyclerView.Adapter<AdapterArticulos.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<Articulo> model;
    View viewClass;

    private View.OnClickListener listener;

    public AdapterArticulos(Context context, ArrayList<Articulo> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterArticulos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_articulo, parent, false);
        view.setOnClickListener(this);
        viewClass = inflater.inflate(R.layout.lista_articulo, parent, false);
        return new AdapterArticulos.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArticulos.ViewHolder holder, int position) {
        String descripcion = String.valueOf(model.get(position).getDescripcionCatalogo());
        String estado       = model.get(position).getEstado();

        //int    imageid = model.get(position).getImagenid();
        holder.descripcion.setText      (descripcion);
        holder.estado.setText           ("Estado: " + estado);

        Glide.with(viewClass).load(model.get(position).getImagen()).into(holder.imagen);

        //holder.imagen.setImageResource  (R.drawable.generic_article);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView descripcion, estado;
        ImageView imagen;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            descripcion    = itemView.findViewById(R.id.tv_description_la);
            estado         = itemView.findViewById(R.id.tv_estado_la);
            imagen         = itemView.findViewById(R.id.imagen_article);
        }

    }
}