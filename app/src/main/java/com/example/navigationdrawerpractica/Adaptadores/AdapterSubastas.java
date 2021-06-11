package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class AdapterSubastas extends RecyclerView.Adapter<AdapterSubastas.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<Subasta> model;

    private View.OnClickListener listener;

    public AdapterSubastas(Context context, ArrayList<Subasta> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterSubastas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_personas, parent, false);
        view.setOnClickListener(this);
        return new AdapterSubastas.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSubastas.ViewHolder holder, int position) {
        String nombres   = String.valueOf(model.get(position).getId());
        String fecha     = model.get(position).getFecha().toString();
        String categoria = model.get(position).getCategoria();
        //int    imageid = model.get(position).getImagenid();
        holder.nombres.setText        ("Subasta:" + nombres);
        holder.fechancimiento.setText ("Fecha: " + fecha);
        holder.categoria.setText      ("Categoría: " + categoria);
        holder.imagen.setImageResource(R.drawable.martillo_small);
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
}
