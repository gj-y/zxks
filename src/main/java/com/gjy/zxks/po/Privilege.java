package com.gjy.zxks.po;

import java.io.Serializable;

public class Privilege implements Serializable {
    private String userId;

    private String rightstr;

    private String defunct;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRightstr() {
        return rightstr;
    }

    public void setRightstr(String rightstr) {
        this.rightstr = rightstr == null ? null : rightstr.trim();
    }

    public String getDefunct() {
        return defunct;
    }

    public void setDefunct(String defunct) {
        this.defunct = defunct == null ? null : defunct.trim();
    }
}