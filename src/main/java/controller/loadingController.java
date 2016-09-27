package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by java on 2016-09-21.
 */

// 모든 메뉴를 선택 후 스플래쉬 이미지로 넘어가는 컨트롤러
public class loadingController implements Initializable {
    @FXML
    public ProgressBar pgbar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task<Void> t = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for(int i = 1; i <= 100; i++){

                    updateProgress(i, 100);
                    Thread.sleep(80);
                }
                return null;
            }
            @Override
            protected void succeeded() {

                ((Stage)pgbar.getScene().getWindow()).close();
                showMainStage();
            }
        };
        pgbar.progressProperty().bind(t.progressProperty());

        Thread task = new Thread(t);
        task.setDaemon(true);
        task.start();

    }
    private void showMainStage() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CPayment.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("MyPizza pay");
        stage.show();
    }

}
