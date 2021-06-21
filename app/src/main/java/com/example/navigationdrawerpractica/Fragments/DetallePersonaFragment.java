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

import com.bumptech.glide.Glide;
import com.example.navigationdrawerpractica.DAO.AuctionDao;
import com.example.navigationdrawerpractica.DAO.RegisterDao;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.home.Auction;
import com.example.navigationdrawerpractica.Entidades.home.AuctionHome;
import com.example.navigationdrawerpractica.R;

import java.util.concurrent.ExecutionException;

import retrofit2.Response;

public class DetallePersonaFragment extends Fragment {

    TextView  title;
    TextView  date;
    TextView  category;
    TextView  auctioneer;
    TextView  itemAmout;
    TextView  description;
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
        Auction auction = new Auction();

        title       = view.findViewById(R.id.tv_title_df);
        date        = view.findViewById(R.id.tv_date_df);
        category    = view.findViewById(R.id.tv_category_df);
        auctioneer  = view.findViewById(R.id.tv_auctioneer_df);
        itemAmout   = view.findViewById(R.id.tv_items_amount_df);
        description = view.findViewById(R.id.tv_description_df);
        imagen      = view.findViewById(R.id.imagen_detalleid);

        AuctionHome subasta = (AuctionHome) getArguments().getSerializable("objeto");

        try {
            Response response = new AuctionDao().execute(subasta.getId()).get();
            auction = (Auction) response.body();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(auction !=null){
            title.setText          (auction.getTitle());
            date.setText           (auction.getDetail().getStartDate());
            category.setText       (auction.getDetail().getCategory());
            auctioneer.setText     (auction.getDetail().getOwner());
            itemAmout.setText      (String.valueOf(auction.getDetail().getArticleAmount()));
            description.setText    (auction.getDetail().getDescription());
            //description.setText    ("Exclusiva subasta de artículos electronicos llegados de Japon, entre ellos se podrá encontrar una importante consola de última generación, varios juegos, casco de realidad virtual y muchas cosas mas.");
            Glide.with(view).load(subasta.getImage()).into(imagen);

            //imagen.setImageResource(R.drawable.martillo_small);
        }
        return view;
    }

}
