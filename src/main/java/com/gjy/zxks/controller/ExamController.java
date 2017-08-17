package com.gjy.zxks.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gjy.zxks.po.*;
import com.gjy.zxks.service.inter.*;
import com.gjy.zxks.util.Page;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/examCtr")
public class ExamController {
    @Resource
    TestPaperServiceInter testPaperServiceInter;
    @Resource
    SolutionServiceInter solutionServiceInter;
    @Resource
    SourceCodeServiceInter sourceCodeServiceInter;
    @Resource
    ProblemServiceInter problemServiceInter;
    @Resource
    CompileInfoServiceInter compileInfoServiceInter;
    @Resource
    UserServiceInter userServiceInter;

    /**
     * 得到生成考试页面
     * @return ModelAndView
     */
    @RequestMapping("/admin/to_admin_page")
    public ModelAndView toAdminPage(){

        return new ModelAndView("admin/main");
    }

    /**
     * 跳转到题库管理界面
     * @return ModelAndView
     */
    @RequestMapping("/admin/to_problem_manage")
    public ModelAndView toProblemManage(){
        return new ModelAndView("admin/problem/problem_list");
    }

    /**
     * 删除题目
     */
    @RequestMapping("/admin/delete_problem")
    @ResponseBody
    public int deleteProblem(int id){
        problemServiceInter.deleteById(id);
        return 1;
    }

