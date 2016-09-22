package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * Created by java on 2016-09-08.
 */
public class MainController {

    public void showWarn(String s) {
        Alert warn = new Alert(Alert.AlertType.WARNING);
        warn.setTitle("ERROR!!");
        warn.setHeaderText(null);
        warn.setContentText(s);
        warn.showAndWait();
    }

    public void showSuccess(String s){
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
        conf.setTitle("Success!!");
        conf.setContentText(s);
        ButtonType btn = new ButtonType("확인");
        conf.getButtonTypes().setAll(btn);
        conf.showAndWait();
    }

    public void showPinfo (){
        Alert pinfo = new Alert(Alert.AlertType.INFORMATION);
        pinfo.setTitle("MyPizza");
        pinfo.setHeaderText(null);
        pinfo.setContentText("MyPizza \nVer 1.00 \n\nCopyright© 2016. MyPizza. All Rights Reserved");
        pinfo.showAndWait();
    }
}