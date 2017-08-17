package com.gjy.zxks.dao;

import com.gjy.zxks.po.Privilege;

public interface PrivilegeMapper {
    int insert(Privilege record);

    int insertSelective(Privilege record);
}