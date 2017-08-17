package com.gjy.zxks.dao;

import com.gjy.zxks.po.SourceCode;

public interface SourceCodeMapper {
    int deleteByPrimaryKey(Integer solutionId);

    int insert(SourceCode record);

    int insertSelective(SourceCode record);

    SourceCode selectByPrimaryKey(Integer solutionId);

    int updateByPrimaryKeySelective(SourceCode record);

    int updateByPrimaryKeyWithBLOBs(SourceCode record);
}