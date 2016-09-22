package controller;

import dao.CustomerDAO;
import model.OrderlistVO;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by java on 2016-09-19.
 */
public class SelectController extends LoginController implements Initializable {

    @FXML
    private Pane infoPane;
    @FXML
    private Button next;
    @FXML
    private TextArea txt;
    @FXML
    private ToggleGroup group;
    @FXML
    public Label idInfo;

    public static String sname = "";
    public static String dname = "";
    public static String tname1 = "";
    public static String tname2 = "";
    public static String tname3 = "";
    public static String tname4 = "";
    public static String sidename1 = "";
    public static String sidename2 = "";
    public static String sidename3 = "";

    OrderlistVO o = new OrderlistVO();



    // select 페이지들에 변수 설정
    // i = 0 이면 dough, i = 1 이면 source, i = 2 이면 topping i = 3 이면 sidemenu
    public static int i = 0;

    // 페이지 이동 방식에 따른 변수
    // p = 0이면 next, 1이면 prev 버튼이 눌렸고, textArea 에 들어갈 변수들이 달라짐
    //public static int p = 0;

    // 왼쪽 사이드 Info 패널 액션에 따른 변수
    // act = 0 이면 닫혀 있는 상태, 1이면 열린 상태
    public static int act = 0;

    // 메뉴 선택 시 변화할 count 변수
    public static int cnt = 0;

    public static String rs = null;

    public void nextPage(ActionEvent ae) {
        Stage stage = null;
        FXMLLoader loader = null;
        Parent root = null;

        stage = (Stage) next.getScene().getWindow();

        if (i == 0) {
            if (cnt != 0) {
                loader = new FXMLLoader(getClass().getResource("/fxml/selectSource.fxml"));
                i = 1;
            } else showWarn("메뉴를 선택해주세요.");
            System.out.println(cnt);
        } else if (i == 1) {
            if (cnt != 0) {
                loader = new FXMLLoader(getClass().getResource("/fxml/selectTopping.fxml"));
                i = 2;
            } else showWarn("메뉴를 선택해주세요.");
            tname1 = "";
            tname2 = "";
            tname3 = "";
            tname4 = "";
            sidename1 = "";
            sidename2 = "";
            sidename3 = "";
            System.out.println(cnt);
        } else if (i == 2) {
            if (cnt != 0) {
                loader = new FXMLLoader(getClass().getResource("/fxml/selectSideMenu.fxml"));
                i = 3;
            } else showWarn("메뉴를 선택해주세요");
            System.out.println(cnt);
        } else if (i == 3) {
            loader = new FXMLLoader(getClass().getResource("/fxml/payment.fxml"));
            i = 0;
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        cnt = 0;
    }

    public void prevPage(ActionEvent ae) {
        Stage stage = null;
        FXMLLoader loader = null;
        Parent root = null;

        stage = (Stage) next.getScene().getWindow();

        if (i == 3) {
            tname1 = "";
            tname2 = "";
            tname3 = "";
            tname4 = "";
            sidename1 = "";
            sidename2 = "";
            sidename3 = "";
            loader = new FXMLLoader(getClass().getResource("/fxml/selectTopping.fxml"));
            i = 2;
        } else if (i == 2) {
            tname1 = "";
            tname2 = "";
            tname3 = "";
            tname4 = "";
            sname = "";
            loader = new FXMLLoader(getClass().getResource("/fxml/selectSource.fxml"));
            i = 1;
        } else if (i == 1) {
            dname = "";
            loader = new FXMLLoader(getClass().getResource("/fxml/selectDough.fxml"));
            i = 0;
        } else if (i == 0) {
            loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        cnt = 0;
    }

    // 라디오 버튼 선택 시,
    public void selected(ActionEvent ae) {
        // selectDough 에서 선택시, dname 에 값 저장
        if (i == 0) {
            if (group.getSelectedToggle() != null) {
                dname = (String) group.getSelectedToggle().getUserData();
                txt.setText(dname);
                cnt = 1;
            }
            // selectSource 에서 선택시, sname 에 값 저장
        } else if (i == 1) {
            if (group.getSelectedToggle() != null) {
                sname = (String) group.getSelectedToggle().getUserData();
                txt.setText(dname + "\n" + sname);
                cnt = 1;
            }
            // selectTopping 에서 선택시, tname 들에 값 저장
        } else if (i == 2) {
            if (cnt > 3) {
                showWarn("더 이상 선택할 수 없습니다.");
                group.getSelectedToggle().setSelected(false);
            } else {
                if (group.getSelectedToggle() != null) {
                    if (cnt == 0) tname1 = (String) group.getSelectedToggle().getUserData();
                    else if (cnt == 1) tname2 = (String) group.getSelectedToggle().getUserData();
                    else if (cnt == 2) tname3 = (String) group.getSelectedToggle().getUserData();
                    else if (cnt == 3) tname4 = (String) group.getSelectedToggle().getUserData();
                    txt.setText(dname + "\n" + sname + "\n" + tname1 + "\n" + tname2 + "\n" + tname3 + "\n" + tname4);
                    cnt++;
                }
            }
            // selectSideMenu 에서 선택시, sidename 들에 값 저장
        } else if (i == 3) {
            if (cnt > 2) {
                showWarn("더 이상 선택할 수 없습니다.");
                group.getSelectedToggle().setSelected(false);
            } else {
                if (group.getSelectedToggle() != null) {
                    if (cnt == 0) sidename1 = (String) group.getSelectedToggle().getUserData();
                    else if (cnt == 1) sidename2 = (String) group.getSelectedToggle().getUserData();
                    else if (cnt == 2) sidename3 = (String) group.getSelectedToggle().getUserData();
                    txt.setText(dname + "\n" + sname + "\n" + tname1 + "\n" + tname2 + "\n" + tname3 + "\n" + tname4 + "\n" + sidename1 + "\n" + sidename2 + "\n" + sidename3);
                    cnt++;
                }
            }
        }
    }


    // 왼쪽
    public void onInfo(ActionEvent ae) {
        try {
            TranslateTransition swipe = new TranslateTransition();
            swipe.setNode(infoPane);
            if (act == 0) {
                swipe.setToX(105);
                swipe.setDuration(Duration.millis(500));
                act = 1;
            } else {
                swipe.setToX(0);
                swipe.setDuration(Duration.millis(500));
                act = 0;
            }
            swipe.play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        act = 0;
        cnt = 0;

        if(!userid.equals("admin")) idInfo.setText(CustomerDAO.selectName(userid));
        if (i == 0) txt.setText("");
        else if (i == 1) txt.setText(dname);
        else if (i == 2) txt.setText(dname + "\n" + sname);
        else if (i == 3) txt.setText(dname + "\n" + sname + "\n" + tname1 + "\n" + tname2 + "\n" + tname3 + "\n" + tname4);
    }
}