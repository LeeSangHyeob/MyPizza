package v1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by SteveLEE on 2016-09-18.
 */
public class AdminDAO {

    private static final String drv = "oracle.jdbc.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@//192.168.199.128/xe";
    private static final String usr = "root"; // MySQL ID
    private static final String pwd = "123456";     // MYSQL Password

    private String allSales = "select * from orderlist";

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

    public void AllSales() {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(allSales);

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    }

}