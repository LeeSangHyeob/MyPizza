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
public class OrderController extends SelectController implements Initializable {

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

    private ObservableList<OrderlistVO> olist=null;

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
        List<OrderlistVO> os = CustomerDAO.Orderlist(userid);
        for(OrderlistVO o : os)olist.add(o);

        bdtv.setItems(olist);

        System.out.println(CustomerDAO.Orderlist(userid));

        if(!userid.equals("admin")) idInfo.setText(userid);


    }

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