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
public class SalesController implements Initializable {
    @FXML
    Button cbtn;

    @FXML
    private ComboBox cbox;

    private String[] rs = new String[12];



    @FXML private TableView adtv;
    @FXML private TableColumn orderdate;
    @FXML private TableColumn orderno;
    @FXML private TableColumn userid1;
    @FXML private TableColumn olistno;
    @FXML private TableColumn totalprice;

    @FXML private Label total;



    private ObservableList<OrdersVO> olist=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < 12; i++){
            rs[i] = (i+1) + "";
        }
        ObservableList month = FXCollections.observableArrayList(rs);
        cbox.setItems(month);

        showChart();
        orderdate.setCellValueFactory(new PropertyValueFactory<OrdersVO,String>("orderdate"));
        orderno.setCellValueFactory(new PropertyValueFactory<OrdersVO,String>("orderno"));
        userid1.setCellValueFactory(new PropertyValueFactory<OrdersVO,String>("userid1"));
        olistno.setCellValueFactory(new PropertyValueFactory<OrdersVO,String>("olistno"));
        totalprice.setCellValueFactory(new PropertyValueFactory<OrdersVO, String>("totalprice"));

        olist = FXCollections.observableArrayList();
        if(cbox.getSelectionModel().getSelectedItem() != null) {
            List<OrdersVO> os = AdminDAO.allOrders();/*AdminDAO.Orders(Integer.parseInt(String.valueOf(cbox.getSelectionModel().getSelectedItem())));
            for (OrdersVO o : os) olist.add(o);*/
            adtv.setItems(olist);

            //ObservableList t = FXCollections.observableArrayList(rs);
        }
    }

    public void showList(){
        System.out.println(Integer.parseInt(String.valueOf(cbox.getSelectionModel().getSelectedItem())));
        List<OrdersVO> os = AdminDAO.Orders(Integer.parseInt(String.valueOf(cbox.getSelectionModel().getSelectedItem())));
        for (OrdersVO o : os) olist.setAll(os);
        adtv.setItems(olist);
    }

    public void showChart() {
        System.out.println(cbox.getSelectionModel().getSelectedItem());
    }

    public void close(ActionEvent event) {

        Stage prev = (Stage)cbtn.getScene().getWindow();
        prev.close();

    }
}