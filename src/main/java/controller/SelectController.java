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
public class SelectController extends MenuController implements Initializable {

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


    // 데이터 저장을 위한 주문목록 데이터의 전역변수 선언
    // 페이지 이동 시마다 해당 변수의 값을 유지시키기 위해서 전역 변수로 선언
    // 만약 이런 식으로 안했으면 객체에 데이터를 저장하거나 각각의 변수들을 매번 저장해놓고 옮겨야 하는 번거로움이 있음
    public static String sname = "";
    public static String dname = "";
    public static String tname1 = "";
    public static String tname2 = "";
    public static String tname3 = "";
    public static String tname4 = "";
    public static String sidename1 = "";
    public static String sidename2 = "";
    public static String sidename3 = "";
    public static String pname = "";

    OrderlistVO o = new OrderlistVO();


    // select 페이지들에 변수 설정
    // i = 0 이면 dough, i = 1 이면 source, i = 2 이면 topping i = 3 이면 sidemenu
    //public static int i = 0;

    // 페이지 이동 방식에 따른 변수
    // p = 0이면 next, 1이면 prev 버튼이 눌렸고, textArea 에 들어갈 변수들이 달라짐
    //public static int p = 0;

    // 왼쪽 사이드 Info 패널 액션에 따른 변수
    // act = 0 이면 닫혀 있는 상태, 1이면 열린 상태
    public static int act = 0;

    // 메뉴 선택 시 변화할 count 변수
    public static int cnt = 0;

    public static String rs = null;


