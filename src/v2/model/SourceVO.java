package v2.model;

/**
 * Created by SteveLEE on 2016-09-16.
 */
public class SourceVO {

    private String sname;
    private int samount;
    private int sprice;

    public SourceVO() {
    }

    public SourceVO(String sname, int samount, int sprice) {
        this.sname = sname;
        this.samount = samount;
        this.sprice = sprice;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSamount() {
        return samount;
    }

    public void setSamount(int samount) {
        this.samount = samount;
    }

    public int getSprice() {
        return sprice;
    }

    public void setSprice(int sprice) {
        this.sprice = sprice;
    }
}
