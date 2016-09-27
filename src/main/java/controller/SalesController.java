package controller;

import dao.AdminDAO;
import model.OrdersVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Created by java on 2016-09-09.
 */

// 관리자 모드에서 매출 관리 탭을 선택하였을 때 실행되는 컨트롤러
public class SalesController implements Initializable {
    @FXML
    Button cbtn;

    @FXML
    private ComboBox cbox;

    private String[] rs = new String[12];

    // 매출 내역을 출력할 테이블 뷰 및 테이블 컬럼
    @FXML private TableView adtv;
    @FXML private TableColumn orderdate;
    @FXML private TableColumn orderno;
    @FXML private TableColumn userid1;
    @FXML private TableColumn olistno;
    @FXML private TableColumn totalprice;

    @FXML private Label total;

    private ObservableList<OrdersVO> olist=null;


    // 초기화
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 콤보박스에 1~12월 데이터 저장 (월 매출 조회를 위한)
        for(int i = 0; i < 12; i++){
            rs[i] = (i+1) + "";
        }
        ObservableList month = FXCollections.observableArrayList(rs);
        cbox.setItems(month);


        orderdate.setCellValueFactory(new PropertyValueFactory<OrdersVO,String>("orderdate"));
        orderno.setCellValueFactory(new PropertyValueFactory<OrdersVO,Integer>("orderno"));
        userid1.setCellValueFactory(new PropertyValueFactory<OrdersVO,String>("userid1"));
        olistno.setCellValueFactory(new PropertyValueFactory<OrdersVO,Integer>("olistno"));
        totalprice.setCellValueFactory(new PropertyValueFactory<OrdersVO, Integer>("totalprice"));

        // 기본적으로는 총매출을 출력하게 하기 위한 AdminDAO.allOrders 메서드를 실행하여 ordersVO 배열인 os에 저장
        olist = FXCollections.observableArrayList();
        if(cbox.getSelectionModel().getSelectedItem() == null) {
            List<OrdersVO> os = AdminDAO.allOrders();

            // olist에 os 객체들을 모두 저장
            for (OrdersVO o : os) olist.setAll(os);

            // 테이블 뷰에 olist 셋팅
            adtv.setItems(olist);

        }
        // AdminDAO.allSales 메서드로 총 매출액을 계산하여 표시
        total.setText(String.valueOf(AdminDAO.allSales()));
    }

    // 콤보 박스에서 월 선택시 마다 실행
    public void showList(){
        System.out.println(Integer.parseInt(String.valueOf(cbox.getSelectionModel().getSelectedItem())));

        // 해당 월에 해당하는 매출액을 조회하는 AdminDAO.Orders 메서드를 실행
        List<OrdersVO> os = AdminDAO.Orders(Integer.parseInt(String.valueOf(cbox.getSelectionModel().getSelectedItem())));
        for (OrdersVO o : os) olist.setAll(os);
        adtv.setItems(olist);

        // AdminDAO.monthSales를 통해 월 매출액을 계산하여 표시
        total.setText(String.valueOf(AdminDAO.monthSales(Integer.parseInt(String.valueOf(cbox.getSelectionModel().getSelectedItem())))));
    }

    public void close(ActionEvent event) {

        Stage prev = (Stage)cbtn.getScene().getWindow();
        prev.close();

    }
}