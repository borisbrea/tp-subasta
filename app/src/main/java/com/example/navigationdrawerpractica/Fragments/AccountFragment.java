package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.navigationdrawerpractica.DAO.AccountDao;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.AccountResponse;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Utils.Utils;

import java.util.concurrent.ExecutionException;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView nombre;
    private TextView apellido;
    private TextView email;
    private TextView telefono;
    private TextView direccion;
    private TextView documento;
    private TextView clave;


    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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

        View view = inflater.inflate(R.layout.account_fragment, container, false);
        getActivity().setTitle(Utils.TITLE_MI_CUENTA);

        nombre     = view.findViewById(R.id.edit_name);
        apellido   = view.findViewById(R.id.edit_surname);
        email      = view.findViewById(R.id.edit_email);
        telefono   = view.findViewById(R.id.edit_phone);;
        direccion  = view.findViewById(R.id.edit_address);;
        documento  = view.findViewById(R.id.edit_document);;
        clave      = view.findViewById(R.id.edit_password);


        try {
            Response response = new AccountDao().execute(Integer.valueOf("1")).get();

            AccountResponse accountResponse = (AccountResponse) response.body();

            if(accountResponse != null){
                nombre.setText(accountResponse.getFirstName());
                apellido.setText(accountResponse.getLastName());
                email.setText(accountResponse.getEmail());
                telefono.setText(accountResponse.getPhone());
                direccion.setText(accountResponse.getAddress());
                documento.setText(accountResponse.getDni());
                //clave.setText(accountResponse.get);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return view;
    }
}