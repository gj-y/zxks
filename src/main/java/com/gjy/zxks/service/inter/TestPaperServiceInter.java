package com.gjy.zxks.service.inter;

import com.alibaba.fastjson.JSONObject;
import com.gjy.zxks.po.Exam;
import com.gjy.zxks.po.ProblemWithBLOBs;
import com.gjy.zxks.po.Solution;
import com.gjy.zxks.po.TestPaper;

import java.util.List;

public interface TestPaperServiceInter {

    int generatNewPaper(Exam exam, TestPaper testpaper);

    List<ProblemWithBLOBs> selectByTestId(int testId);

    int startExam(Exam exam);

    Exam getExamById(int id);

    List<TestPaper> getTestPaersByExam(int id);

    JSONObject sortTestPaperRank(int examid );

    List<Exam> checkHasExam(String token);

    TestPaper getTestPaperById(int id);

    void update(TestPaper testPaper);

    List<Exam> getExamsByStuId(String id);

    Exam checkExamByStuid(String stuid);
}
