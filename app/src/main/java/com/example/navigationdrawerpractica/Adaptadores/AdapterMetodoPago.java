package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class AdapterMetodoPago extends RecyclerView.Adapter<AdapterMetodoPago.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<MetodoPago> model;
    private View.OnClickListener listener;
    public AdapterMetodoPago(Context context, ArrayList<MetodoPago> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public AdapterMetodoPago.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_metodo_pago, parent, false);
        view.setOnClickListener(this);
        return new AdapterMetodoPago.ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMetodoPago.ViewHolder holder, int position) {
        String type    = model.get(position).getType();
        String numero  = model.get(position).getNumber();
        String company = model.get(position).getCompany();

        holder.nombres.setText(model.get(position).getName());
        holder.numero.setText(numero);
        if (company.contains("Visa"))
            holder.imagen.setImageResource(R.drawable.visa_logo);
        if (company.contains("Master"))
            holder.imagen.setImageResource(R.drawable.mastercard_logo);
        if (company.contains("Santander"))
            holder.imagen.setImageResource(R.drawable.profile);
        if (company.contains("Galicia"))
            holder.imagen.setImageResource(R.drawable.galicia_logo);
        if (company.contains("BBVA"))
            holder.imagen.setImageResource(R.drawable.bbva_logo);

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
        TextView nombres, numero;
        ImageView imagen;
        public RelativeLayout viewF, viewB;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nombres  = itemView.findViewById(R.id.tv_nombre_lmp);
            numero   = itemView.findViewById(R.id.tv_numero_lmp);
            imagen   = itemView.findViewById(R.id.img_metodoPago);
            viewF    = itemView.findViewById(R.id.rl);
            viewB    = itemView.findViewById(R.id.view_background);
        }

    }

    public void removeItem(int position){
        model.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(MetodoPago item, int position){
        model.add(position,item);
        notifyItemInserted(position);
    }

}
