package com.example.navigationdrawerpractica.Interfaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Fragments.AccessMenuFragment;
import com.example.navigationdrawerpractica.Fragments.AccountFragment;
import com.example.navigationdrawerpractica.Fragments.BidFragment;
import com.example.navigationdrawerpractica.Fragments.DetallePersonaFragment;
import com.example.navigationdrawerpractica.Fragments.GeneratePasswordFragment;
import com.example.navigationdrawerpractica.Fragments.MainFragment;
import com.example.navigationdrawerpractica.Fragments.PaymentFragment;
import com.example.navigationdrawerpractica.Fragments.PersonasFragment;
import com.example.navigationdrawerpractica.Fragments.StatisticFragment;
import com.example.navigationdrawerpractica.Fragments.ValidateMailFragment;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;
import com.example.navigationdrawerpractica.Utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //variable del fragment detalle
    DetallePersonaFragment detallePersonaFragment;

    //Conexión a la Api
    private RetrofitApiService apiService = RetrofitClient.getApiService();
    public static int responseValidate = 0;
    public GenericDao dao = new GenericDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        //lo sgt se implementa luego de haber implementado NavigationView.OnNavigationItemSelectedListener
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //cargar fragment principal en la actividad
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new PersonasFragment());
        fragmentTransaction.commit();

        managerMenuOption(false);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //para cerrar automaticamente el menu
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.access_register){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new AccessMenuFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.nav_home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new PersonasFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.account){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new ValidateMailFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.payment){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new PaymentFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.bid){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new BidFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.statistic){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new StatisticFragment());
            fragmentTransaction.commit();
        }

        return false;
    }


    @Override
    public void enviarPersona(Persona persona) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto",persona);
        detallePersonaFragment.setArguments(bundleEnvio);

        //CArgar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }



    public void showValidateFragment(View view) {
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new ValidateMailFragment());
        fragmentTransaction.commit();
    }
//------------------------------------ ACTIONS ---------------------------------------
    public void loginAction(View view) {

        String usuario = ((TextView) findViewById(R.id.lgUsername)).getText().toString();
        String clave   = ((TextView) findViewById(R.id.lgPassword)).getText().toString();

        int responseLogin = 200;

        //int responseLogin = dao.login(usuario, clave);

        switch(responseLogin){
            case 200: managerMenuOption(true); showMainMenuFragment();
                break;
            case 400:simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_LOGIN_400);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
                break;
        }

    }

    public void validateUserAction(View view){

        //showGeneratePasswordFragment();

        TextView email = (TextView) findViewById(R.id.vmUsername);
        //int validateResponse = validate(email.getText().toString());
        //int validateResponse = dao.validate(email.getText().toString());

        int validateResponse = 200;

        switch (validateResponse){
            case   0:
                break;
            case 200: showGeneratePasswordFragment();
                break;
            case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                break;
            case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
        }
    }

    public void generatePasswordAction(View view){

        String code     = ((TextView) findViewById(R.id.etVerificacion)).getText().toString();
        String password = ((TextView) findViewById(R.id.etClave)).getText().toString();

        //int generatePasswordResponse = dao.generatePassword(code.toString(), password);

        int generatePasswordResponse = 200;

        switch (generatePasswordResponse){
            case   0:
                break;
            case 200: registerPasswordDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_CONFIRM_CREATE_PASSWORD, 200);
                break;
            case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                break;
            case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
        }
    }

    // ---------------------------------  DAO  -------------------------------------------

    public int validate(String email){

        apiService = RetrofitClient.getApiService();
        apiService.validate(email).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });

        return responseValidate;
    }

    private void validates(String email){
        email = "caetao@gmail.com";
        apiService.validates(email).enqueue(new Callback<List<PersonaPrueba>>() {
            @Override
            public void onResponse(Call<List<PersonaPrueba>> call, Response<List<PersonaPrueba>> response) {
                List<PersonaPrueba> articuloList = response.body();

                String prueba = "";
                //tvResponse.setText(articuloList.size() + "\n\n" + articuloList.get(3).toString());
            }

            @Override
            public void onFailure(Call<List<PersonaPrueba>> call, Throwable t) {

                String prueba = "";

                //tvResponse.setText(t.getMessage());
            }
        });
    }

    //---------------------------------------------------------------------------------------

    public void simpleDialogAlert(String title, String message){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setMessage(message)
                .setIcon(R.drawable.icon_alert)
                .setCancelable(true)
                .setPositiveButton(Utils.BTN_ACCEPT, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog titulo = alerta.create();
        titulo.setTitle(title);
        titulo.show();
    }

    public void registerPasswordDialogAlert(String title, String message, int code){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setMessage(message)
                .setIcon(R.drawable.icon_alert)
                .setCancelable(true);
                if(code == 200){
                    alerta.setPositiveButton(Utils.BTN_ACCEPT, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showMainMenuFragment();
                        }
                    });
                }
                if(code == 201){
                    alerta.setPositiveButton(Utils.BTN_ACCEPT, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                }

        AlertDialog titulo = alerta.create();
        titulo.setTitle(title);
        titulo.show();
    }


    public void showGeneratePasswordFragment(){
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new GeneratePasswordFragment());
        fragmentTransaction.commit();
    }

    public void showMainMenuFragment(){

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new PersonasFragment());
        fragmentTransaction.commit();
    }

    public void managerMenuOption(boolean option){

        NavigationView navigationView = findViewById(R.id.navigationView);

        navigationView.getMenu().findItem(R.id.account).setVisible(option);
        navigationView.getMenu().findItem(R.id.payment).setVisible(option);
        navigationView.getMenu().findItem(R.id.article).setVisible(option);
        navigationView.getMenu().findItem(R.id.bid).setVisible(option);
        navigationView.getMenu().findItem(R.id.statistic).setVisible(option);
    }

}
