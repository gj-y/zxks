package com.gjy.zxks.dao;

import com.gjy.zxks.po.TestPaper;

import java.util.List;

public interface TestPaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TestPaper record);

    int insertSelective(TestPaper record);

    TestPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TestPaper record);

    int updateByPrimaryKey(TestPaper record);

    List<TestPaper> getByExamId(int id);
}