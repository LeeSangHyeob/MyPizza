package v2.controller;

import Project1.v2.model.OrderlistVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by java on 2016-09-19.
 */
public class SelectController extends LoginController {

    @FXML private Button next;

    OrderlistVO o = new OrderlistVO();
    FXMLLoader loader = null;
    Parent root = null;

    public static int i = 0;

    public void nextPage(ActionEvent ae) {
        Stage stage = (Stage) next.getScene().getWindow();
        System.out.println(next.getScene());
        System.out.println(next.getScene().getWindow().getProperties());

        if (i == 0) {
            loader = new FXMLLoader(getClass().getResource("../view/selectSource.fxml"));
            i = 1;
        } else if(i == 1){
            loader = new FXMLLoader(getClass().getResource("../view/selectTopping.fxml"));
            i = 2;
        } else if(i == 2){
            loader = new FXMLLoader(getClass().getResource("../view/payment.fxml"));
            i = 3;
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void prevPage(ActionEvent ae) {
        Stage stage = (Stage) next.getScene().getWindow();
        System.out.println(next.getScene());
        System.out.println(next.getScene().getWindow().getProperties());

        if (i == 3) {
            loader = new FXMLLoader(getClass().getResource("../view/selectTopping.fxml"));
            i = 2;
        } else if(i == 2){
            loader = new FXMLLoader(getClass().getResource("../view/selectSource.fxml"));
            i = 1;
        } else if(i == 1){
            loader = new FXMLLoader(getClass().getResource("../view/selectDough.fxml"));
            i = 0;
        } else if(i == 0){
            loader = new FXMLLoader(getClass().getResource("../view/selectCMenu.fxml"));
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root));
        stage.show();
    }

    public void selected(ActionEvent ae) {
    }

    public void onInfo(ActionEvent actionEvent) {
    }
}
