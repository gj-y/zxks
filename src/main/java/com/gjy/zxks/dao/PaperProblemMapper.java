package com.gjy.zxks.dao;

import com.gjy.zxks.po.PaperProblem;

public interface PaperProblemMapper {
    int insert(PaperProblem record);

    int insertSelective(PaperProblem record);
}