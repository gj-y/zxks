package com.gjy.zxks.dao;

import com.gjy.zxks.po.Problem;
import com.gjy.zxks.po.ProblemWithBLOBs;

import java.util.List;

public interface ProblemMapper {
    int deleteByPrimaryKey(Integer problemId);

    int insert(ProblemWithBLOBs record);

    int insertSelective(ProblemWithBLOBs record);

    ProblemWithBLOBs selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(ProblemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record);

    int updateByPrimaryKey(Problem record);

    List<ProblemWithBLOBs> getAll();

    List<ProblemWithBLOBs> selectByTestId(Integer id);

    List<ProblemWithBLOBs> selectByPage(int pageNo, int pageSize);

    int count();

    List<Problem> getByTestId(int testid);
}