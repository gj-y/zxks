package com.gjy.zxks.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TestPaper implements Serializable,Comparable {

    private Integer id;

    private String stuid;

    private String stuname;

    private Integer examid;

    private String used;

    private List<Solution> solutions;

    private User stuinfo;

    private int score;

    private Date start;

    private int timeuse;

    public Date getStart() {
        return start;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getTimeuse() {
        return timeuse;
    }

    public void setTimeuse(int timeuse) {
        this.timeuse = timeuse;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    public User getStuinfo() {
        return stuinfo;
    }

    public void setStuinfo(User stuinfo) {
        this.stuinfo = stuinfo;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used == null ? null : used.trim();
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public int compareTo(Object o) {
        TestPaper that = (TestPaper)o;
        return (that.getScore() - this.getScore() );
    }

    public int getScore(){
        int result = 0;
        for (int i = 0; i < this.getSolutions().size(); i++){
            Solution solution = this.getSolutions().get(i);
            if(solution.getResult() == 4){
                result += solution.getGoal();
            }
        }
        return result;
    }
}