package v2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/*
 * Created by SteveLEE on 2016-09-16.
 */
public class CMenuController {

    @FXML private Button basic;
    @FXML private Button custom;
    @FXML private BorderPane top;

    public void showMenu(ActionEvent e) {

        FXMLLoader loader = null;
        Parent root = null;

        if (e.getTarget() == basic) {
            loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        } else if (e.getTarget() == custom) {
            loader = new FXMLLoader(getClass().getResource("../view/selectDough.fxml"));
        } else{
            loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        }

        try {
            root = loader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Stage stage = (Stage) basic.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}