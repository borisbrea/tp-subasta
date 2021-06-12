package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        String nombres    = model.get(position).getMetodo();
        String numero = "";
        if (nombres.contains("Cuenta"))
             numero     = model.get(position).getNumeroCuentaCorriente();
        else
             numero     = model.get(position).getNumeroTarjeta();
//        int    imageid    = model.get(position).getImagenid();

        holder.nombres.setText(nombres);
        holder.numero.setText(numero);
        if (nombres.contains("VISA"))
         holder.imagen.setImageResource(R.drawable.visa_logo);
        if (nombres.contains("MASTER"))
            holder.imagen.setImageResource(R.drawable.mastercard_logo);
        if (nombres.contains("Cuenta"))
            holder.imagen.setImageResource(R.drawable.cuenta_corriente_logo);
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

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombres  = itemView.findViewById(R.id.tv_nombre_lmp);
            numero   = itemView.findViewById(R.id.tv_numero_lmp);
            imagen   = itemView.findViewById(R.id.imagen_metodo_pago);
        }

    }
}
