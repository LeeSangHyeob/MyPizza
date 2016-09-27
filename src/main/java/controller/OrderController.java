package controller;

import dao.CustomerDAO;
import model.OrderlistVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Created by java on 2016-09-22.
 */

// 주문 내역을 출력하기 위한 컨트롤러
public class OrderController extends MenuController implements Initializable {

    // 테이블 뷰 및 테이블 컬럼 선언
    @FXML TableView bdtv;
    @FXML TableColumn olistno;
    @FXML TableColumn dname;
    @FXML TableColumn sname;
    @FXML TableColumn tname1;
    @FXML TableColumn tname2;
    @FXML TableColumn tname3;
    @FXML TableColumn tname4;
    @FXML TableColumn sidename1;
    @FXML TableColumn sidename2;
    @FXML TableColumn sidename3;

    // 테이블 뷰에 나타낼 주문 리스트를 저장할 변수 선언
    private ObservableList<OrderlistVO> olist=null;

    // 초기화, 이 컨트롤러가 실행될 때마다 가장 먼저 실행되는 것 Initialize
    // 버튼 클릭으로 페이지가 로딩 될 때, 바로 주문 내역이 뜨도록 Initialize에 선언하였음
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        olistno.setCellValueFactory(new PropertyValueFactory<OrderlistVO, Integer>("olistno"));
        dname.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("dname"));
        sname.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("sname"));
        tname1.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("tname1"));
        tname2.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("tname2"));
        tname3.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("tname3"));
        tname4.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("tname4"));
        sidename1.setCellValueFactory(new PropertyValueFactory<OrderlistVO, String>("sidename1"));
        sidename2.setCellValueFactory(new PropertyValueFactory<OrderlistVO,String>("sidename2"));
        sidename3.setCellValueFactory(new PropertyValueFactory<OrderlistVO,String>("sidename3"));

        olist = FXCollections.observableArrayList();

        //CustomerDAO.Orderlist 메서드를 통해 OrderlistVO 배열 객체인 os에 해당 데이터들을 저장
        List<OrderlistVO> os = CustomerDAO.Orderlist(userid);

        // os에 있는 OrderlistVO o의 객체 수만큼 olist에 해당 o를 저장
        for(OrderlistVO o : os)olist.add(o);

        // 테이블 뷰에 olist를 셋팅해줌
        bdtv.setItems(olist);
    }

    // 이전 메뉴로 돌아가기
    public void gohome(ActionEvent event) {

        Stage stage = null;
        try {
            Parent login = FXMLLoader.load(getClass().getResource("/fxml/selectCMenu.fxml"));

            stage =(Stage) bdtv.getScene().getWindow();
            stage.setScene(new Scene(login));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}