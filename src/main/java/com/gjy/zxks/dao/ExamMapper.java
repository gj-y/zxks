package com.gjy.zxks.dao;

import com.gjy.zxks.po.Exam;

import java.util.List;

public interface ExamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exam record);

    int insertSelective(Exam record);

    Exam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exam record);

    int updateByPrimaryKey(Exam record);

    List<Exam> selectByUserId(String userid);

    List<Exam> selectExamsByStuid(String userid);

    int hasExamProceed(String creater);

    Exam hasMyExam(String stuid);
}