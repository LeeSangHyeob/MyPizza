package model;

import java.util.Date;

/**
 * Created by SteveLEE on 2016-09-17.
 */
public class MemberVO {

    private String userid;
    private String pwd;
    private String name;
    private String birth;

    public MemberVO() {
    }

    public MemberVO(String userid, String pwd, String name, String birth) {
        this.userid = userid;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String uid) {
        this.userid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
