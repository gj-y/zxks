package com.gjy.zxks.po;

import java.io.Serializable;

public class SourceCode implements Serializable {
    private Integer solutionId;

    private String source;

    private static final long serialVersionUID = 1L;

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}