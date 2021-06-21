package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
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
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

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


    AdapterArticulos adapterArticulos;
    RecyclerView      recyclerViewArticulos;
    ArrayList<Articulo> listaArticulos;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.article_fragment,container,false);

        getActivity().setTitle(Utils.TITLE_MIS_ARTICULOS);

        txtnombre = view.findViewById(R.id.txtnombre);
        recyclerViewArticulos = view.findViewById(R.id.recyclerView_af);
        listaArticulos = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }

    public void cargarLista(){
        List<Articulo> articulos = new ArrayList<>();
        dao.getArticulos(articulos);
        listaArticulos.addAll(articulos);
    }

    private void mostrarData(){
        recyclerViewArticulos.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterArticulos = new AdapterArticulos(getContext(), listaArticulos);
        recyclerViewArticulos.setAdapter(adapterArticulos);

        adapterArticulos.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descripcion = String.valueOf(listaArticulos.get(recyclerViewArticulos.getChildAdapterPosition(view)).getDescripcionCatalogo());
                txtnombre.setText(descripcion);
                Toast.makeText(getContext(), "Seleccionó: "+listaArticulos.get(recyclerViewArticulos.getChildAdapterPosition(view)).getDescripcionCatalogo(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarArticulo(listaArticulos.get(recyclerViewArticulos.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }
}