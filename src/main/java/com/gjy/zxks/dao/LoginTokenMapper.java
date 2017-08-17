package com.gjy.zxks.dao;

import com.gjy.zxks.po.LoginToken;

public interface LoginTokenMapper {
    int insert(LoginToken record);

    int insertSelective(LoginToken record);

    void update(LoginToken record);

    LoginToken getByToken(String token);
}