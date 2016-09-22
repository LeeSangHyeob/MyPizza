package controller;

import Project1.v3.controller.*;
import Project1.v3.dao.CustomerDAO;
import Project1.v3.model.MemberVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by SteveLEE on 2016-09-16.
 */
public class LoginController extends MainController {

    @FXML
    private TextField uid;
    @FXML
    private PasswordField pwd;
    @FXML
    public Button home;
    @FXML
    public Button logout;
    @FXML
    public Button about;

    public static String userid = "";

    public void login(ActionEvent e) {

        FXMLLoader loader = null;

        userid = CustomerDAO.loginChecked(uid.getText(), pwd.getText());

        userid = uid.getText();
        if (userid.equals("admin")) {
            loader = new FXMLLoader(getClass().getResource("/fxml/selectAMenu.fxml"));
        } else if (userid.equals("no")) {
            showWarn("로그인 실패!!");
        } else {
            showSuccess("로그인 성공!!");
            loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
        }

        Parent root = null;

        try {
            root = loader.load();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(userid.toString());
        Stage stage = (Stage) uid.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void join(ActionEvent ae) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/join.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void actTool(ActionEvent e) {

        FXMLLoader loader = null;
        Parent root = null;
        Stage stage = null;

        if (e.getTarget() == about) {
            showPinfo();
        } else {
            if (e.getTarget() == home) {
                loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
                stage = (Stage) home.getScene().getWindow();
            } else if (e.getTarget() == logout) {
                loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
                stage = (Stage) about.getScene().getWindow();
            }
            try {
                root = loader.load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}