package com.gjy.zxks.dao;

import com.gjy.zxks.po.Solution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SolutionMapper {
    int deleteByPrimaryKey(Integer solutionId);

    int insert(Solution record);

    int insertSelective(Solution record);

    Solution selectByPrimaryKey(Integer solutionId);

    int updateByPrimaryKeySelective(Solution record);

    int updateByPrimaryKey(Solution record);

    List<Solution> getByTestIdAndStuId(@Param(value="testid") int testid, @Param(value = "stuid") String stuid);

    List<Solution> getByTestId(@Param(value="testid") int testid);
}