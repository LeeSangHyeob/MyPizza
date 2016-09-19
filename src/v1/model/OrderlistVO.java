package v1.model;

/**
 * Created by SteveLEE on 2016-09-17.
 */
public class OrderlistVO {

    private int olistno;
    private String sname;
    private String dname;
    private String tname1;
    private String tname2;
    private String tname3;
    private String tname4;
    private String tname5;
    private String tname6;
    private String sidename1;
    private String sidename2;
    private String sidename3;

    public OrderlistVO() {
    }

    public OrderlistVO(int olistno, String sname, String dname, String tname1, String tname2, String tname3, String tname4, String tname5, String tname6, String sidename1, String sidename2, String sidename3) {
        this.olistno = olistno;
        this.sname = sname;
        this.dname = dname;
        this.tname1 = tname1;
        this.tname2 = tname2;
        this.tname3 = tname3;
        this.tname4 = tname4;
        this.tname5 = tname5;
        this.tname6 = tname6;
        this.sidename1 = sidename1;
        this.sidename2 = sidename2;
        this.sidename3 = sidename3;
    }

    public int getOlistno() {
        return olistno;
    }

    public void setOlistno(int olistno) {
        this.olistno = olistno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getTname1() {
        return tname1;
    }

    public void setTname1(String tname1) {
        this.tname1 = tname1;
    }

    public String getTname2() {
        return tname2;
    }

    public void setTname2(String tname2) {
        this.tname2 = tname2;
    }

    public String getTname3() {
        return tname3;
    }

    public void setTname3(String tname3) {
        this.tname3 = tname3;
    }

    public String getTname4() {
        return tname4;
    }

    public void setTname4(String tname4) {
        this.tname4 = tname4;
    }

    public String getTname5() {
        return tname5;
    }

    public void setTname5(String tname5) {
        this.tname5 = tname5;
    }

    public String getTname6() {
        return tname6;
    }

    public void setTname6(String tname6) {
        this.tname6 = tname6;
    }

    public String getSidename1() {
        return sidename1;
    }

    public void setSidename1(String sidename1) {
        this.sidename1 = sidename1;
    }

    public String getSidename2() {
        return sidename2;
    }

    public void setSidename2(String sidename2) {
        this.sidename2 = sidename2;
    }

    public String getSidename3() {
        return sidename3;
    }

    public void setSidename3(String sidename3) {
        this.sidename3 = sidename3;
    }
}
