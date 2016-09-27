package controller;

import controller.*;
import dao.CustomerDAO;
import model.MemberVO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SteveLEE on 2016-09-16.
 */

// 로그인할 때 사용되는 컨트롤러
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

    // 전역변수 userid를 선언, 로그인이 성공한다면 해당 id의 값을 저장하여 유지시켜줌
    public static String userid = "";

    // SelectController 에서 사용될 전역변수 선언, i의 값에 따라 loader에서 가져오는 페이지가 다름
    // 사용은 SelectController 에서 하지만, 초기화를 위해 미리 선언
    public static int i = 0;

    // 로그인 버튼 클릭시 작동하는 메서드
    public void login(ActionEvent e) {

        FXMLLoader loader = null;

        userid = uid.getText();

        // CustomerDAO.loginChecked 메서드를 통해 userid의 값을 받아옴
        userid = CustomerDAO.loginChecked(uid.getText(), pwd.getText());

        // 만약 해당 아이디가 없다면 로그인 실패
        if (userid.equals("no")) {
            showWarn("로그인 실패!!");
        } else {
            // 아이디가 admin 이라면 selectAMenu fxml을 로딩
            if (userid.equals("admin")) {
                loader = new FXMLLoader(getClass().getResource("/fxml/selectAMenu.fxml"));
            } else {
                // 그 외 아이디라면 selectCMenu fxml을 로딩
                showSuccess("로그인 성공!!");
                loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
            }

            Parent root = null;

            try {
                root = loader.load();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // 현재 스테이지의 값을 stage에 저장하고 새로운 Scene을 셋팅해줌
            Stage stage = (Stage) uid.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    // 회원 가입 버튼을 눌렀을 때 실행되는 메서드
    public void join(ActionEvent ae) {

        // 회원가입 fxml을 새로 띄워줌
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

    // Toolbar 에 있는 세가지 버튼을 클릭할 때 실행되는 메서드
    public void actTool(ActionEvent e) {

        FXMLLoader loader = null;
        Parent root = null;
        Stage stage = null;

        // 프로그램 정보를 확인 버튼 클릭 시 확인하는 Alert 창을 띄워줌
        if (e.getTarget() == about) {
            showPinfo();


        } else {
            // i 값 초기화 (이후 다시 메뉴 선택 시 아무런 문제 없는 이동을 위해서)
            i = 0;

            // 위 about 과 따로 나눈 이유 : about의 경우 Alert 창, 다른 두개의 경우는 root, loader.road()를 이용하기 때문에 따로 선언을 해줘야 함
            // home 버튼 클릭 시 실행
            if (e.getTarget() == home) {
                loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
                stage = (Stage) home.getScene().getWindow();

                // logout 버튼 클릭 시 실행
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