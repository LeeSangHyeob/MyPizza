package controller;

import dao.CustomerDAO;
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
    public Label bprice;
    @FXML
    public Label cprice;
    @FXML
    public Label doughInfo;
    @FXML
    public Label sourceInfo;
    @FXML
    public Label toppingInfo;
    @FXML
    public Label sideInfo;

    @FXML
    public Label pizzaInfo;

    public static int totalprice = 0;


    public void pay(ActionEvent ae) {

        Parent root = null;
        FXMLLoader loader = null;
        Stage stage = null;

       if(s == 1) {
            loader = new FXMLLoader(getClass().getResource("/fxml/successPay.fxml"));
           stage = (Stage) cprice.getScene().getWindow();

        }
        else if(s == 0){
           loader = new FXMLLoader(getClass().getResource("/fxml/successPay2.fxml"));
           stage = (Stage) bprice.getScene().getWindow();
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomerDAO.insertOrder(userid, totalprice);


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

        if(s == 1) {

            insertOrderlist();

            totalprice = CustomerDAO.selectDprice(dname) + CustomerDAO.selectSprice(sidename1)
                    + CustomerDAO.selectSprice(sidename2) + CustomerDAO.selectSprice(sidename3)
                    + CustomerDAO.selectTprice(tname1) + CustomerDAO.selectTprice(tname2)
                    + CustomerDAO.selectTprice(tname3) + CustomerDAO.selectTprice(tname4);

            doughInfo.setText(dname);
            sourceInfo.setText(sname);
            toppingInfo.setText(tname1 + "\n" + tname2 + "\n" + tname3 + "\n" + tname4);
            sideInfo.setText(sidename1 + "\n" + sidename2 + "\n" + sidename3);
            cprice.setText(String.valueOf(totalprice));

        } else if(s == 0){

            pizzaInfo.setText(pname);
            sideInfo.setText(sidename1 + "\n" + sidename2 + "\n" + sidename3);
            pizzaInfo.setText(pname);
            sideInfo.setText(sidename1 + "\n" + sidename2 + "\n" + sidename3);
            if(pname.equals("콤비네이션 M")){
                dname = "오리지널 M"; sname = "토마토 소스";
                tname1 = "페퍼로니 M"; tname2 = "양송이 버섯 M";
                totalprice = 9000;
            } else if(pname.equals("콤비네이션 L")){
                dname = "오리지널 L"; sname = "토마토 소스";
                tname1 = "페퍼로니 L"; tname2 = "양송이 버섯 L";
                totalprice = 13900;
            } else if(pname.equals("슈퍼슈프림 M")){
                dname = "오리지널 M"; sname = "토마토 소스";
                tname1 = "양송이 버섯 M"; tname2 = "파인애플 M";
                tname3 = "양파 M"; tname4 = "베이컨 M"; totalprice = 18000;
            } else if(pname.equals("슈퍼슈프림 L")){
                dname = "오리지널 L"; sname = "토마토 소스";
                tname1 = "양송이 버섯 L"; tname2 = "파인애플 L";
                tname3 = "양파 L"; tname4 = "베이컨 L"; totalprice = 24900;
            } else if(pname.equals("크림치즈갈릭 M")){
                dname = "오리지널 M"; sname = "크림갈릭 소스";
                tname1 = "양송이 버섯 M"; tname2 = "페퍼로니 M";
                tname3 = "양파 M"; totalprice = 14900;
            } else if(pname.equals("크림치즈갈릭 L")){
                dname = "오리지널 L"; sname = "크림갈릭 소스";
                tname1 = "양송이 버섯 L"; tname2 = "페퍼로니 L";
                tname3 = "양파 L"; totalprice = 18000;
            } else if(pname.equals("갈릭쉬림프 M")){
                dname = "오리지널 M"; sname = "크림갈릭 소스";
                tname1 = "새우 M"; tname2 = "베이컨 M";
                tname3 = "양파 M"; tname4 = "피망 M"; totalprice = 17900;
            } else if(pname.equals("갈림쉬림프 L")){
                dname = "오리지널 L"; sname = "크림갈릭 소스";
                tname1 = "새우 L"; tname2 = "베이컨 L";
                tname3 = "양파 L"; tname4 = "피망 L"; totalprice = 21900;
            } else if(pname.equals("바베큐쉬림프 M")){
                dname = "오리지널 M"; sname = "바베큐 소스";
                tname1 = "감자 M"; tname2 = "베이컨 M";
                tname3 = "파인애플 M"; tname4 = "피망 M"; totalprice = 17900;
            } else if(pname.equals("바베큐쉬림프 L")){
                dname = "오리지널 L"; sname = "바베큐 소스";
                tname1 = "감자 L"; tname2 = "베이컨 L";
                tname3 = "파인애플 L"; tname4 = "피망 L"; totalprice = 21900;
            }

            insertOrderlist();

            totalprice += CustomerDAO.selectSprice(sidename1)
                    + CustomerDAO.selectSprice(sidename2) + CustomerDAO.selectSprice(sidename3);
            bprice.setText(String.valueOf(totalprice));

        }
    }
}