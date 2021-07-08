package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigationdrawerpractica.Adaptadores.AdapterArticulos;
import com.example.navigationdrawerpractica.Adaptadores.AdapterMetodoPago;
import com.example.navigationdrawerpractica.DAO.ArticleDao;
import com.example.navigationdrawerpractica.DAO.AuctionWithItemsDao;
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.Bundle.AuctionBundle;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ArticleResponse;
import com.example.navigationdrawerpractica.Entidades.requestEntities.ArticleRequest;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String userId;
    AdapterArticulos adapterArticulos;
    RecyclerView      recyclerViewArticulos;
    ArrayList<ArticleResponse> articleList;

    EditText txtnombre;

    GenericDao dao = new GenericDao();

    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    public ArticleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleFragment newInstance(String param1, String param2) {
        ArticleFragment fragment = new ArticleFragment();
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

        getActivity().setTitle(Utils.TITLE_MIS_ARTICULOS);

        View view = inflater.inflate(R.layout.article_fragment,container,false);

        userId = (String) getArguments().getSerializable("userId");

        txtnombre = view.findViewById(R.id.txtnombre);
        recyclerViewArticulos = view.findViewById(R.id.recyclerView_af);
        articleList = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }

    public void cargarLista(){

        ArticleResponse articleResponse = new ArticleResponse();

        articleResponse.setDescription("Casco Virtual");
        articleResponse.setProductStatus("pending_approval");
        List<String> images = new ArrayList<>();
        images.add("https://http2.mlstatic.com/D_NQ_NP_600075-MLA41074180964_032020-O.jpg");
        articleResponse.setImages(images);

        articleList.add(articleResponse);

        try {
            Response response = new ArticleDao().execute(userId).get();

            if(response.body() != null){
                articleList.addAll((List<ArticleResponse>) response.body());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void mostrarData(){
        recyclerViewArticulos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterArticulos = new AdapterArticulos(getContext(), articleList);
        recyclerViewArticulos.setAdapter(adapterArticulos);

        adapterArticulos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String descripcion = String.valueOf(listaArticulos.get(recyclerViewArticulos.getChildAdapterPosition(view)).getDescripcionCatalogo());
                //txtnombre.setText(descripcion);
                //Toast.makeText(getContext(), "Seleccionó: "+listaArticulos.get(recyclerViewArticulos.getChildAdapterPosition(view)).getDescripcionCatalogo(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarArticulo(articleList.get(recyclerViewArticulos.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if (context instanceof Activity) {
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad = (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments = (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }
}