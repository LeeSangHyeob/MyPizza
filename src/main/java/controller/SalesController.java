package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-09.
 */
public class SalesController implements Initializable {

    @FXML
    private ComboBox cbox;

    private String[] rs = new String[12];


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < 12; i++){
            rs[i] = (i+1) + "월";
        }
        ObservableList month = FXCollections.observableArrayList(rs);
        cbox.setItems(month);

        showChart();
    }

    public void showChart() {
        System.out.println(cbox.getSelectionModel().getSelectedItem());
    }
}