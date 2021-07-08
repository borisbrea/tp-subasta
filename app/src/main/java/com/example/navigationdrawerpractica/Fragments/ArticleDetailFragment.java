package com.example.navigationdrawerpractica.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.navigationdrawerpractica.DAO.ArticleDetailDao;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ArticleResponse;
import com.example.navigationdrawerpractica.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.concurrent.ExecutionException;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView  idArticulo, title, description, precioBase, comision;
    TextView  estado, date, subasta, montoVendido, ganancia;
    ImageView imagen;
    LinearLayout precioComision, botones, datos;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ArticleDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleDetailFragment newInstance(String param1, String param2) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.article_detail_fragment, container, false);

        Bundle objeto = getArguments();
        String idArticulo = "";
        if (objeto != null){
            idArticulo = (String) objeto.getSerializable("idSubasta");
        }

        //idArticulo;
        title        = view.findViewById(R.id.tv_item_title_adf);
        description  = view.findViewById(R.id.tv_description_adf);
        precioBase   = view.findViewById(R.id.tv_precio_base_adf);
        comision     = view.findViewById(R.id.tv_comision_adf);
        estado       = view.findViewById(R.id.tv_estado_adf);
        date         = view.findViewById(R.id.tv_fecha_adf);
        subasta      = view.findViewById(R.id.tv_auction_assigned_adf);
        montoVendido = view.findViewById(R.id.tv_monto_vendido_adf);
        ganancia     = view.findViewById(R.id.tv_ganancia_adf);

        precioComision = view.findViewById(R.id.ll_precio_comision_adf);
        botones        = view.findViewById(R.id.ll_botones_adf);
        datos          = view.findViewById(R.id.ll_datos_adf);

        ArticleResponse article = (ArticleResponse) getArguments().getSerializable("article");

        if(article!= null){

            //title.setText(article.);
            CarouselView carouselView = view.findViewById(R.id.cv_article_images_adv);
            carouselView.setPageCount(article.getImages().size());
            carouselView.setViewListener(new ViewListener() {
                                             @Override
                                             public View setViewForPosition(int position) {
                                                 ImageView imageView = new ImageView(view.getContext());
                                                 imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                                 Glide.with(view)
                                                         .load(article.getImages().get(position))
                                                         .fitCenter()
                                                         .centerInside()
                                                         .into(imageView);
                                                 return imageView;
                                             }
            });



            description.setText(article.getDescription());

            if(article.getProductStatus().equals("pending_approval")){
                estado.setText("Pendiente de aprobación");
                estado.setTextColor(Color.parseColor("#D0312D"));
            }
            if(article.getProductStatus().equals("pending_confirmation")){
                estado.setText("Pendiente de Confirmación");
                estado.setTextColor(Color.parseColor("#E5BE01"));

                precioComision.setVisibility(View.VISIBLE);
                precioBase.setText("Precio base: " + article.getBasePrice());
                comision.  setText("Comisión: "    + article.getCommission());

                botones.setVisibility(View.VISIBLE);

                datos.setVisibility(View.GONE);

            }
            if(article.getProductStatus().equals("assigned_auction")){
                estado.setText("Asignado a Subasta");
                estado.setTextColor(Color.parseColor("#ED7014"));

                precioComision.setVisibility(View.VISIBLE);
                precioBase.setText("Precio base: " + article.getBasePrice());
                comision.  setText("Comisión: "    + article.getCommission());

                botones.setVisibility(View.GONE);

                datos.setVisibility(View.VISIBLE);
                date.setText   ("Fecha: "   + article.getAssignedDate());
                subasta.setText("Subasta: " + article.getAssignedAuction());

            }
            if(article.getProductStatus().equals("sold")){
                estado.setText("Vendido");
                estado.setTextColor(Color.parseColor("#3C8C3F"));

                precioComision.setVisibility(View.VISIBLE);
                precioBase.setText("Precio base: " + article.getBasePrice());
                comision.  setText("Comisión: "    + article.getCommission());

                botones.setVisibility(View.GONE);

                datos.setVisibility(View.VISIBLE);
                date.        setText("Fecha: "         + article.getSoldDate());
                montoVendido.setText("Monto Vendido: " + article.getSoldAmount());
                ganancia.    setText("Ganancia: "      + article.getEarnings());
            }
        }

        try {
            Response response = new ArticleDetailDao().execute(idArticulo).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return view;
    }
}