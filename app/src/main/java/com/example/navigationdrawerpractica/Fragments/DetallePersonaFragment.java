package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.R;

public class DetallePersonaFragment extends Fragment {
    TextView nombre;
    TextView categoria;
    ImageView imagen;

    /*@Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        imagen = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoPersona = getArguments();
        Persona persona = null;;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoPersona !=null){
            persona = (Persona) objetoPersona.getSerializable("objeto");
            imagen.setImageResource(persona.getImagenid());
            nombre.setText(persona.getNombre());
        }
        return view;
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        nombre    = view.findViewById(R.id.nombre_detalle);
        categoria = view.findViewById(R.id.categoria_detalle);
        imagen    = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoSubasta = getArguments();
        Subasta subasta = null;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoSubasta !=null){
            subasta = (Subasta) objetoSubasta.getSerializable("objeto");
            imagen.setImageResource(R.drawable.martillo_small);
            nombre.setText(String.valueOf(subasta.getId()));
            categoria.setText(subasta.getCategoria());
        }
        return view;
    }

}
