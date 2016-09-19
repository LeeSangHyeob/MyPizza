package v1.model;

/**
 * Created by SteveLEE on 2016-09-16.
 */
public class ToppingVO {

    private String tname;
    private int tamount;
    private int tprice;

    public ToppingVO() {
    }

    public ToppingVO(String tname, int tamount, int tprice) {
        this.tname = tname;
        this.tamount = tamount;
        this.tprice = tprice;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getTamount() {
        return tamount;
    }

    public void setTamount(int tamount) {
        this.tamount = tamount;
    }

    public int getTprice() {
        return tprice;
    }

    public void setTprice(int tprice) {
        this.tprice = tprice;
    }
}
