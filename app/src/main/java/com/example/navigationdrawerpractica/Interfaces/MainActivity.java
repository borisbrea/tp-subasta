package com.example.navigationdrawerpractica.Interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.navigationdrawerpractica.Adaptadores.PhotoAdapter;
import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.DAO.AuctionWithItemsDao;
import com.example.navigationdrawerpractica.DAO.GeneratePasswordDao;
import com.example.navigationdrawerpractica.DAO.GenericDao;
import com.example.navigationdrawerpractica.DAO.PutPaymentMethodAccountDao;
import com.example.navigationdrawerpractica.DAO.PutPaymentMethodCreditCardDao;
import com.example.navigationdrawerpractica.DAO.RegisterBidDao;
import com.example.navigationdrawerpractica.DAO.RegisterDao;
import com.example.navigationdrawerpractica.DAO.RegisterNewArticleDao;
import com.example.navigationdrawerpractica.DAO.UserUpdateDao;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.Bundle.AuctionBundle;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ArticleResponse;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.SubastaClases.SubastaConArticulos;
import com.example.navigationdrawerpractica.Entidades.home.AuctionDetail;
import com.example.navigationdrawerpractica.Entidades.home.AuctionHome;
import com.example.navigationdrawerpractica.Entidades.requestEntities.AccountRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.ArticleRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.CreditCardRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.UserUpdateRequest;
import com.example.navigationdrawerpractica.Fragments.AccessMenuFragment;
import com.example.navigationdrawerpractica.Fragments.AccountFragment;
import com.example.navigationdrawerpractica.Fragments.AddPaymentFragment;
import com.example.navigationdrawerpractica.Fragments.ArticleDetailFragment;
import com.example.navigationdrawerpractica.Fragments.ArticleFragment;
import com.example.navigationdrawerpractica.Fragments.AuctionFragment;
import com.example.navigationdrawerpractica.Fragments.BidFragment;
import com.example.navigationdrawerpractica.Fragments.CatalogDetailFragment;
import com.example.navigationdrawerpractica.Fragments.DetallePersonaFragment;
import com.example.navigationdrawerpractica.Fragments.DetalleSubastaFragment;
import com.example.navigationdrawerpractica.Fragments.GeneratePasswordFragment;
import com.example.navigationdrawerpractica.Fragments.NewArticleFragment;
import com.example.navigationdrawerpractica.Fragments.PaymentFragment;
import com.example.navigationdrawerpractica.Fragments.PersonasFragment;
import com.example.navigationdrawerpractica.Fragments.StatisticFragment;
import com.example.navigationdrawerpractica.Fragments.ValidateMailFragment;
import com.example.navigationdrawerpractica.R;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;
import com.example.navigationdrawerpractica.Utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, iComunicaFragments {

    private static final int PERMISSION_CODE =1;
    private static final int PICK_IMAGE=1;

    DrawerLayout          drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar               toolbar;
    NavigationView        navigationView;

    private DrawerLayout          drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean               mToolBarNavigationListenerIsRegistered = false;

    private TableLayout           tableLayout;
    private TextView              name, lastName, uploadNaf;
    private ImageView             imageArticle, image, image1, image2, image3, image4;

    FragmentManager               fragmentManager;
    FragmentTransaction           fragmentTransaction;
    DetallePersonaFragment        detallePersonaFragment;

    //Conexión a la Api
    private RetrofitApiService    apiService = RetrofitClient.getApiService();
    public static int             responseValidate = 0;
    public GenericDao             dao = new GenericDao();
    Button btnSelectedImage;
    RecyclerView rcvPhoto;
    PhotoAdapter photoAdapter;
    public int indiceSubasta   = 0;

    private List<String> localPhotoList = new ArrayList<>();
    private List<String> photoUrls      = new ArrayList<>();

    private String global_email = "";

    String       filePath;
    List<String> filePathList       = new ArrayList<>();
    List<String> cloudinaryUrlsList = new ArrayList<>();

    Map  config = new HashMap();

    private void configCloudinary() {
        config.put("cloud_name", "dyfd65fcm");
        config.put("api_key", "683413195193611");
        config.put("api_secret", "BpFT75Mlw_zSGe5VmYLxn_HMPaU");
        MediaManager.init(MainActivity.this, config);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        configCloudinary();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        //Cargar el fragment principal en la actividad
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new PersonasFragment());
        fragmentTransaction.commit();

        managerMenuOption(true);
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

            AccountFragment accountFragment = new AccountFragment();

            NavigationView navigationView = findViewById(R.id.navigationView);
            TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

            Bundle bundle = new Bundle();
            bundle.putSerializable("userId",(String) tv_user_id.getText());
            accountFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,accountFragment);
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

            ArticleFragment articleFragment = new ArticleFragment();

            NavigationView navigationView = findViewById(R.id.navigationView);
            TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

            Bundle bundle = new Bundle();
            bundle.putSerializable("userId",(String) tv_user_id.getText());
            articleFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,articleFragment);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.bid){

            BidFragment bidFragment = new BidFragment();

            NavigationView navigationView = findViewById(R.id.navigationView);
            TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

            Bundle bundle = new Bundle();
            bundle.putSerializable("userId",(String) tv_user_id.getText());
            bidFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment,bidFragment);
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.statistic){

            StatisticFragment statisticFragment = new StatisticFragment();

            NavigationView navigationView = findViewById(R.id.navigationView);
            TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

            Bundle bundle = new Bundle();
            bundle.putSerializable("userId",(String) tv_user_id.getText());
            statisticFragment.setArguments(bundle);

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_fragment, statisticFragment);
            fragmentTransaction.commit();
        }

        return false;
    }

    /************************************************************************************************
     *                                      SEND INFORMATION
     ***********************************************************************************************/

    @Override
    public void enviarPersona(Persona persona) {

        detallePersonaFragment = new DetallePersonaFragment();

        Bundle bundleEnvio = new Bundle();
               bundleEnvio.putSerializable("objeto",persona);
        detallePersonaFragment.setArguments(bundleEnvio);

        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void enviarSubasta(AuctionHome subasta) {

        detallePersonaFragment = new DetallePersonaFragment();

        NavigationView navigationView = findViewById(R.id.navigationView);
        TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

        Bundle bundle = new Bundle();
               bundle.putSerializable("auction",subasta);
               bundle.putSerializable("userId",(String) tv_user_id.getText());

        detallePersonaFragment.setArguments(bundle);

        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, detallePersonaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
    public void enviarArticulo(ArticleResponse article) {

        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();

        Bundle bundle = new Bundle();
               bundle.putSerializable("article",article);

        articleDetailFragment.setArguments(bundle);

        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, articleDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /************************************************************************************************
     *                                      ACTIONS
     ***********************************************************************************************/

    public void approveArticle(View view) {

        NavigationView navigationView = findViewById(R.id.navigationView);
        String userId = ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf)).getText().toString();

        String articleCode = ((TextView) findViewById(R.id.lgUsername)).getText().toString();


    }

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

    public void bidAction(View view){

        String catalogId = (String) ((TextView) findViewById(R.id.tv_catalog_id_af)).getText().toString();
        String amount    = (String) ((TextView) findViewById(R.id.et_precio_puja_af)).getText().toString();
        String userId    = (String) ((TextView) findViewById(R.id.tv_user_id_af)).getText().toString();

        if (amount.trim().matches("")) {
            ((TextView) findViewById(R.id.et_precio_puja_af)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        try {
            Response response = new RegisterBidDao().execute(catalogId, amount, userId).get();

            if(response.errorBody()!= null){
                try {
                    String res = response.errorBody().string().split("message\":\"")[1].split("\"")[0];

                    switch (res){
                        case "alredy_auctioned":
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_ALREADY_AUCTIONED); break;
                        case "user_has_active_bid":
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_ACTIVE_BID); break;
                        case "max_time_elapsed":
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_MAX_TIME); break;
                        case "invalid_amount":
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_INVALID_AMOUNT); break;
                        case "user_already_has_winning_bid":
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_WINNING_BID); break;
                        case "user_is_owner":
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_OWN_BID); break;
                        default:
                            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_UNKNOW); break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                    simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.BID_ALERT_CORRECT_BID);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void userUpdateAction(View view){

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();


        NavigationView navigationView = findViewById(R.id.navigationView);
        String userId = ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf)).getText().toString();

        String nombre     = ((TextView) findViewById(R.id.edit_name)).getText().toString();
        String apellido   = ((TextView) findViewById(R.id.edit_surname)).getText().toString();
        String email      = ((TextView) findViewById(R.id.edit_email)).getText().toString();
        String telefono   = ((TextView) findViewById(R.id.edit_phone)).getText().toString();
        String direccion  = ((TextView) findViewById(R.id.edit_address)).getText().toString();
        String documento  = ((TextView) findViewById(R.id.edit_document)).getText().toString();
        String clave      = ((TextView) findViewById(R.id.edit_password)).getText().toString();

        userUpdateRequest.setUserId(userId);
        userUpdateRequest.setEmail(email);
        userUpdateRequest.setFirstName(nombre);
        userUpdateRequest.setLastName(apellido);
        userUpdateRequest.setPhone(telefono);
        userUpdateRequest.setAddress(direccion);
        userUpdateRequest.setPassword(clave);

        try {
            Response response = new UserUpdateDao().execute(userUpdateRequest).get();
            String sdr = "";
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /************************************************************************************************
     *                                      DAO CONNECTIONS
     ***********************************************************************************************/

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

    /************************************************************************************************
     *                                      SHOW DIALOG ALERTS
     ***********************************************************************************************/

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

    /************************************************************************************************
     *                                          SHOW FRAGMENTS
     ***********************************************************************************************/

    public void showValidateFragment(View view) {
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new ValidateMailFragment());
        fragmentTransaction.commit();
    }

    public void showGeneratePasswordFragment(String email){

        Bundle bundleEnvio = new Bundle();
               bundleEnvio.putSerializable("objeto", email);

        GeneratePasswordFragment generatePasswordFragment = new GeneratePasswordFragment();
                                 generatePasswordFragment.setArguments(bundleEnvio);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,generatePasswordFragment);
        fragmentTransaction.commit();
    }

    public void showMainMenuFragment(){

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new PersonasFragment());
        fragmentTransaction.commit();
    }

    public void showPaymentMethodFragment(){

        PaymentFragment paymentFragment = new PaymentFragment();

        NavigationView navigationView = findViewById(R.id.navigationView);
        TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

        Bundle bundle = new Bundle();
               bundle.putSerializable("userId",(String) tv_user_id.getText());
        paymentFragment.setArguments(bundle);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,paymentFragment);
        fragmentTransaction.commit();
    }

    public void showArticleFragment() {

        ArticleFragment articleFragment = new ArticleFragment();

        NavigationView navigationView = findViewById(R.id.navigationView);
        TextView tv_user_id = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);

        Bundle bundle = new Bundle();
        bundle.putSerializable("userId", (String) tv_user_id.getText());
        articleFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, articleFragment);
        fragmentTransaction.commit();

    }

    public void showAddPaymentFragment(View view){

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,new AddPaymentFragment());
        fragmentTransaction.commit();
    }

    public void showAuctionFragment(View view){

        AuctionFragment auctionFragment = new AuctionFragment();

        TextView id     = findViewById(R.id.tv_id_df);
        TextView userId = findViewById(R.id.tv_user_id_df);

        Bundle bundle = new Bundle();

        AuctionBundle auctionBundle = new AuctionBundle((String) userId.getText(), (String) id.getText(), "0");
        bundle.putSerializable("auctionBundle",auctionBundle);

               /*bundle.putSerializable("idSubasta",(String) id.getText());
               bundle.putSerializable("userId",   (String) userId.getText());*/

        auctionFragment.setArguments(bundle);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,auctionFragment);
        fragmentTransaction.commit();
    }

    public void showAuctionFromCatalogFragment(View view){

        AuctionFragment auctionFragment = new AuctionFragment();

        TextView userId         = findViewById(R.id.tv_user_id_cdf);
        TextView auctionId      = findViewById(R.id.tv_auction_id_cdf);
        TextView catalogIndex   = findViewById(R.id.tv_catalog_index_cdf);

        Bundle bundle = new Bundle();

        AuctionBundle auctionBundle = new AuctionBundle((String) userId.getText(), (String) auctionId.getText(), (String) catalogIndex.getText());
        bundle.putSerializable("auctionBundle",auctionBundle);

        auctionFragment.setArguments(bundle);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,auctionFragment);
        fragmentTransaction.commit();
    }

    public void showAuctionDetailFragment(View view){

        AuctionBundle auctionBundle = new AuctionBundle();

        String userId             = ((TextView) findViewById(R.id.tv_user_id_af)).getText().toString();
        String auctionId          = ((TextView) findViewById(R.id.tv_auction_id_af)).getText().toString();
        String catalogId          = ((TextView) findViewById(R.id.tv_catalog_id_af)).getText().toString();
        String catalogDescription = ((TextView) findViewById(R.id.tv_catalog_description_af)).getText().toString();
        String catalogIndex       = ((TextView) findViewById(R.id.tv_catalog_index_af)).getText().toString();

        auctionBundle.setUserId(userId);
        auctionBundle.setAuctionId(auctionId);
        auctionBundle.setCatalogId(catalogId);
        auctionBundle.setCatalogDescription(catalogDescription);
        auctionBundle.setIndexCatalog(catalogIndex);

        CatalogDetailFragment catalogDetailFragment = new CatalogDetailFragment();

        Bundle bundle = new Bundle();
               bundle.putSerializable("auctionBundle", auctionBundle);
        catalogDetailFragment.setArguments(bundle);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment,catalogDetailFragment);
        fragmentTransaction.commit();
    }

    public void showNewArticleFragment(View view){

        //Bundle bundleEnvio = new Bundle();
        //bundleEnvio.putSerializable("objeto", email);

        NewArticleFragment newArticleFragment = new NewArticleFragment();
        //newArticleFragment.setArguments(bundleEnvio);

        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, newArticleFragment);
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
                 email.setText("Categoría: " + user.getCategory());
        TextView userId   = navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf);
        userId.setText(String.valueOf(user.getUserId()));
        ImageView photo   = navigationView.getHeaderView(0).findViewById(R.id.iv_photo_hf);
                  photo.setImageResource(R.drawable.perfil_caetano);
    }

    public void nextAuctionCatalogItem(View view){

        String auctionId     = ((TextView) findViewById(R.id.tv_auction_id_af)).getText().toString();

        NavigationView navigationView = findViewById(R.id.navigationView);
        String userId = ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf)).getText().toString();

        try {
            Response response = new AuctionWithItemsDao().execute(Integer.valueOf(auctionId), userId.equals("User Id")? null: Integer.valueOf(userId)).get();

            SubastaConArticulos auctionComplete = (SubastaConArticulos) response.body();

            Integer catalogIndex     = Integer.valueOf(((TextView) findViewById(R.id.tv_catalog_index_af)).getText().toString()) + 1;

            if(auctionComplete.getArticles().size() - 1 >= catalogIndex){

                TextView catalogIndexTv   = findViewById(R.id.tv_catalog_index_af);
                         catalogIndexTv.setText(String.valueOf(catalogIndex));

                TextView articleTitle   = findViewById(R.id.tv_item_title_af);
                         articleTitle.setText(auctionComplete.getArticles().get(catalogIndex).getTitle());

                TextView status           = findViewById(R.id.tv_state_af);
                         status.setText(auctionComplete.getArticles().get(catalogIndex).getStatus());

                TextView description      = findViewById(R.id.tv_description_af);
                         description.setText(auctionComplete.getArticles().get(catalogIndex).getDescription());

                TextView owner            = findViewById(R.id.tv_duenio_af);
                         owner.setText(auctionComplete.getArticles().get(catalogIndex).getOwner());

                TextView basePrice       = findViewById(R.id.tv_precio_base_af);
                         basePrice.setText(auctionComplete.getArticles().get(catalogIndex).getBasePrice());

                TextView catalogDescription = findViewById(R.id.tv_catalog_description_af);
                         catalogDescription.setText(auctionComplete.getArticles().get(catalogIndex).getCatalogDescription());

                Button  btnCatalogIndex  = findViewById(R.id.btn_catalog_index_af);
                        btnCatalogIndex.setText((catalogIndex + 1) +" DE " + auctionComplete.getArticles().size());

                CarouselView carouselView = findViewById(R.id.auction_carousel);
                carouselView.setPageCount(auctionComplete.getArticles().get(catalogIndex).getPictures().size());

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void previousAuctionCatalogItem(View view){

        String auctionId     = ((TextView) findViewById(R.id.tv_auction_id_af)).getText().toString();

        NavigationView navigationView = findViewById(R.id.navigationView);
        String userId = ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf)).getText().toString();

        try {
            Response response = new AuctionWithItemsDao().execute(Integer.valueOf(auctionId), userId.equals("User Id")? null: Integer.valueOf(userId)).get();

            SubastaConArticulos auctionComplete = (SubastaConArticulos) response.body();

            Integer catalogIndex     = Integer.valueOf(((TextView) findViewById(R.id.tv_catalog_index_af)).getText().toString()) - 1;

            if(catalogIndex >= 0){

                TextView catalogIndexTv   = findViewById(R.id.tv_catalog_index_af);
                         catalogIndexTv.setText(String.valueOf(catalogIndex));

                TextView articleTitle   = findViewById(R.id.tv_item_title_af);
                         articleTitle.setText(auctionComplete.getArticles().get(catalogIndex).getTitle());

                TextView status           = findViewById(R.id.tv_state_af);
                         status.setText(auctionComplete.getArticles().get(catalogIndex).getStatus());

                TextView description      = findViewById(R.id.tv_description_af);
                         description.setText(auctionComplete.getArticles().get(catalogIndex).getDescription());

                TextView owner            = findViewById(R.id.tv_duenio_af);
                         owner.setText(auctionComplete.getArticles().get(catalogIndex).getOwner());

                TextView basePrice       = findViewById(R.id.tv_precio_base_af);
                         basePrice.setText(auctionComplete.getArticles().get(catalogIndex).getBasePrice());

                TextView catalogDescription = findViewById(R.id.tv_catalog_description_af);
                         catalogDescription.setText(auctionComplete.getArticles().get(catalogIndex).getCatalogDescription());

                Button  btnCatalogIndex  = findViewById(R.id.btn_catalog_index_af);
                        btnCatalogIndex.setText( (catalogIndex + 1) +" DE " + auctionComplete.getArticles().size());

                CarouselView carouselView = findViewById(R.id.auction_carousel);
                carouselView.setPageCount(auctionComplete.getArticles().get(catalogIndex).getPictures().size());

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void saveNewArticleAction(View view){

        String userId           = ((TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_user_id_hf)).getText().toString();
        //String title          = ((TextView) findViewById(R.id.tv_titulo_naf)).     getText().toString();
        String description      = ((TextView) findViewById(R.id.tv_description_naf)).getText().toString();
        String fullDescription  = ((TextView) findViewById(R.id.tv_description_naf)).getText().toString();


        uploadNaf = (TextView) findViewById(R.id.tv_upload_naf);

        saveImageCloudinaryAction();


        if (description.matches("")) {
            ((TextView) findViewById(R.id.tv_description_naf)).setError(Utils.OBLIGATORY_FIELD);
            return;
        }

        if(cloudinaryUrlsList.size() == 0){
            simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_ERROR_IMAGE_REQUIRED);
            return;
        }

        ArticleRequest request = new ArticleRequest();
                       request.setUserId(userId);
                       request.setDescription(description);
                       request.setFullDescription(fullDescription);
                       request.setImages(cloudinaryUrlsList);

        try {

            Response response = new RegisterNewArticleDao().execute(request).get();

            switch (response.code()){
                case 201:
                    simpleDialogAlert(Utils.ALERT_MESSAGE, Utils.ALERT_NEW_ARTICLE_OK); showArticleFragment(); break;
                default:
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        };
    }


    public void initiatePhotoRequest1(View view){
        image     = findViewById(R.id.iv_image_1_naf);
        requestPermission();
    }

    public void initiatePhotoRequest2(View view){
        image     = findViewById(R.id.iv_image_2_naf);
        requestPermission();
    }

    public void initiatePhotoRequest3(View view){
        image     = findViewById(R.id.iv_image_3_naf);
        requestPermission();
    }

    public void initiatePhotoRequest4(View view){
        image     = findViewById(R.id.iv_image_4_naf);
        requestPermission();
    }

    private void requestPermission(){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            accessTheGallery();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
        }
    }

    public void accessTheGallery(){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, PICK_IMAGE);
    }

    private void selectImageFromGallery() {
        TedBottomPicker.with(MainActivity.this)
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
                .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {
                        if(uriList != null && !uriList.isEmpty()){
                            for(int i = 0; i < uriList.size(); i++)
                                localPhotoList.add(uriList.get(i).toString());
                            photoAdapter.setData(uriList);
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        filePath = getRealPathFromUri(data.getData(), MainActivity.this);
        filePathList.add(filePath);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode== PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                accessTheGallery();
            }else {
                Toast.makeText(MainActivity.this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getRealPathFromUri(Uri imageUri, Activity activity){
        Cursor cursor = activity.getContentResolver().query(imageUri, null, null, null, null);
        if(cursor==null) {
            return imageUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    public void saveImageCloudinaryAction(View view) {
        uploadNaf = findViewById(R.id.tv_upload_naf);
        //uploadToCloudinary(filePath);
        for(String file : filePathList){
            uploadToCloudinary(filePath);
        }
    }

    public void saveImageCloudinaryAction() {
        uploadNaf = findViewById(R.id.tv_upload_naf);
        //uploadToCloudinary(filePath);
        for(String file : filePathList){
            uploadToCloudinary(filePath);
        }
    }

    /*******************************************************************************************
     *                          UPLOAD CLOUDINARY
     ******************************************************************************************/

    private void uploadToCloudinary(String filePath) {
        Log.d("A", "sign up uploadToCloudinary- ");
        MediaManager.get().upload(filePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
                uploadNaf.setText("start");
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
                uploadNaf.setText("Uploading... " );
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                uploadNaf.setText("image URL: "+resultData.get("url").toString());
                cloudinaryUrlsList.add(resultData.get("url").toString());
            }

            @Override
            public void onError(String requestId, ErrorInfo error) {
                uploadNaf.setText("error "+ error.getDescription());
            }

            @Override
            public void onReschedule(String requestId, ErrorInfo error) {
                uploadNaf.setText("Reshedule "+error.getDescription());
            }
        }).dispatch();
    }

}