    // 다음 버튼 클릭 시
    public void nextPage(ActionEvent ae) {
        Stage stage = null;
        FXMLLoader loader = null;
        Parent root = null;

        stage = (Stage) next.getScene().getWindow();

        // 커스텀 메뉴에서,
        if (s == 1) {

            // 페이지가 selectDough.fxml 이면,
            if (i == 0) {

                // 메뉴가 하나 선택 되었다면,
                if (cnt != 0) {

                    // selectSource fxml로 페이지 이동
                    loader = new FXMLLoader(getClass().getResource("/fxml/selectSource.fxml"));
                    i = 1;

                    // 메뉴가 하나도 선택이 안되었다면,
                } else showWarn("메뉴를 선택해주세요.");

                // 페이지가 selectSource fxml 이라면,
            } else if (i == 1) {

                // 메뉴가 선택되었다면,
                if (cnt != 0) {

                    // selectTopping fxml로 페이지 이동
                    loader = new FXMLLoader(getClass().getResource("/fxml/selectTopping.fxml"));
                    i = 2;

                    // 메뉴가 선택이 안되었다면
                } else showWarn("메뉴를 선택해주세요.");
                tname1 = "";
                tname2 = "";
                tname3 = "";
                tname4 = "";
                sidename1 = "";
                sidename2 = "";
                sidename3 = "";

                // 페이지가 selectTopping fxml 이라면
            } else if (i == 2) {

                // 메뉴가 선택되었다면,
                if (cnt != 0) {
                    loader = new FXMLLoader(getClass().getResource("/fxml/selectSideMenu.fxml"));
                    i = 3;

                    // 메뉴가 선택되지 않았다면,
                } else showWarn("메뉴를 선택해주세요");

                // 페이지가 selectSidemenu fxml 이라면
            } else if (i == 3) {
                //loader = new FXMLLoader(getClass().getResource("/fxml/CPayment.fxml"));

                // loading1 fxml로 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/loading1.fxml"));
                i = 4;
            }

            // 베이직 메뉴 선택 시
        } else if(s == 0) {

            // selectBasic fxml 페이지라면,
            if (i == 0) {

                // 메뉴가 선택되었다면,
                if (cnt != 0) {

                    // selectSideMenu fxml로 페이지 이동
                    loader = new FXMLLoader(getClass().getResource("/fxml/selectSideMenu.fxml"));
                    i = 3;

                    // 메뉴가 선택 안되었다면,
                } else showWarn("메뉴를 선택해주세요.");

                // selectSidemenu fxml 페이지라면,
            } else if(i == 3){
                //loader = new FXMLLoader(getClass().getResource("/fxml/BPayment.fxml"));

                // loading2 fxml로 페이지 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/loading2.fxml"));
                i = 4;
            }
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        // 페이지 이동 시마다 cnt 값을 0 초기화
        cnt = 0;
    }

    // 이전 버튼 클릭 시
    public void prevPage(ActionEvent ae) {
        Stage stage = null;
        FXMLLoader loader = null;
        Parent root = null;

        stage = (Stage) next.getScene().getWindow();

        // 커스텀 메뉴 선택 시
        if (s == 1) {

            // selectSidemenu fxml 이라면
            if (i == 3) {

                // 이전에 선택된 데이터들을 초기화 시켜줌, 다시 선택
                tname1 = "";
                tname2 = "";
                tname3 = "";
                tname4 = "";
                sidename1 = "";
                sidename2 = "";
                sidename3 = "";

                // selectTopping fxml 로 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/selectTopping.fxml"));
                i = 2;

                // selectTopping fxml 이라면
            } else if (i == 2) {

                // 이전에 선택된 데이터들을 초기화 시켜줌, 다시 선택
                tname1 = "";
                tname2 = "";
                tname3 = "";
                tname4 = "";
                sname = "";

                // selectSource fxml 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/selectSource.fxml"));
                i = 1;

                // selectSource  fxml 이라면
            } else if (i == 1) {

                // 이전에 선택된 데이터들을 초기화 시켜줌, 다시 선택
                dname = "";

                // selectdough fxml 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/selectDough.fxml"));
                i = 0;

                // selectdough fxml 이라면
            } else if (i == 0) {

                // selectcmenu fxml로 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
            }

            // 베이직 메뉴 선택 시
        } else {

            // selectSidemenu fxml 이라면
            if (i == 3) {

                // 이전에 선택된 데이터들을 초기화 시켜줌, 다시 선택
                tname1 = "";
                tname2 = "";
                tname3 = "";
                tname4 = "";
                sidename1 = "";
                sidename2 = "";
                sidename3 = "";

                // selectBasic fxml 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/selectBasic.fxml"));
                i = 0;

                // selectBasic fxml 이라면
            } else {
                pname = "";

                // selectCmenu fxml 이동
                loader = new FXMLLoader(getClass().getResource("/fxml/selectCMenu.fxml"));
            }
        }

        try {
            root = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        // 페이지 이동시 마다 cnt 값을 초기화
        cnt = 0;

    }

    // 라디오 버튼 선택 시,
    public void selected(ActionEvent ae) {

        // 커스텀 메뉴 선택 시
        // selectDough 에서 선택시, dname 에 값 저장
        if (s == 1) {
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

        // 베이직 메뉴 선택 시
        else {

            // selectBasic 페이지라면, 선택한 베이직 피자의 데이터를 pname 변수에 저장
            if(i == 0){
                if (group.getSelectedToggle() != null) {
                    pname = (String) group.getSelectedToggle().getUserData();
                    txt.setText(pname);
                    cnt = 1;
                }
            }

            // selectSideMenu 에서 선택시, sidename 들에 값 저장
            else if (i == 3) {
                if (cnt > 2) {
                    showWarn("더 이상 선택할 수 없습니다.");
                    group.getSelectedToggle().setSelected(false);
                } else {
                    if (group.getSelectedToggle() != null) {
                        if (cnt == 0) sidename1 = (String) group.getSelectedToggle().getUserData();
                        else if (cnt == 1) sidename2 = (String) group.getSelectedToggle().getUserData();
                        else if (cnt == 2) sidename3 = (String) group.getSelectedToggle().getUserData();
                        txt.setText(pname + "\n\n\n\n" + sidename1 + "\n" + sidename2 + "\n" + sidename3);
                        cnt++;
                    }
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


    // 초기화
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        act = 0;
        cnt = 0;

        if (!userid.equals("admin")) idInfo.setText(CustomerDAO.selectName(userid));

        // 페이지마다 onInfo 에 있는 textArea에 들어갈 데이터들을 다르게 셋팅해줘야 함
        if (s == 1) {
            if (i == 0) txt.setText("");
            else if (i == 1) txt.setText(dname);
            else if (i == 2) txt.setText(dname + "\n" + sname);
            else if (i == 3)
                txt.setText(dname + "\n" + sname + "\n" + tname1 + "\n" + tname2 + "\n" + tname3 + "\n" + tname4);
        } else {
            if (i == 0) txt.setText("");
            else if (i == 3) txt.setText(pname);
        }
    }
}