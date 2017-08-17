package com.gjy.zxks.po;

import java.io.Serializable;
import java.util.Date;

public class LoginToken implements Serializable {
    private Integer id;

    private String token;

    private Date outtime;

    private String userid;

    private String used;

    private String rightstr;

    public String getRightstr() {
        return rightstr;
    }

    public void setRightstr(String rightstr) {
        this.rightstr = rightstr;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used == null ? null : used.trim();
    }
}