package com.gjy.zxks.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gjy.zxks.dao.*;
import com.gjy.zxks.po.*;
import com.gjy.zxks.service.inter.TestPaperServiceInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author jiayao gao
 * @create 2017-01-29 21:15
 **/
@Service
public class TestPaperServiceImpl implements TestPaperServiceInter {

    @Resource
    private TestPaperMapper testPaperMapper;
    @Resource
    private ProblemMapper problemMapper;
    @Resource
    private PaperProblemMapper paperProblemMapper;
    @Resource
    private ExamMapper examMapper;
    @Resource
    private SolutionMapper solutionMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginTokenMapper loginTokenMapper;

    /**
     * 生成一场考试
     * @param exam
     * @return
     */
    public int startExam(Exam exam){
        if (examMapper.hasExamProceed(exam.getCreaterid()) > 0){
            return -1;
        }
        examMapper.insert(exam);
        List<User> users = userMapper.getAll();
        for (int i = 0; i < users.size(); i++) {
            User user =  users.get(i);
            TestPaper testpaper = new TestPaper();
            testpaper.setStuid(user.getUserId());
            testpaper.setExamid(exam.getId());
            testpaper.setUsed("1");
            testPaperMapper.insert(testpaper);
            generatNewPaper(exam, testpaper);
        }

        return examMapper.insert(exam);
    }

    /**
     * 根据id得到考试信息
     * @param id
     * @return
     */
    public Exam getExamById(int id) {
        return examMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据考试id得到该考试下的所有试卷信息
     * @param id
     * @return
     */
    public List<TestPaper> getTestPaersByExam(int id) {
        return testPaperMapper.getByExamId(id);
    }

    /**
     * 得到一个考试的所有试卷，并且得到每张试卷的分数，并排序
     * @param examid
     * @return
     */
    public JSONObject sortTestPaperRank(int examid) {
        JSONObject result = null;

        result = new JSONObject();
        //查询考试信息
        Exam exam = examMapper.selectByPrimaryKey(examid);
        result.put("examInfo", exam);

        //根据考试id查询对应的学生以及学生的试卷
        List<TestPaper> testPapers = testPaperMapper.getByExamId(examid);
        for (int i = 0; i < testPapers.size(); i++){
            TestPaper testPaper = testPapers.get(i);
            testPaper.setSolutions(solutionMapper.getByTestIdAndStuId(testPaper.getId(), testPaper.getStuid()));
            testPaper.setStuinfo(userMapper.selectByPrimaryKey(testPaper.getStuid()));
            testPaper.getScore();
        }
        //按成绩为testpaper排序
        Collections.sort(testPapers);
        result.put("testPaper", testPapers);

        return  result;
    }

    /**
     * 查询是否现在有该学生正在进行的考试
     * @param token
     * @return
     */
    public List<Exam> checkHasExam(String token) {
        User user = userMapper.selectByPrimaryKey(loginTokenMapper.getByToken(token).getUserid());

        return examMapper.selectByUserId(user.getUserId());
    }

    /**
     * 按id查询试卷信息
     * @param id
     * @return
     */
    public TestPaper getTestPaperById(int id) {
        return testPaperMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新试卷信息
     * @param testPaper
     */
    public void update(TestPaper testPaper) {
        testPaperMapper.updateByPrimaryKey(testPaper);
    }

    /**
     * 得到某个学生的全部考试纪录
     * @param id
     * @return
     */
    public List<Exam> getExamsByStuId(String id) {
        return examMapper.selectExamsByStuid(id);
    }

    public Exam checkExamByStuid(String stuid) {
        return examMapper.hasMyExam(stuid);
    }

    /**
     * 组卷
     * @return
     */
    public int generatNewPaper(Exam exam, TestPaper testpaper) {
        Map<Integer, List<ProblemWithBLOBs>> problemsorted = new HashMap<Integer, List<ProblemWithBLOBs>>();
        //查找题目，并加入 试卷-题目关联表
        List<ProblemWithBLOBs> problems =  problemMapper.getAll();
        List<ProblemWithBLOBs> result =  new ArrayList<ProblemWithBLOBs>();
        for (int i = 0; i < problems.size(); i++) {
            if (!problemsorted.containsKey(problems.get(i).getGoal())){
                List<ProblemWithBLOBs> temp = new ArrayList<ProblemWithBLOBs>();
                temp.add(problems.get(i));
                problemsorted.put(problems.get(i).getGoal(), temp);
            }else{
                problemsorted.get(problems.get(i).getGoal()).add(problems.get(i));
            }
        }
        int problemNumber = exam.getNum();
        int testid = testpaper.getId();
        for (Integer key : problemsorted.keySet()) {
            if(problemNumber > 0){
                List<ProblemWithBLOBs> temp = problemsorted.get(key);
                Problem problem = temp.get(new Random().nextInt(temp.size()));
                paperProblemMapper.insert(new PaperProblem(testid, problem.getProblemId()));
                problemNumber--;
            }else {
                break;
            }
        }

        return testid;
    }

    /**
     * 得到一张试卷所有的题目
     * @param testId
     * @return
     */
    public List<ProblemWithBLOBs> selectByTestId(int testId) {
        return problemMapper.selectByTestId(testId);
    }
}
