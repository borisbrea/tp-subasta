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

import com.example.navigationdrawerpractica.Adaptadores.AdapterMetodoPago;
import com.example.navigationdrawerpractica.Adaptadores.AdapterSubastas;
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    AdapterMetodoPago adapterMetodoPago;
    RecyclerView recyclerViewMetodoPago;
    ArrayList<MetodoPago> listaMetodoPago;

    EditText txtnombre;

    GenericDao dao = new GenericDao();

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentFragment newInstance(String param1, String param2) {
        PaymentFragment fragment = new PaymentFragment();
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

        View view = inflater.inflate(R.layout.payment_fragment,container,false);
        txtnombre = view.findViewById(R.id.txtnombre);

        recyclerViewMetodoPago = view.findViewById(R.id.recyclerView_pf);
        listaMetodoPago = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }

    public void cargarLista(){
        List<MetodoPago> metodosPago = new ArrayList<>();
        dao.getMetodosPago(metodosPago);
        listaMetodoPago.addAll(metodosPago);
    }

    private void mostrarData(){
        recyclerViewMetodoPago.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterMetodoPago = new AdapterMetodoPago(getContext(), listaMetodoPago);
        recyclerViewMetodoPago.setAdapter(adapterMetodoPago);

        adapterMetodoPago.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = String.valueOf(listaMetodoPago.get(recyclerViewMetodoPago.getChildAdapterPosition(view)).getId());
                txtnombre.setText(nombre);
                Toast.makeText(getContext(), "Seleccionó: "+listaMetodoPago.get(recyclerViewMetodoPago.getChildAdapterPosition(view)).getId(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarMetodoPago(listaMetodoPago.get(recyclerViewMetodoPago.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }


}