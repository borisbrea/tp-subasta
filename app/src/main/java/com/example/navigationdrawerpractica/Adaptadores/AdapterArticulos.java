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
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ArticleResponse;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class AdapterArticulos extends RecyclerView.Adapter<AdapterArticulos.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<ArticleResponse> model;
    View viewClass;

    private View.OnClickListener listener;

    public AdapterArticulos(Context context, ArrayList<ArticleResponse> model){
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
        String descripcion = String.valueOf(model.get(position).getDescription());
        String estado       = model.get(position).getProductStatus();

        holder.descripcion.setText(descripcion);

        if(estado.equals("pending_approval"))
            holder.estado.setText("Estado: Pendiente de aprobación");
        if(estado.equals("pending_confirmation"))
            holder.estado.setText("Estado: Pendiente de confirmación");
        if(estado.equals("assigned_auction"))
            holder.estado.setText("Estado: Asignado a subasta");
        if(estado.equals("sold"))
            holder.estado.setText("Estado: Vendido");

        Glide.with(viewClass).load(model.get(position).getImages().get(0)).into(holder.imagen);

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