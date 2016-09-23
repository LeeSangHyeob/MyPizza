package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by java on 2016-09-21.
 */
public class MenuController extends LoginController implements Initializable {

    @FXML public Button basic;
    @FXML public Button custom;
    @FXML public Button inventory;
    @FXML public Button sales;
    @FXML public Button list;
    @FXML public Button about;
    @FXML public ImageView logo;
    @FXML public Label idInfo;

    public static int s = 0;

    public void showMenu(ActionEvent e) {
        FXMLLoader loader = null;
        Parent root = null;
        Stage stage = null;

        if (userid.equals("admin")){

            if (e.getTarget() == sales) {
                loader = new FXMLLoader(getClass().getResource("/fxml/showSales.fxml"));
                try {
                    root = loader.load();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } else {
                showSuccess("See you Soon~");
            }

        } else {
            if (e.getTarget() == basic) {
                s = 0;
                loader = new FXMLLoader(getClass().getResource("/fxml/selectBasic.fxml"));
            } else if (e.getTarget() == custom) {
                s = 1;
                loader = new FXMLLoader(getClass().getResource("/fxml/selectDough.fxml"));
            } else if (e.getTarget() == list){
                loader = new FXMLLoader(getClass().getResource("/fxml/showOrder.fxml"));
            }
            try {
                root = loader.load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            stage = (Stage) about.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!userid.equals("admin")) idInfo.setText(userid);
        i = 0;
        s = 0;
    }
}