package com.gjy.zxks.service.impl;

import com.gjy.zxks.dao.SourceCodeMapper;
import com.gjy.zxks.po.SourceCode;
import com.gjy.zxks.service.inter.SourceCodeServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiayao gao
 * @create 2017-02-11 11:01
 **/
@Service
public class SourceCodeServiceImpl implements SourceCodeServiceInter {

    @Resource
    SourceCodeMapper sourceCodeMapper;

    public SourceCode getById(int id) {
        return sourceCodeMapper.selectByPrimaryKey(id);
    }

    public void insert(SourceCode sourceCode) {
        sourceCodeMapper.insert(sourceCode);
    }
}
