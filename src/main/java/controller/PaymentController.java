package controller;

import Project1.v3.dao.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-21.
 */
public class PaymentController extends SelectController implements Initializable {

    @FXML
    public Label price;

    @FXML
    public Label doughInfo;
    @FXML
    public Label sourceInfo;
    @FXML
    public Label toppingInfo;
    @FXML
    public Label sideInfo;

    public static int totalprice = 0;


    public void pay(ActionEvent ae) {

        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/fxml/successPay.fxml"));

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) doughInfo.getScene().getWindow();
        CustomerDAO.insertOrder(userid, totalprice);
        System.out.println(stage);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void insertOrderlist() {

        o.setDname(dname);
        o.setSname(sname);
        o.setTname1(tname1);
        o.setTname2(tname2);
        o.setTname3(tname3);
        o.setTname4(tname4);
        o.setSidename1(sidename1);
        o.setSidename2(sidename2);
        o.setSidename3(sidename3);

        CustomerDAO.insertOrderList(o);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        insertOrderlist();

        totalprice = CustomerDAO.selectDprice(dname)+CustomerDAO.selectSprice(sidename1)
                +CustomerDAO.selectSprice(sidename2)+CustomerDAO.selectSprice(sidename3)
                +CustomerDAO.selectTprice(tname1)+CustomerDAO.selectTprice(tname2)
                +CustomerDAO.selectTprice(tname3)+CustomerDAO.selectTprice(tname4);

        doughInfo.setText(dname);
        sourceInfo.setText(sname);
        toppingInfo.setText(tname1 + "\n" + tname2 + "\n" + tname3 + "\n" + tname4);
        sideInfo.setText(sidename1 + "\n" + sidename2 + "\n" + sidename3);
        price.setText(String.valueOf(totalprice));
    }
}