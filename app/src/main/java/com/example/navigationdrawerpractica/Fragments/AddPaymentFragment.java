package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Spinner spinner, spinner2, spinner3;
    EditText etNroTarjeta, etNroCC;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPaymentFragment newInstance(String param1, String param2) {
        AddPaymentFragment fragment = new AddPaymentFragment();
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
        List<String> metodos = new ArrayList<String>();
        metodos.add("Métodos de pago");
        metodos.add("Tarjeta");
        metodos.add("Cuenta Corriente");


        List<String> tipos = new ArrayList<String>();
        tipos.add("VISA");
        tipos.add("MASTER CARD");

        List<String> bancos = new ArrayList<String>();
        bancos.add("Seleccione un Banco");
        bancos.add("BBVA");
        bancos.add("Santander");
        bancos.add("Galicia");


        View view =inflater.inflate(R.layout.add_payment_fragment,container,false);
        spinner= view.findViewById(R.id.spinner1);
        spinner2= view.findViewById(R.id.spinner2);
        spinner3= view.findViewById(R.id.spinner3);

        etNroTarjeta= view.findViewById(R.id.et_numero_tarjeta_apf);
        etNroCC= view.findViewById(R.id.et_numero_cuenta_apf);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, metodos);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipos);
        spinner2.setAdapter(dataAdapter2);

        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, bancos);
        spinner3.setAdapter(dataAdapter3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"" + metodos.get(position), Toast.LENGTH_SHORT).show();

                switch(position)
                {
                    case 0:
                        spinner2.setVisibility    (View.GONE);
                        spinner3.setVisibility    (View.GONE);
                        etNroCC.setVisibility     (View.GONE);
                        etNroTarjeta.setVisibility(View.GONE);

                        break;
                    case 1:
                        spinner3.setVisibility    (View.GONE);
                        etNroCC.setVisibility     (View.GONE);
                        spinner2.setVisibility    (View.VISIBLE);
                        etNroTarjeta.setVisibility(View.VISIBLE);

                        break;
                    case 2:
                        spinner2.setVisibility    (View.GONE);
                        etNroTarjeta.setVisibility(View.GONE);
                        spinner3.setVisibility    (View.VISIBLE);
                        etNroCC.setVisibility     (View.VISIBLE);

                        break;
                    default:
                        break;
                }

                spinner2.setSelection(0);
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }
}