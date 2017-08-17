package com.gjy.zxks.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Solution implements Serializable {
    private Integer solutionId;

    private Integer problemId;

    private String userId;

    private Integer time;

    private Integer memory;

    private Date inDate;

    private Short result;

    private Integer language;

    private String ip;

    private Integer contestId;

    private Byte valid;

    private Byte num;

    private Integer codeLength;

    private Date judgetime;

    private BigDecimal passRate;

    private Integer lintError;

    private String judger;

    private Integer testid;

    private Integer goal;

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getTestid() {
        return testid;
    }

    public void setTestid(Integer testid) {
        this.testid = testid;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Integer solutionId) {
        this.solutionId = solutionId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Short getResult() {
        return result;
    }

    public void setResult(Short result) {
        this.result = result;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }

    public Byte getNum() {
        return num;
    }

    public void setNum(Byte num) {
        this.num = num;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Date getJudgetime() {
        return judgetime;
    }

    public void setJudgetime(Date judgetime) {
        this.judgetime = judgetime;
    }

    public BigDecimal getPassRate() {
        return passRate;
    }

    public void setPassRate(BigDecimal passRate) {
        this.passRate = passRate;
    }

    public Integer getLintError() {
        return lintError;
    }

    public void setLintError(Integer lintError) {
        this.lintError = lintError;
    }

    public String getJudger() {
        return judger;
    }

    public void setJudger(String judger) {
        this.judger = judger == null ? null : judger.trim();
    }
}