package com.example.navigationdrawerpractica.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.navigationdrawerpractica.Adaptadores.AdapterPujasTable;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BidFragment extends Fragment {

    private TableLayout tableLayout;
    private TextView name, lastName;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BidFragment newInstance(String param1, String param2) {
        BidFragment fragment = new BidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String[] header = {"Fecha", "Artículo", "Monto", "Resultado"};


        View view = inflater.inflate(R.layout.bid_fragment,container,false);
        tableLayout = (TableLayout) view.findViewById(R.id.table);

        AdapterPujasTable adapterPujasTable = new AdapterPujasTable(tableLayout, getActivity().getApplicationContext());
        adapterPujasTable.addHeader(header);
        adapterPujasTable.addData(getClientes());
        adapterPujasTable.backGroundHeader(Color.BLUE);

        return view;
    }

    private ArrayList<String[]> getClientes(){
        ArrayList<String[]> rows = new ArrayList<>();

        rows.add(new String[]{"12/6/2021","Cuadro",       "$1550", "Ganó"});
        rows.add(new String[]{"13/6/2021","Rolex",        "$2000", "Perdió"});
        rows.add(new String[]{"14/6/2021","Enciclopedia", "$8000", "Ganó"});

        return rows;
    }
}