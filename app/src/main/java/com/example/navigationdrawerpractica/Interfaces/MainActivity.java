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
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.DAO.GeneratePasswordDao;
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.DAO.PutPaymentMethodAccountDao;
import com.example.navigationdrawerpractica.DAO.PutPaymentMethodCreditCardDao;
import com.example.navigationdrawerpractica.DAO.RegisterDao;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.home.AuctionDetail;
import com.example.navigationdrawerpractica.Entidades.home.AuctionHome;
import com.example.navigationdrawerpractica.Entidades.requestEntities.AccountRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.CreditCardRequest;
import com.example.navigationdrawerpractica.Fragments.AccessMenuFragment;
import com.example.navigationdrawerpractica.Fragments.AccountFragment;
import com.example.navigationdrawerpractica.Fragments.AddPaymentFragment;
import com.example.navigationdrawerpractica.Fragments.ArticleDetailFragment;
import com.example.navigationdrawerpractica.Fragments.ArticleFragment;
import com.example.navigationdrawerpractica.Fragments.AuctionFragment;
import com.example.navigationdrawerpractica.Fragments.BidFragment;
import com.example.navigationdrawerpractica.Fragments.DetallePersonaFragment;
import com.example.navigationdrawerpractica.Fragments.DetalleSubastaFragment;
import com.example.navigationdrawerpractica.Fragments.GeneratePasswordFragment;
import com.example.navigationdrawerpractica.Fragments.PaymentFragment;
import com.example.navigationdrawerpractica.Fragments.PersonasFragment;
import com.example.navigationdrawerpractica.Fragments.StatisticFragment;
import com.example.navigationdrawerpractica.Fragments.ValidateMailFragment;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;
import com.example.navigationdrawerpractica.Utils.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {

    DrawerLayout          drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar               toolbar;
    NavigationView        navigationView;

    private DrawerLayout          drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean               mToolBarNavigationListenerIsRegistered = false;

    private TableLayout           tableLayout;
    private TextView              name, lastName;

    FragmentManager               fragmentManager;
    FragmentTransaction           fragmentTransaction;
    DetallePersonaFragment        detallePersonaFragment;

    //Conexión a la Api
    private RetrofitApiService    apiService = RetrofitClient.getApiService();
    public static int             responseValidate = 0;
    public GenericDao             dao = new GenericDao();
    public int indiceSubasta   = 0;

    private String global_email = "";

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
            fragmentTransaction.replace(R.id.container_fragment,new AccountFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.payment){

            PaymentFragment paymentFragment = new PaymentFragment();

            NavigationView navigationView = findViewById(R.id.navigationView);
            TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

            Bundle bundle = new Bundle();
            bundle.putSerializable("userId",(String) tv_user_id.getText());
            paymentFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,paymentFragment);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.article){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,new ArticleFragment());
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

    private ArrayList<String[]> getClientes(){
        ArrayList<String[]> rows = new ArrayList<>();

        rows.add(new String[]{"1","Boris", "Brea"});
        rows.add(new String[]{"2","Ezequiel", "Viacava"});
        rows.add(new String[]{"3","Emanuel", "Odstrcil"});

        return rows;
    }

    @Override
    public void enviarSubasta(AuctionHome subasta) {
        //gracias a haber implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
        detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto",subasta);
        detallePersonaFragment.setArguments(bundleEnvio);

        //Cargar fragment en el activity
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

    @Override
    public void enviarMetodoPago(MetodoPago metodoPago) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
       /* detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto",subasta);
        detallePersonaFragment.setArguments(bundleEnvio);

        //Cargar fragment en el activity
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/

        /*
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.container_fragment, detallePersonaFragment)
                 .addToBackStack(null).commit();
        */
        //***Luego pasar a programar al fragmentdetalle
    }

    @Override
    public void enviarArticulo(Articulo articulo) {
        //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
        //o mejor dicho este metodo.
        //Aqui se realiza toda la logica necesaria para poder realizar el envio
       /* detallePersonaFragment = new DetallePersonaFragment();
        //objeto bundle para transportar la informacion
        Bundle bundleEnvio = new Bundle();
        //se manda el objeto que le esta llegando:
        bundleEnvio.putSerializable("objeto",subasta);
        detallePersonaFragment.setArguments(bundleEnvio);*/

        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();

        //Cargar fragment en el activity
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, articleDetailFragment);
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

        String email      = ((TextView) findViewById(R.id.lgUsername)).getText().toString();
        String password   = ((TextView) findViewById(R.id.lgPassword)).getText().toString();

        if (email.matches("")) {
            ((TextView) findViewById(R.id.lgUsername)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (password.matches("")) {
            ((TextView) findViewById(R.id.lgPassword)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

         login(email, password);

        /*switch(responseLogin){
            case  0:
                break;
            case 200: managerMenuOption(true); showMainMenuFragment();
                break;
            case 400:simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_LOGIN_400);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
                break;
        }*/

    }

    public void validateUserAction(View view){

        TextView email = (TextView) findViewById(R.id.vmUsername);

        if ((email.getText().toString()).matches("")) {
            ((TextView) findViewById(R.id.vmUsername)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

         validate(email.getText().toString());
        //int validateResponse = dao.validate(email.getText().toString());

       /* switch (validateResponse){
            case   0:
                break;
            case 200: showGeneratePasswordFragment();
                break;
            case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                break;
            case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
        }*/
    }

    public void generatePasswordAction(View view) throws ExecutionException, InterruptedException {

        String password  = ((TextView) findViewById(R.id.etClave)).getText().toString();
        String password2 = ((TextView) findViewById(R.id.etClave2)).getText().toString();
        String code      = ((TextView) findViewById(R.id.etVerificacion)).getText().toString();
        String email     = ((TextView) findViewById(R.id.tv_email_gpf)).getText().toString();

        if (password.matches("")) {
            ((TextView) findViewById(R.id.etClave)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (password2.matches("")) {
            ((TextView) findViewById(R.id.etClave2)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (!password.equals(password2)) {
            ((TextView) findViewById(R.id.etClave2)).setError("Las claves ingresadas no coinciden");
            return;
        }

        if (code.matches("")) {
            ((TextView) findViewById(R.id.etClave2)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        Response response = new GeneratePasswordDao().execute(email, password, code).get();

        switch (response.code()){
            case   0:
                break;
            case 201: registerPasswordDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_CONFIRM_CREATE_PASSWORD, 200);
                break;
            case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                break;
            case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
        }
    }

    public void registerAction(View view) throws ExecutionException, InterruptedException {

        String email     = ((TextView) findViewById(R.id.et_email_rf)).getText().toString();
        String nombre    = ((TextView) findViewById(R.id.et_name_rf)).getText().toString();
        String apellido  = ((TextView) findViewById(R.id.et_surname_rf)).getText().toString();
        String documento = ((TextView) findViewById(R.id.et_document_rf)).getText().toString();
        String direccion = ((TextView) findViewById(R.id.et_address_rf)).getText().toString();
        String telefono  = ((TextView) findViewById(R.id.et_phone_rf)).getText().toString();


        if (email.matches("")) {
            ((TextView) findViewById(R.id.et_email_rf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (nombre.matches("")) {
            ((TextView) findViewById(R.id.et_name_rf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (apellido.matches("")) {
            ((TextView) findViewById(R.id.et_surname_rf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (documento.matches("")) {
            ((TextView) findViewById(R.id.et_document_rf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (direccion.matches("")) {
            ((TextView) findViewById(R.id.et_address_rf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if (telefono.matches("")) {
            ((TextView) findViewById(R.id.et_phone_rf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        Response response = new RegisterDao().execute(email, nombre, documento, direccion, telefono, apellido).get();

        switch (response.code()){
            case   0:
                break;
            case 201: registerPasswordDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_CONFIRM_REGISTER, 200);
                break;
            case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                break;
            case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
        }
    }

    public void registerPaymentMethodAction(View view) throws ExecutionException, InterruptedException {

        String tipo      = ((Spinner) findViewById(R.id.spinner1)).getSelectedItem().toString();

        if(tipo.contains("Métodos")){
            //((Spinner) findViewById(R.id.spinner1)) setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        Response response = null;

        if(tipo.contains("Tarjeta")){

            tipo = "credit_card";
            String compania  = ((Spinner) findViewById(R.id.spinner2)).getSelectedItem().toString();
            String numero    = ((TextView) findViewById(R.id.et_numero_tarjeta_apf)).getText().toString();

            if (numero.matches("")) {
                ((TextView) findViewById(R.id.et_numero_tarjeta_apf)).setError(Utils.OBLIGATORY_FIELD);
                return;
            }

            CreditCardRequest creditCardRequest = new CreditCardRequest(tipo, compania, numero);

            response = new PutPaymentMethodCreditCardDao().execute(creditCardRequest).get();

        } else {

            tipo = "bank_account";
            String compania  = ((Spinner) findViewById(R.id.spinner3)).getSelectedItem().toString();
            String numero  = ((TextView) findViewById(R.id.et_numero_cuenta_apf)).getText().toString();

            if (numero.matches("")) {
                ((TextView) findViewById(R.id.et_numero_cuenta_apf)).setError(Utils.OBLIGATORY_FIELD);
                return;
            }

            AccountRequest accountRequest = new AccountRequest(tipo, compania, numero);
            response = new PutPaymentMethodAccountDao().execute(accountRequest).get();

        }

        switch (response.code()){
            case   0:
                break;
            case 201: registerPaymentMethodDialogAlert(Utils.CONFIRMATION_MESSAGE, Utils.ALERT_CONFIRM_PAYMENT_METHOD, 200);
                break;
            case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                break;
            case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                break;
            default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
        }
    }

    // ---------------------------------  DAO  -------------------------------------------

    public void validate(String email){
         global_email = email;

        apiService = RetrofitClient.getApiService();
        apiService.validate(email).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();

                switch (responseValidate){
                    case   0:
                        break;
                    case 200: showGeneratePasswordFragment(global_email);
                        break;
                    case 400: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_400);
                        break;
                    case 404: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_404);
                        break;
                    default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
                }

            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
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

    public void login(String email, String clave){
        responseValidate = 0;

        apiService = RetrofitClient.getApiService();
        apiService.login(email, clave).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();

                switch(responseValidate){
                    case  0:
                        break;
                    case 200: managerMenuOption(true); managerHeader(response); showMainMenuFragment();
                        break;
                    case 400:simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_LOGIN_400);
                        break;
                    default: simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_DEFAULT);
                        break;
                }
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
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


    public void registerPaymentMethodDialogAlert(String title, String message, int code){
        AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
        alerta.setMessage(message);
        if(code == 200){
            alerta.setPositiveButton(Utils.BTN_ACCEPT, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    showPaymentMethodFragment();
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

    public void showGeneratePasswordFragment(String email){

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("objeto", email);

        GeneratePasswordFragment generatePasswordFragment = new GeneratePasswordFragment();
                                 generatePasswordFragment.setArguments(bundleEnvio);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,generatePasswordFragment);
        fragmentTransaction.commit();
    }

    public void showMainMenuFragment(){

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new PersonasFragment());
        fragmentTransaction.commit();
    }

    public void showPaymentMethodFragment(){

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new PaymentFragment());
        fragmentTransaction.commit();
    }

    public void showAddPaymentFragment(View view){

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new AddPaymentFragment());
        fragmentTransaction.commit();
    }

    public void showAuctionFragment(View view){

        TextView id = findViewById(R.id.tv_id_df);
        AuctionFragment auctionFragment = new AuctionFragment();

        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("idSubasta",(String) id.getText());
        auctionFragment.setArguments(bundleEnvio);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,auctionFragment);
        fragmentTransaction.commit();
    }

    public void showAuctionDetailFragment(View view){

        TextView id = findViewById(R.id.tv_id_df);
        DetalleSubastaFragment detalleSubastaDetalle = new DetalleSubastaFragment();

        /*Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("idSubasta",(String) id.getText());
        auctionFragment.setArguments(bundleEnvio);*/

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,detalleSubastaDetalle);
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

    public void managerHeader(Response response){

        NavigationView navigationView = findViewById(R.id.navigationView);
        PersonaPrueba user = (PersonaPrueba)response.body();

        TextView username = navigationView.getHeaderView(0).findViewById(R.id.tv_username_hf);
                 username.setText(user.getFirstName() + " " + user.getLastName());
        TextView email    = navigationView.getHeaderView(0).findViewById(R.id.tv_email_hf);
                 email.setText(user.getEmail());
        TextView userId   = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);
        userId.setText(String.valueOf(user.getUserId()));
        ImageView photo   = navigationView.getHeaderView(0).findViewById(R.id.iv_photo_hf);
                  photo.setImageResource(R.drawable.perfil_caetano);

    }

}