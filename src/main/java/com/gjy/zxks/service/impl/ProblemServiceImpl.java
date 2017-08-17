package com.gjy.zxks.service.impl;

import com.gjy.zxks.dao.ProblemMapper;
import com.gjy.zxks.po.Problem;
import com.gjy.zxks.po.ProblemWithBLOBs;
import com.gjy.zxks.service.inter.ProblemServiceInter;
import com.gjy.zxks.util.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author jiayao gao
 * @create 2017-02-16 16:43
 **/
@Service
public class ProblemServiceImpl implements ProblemServiceInter  {

    @Resource
    ProblemMapper problemMapper;

    public ProblemWithBLOBs getById(int id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    public List<ProblemWithBLOBs> loadAll() {
        return problemMapper.getAll();
    }

    public Page loadByPage(Page page) {
        page.setTotalRecNum(problemMapper.count());
        page.setPageContent(problemMapper.selectByPage(page.getStartIndex(), page.getPageSize()));
        return page;
    }

    public int deleteById(int id) {
        return problemMapper.deleteByPrimaryKey(id);
    }

    public boolean addProblem(ProblemWithBLOBs problem) throws IOException {
        int id = problemMapper.insert(problem);
        //生成判题文件
        /*
        FileOutputStream out = null;
        try {
            //samplein
            out = new FileOutputStream(new File("/home/judge/" + id + "/sample.in"));
            out.write(problem.getSampleInput().getBytes());
            //sampleout
            out = new FileOutputStream(new File("/home/judge/" + id + "/sample.out"));
            out.write(problem.getSampleOutput().getBytes());
            //testin
            out = new FileOutputStream(new File("/home/judge/" + id + "/test.in"));
            out.write(problem.getInput().getBytes());
            //testout
            out = new FileOutputStream(new File("/home/judge/" + id + "/test.out"));
            out.write(problem.getOutput().getBytes());
        }finally {
            if(out != null){
                out.flush();
                out.close();
            }
        }*/
        return true;
    }

    public void update(ProblemWithBLOBs problem) {
        problemMapper.updateByPrimaryKeyWithBLOBs(problem);
        //problemMapper.updateByPrimaryKeySelective(problem);
    }
}
