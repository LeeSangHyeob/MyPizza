package model;

/**
 * Created by java on 2016-09-23.
 */
public class OrdersVO {

    private String orderdate;
    private int orderno;
    private String userid;
    private int olistno;
    private int totalprice;

    public OrdersVO() {
    }

    public OrdersVO(String orderdate, int orderno, String userid, int olistno, int totalprice) {
        this.orderdate = orderdate;
        this.orderno = orderno;
        this.userid = userid;
        this.olistno = olistno;
        this.totalprice = totalprice;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public int getOrderno() {
        return orderno;
    }

    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getOlistno() {
        return olistno;
    }

    public void setOlistno(int olistno) {
        this.olistno = olistno;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}