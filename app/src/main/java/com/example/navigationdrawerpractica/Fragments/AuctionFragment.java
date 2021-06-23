package com.example.navigationdrawerpractica.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationdrawerpractica.DAO.AuctionWithItemsDao;
import com.example.navigationdrawerpractica.DAO.GeneratePasswordDao;
import com.example.navigationdrawerpractica.Entidades.SubastaClases.SubastaConArticulos;
import com.example.navigationdrawerpractica.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.concurrent.ExecutionException;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuctionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuctionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int[] mImages = new int[] {
            R.drawable.gohan_cara1, R.drawable.goku_cara2, R.drawable.goten_cara3, R.drawable.vegueta_cara7,
            R.drawable.picoro_cara5
    };

    private String[] mImagesTitle = new String[] {
            "Gohan", "Goku", "Goten", "Vegueta", "Picoro"
    };

    private TextView estado, descripcion, duenio, precioBase, tituloArticulo;
    private Button   btnPujar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AuctionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuctionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuctionFragment newInstance(String param1, String param2) {
        AuctionFragment fragment = new AuctionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.auction_fragment, container, false);

       tituloArticulo   = view.findViewById(R.id.tv_item_title_af);
       estado           = view.findViewById(R.id.tv_state_af);
       descripcion      = view.findViewById(R.id.tv_description_af);
       duenio           = view.findViewById(R.id.tv_duenio_af);
       precioBase       = view.findViewById(R.id.tv_precio_base_af);
       btnPujar         = view.findViewById(R.id.btn_pujar_af);

        try {
            Response response = new AuctionWithItemsDao().execute(5).get();

            SubastaConArticulos subastaCompleta = (SubastaConArticulos) response.body();

            if(subastaCompleta!= null){

                CarouselView carouselView = view.findViewById(R.id.auction_carousel);
                carouselView.setPageCount(mImages.length);
                carouselView.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        imageView.setImageResource(mImages[position]);
                    }
                });
                carouselView.setImageClickListener(new ImageClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(getContext(), mImagesTitle[position], Toast.LENGTH_SHORT).show();
                    }
                });

                estado.setText(subastaCompleta.getArticles().get(0).getStatus());

                if(subastaCompleta.getArticles().get(0).getStatus().equals("Subastandose")){
                    estado.setTextColor(Color.GREEN);
                }

                tituloArticulo. setText(subastaCompleta.getArticles().get(0).getTitle());
                descripcion.    setText(subastaCompleta.getArticles().get(0).getDescription());
                duenio.         setText(subastaCompleta.getArticles().get(0).getOwner());
                precioBase.     setText(subastaCompleta.getArticles().get(0).getBasePrice());

                if(subastaCompleta.getArticles().get(0).isCanBid())
                    btnPujar.setEnabled(true);
                else
                    btnPujar.setEnabled(false);

            }

            /*CarouselView carouselView = view.findViewById(R.id.auction_carousel);
            carouselView.setPageCount(mImages.length);
            carouselView.setImageListener(new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(mImages[position]);
                }
            });
            carouselView.setImageClickListener(new ImageClickListener() {
                @Override
                public void onClick(int position) {
                    Toast.makeText(getContext(), mImagesTitle[position], Toast.LENGTH_SHORT).show();
                }
            });*/

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }





        return view;
    }
}