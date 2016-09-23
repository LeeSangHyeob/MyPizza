package dao;


import model.OrdersVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteveLEE on 2016-09-18.
 */
public class AdminDAO {

    private static final String drv = "oracle.jdbc.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@//192.168.0.35/xe";
    private static final String usr = "MYPIZZA";
    private static final String pwd = "MYPIZZA";

    private static String allsales = "select sum(totalprice) from orders ";
    private static String allorders = "select * from orders ";
    private static String orders = " select orderdate, orderno, userid, olistno, totalprice from ORDERs  where TO_NUMBER(substr(ORDERDATE, 4,2)) = ? ";
    private static String monthsales = " select sum(totalprice)  where TO_NUMBER(substr(ORDERDATE, 4,2)) = ? ";

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

    public static List<OrdersVO> Orders(int month) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<OrdersVO> olist = new ArrayList<>();

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(orders);
            pstmt.setInt(1, month);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                OrdersVO o = new OrdersVO(rs.getString(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5));

                olist.add(o);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConn(conn, pstmt, rs);
        }
        return olist;
    }

    public static List<OrdersVO> allOrders() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<OrdersVO> olist = new ArrayList<>();

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(allorders);
            rs = pstmt.executeQuery();

            while (rs.next()) {

                OrdersVO o = new OrdersVO(rs.getString(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getInt(5));

                olist.add(o);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConn(conn, pstmt, rs);
        }
        return olist;
    }

    public static int allSales() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<OrdersVO> olist = new ArrayList<>();

        int result = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(allsales);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConn(conn, pstmt, rs);
        }
        return result;
    }

    public static int monthSales() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<OrdersVO> olist = new ArrayList<>();

        int result = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(monthsales);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                result = rs.getInt(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConn(conn, pstmt, rs);
        }
        return result;
    }
}