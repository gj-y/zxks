package com.gjy.zxks.service.impl;

import com.gjy.zxks.dao.CompileInfoMapper;
import com.gjy.zxks.po.CompileInfo;
import com.gjy.zxks.service.inter.CompileInfoServiceInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author jiayao gao
 * @create 2017-02-21 16:31
 **/
@Service
public class CompileInfoServiceImpl implements CompileInfoServiceInter {

    @Resource
    CompileInfoMapper compileInfoMapper;

    public void update(CompileInfo compileInfo) {
        compileInfoMapper.updateByPrimaryKeyWithBLOBs(compileInfo);
    }
}
