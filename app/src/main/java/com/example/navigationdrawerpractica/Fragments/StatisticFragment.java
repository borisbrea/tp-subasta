package com.example.navigationdrawerpractica.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.navigationdrawerpractica.Adaptadores.AdapterPujasTable;
import com.example.navigationdrawerpractica.DAO.BidsDao;
import com.example.navigationdrawerpractica.DAO.StaticsDao;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.BidResponse;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.StaticsResponse;
import com.example.navigationdrawerpractica.Entidades.Statics.CategoryParticipation;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatisticFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PieChartView pieChartView;
    TableLayout tableLayout;

    public StatisticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatisticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatisticFragment newInstance(String param1, String param2) {
        StatisticFragment fragment = new StatisticFragment();
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

        getActivity().setTitle(Utils.TITLE_MIS_ESTADISTICAS);

        View view = inflater.inflate(R.layout.statistic_fragment, container, false);

        String userId = (String) getArguments().getSerializable("userId");

        Response response = null;
        try {
            response = new StaticsDao().execute(userId).get();
            StaticsResponse statistic = (StaticsResponse) response.body();

            PieChartView pieChartView = view.findViewById(R.id.chart);

            List pieData = new ArrayList<>();

            Long won  = statistic.getAuctionRatio().getWon();
            Long lost = statistic.getAuctionRatio().getLost();
            Long total = won + lost == 0? 1: won + lost;

            pieData.add(new SliceValue(won, Color.BLUE).setLabel((won  / total) * 100 + "%"));
            pieData.add(new SliceValue(lost, Color.RED).setLabel((lost / total) * 100 + "%"));

            PieChartData pieChartData = new PieChartData(pieData);
            pieChartData.setHasLabels(true).setValueLabelTextSize(14);
            pieChartData.setHasCenterCircle(true).setCenterText1("").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
            pieChartView.setPieChartData(pieChartData);

            String[] header = {"Categoría", "Cantidad"};

            tableLayout = (TableLayout) view.findViewById(R.id.statistic_table);

            AdapterPujasTable adapterPujasTable = new AdapterPujasTable(tableLayout, getActivity().getApplicationContext());
            adapterPujasTable.addHeader(header);
            adapterPujasTable.addData(getBids(statistic));
            adapterPujasTable.backGroundHeader(Color.BLUE);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return view;
    }


    private ArrayList<String[]> getBids(StaticsResponse statics){
        ArrayList<String[]> rows = new ArrayList<>();

       for(CategoryParticipation participation :statics.getCategoryParticipation())
           rows.add(new String[]{participation.getCategory(), String.valueOf(participation.getValue())});

        return rows;
    }

}