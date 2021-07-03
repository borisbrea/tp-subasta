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

    TextView  idSubasta, title, date, category, auctioneer;
    TextView  itemAmount, description, userId;

    ImageView image;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        Auction auction = new Auction();

        idSubasta   = view.findViewById(R.id.tv_id_df);
        userId      = view.findViewById(R.id.tv_user_id_df);
        title       = view.findViewById(R.id.tv_title_df);
        date        = view.findViewById(R.id.tv_date_df);
        category    = view.findViewById(R.id.tv_category_df);
        auctioneer  = view.findViewById(R.id.tv_auctioneer_df);
        itemAmount   = view.findViewById(R.id.tv_items_amount_df);
        description = view.findViewById(R.id.tv_description_df);
        image       = view.findViewById(R.id.imagen_detalleid);

        AuctionHome subasta = (AuctionHome) getArguments().getSerializable("auction");
        String      idUser  = (String)      getArguments().getSerializable("userId");

        try {
            Response response = new AuctionDao().execute(subasta.getId()).get();
            auction = (Auction) response.body();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(auction !=null){
            idSubasta.setText      (String.valueOf(auction.getId()));
            userId.setText         (idUser);
            title.setText          (auction.getTitle());
            date.setText           (auction.getDetail().getStartDate());
            category.setText       (auction.getDetail().getCategory());
            auctioneer.setText     (auction.getDetail().getOwner());
            itemAmount.setText     (String.valueOf(auction.getDetail().getArticleAmount()));
            description.setText    (auction.getDetail().getDescription());
            Glide.with(view).load(subasta.getImage()).into(image);

            //imagen.setImageResource(R.drawable.martillo_small);
        }
        return view;
    }

}
