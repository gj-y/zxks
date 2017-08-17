package com.gjy.zxks.service.impl;

import com.gjy.zxks.dao.SolutionMapper;
import com.gjy.zxks.po.Solution;
import com.gjy.zxks.service.inter.SolutionServiceInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiayao gao
 * @create 2017-02-08 16:17
 **/
@Service
public class SolutionServiceImpl implements SolutionServiceInter {

    @Resource
    SolutionMapper solutionMapper;

    public int update(Solution solution) {
        return solutionMapper.updateByPrimaryKeySelective(solution);
    }

    public int checkout(String sid, String result) {
      //  return solutionMapper.checkout(sid, result);
        return 1;
    }

    public Solution getById(int sid) {
        return solutionMapper.selectByPrimaryKey(sid);
    }

    public void insert(Solution solution) {
        solutionMapper.insertSelective(solution);
    }

    public List<Solution> getByTestId(int testid) {
        return solutionMapper.getByTestId(testid);
    }
}
