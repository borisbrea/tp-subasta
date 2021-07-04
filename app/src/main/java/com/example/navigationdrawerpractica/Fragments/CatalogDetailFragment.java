package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigationdrawerpractica.Entidades.Bundle.AuctionBundle;
import com.example.navigationdrawerpractica.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView userId, auctionId, catalogId, catalogIndex, catalogDescription;

    public CatalogDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogDetailFragment newInstance(String param1, String param2) {
        CatalogDetailFragment fragment = new CatalogDetailFragment();
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

        View view = inflater.inflate(R.layout.catalog_detail_fragment, container, false);

        catalogDescription = view.findViewById(R.id.et_catalog_detail_cdf);

        Bundle objeto = getArguments();
        AuctionBundle auctionBundle = new AuctionBundle();
        if (objeto != null){
            auctionBundle = (AuctionBundle) objeto.getSerializable("auctionBundle");
            catalogDescription.setText(auctionBundle.getCatalogDescription());

            userId       = view.findViewById(R.id.tv_user_id_cdf);
            auctionId    = view.findViewById(R.id.tv_auction_id_cdf);
            catalogId    = view.findViewById(R.id.tv_catalog_id_cdf);
            catalogIndex = view.findViewById(R.id.tv_catalog_index_cdf);

            userId.setText(auctionBundle.getUserId());
            auctionId.setText(auctionBundle.getAuctionId());
            catalogId.setText(auctionBundle.getCatalogId());
            catalogIndex.setText(auctionBundle.getIndexCatalog());
        }

        return view;
    }
}