    /**
     * 添加题目
     */
    @RequestMapping("/admin/add_problem")
    @ResponseBody
    public int addProblem(ProblemWithBLOBs problem){
        try {
            problemServiceInter.addProblem(problem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping("/admin/download_temp")
    public void tempDownLoad(HttpServletRequest request, HttpServletResponse response){
        String realPath = request.getServletContext().getRealPath("WEB-INF/File/");
        File file = new File(realPath);
    }

    /**
     * 文件导入题目
     * @return
     */
    @RequestMapping("/admin/file_update_problems")
    public ModelAndView addProblemsFromFile(@RequestParam("file") CommonsMultipartFile file, String token){
        FileInputStream fis = null;
        try {
            File newFile = new File(System.getProperty("user.dir") + file.getOriginalFilename() );
            file.transferTo(newFile);
            System.out.println(System.getProperty("user.dir"));
            fis = new FileInputStream(newFile);

            Workbook wb0 = null;
            if (newFile.getName().contains("xlsx")){
                wb0 = WorkbookFactory.create(fis);
            }else{
                wb0 = new HSSFWorkbook(fis);
            }

            Sheet sht0 = wb0.getSheetAt(0);
            for (Row r : sht0) {
                //如果当前行的行号（从0开始）未达到2（第三行）则从新循环
                if(r.getRowNum()<1){
                    continue;
                }
                ProblemWithBLOBs problem = new ProblemWithBLOBs();
                problem.setTitle(r.getCell(0).getStringCellValue());
                problem.setDescription(r.getCell(1).getStringCellValue());
                problem.setGoal((int) r.getCell(2).getNumericCellValue());
                r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                problem.setInput(r.getCell(3).getStringCellValue());
                r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                problem.setOutput(r.getCell(4).getStringCellValue());
                r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                problem.setSampleInput(r.getCell(5).getStringCellValue());
                r.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                problem.setSampleOutput(r.getCell(6).getStringCellValue());
                problem.setSpj("0");
                problem.setDefunct("Y");
                problem.setInDate(new Date());
                problem.setTimeLimit(1);
                problem.setMemoryLimit(128);
                problemServiceInter.addProblem(problem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView mv =new ModelAndView("redirect:to_problem_manage");
        mv.addObject("token" , token);
        return mv;
    }

    private String getExcelStringCell(Cell cell) {
        String temp = "";
        if (cell == null) {
            System.out.println("该列为空，赋值双引号");
            temp = "NULL";
        } else {
            int cellType = cell.getCellType();
            switch (cellType) {
                case Cell.CELL_TYPE_STRING:
                    temp = cell.getStringCellValue().trim();
                    temp = StringUtils.isEmpty(temp) ? "NULL" : temp;
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    temp = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    temp = String.valueOf(cell.getCellFormula().trim());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    temp = String.valueOf(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    temp = "NULL";
                    break;
                case Cell.CELL_TYPE_ERROR:
                    temp = "ERROR";
                    break;
                default:
                    temp = cell.toString().trim();
                    break;
            }
        }
        return temp;
    }
    @RequestMapping("/admin/to_update_problem")
    public ModelAndView toUpdateProblem(int problemid){
        ModelAndView mv = new ModelAndView("admin/problem/problem_detail");
        mv.addObject("problem", problemServiceInter.getById(problemid));
        return mv;
    }

    @RequestMapping("/admin/to_add_problem")
    public ModelAndView toAddProblem(){
        ModelAndView mv = new ModelAndView("admin/problem/problem_detail");
        return mv;
    }

    @RequestMapping("/admin/add_or_update_problem")
    public ModelAndView addOrUpdateProblem(ProblemWithBLOBs problem, boolean isAdd, String token) throws IOException {
        if(isAdd == true){
            problem.setSpj("0");
            problem.setDefunct("N");
            problemServiceInter.addProblem(problem);
        }else {
            problemServiceInter.update(problem);
        }
        ModelAndView mv = new ModelAndView("redirect:to_problem_manage");
        mv.addObject("token" , token);
        return mv;
    }

    /**
     * 加载题目
     */
    @RequestMapping("/admin/get_problems")
    @ResponseBody
    public Page getProblem( ){
        Page page = null;
        try {
            page = new Page();
            page.setPageNo(1);
            problemServiceInter.loadByPage(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 得到生成考试页面
     */
    @RequestMapping("/admin/to_start_new_exam")
    public ModelAndView toStartNewExam(){
        ModelAndView mv;
        mv = new ModelAndView("admin/new_exam");
        return mv;
    }

    /**
     *  生成一场新的考试
     */
	@RequestMapping("/admin/start_new_exam")
    public ModelAndView startNewExam(Integer inputTime, Integer inputNum, String starttime, String examname, String token){
        JSONObject result = null;
        try {
            result = new JSONObject();
            Exam exam = new Exam();
            exam.setCreaterid(userServiceInter.checkToken(token).getUserId());
            exam.setTime(inputTime);
            exam.setNum(inputNum);
            exam.setExamname(examname);
            exam.setStatus("0");
            exam.setUsed("1");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            exam.setStarttime(sdf.parse(starttime));

            int count = testPaperServiceInter.startExam(exam);
            if(count == -1){
                result.put("result", -1);
            }else {
                result.put("result", 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mv;
        mv = new ModelAndView("admin/new_exam");
        mv.addObject("result", result);
        return mv;
    }

    @RequestMapping("/admin/to_problem_type")
    public ModelAndView toProblemType(){
        ModelAndView mv = new ModelAndView("admin/problem/problem_type");
        return mv;
    }


    //======================================================================================

    @RequestMapping("/go_exercise")
    public ModelAndView goExercise(){
        ModelAndView mv = new ModelAndView("home/exercise");
        return mv;
    }
    /**
     * 跳转到考试界面
     * @return
     */
    @RequestMapping("/to_exam")
    public ModelAndView toExam(int testid){

        TestPaper testPaper = testPaperServiceInter.getTestPaperById(testid);

        if(testPaper.getStart() == null){
            testPaper.setStart(new Date());
            testPaperServiceInter.update(testPaper);
        }


        ModelAndView mv = new ModelAndView("home/exam");
        mv.addObject("testid", testid);
        return mv;
    }

    /**
     * 查询学生是否有尚未开始，或者还未结束的考试
     * @param token
     * @return
     */
    @RequestMapping("/get_proceeding_exam")
    @ResponseBody
    public Object checkHasMyExam(String token){
        JSONObject result = new JSONObject();
        Exam exam = testPaperServiceInter.checkExamByStuid(userServiceInter.checkToken(token).getUserId());
        if(exam != null && exam.getTestid() != 0){
            result.put("examinfo", exam);
        }else {
        }
        return result;
    }

    /**
     * 加载试题
     * @return
     */
    @RequestMapping("/get_problems")
    @ResponseBody
    public List<ProblemWithBLOBs> getTestProblems(int testid){
        return testPaperServiceInter.selectByTestId(testid);
    }


    @RequestMapping("/put_test")
    @ResponseBody
    public Object judgeProblems(String answer, int testid, String token) {
        JSONObject result = new JSONObject();
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //得到用户信息
            User user = userServiceInter.checkToken(token);

            TestPaper testPaper = testPaperServiceInter.getTestPaperById(testid);
            testPaper.setTimeuse((int) (new Date().getTime() - testPaper.getStart().getTime()));
            testPaperServiceInter.update(testPaper);
            result.put("examid", testPaper.getExamid());

            JSONObject obj = (JSONObject) JSONObject.parse(answer);
            int lanuage = Integer.parseInt((String)obj.get("lanuage"));
            JSONArray codes = (JSONArray) obj.get("codes");

            for (int i = 0; i < codes.size(); i++) {
                JSONObject temp = (JSONObject) codes.get(i);
                Solution solution = new Solution();
                solution.setCodeLength(temp.getString("code").length());
                solution.setProblemId(temp.getInteger("id"));
                solution.setUserId(user.getUserId());
                solution.setInDate(new Date());
                solution.setLanguage(lanuage);
                solution.setIp(request.getRemoteAddr());
                solution.setTestid(testid);
                solutionServiceInter.insert(solution);

                SourceCode sourceCode = new SourceCode();
                sourceCode.setSolutionId(solution.getSolutionId());
                sourceCode.setSource(temp.getString("code"));
                sourceCodeServiceInter.insert(sourceCode);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 跳转到学生考试列表页面
     * @return exam_list.jsp
     */
    @RequestMapping("/go_rank")
    public ModelAndView goRank(){
        //User user = userServiceInter.checkToken(token);
        //List<Exam> exams = testPaperServiceInter.getExamsByStuId(user.getUserId());

        ModelAndView mv = new
                ModelAndView("home/exam_list");
        //mv.addObject("exams", exams);
        return  mv;
    }

    @RequestMapping("/get_exam_list")
    @ResponseBody
    public Object getExamList(String token){
        JSONObject result = new JSONObject();
        User user = userServiceInter.checkToken(token);
        List<Exam> exams = testPaperServiceInter.getExamsByStuId(user.getUserId());
        result.put("user", user);
        result.put("exams", exams);
        return result;
    }

    /**
     * 根据考试id跳转到考试详情页面
     * @param examid
     * @return
     */
    @RequestMapping("/show_exam_rank")
    public ModelAndView showScore(int examid){
        ModelAndView mv = new
                ModelAndView("home/rank");
        mv.addObject("exam", testPaperServiceInter.getExamById(examid));
        return  mv;
    }

    /**
     * 查询一场考试的信息
     * @param examid
     * @return
     */
    @RequestMapping("/get_exam_rank")
    @ResponseBody
    public Object getRankInfo(int examid){
        JSONObject result = null;
        try{
            result = testPaperServiceInter.sortTestPaperRank(examid);

        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 下载一场考试的信息
     * @return
     */
    @RequestMapping("/download_exam_rank")
    @ResponseBody
    public void downLoadRankInfo(int examid, HttpServletResponse response){
        JSONObject result = null;
        try{
            result = testPaperServiceInter.sortTestPaperRank(examid);

            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("成绩表");

            XSSFRow row1 = sheet.createRow(0);
            row1.createCell(0).setCellValue("学号");
            row1.createCell(1).setCellValue("姓名");
            row1.createCell(2).setCellValue("分数");
            List<TestPaper> testPapers = result.getObject("testPaper", List<TestPaper>.getClass());
            List<TestPaper>.
            for (int i = 0; i < testPapers.size(); i++){
                XSSFRow row = sheet.createRow(i+1);
                TestPaper testPaper = testPapers.get(i);
                row.createCell(0).setCellValue(testPaper.getStuid());
                row.createCell(1).setCellValue(testPaper.getStuname());
                row.createCell(2).setCellValue(testPaper.getScore());
            }

            OutputStream os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + result.getJSONObject("examInfo").get("examname") + ".xlsx");
            response.setContentType("application/msexcel");
            wb.write(os);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询学生的考试信息
     * @param token
     * @return
     */
    @RequestMapping("/get_exam_annouce")
    @ResponseBody
    public Object getExamAnno(String token){
        JSONObject result = new JSONObject();
        try{
            List<Exam> exams = testPaperServiceInter.checkHasExam(token);
            if (exams != null && exams.size() > 0){
                    result.put("msg", "有新的考试信息");
                    result.put("exams", exams);
            }else{
                result.put("msg", "目前没有考试");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * 加载题目
     */
    @RequestMapping("/get_exercise_problems")
    @ResponseBody
    public Page getExerciseProblem(int pageNo){
        Page page = null;
        try {
            page = new Page();
            page.setPageNo(pageNo);
            problemServiceInter.loadByPage(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return page;
    }

    /**
     * 跳转到解题页面
     */
    @RequestMapping("/go_exercise_problems")
    public ModelAndView getExerciseProblemMV(int problemid){
        ModelAndView mv = new
                ModelAndView("home/exercise_problem");
        mv.addObject("problem", problemServiceInter.getById(problemid));
        return  mv;
    }

    @RequestMapping("/put_exercise_answer")
    public ModelAndView putExerciseAnswer(int problemid, String code, String token, int lanuage){
        //得到用户信息
        User user = userServiceInter.checkToken(token);

        Solution solution = new Solution();
        solution.setCodeLength(code.length());
        solution.setProblemId(problemid);
        solution.setUserId(user.getUserId());
        solution.setInDate(new Date());
        solution.setLanguage(lanuage);
        solution.setTestid(-1); //练习专用考试id  -1
        solutionServiceInter.insert(solution);

        SourceCode sourceCode = new SourceCode();
        sourceCode.setSolutionId(solution.getSolutionId());
        sourceCode.setSource(code);
        sourceCodeServiceInter.insert(sourceCode);

        ModelAndView mv = new
                ModelAndView("redirect:go_exercise_rank?token="+token);
        return  mv;
    }

    /**
     * 跳转到练习状态页面
     * @return
     */
    @RequestMapping("/go_exercise_rank")
    public ModelAndView goExerciseRank(){
        ModelAndView mv = new
                ModelAndView("home/exercise_rank");
        return  mv;
    }

    /**
     * 异步加载排名信息接口
     * @return
     */
    @RequestMapping("/get_exercise_rank")
    @ResponseBody
    public Object getExerciseRank(){
        return solutionServiceInter.getByTestId(-1);
    }


}
