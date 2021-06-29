package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigationdrawerpractica.DAO.ArticleDetailDao;
import com.example.navigationdrawerpractica.R;

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

    TextView  idArticulo;
    TextView  title;
    TextView  description;
    TextView  precioBase;
    TextView  comision;
    TextView  estado;
    TextView  date;
    TextView  montoVendido;
    TextView  ganancia;

    ImageView imagen;


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
        montoVendido = view.findViewById(R.id.tv_monto_vendido_adf);
        ganancia     = view.findViewById(R.id.tv_ganancia_adf);


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