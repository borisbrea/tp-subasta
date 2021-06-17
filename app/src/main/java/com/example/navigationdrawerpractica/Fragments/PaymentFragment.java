package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.navigationdrawerpractica.Adaptadores.AdapterMetodoPago;
import com.example.navigationdrawerpractica.Adaptadores.AdapterSubastas;
import com.example.navigationdrawerpractica.Callbacks.MyItemTouchHelperCallback;
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Interfaces.CallbackItemtouch;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Utils.Utils;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragment extends Fragment implements CallbackItemtouch {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    AdapterMetodoPago adapterMetodoPago;
    RecyclerView recyclerViewMetodoPago;
    ArrayList<MetodoPago> listaMetodoPago;
    RelativeLayout layout;
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
        layout = view.findViewById(R.id.layout_items_mp);
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
        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerViewMetodoPago);
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


    @Override
    public void itemTouchOnMode(int oldPsition, int newPosition) {
        listaMetodoPago.add(newPosition, listaMetodoPago.remove(oldPsition));
        adapterMetodoPago.notifyItemMoved(oldPsition, newPosition);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int position) {
        String metodo = listaMetodoPago.get(viewHolder.getAdapterPosition()).getMetodo();
        //backup del item que se elimina para luego hacer cancelar
        final MetodoPago deletedItem = listaMetodoPago.get(viewHolder.getAdapterPosition());
        final int deletedIndex = viewHolder.getAdapterPosition();


        String idItemSelected = deletedItem.getId();

        deletePaymentMethodDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_DELETE_PAYMENT_MESSAGE,idItemSelected, position);

        //Mostrar el snackBar para hacer undo/cancelar
        Snackbar snackbar = Snackbar.make(layout, metodo + "=> Eliminado", Snackbar.LENGTH_LONG);
        snackbar.setAction("CANCELAR/UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterMetodoPago.restoreItem(deletedItem, deletedIndex);
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
    }

    public void deletePaymentMethodDialogAlert(String title, String message, String idItemSelected, int position){
        AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
        alerta.setMessage(message)
                .setIcon(R.drawable.icon_alert)
                .setCancelable(true);
            alerta.setPositiveButton(Utils.BTN_ACCEPT, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dao.deletePaymentMethod(idItemSelected);
                    //remover el item del recycleView
                    adapterMetodoPago.removeItem(position);
                }
            });
            alerta.setNegativeButton(Utils.BTN_CANCEL, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

        AlertDialog titulo = alerta.create();
        titulo.setTitle(title);
        titulo.show();
    }
}
