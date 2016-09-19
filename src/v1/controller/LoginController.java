package v1.controller;

import Project1.v1.dao.CustomerDAO;
import Project1.v1.model.MemberVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by SteveLEE on 2016-09-16.
 */
public class LoginController extends MainController {

    @FXML
    private TextField uid;
    @FXML
    private PasswordField pwd;
    //@FXML private Label idInfo;

    public void login(ActionEvent e) {

        FXMLLoader loader = null;

        if (CustomerDAO.loginChecked(uid.getText(), pwd.getText()).equals("admin")) {
            loader = new FXMLLoader(getClass().getResource("../view/selectAMenu.fxml"));
        } else if (CustomerDAO.loginChecked(uid.getText(), pwd.getText()).equals("yes")) {
            showSuccess("로그인 성공!!");
            loader = new FXMLLoader(getClass().getResource("../view/selectCMenu.fxml"));
        } else {
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("로그인 실패!!");
            alert.showAndWait();*/
            showWarn("로그인 실패!!");
        }
        //idInfo.setText(uid.getText() + "님, 환영합니다.");

        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Stage stage = (Stage) uid.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void join(ActionEvent ae) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/join.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}