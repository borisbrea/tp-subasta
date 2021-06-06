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
import android.widget.TextView;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
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
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments,
        androidx.fragment.app.FragmentManager.OnBackStackChangedListener {

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

        validate("caetano@gmail.com");

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

    public void loginAction(View view) {
        // Do something in response to button click
        String var = "prueba";
    }

    public void validateAction(View view) {
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new ValidateMailFragment());
        fragmentTransaction.commit();
    }

    public void validateUserAction(View view){

        // res = llamo a servicio de caetano

        boolean response = false;

        TextView title = (TextView) findViewById(R.id.vmUsername);

        title.getText();

        if(response){
            drawerLayout.closeDrawer(GravityCompat.START);
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new GeneratePasswordFragment());
            fragmentTransaction.commit();
        } else{
            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setMessage("Usuario inexistente")
                    .setIcon(R.drawable.icon_alert)
                    .setCancelable(true)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog titulo = alerta.create();
            titulo.setTitle("Mensaje de alerta");
            titulo.show();
        }



    }

    private void displayHomeUpOrHamburger()
    {
        boolean upbtn = getSupportFragmentManager().getBackStackEntryCount() > 0;

        if(upbtn) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            //remove hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            //need listener for up btn
            if (!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        getSupportFragmentManager().popBackStackImmediate();
                                                                    }
                                                                }
                );
                mToolBarNavigationListenerIsRegistered = true;
            } else {
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                mDrawerToggle.setDrawerIndicatorEnabled(true);
                mDrawerToggle.setToolbarNavigationClickListener(null);
                mToolBarNavigationListenerIsRegistered = false;
            }
        }
    }

    @Override
    public void onBackStackChanged() {
        displayHomeUpOrHamburger();
    }

    public void validate(String email){
        email = "";
        apiService = RetrofitClient.getApiService();
        apiService.validate().enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                PersonaPrueba articulo = response.body();

                String prueba = "";
                //tvResponse.setText(articulo.toString());
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {

                String prueba = "";
                //tvResponse.setText(t.getMessage());
            }
        });
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

}
