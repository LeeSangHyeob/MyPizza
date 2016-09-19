package v1.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


/*
 * Created by SteveLEE on 2016-09-16.
 */
public class AMenuController {

    @FXML private Button inventory;
    @FXML private Button sales;

    public void showMenu(ActionEvent e) {

        FXMLLoader loader = null;
        Parent root = null;

        if (e.getTarget() == inventory) {

        } else if (e.getTarget() == sales) {
            loader = new FXMLLoader(getClass().getResource("../view/showSales.fxml"));
        }

        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}