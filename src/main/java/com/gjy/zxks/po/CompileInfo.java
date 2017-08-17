package com.gjy.zxks.po;

import java.io.Serializable;

public class CompileInfo implements Serializable {
    private Integer solutionId;

    private String error;

    private static final long serialVersionUID = 1L;

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error == null ? null : error.trim();
    }
}