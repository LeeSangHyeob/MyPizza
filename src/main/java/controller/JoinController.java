package controller;

import dao.CustomerDAO;
import model.MemberVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SteveLEE on 2016-09-16.
 */
public class JoinController extends MainController implements Initializable {

    // 생년월일 콤보박스에 들어갈 아이템들을 저장할 변수
    private String[] yrs = new String[57];
    private String[] mrs = new String[12];
    private String[] drs = new String[31];

    // 회원가입에 이용할 개인정보 입력할 컨트롤
    @FXML private TextField uid;
    @FXML private PasswordField pwd;
    @FXML private PasswordField rpwd;
    @FXML private TextField name;

    // 생년월일 입력받을 콤보박스
    @FXML private ComboBox ybox;
    @FXML private ComboBox mbox;
    @FXML private ComboBox dbox;

    // 가입약관 동의 체크박스
    @FXML private CheckBox cbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i <= 56; i++) {
            yrs[i] = (i + 1960) + "";
        }
        ObservableList year = FXCollections.observableArrayList(yrs);
        ybox.setItems(year);

        for (int i = 0; i < 12; i++) {
            mrs[i] = (i + 1) + "";
        }

        ObservableList month = FXCollections.observableArrayList(mrs);
        mbox.setItems(month);

        for (int i = 0; i < 31; i++) {
            drs[i] = (i + 1) + "";
        }

        ObservableList day = FXCollections.observableArrayList(drs);
        dbox.setItems(day);
    }

    // 가입하기 버튼 클릭시 이벤트 발생
    public void joinbtn(ActionEvent e) {
        if (uid.getText().equals("")) {
            showWarn("아이디를 입력하세요.");
            uid.requestFocus();
        } else if (pwd.getText().equals("")) {
            showWarn("비밀번호를 입력하세요.");
            pwd.requestFocus();
        } else if (pwd.getText().length() < 6) {
            showWarn("비밀번호 6자 이상 입력하세요.");
        } else if (rpwd.getText().equals("")) {
            showWarn("비밀번호확인을 입력하세요.");
            rpwd.requestFocus();
        } else if (!(pwd).getText().equals(rpwd.getText())) {
            showWarn("입력한 비밀번호가 다릅니다.");
        } else if (name.getText().equals("")) {
            showWarn("이름을 입력하세요.");
            name.requestFocus();
        } else if (!cbox.isSelected()){
            showWarn("가입 동의 버튼을 체크해주세요.");
            cbox.requestFocus();
        } else if (CustomerDAO.idChecked(uid.getText()).equals("no")){
            showWarn("존재하는 아이디입니다.");
            uid.requestFocus();
        }
        else {
            // rs = 콤보박스로부터 생년월일을 저장할 변수
            String rs = ybox.getSelectionModel().getSelectedItem() + "-" + mbox.getSelectionModel().getSelectedItem() + "-" + dbox.getSelectionModel().getSelectedItem();

            // 컨트롤들을 통해서 입력받은 데이터를 바탕으로 MemberVO 객체를 생성
            MemberVO m = new MemberVO(uid.getText(), pwd.getText(), name.getText(), rs);

            // MemberVO 객체 m 을 DB에 저장
            CustomerDAO.insertMember(m);
            showSuccess("로그인 창으로 넘어갑니다.");
        }

    }

    public void showSuccess(String s){
        Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
        conf.setTitle("가입성공!!");
        conf.setContentText(s);
        ButtonType btn = new ButtonType("확인");
        conf.getButtonTypes().setAll(btn);
        conf.setOnCloseRequest(event -> {
            Stage stage = (Stage) uid.getScene().getWindow();
            stage.close();
        });
        conf.showAndWait();
    }
}