package com.gjy.zxks.dao;

import com.gjy.zxks.po.CompileInfo;

public interface CompileInfoMapper {
    int deleteByPrimaryKey(Integer solutionId);

    int insert(CompileInfo record);

    int insertSelective(CompileInfo record);

    CompileInfo selectByPrimaryKey(Integer solutionId);

    int updateByPrimaryKeySelective(CompileInfo record);

    int updateByPrimaryKeyWithBLOBs(CompileInfo record);
}