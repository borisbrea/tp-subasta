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
import com.example.navigationdrawerpractica.DAO.AuctionWithItemsDao;
import com.example.navigationdrawerpractica.DAO.BidsDao;
import com.example.navigationdrawerpractica.Entidades.Bundle.AuctionBundle;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.BidResponse;
import com.example.navigationdrawerpractica.Entidades.SubastaClases.SubastaConArticulos;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Response;

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

        getActivity().setTitle(Utils.TITLE_MIS_PUJAS);

        String userId = (String) getArguments().getSerializable("userId");

        String[] header = {"Fecha", " Cat??logo ", " Monto ", " Resultado "};

        View view = inflater.inflate(R.layout.bid_fragment,container,false);
        tableLayout = (TableLayout) view.findViewById(R.id.table);

        AdapterPujasTable adapterPujasTable = new AdapterPujasTable(tableLayout, getActivity().getApplicationContext());
                          adapterPujasTable.addHeader(header);
                          adapterPujasTable.addData(getBids(userId));
                          adapterPujasTable.backGroundHeader(Color.BLUE);

        return view;
    }

    private ArrayList<String[]> getBids(String userId){

        ArrayList<String[]> rows = new ArrayList<>();

        try {
            Response response = new BidsDao().execute(userId).get();

            List<BidResponse> bids = (List<BidResponse>) response.body();

            for(BidResponse bid: bids){
                rows.add(new String[]{bid.getCreatedDate(), String.valueOf(bid.getCatalogId()), "$ " + String.valueOf(bid.getAmount()), bid.getResult()});
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/*        rows.add(new String[]{"12/6/2021","Cuadro",       "$1550", "Gan??"});
        rows.add(new String[]{"13/6/2021","Rolex",        "$2000", "Perdi??"});
        rows.add(new String[]{"14/6/2021","Enciclopedia", "$8000", "Gan??"});
*/
        return rows;
    }
}