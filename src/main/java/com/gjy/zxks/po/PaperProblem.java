package com.gjy.zxks.po;

import java.io.Serializable;

public class PaperProblem implements Serializable {
    private Integer testid;

    private Integer problemid;

    private static final long serialVersionUID = 1L;

    public PaperProblem(Integer testid, Integer problemid) {
        this.testid = testid;
        this.problemid = problemid;
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    public Integer getProblemid() {
        return problemid;
    }

    public void setProblemid(Integer problemid) {
        this.problemid = problemid;
    }
}