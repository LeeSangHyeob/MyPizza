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

    // 베이직 메뉴 선택일 때와 커스텀 메뉴 선택일 때를 알려주는 전역 변수 선언
    // 마찬가지로 SelectController 에서 사용되지만 초기화를 위해 미리 이곳에 선언
    public static int s = 0;

    public void showMenu(ActionEvent e) {
        FXMLLoader loader = null;
        Parent root = null;
        Stage stage = null;

        // 만약 admin 으로 로그인 했다면
        if (userid.equals("admin")){

            // 재고 관리 버튼을 눌렀을 때, 실행되는 내용
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

                // 그외 버튼들은 아직 미구현이기 때문에 Alert 창 띄워줌
            } else {
                showSuccess("See you Soon~");
            }

            // userid가 admin이 아니라면
        } else {

            // 베이직 메뉴 선택 시 selectBasic.fxml 호출
            // s == 0 일 때, basic 선택이라는 것을 정의
            if (e.getTarget() == basic) {
                s = 0;
                loader = new FXMLLoader(getClass().getResource("/fxml/selectBasic.fxml"));

                // 커스텀 메뉴 선택 시 selectDough.fxml 호출
                // s == 1 일때, custom 이라는 것을 정의
            } else if (e.getTarget() == custom) {
                s = 1;
                loader = new FXMLLoader(getClass().getResource("/fxml/selectDough.fxml"));

                // 주문 내역 선택 시 , showOrder. fxml 호출
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

    // 초기화, 이 컨트롤러가 실행될 때마다 가장 먼저 실행되는 것 Initialize
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!userid.equals("admin")) idInfo.setText(userid);
        i = 0;
        s = 0;
    }
}