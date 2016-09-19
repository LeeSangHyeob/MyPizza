package v2.dao;

import Project1.v2.model.MemberVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by SteveLEE on 2016-09-18.
 */
public class CustomerDAO {

    private static final String drv = "oracle.jdbc.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@//192.168.199.128/xe";
    private static final String usr = "alone"; // MySQL ID
    private static final String pwd = "123456";     // MYSQL Password

    private static String istMember = "insert into member (userid, pwd, name, birth) values (?, ?, ?, ?)";
    private static String idchecked = "select userid from member where userid = ?";
    private static String loginchecked = "select userid from member where userid = ? and pwd = ?";

    public static Connection openConn() {

        Connection conn = null;
        try {
            Class.forName(drv);
            conn = DriverManager.getConnection(url, usr, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 오라클 접속 해제 메서드
    public static void closeConn(Connection c, PreparedStatement p, ResultSet r) {

        if (r != null) try {
            r.close();
            r = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (p != null) try {
            p.close();
            p = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c != null) try {
            c.close();
            c = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertMember(MemberVO m) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(istMember);

            pstmt.setString(1, m.getUserid());
            pstmt.setString(2, m.getPwd());
            pstmt.setString(3, m.getName());
            pstmt.setString(4, m.getBirth());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    }

    public static String idChecked(String userid) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String checked = "yes";

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(idchecked);
            pstmt.setString(1, userid);
            rs = pstmt.executeQuery();

            if(rs.next()){
                if(rs.getString(1) != null) checked = "no";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return checked;
    }

    public static String loginChecked(String userid, String pwd) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String checked = "no";

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(loginchecked);
            pstmt.setString(1, userid);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();

            if(rs.next()){
                if(rs.getString(1).equals("admin")){
                    checked = "admin";
                } else if(rs.getString(1) != null) {
                    checked = "yes";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return checked;
    }
}