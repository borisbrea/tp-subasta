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
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.home.Auction;
import com.example.navigationdrawerpractica.Entidades.home.AuctionHome;
import com.example.navigationdrawerpractica.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterSubastas extends RecyclerView.Adapter<AdapterSubastas.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<AuctionHome> model;
    View viewClass;

    private View.OnClickListener listener;

    public AdapterSubastas(Context context, ArrayList<AuctionHome> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterSubastas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_personas, parent, false);
        view.setOnClickListener(this);
        viewClass = inflater.inflate(R.layout.lista_personas, parent, false);
        return new AdapterSubastas.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSubastas.ViewHolder holder, int position) {
        String nombre    = String.valueOf(model.get(position).getTitle());
        String fecha     = model.get(position).getDate()   != null? model.get(position).getDate():"";
        String categoria = model.get(position).getCategory() != null? model.get(position).getCategory(): "";
        //int    imageid = model.get(position).getImagenid();
        holder.nombres.setText        (nombre);
        holder.fechancimiento.setText ("Fecha: "  + fecha);
        holder.categoria.setText      ("Categoría: " + categoria);

        Glide.with(viewClass).load(model.get(position).getImage()).into(holder.imagen);

        //holder.imagen.setImageResource(R.drawable.martillo_small);
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
        TextView nombres, fechancimiento, categoria;
        ImageView imagen;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombres        = itemView.findViewById(R.id.nombres);
            fechancimiento = itemView.findViewById(R.id.fechanacimiento);
            categoria      = itemView.findViewById(R.id.categoria);
            imagen         = itemView.findViewById(R.id.imagen_persona);
        }

    }

   /* public String convertDateFormat(Date date){
        String stringDate = (android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss a", date)).toString();

        String anio = ;
        String mes;
        String dia;
        String hora;

        String formatDate = "";

        return dia + "/" + mes + "/" + anio ;
    }*/
}
