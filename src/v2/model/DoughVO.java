package v2.model;

/**
 * Created by SteveLEE on 2016-09-16.
 */
public class DoughVO {

    private String dname;
    private int damount;
    private int dprice;

    public DoughVO() {
    }

    public DoughVO(String dname, int damount, int dprice) {
        this.dname = dname;
        this.damount = damount;
        this.dprice = dprice;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getDamount() {
        return damount;
    }

    public void setDamount(int damount) {
        this.damount = damount;
    }

    public int getDprice() {
        return dprice;
    }

    public void setDprice(int dprice) {
        this.dprice = dprice;
    }
}
