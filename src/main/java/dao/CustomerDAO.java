package dao;

import model.MemberVO;
import model.OrderlistVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SteveLEE on 2016-09-18.
 */
public class CustomerDAO {

    private static final String drv = "oracle.jdbc.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@//192.168.0.35/xe";
    private static final String usr = "MYPIZZA";
    private static final String pwd = "MYPIZZA";

    // 회원가입 sql
    private static String istMember = "insert into member (userid, pwd, name, birth) values (?, ?, ?, ?)";

    // 주문 목록 입력 sql
    private static String istOrderlist = "insert into orderlist values (olistno.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // 아이디 중복체크 sql
    private static String idchecked = "select userid from member where userid = ?";

    // 로그인 확인 sql
    private static String loginchecked = "select userid from member where userid = ? and pwd = ?";

    // 주문 입력 sql
    private static String istOrder = " insert into orders (orderno, userid, olistno, TOTALPRICE)  values (orderno.nextval, ?, (select max(olistno) from orderlist), ?)";

    // 도우 가격조회 조회 sql
    private static String selectDprice = " select dprice from dough where dname = ? ";

    // 사이드메뉴 가격조회 sql
    private static String selectSprice = " select sideprice from sidemenu where sidename = ? ";

    // 토핑메뉴 가격조회 sql
    private static String selectTprice = " select tprice from topping where tname = ? ";

    // 로그인 세션 유지를 위한 이름을 저장하는 sql
    private static String selectname = " select name from member where userid = ? ";

    // 주문 내역 출력을 위한 sql문
    private  static  String orderlist = " select olistno, DNAME, SNAME, TNAME1,TNAME2,TNAME3,TNAME4,SIDENAME1,SIDENAME2,SIDENAME3 from  orderlist join orders using (olistno) JOIN MEMBER USING (userid) WHERE USERID= ? order by olistno ";


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

    // 회원가입 메서드
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


    // 아이디 중복체크 메서드
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

            if (rs.next()) {
                if (rs.getString(1) != null) checked = "no";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return checked;
    }


    //로그인 체크 메서드
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

            if (rs.next()) {
                if (rs.getString(1).equals("admin")) {
                    checked = "admin";
                } else if (rs.getString(1) != null) {
                    checked = rs.getString(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return checked;
    }

    // 주문목록 입력 메서드
    public static void insertOrderList(OrderlistVO o) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(istOrderlist);

            pstmt.setString(1, o.getDname());
            pstmt.setString(2, o.getSname());
            pstmt.setString(3, o.getTname1());
            pstmt.setString(4, o.getTname2());
            pstmt.setString(5, o.getTname3());
            pstmt.setString(6, o.getTname4());
            pstmt.setString(7, o.getSidename1());
            pstmt.setString(8, o.getSidename2());
            pstmt.setString(9, o.getSidename3());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
            System.out.println("insertOrderList");
        }
    }


    // 주문  입력 메서드
    public static void insertOrder(String userid, int totalprice) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(istOrder);
            pstmt.setString(1, userid);
            pstmt.setInt(2, totalprice);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    }


    // 도우 가격 조회하는 메서드
    public static int selectDprice(String dname) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(selectDprice);
            pstmt.setString(1, dname);

            rs = pstmt.executeQuery();
            if (rs.next()) result = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return result;
    }


    //사이드 메뉴가격 조회 메서드
    public static int selectSprice(String sidename) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(selectSprice);
            pstmt.setString(1, sidename);

            rs = pstmt.executeQuery();
            if (rs.next()) result = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return result;
    }


    // 토핑 가격 조회 메서드
    public static int selectTprice(String tname) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int result = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(selectTprice);
            pstmt.setString(1, tname);

            rs = pstmt.executeQuery();
            if (rs.next()) result = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return result;
    }

    // 이름 조회 메서드
    public static String selectName(String userid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String result = "";

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(selectname);
            pstmt.setString(1, userid);

            rs = pstmt.executeQuery();
            if (rs.next()) result = rs.getString(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }

        return result;
    }

    // 주문 내역 출력하는 메서드
    public  static List<OrderlistVO> Orderlist(String userid){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        List<OrderlistVO> olist = new ArrayList<>();

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(orderlist);
            pstmt.setString(1,userid);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderlistVO o = new OrderlistVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
                        , rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
                olist.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt, rs);
        }
        return olist;
    }
}