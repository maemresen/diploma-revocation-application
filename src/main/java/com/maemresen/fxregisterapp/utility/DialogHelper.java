package com.maemresen.fxregisterapp.utility;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogHelper {

    /*-------------------------------
        Dialogs
    -------------------------------*/

    // confirmation
    public static boolean confirmationDialogForInsert() {
        ButtonType buttonType = confirmationDialog("Bu Kaydı Eklemek İstediğinize Eminmisiniz?", "Yeni Kayıt", "Kaydı eklemek için OK tuşuna basınız");
        return isOk(buttonType);
    }

    public static boolean confirmationDialogForUpdate() {
        ButtonType buttonType = confirmationDialog("Bu Kaydı Güncellemek İstediğinize Eminmisiniz?", "Kaydı Güncelleme", "Kaydı Güncellemek için OK tuşuna basınız");
        return isOk(buttonType);
    }

    private static ButtonType confirmationDialog(String msg, String title, String context) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(msg);
        alert.setContentText(context);
        Optional<ButtonType> result = alert.showAndWait();
        //noinspection ConstantConditions
        return result.get();
    }

    private static boolean isOk(ButtonType buttonType) {
        return buttonType == ButtonType.OK;
    }

    /* info */
    public static void setInfo(String info) {
        showAlert(Alert.AlertType.INFORMATION, info);
    }

    /* alert */
    private static void showAlert(Alert.AlertType alertType, String msg) {
        Alert alert = new Alert(alertType, msg, ButtonType.CLOSE);
        alert.showAndWait();
    }

    public  static void setResponse(String msg) {
        DialogHelper.showAlert(Alert.AlertType.ERROR, msg);
    }



}
