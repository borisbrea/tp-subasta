package com.example.navigationdrawerpractica.Utils;

import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.R;

public class Utils {

    public final static String ALERT_MESSAGE        = "Mensaje de Alerta";
    public final static String CONFIRMATION_MESSAGE = "Mensaje de Confirmación";

    public final static String ALERT_ERROR_400 = "Solicitud inválida";
    public final static String ALERT_ERROR_404 = "Usuario inexistente";

    public final static String ALERT_ERROR_LOGIN_400 = "Usuario o clave incorrecta";

    public final static String BID_ALERT_CORRECT_BID        = "Felicitaciones, su puja se ha realizado correctamente.";
    public final static String BID_ALERT_ALREADY_AUCTIONED  = "El catálogo ya no se encuentra subastandose.";
    public final static String BID_ALERT_ACTIVE_BID         = "Usted ya se encuentra participando en otra subasta.";
    public final static String BID_ALERT_MAX_TIME           = "El tiempo maximo para pujar ha sido superado.";
    public final static String BID_ALERT_INVALID_AMOUNT     = "El valor ingresado para la puja no es válido.";
    public final static String BID_ALERT_WINNING_BID        = "No se puede realizar una puja mientras esté ganando la subasta.";
    public final static String BID_ALERT_OWN_BID            = "No se puede realizar una puja por un artículo propio.";
    public final static String BID_ALERT_UNKNOW             = "Ha ocurrido un error inesperado, intente nuevamente mas tarde.";


    public final static String ALERT_DELETE_PAYMENT_MESSAGE = "¿Esta seguro que deséa eliminar este método de pago?";

    public final static String ALERT_CONFIRM_CREATE_PASSWORD   = "Clave generada exitosamente";
    public final static String ALERT_CONFIRM_REGISTER          = "Su usuario ha sido registrado exitosamente. Revise su mail para finalizar el proceso.";
    public final static String ALERT_CONFIRM_PAYMENT_METHOD    = "Su nuevo medio de pago ha sido registrado exitosamente.";
    public final static String ALERT_ERROR_CREATE_PASSWORD_500 = "Su clave y confirmación deben ser iguales";

    public final static String ALERT_ERROR_IMAGE_REQUIRED      = "Para cargar un nuevo artículo es obligatorio incluir como mínimo una imagen";
    public final static String ALERT_NEW_ARTICLE_OK            = "Felicitaciones, su artículo se ha cargado exitosamente y será evaluado por nuestros especialistas";


    public final static String ALERT_ERROR_DEFAULT = "Error de sistema, comuniquese con el administrador";

    public final static String BTN_ACCEPT = "Aceptar";
    public final static String BTN_CANCEL = "Cancelar";

    public final static String TITLE_MI_CUENTA        = "Mis Cuenta";
    public final static String TITLE_METODO_PAGO      = "Métodos de Pago";
    public final static String TITLE_MIS_PUJAS        = "Mis Pujas";
    public final static String TITLE_MIS_ESTADISTICAS = "Mis Estadísticas";
    public final static String TITLE_MIS_ARTICULOS    = "Mis Artículos";

    public final static String OBLIGATORY_FIELD       = "Campo obligatorio.";


}
