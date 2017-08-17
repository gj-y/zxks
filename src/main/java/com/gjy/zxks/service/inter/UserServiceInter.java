package com.gjy.zxks.service.inter;

import com.alibaba.fastjson.JSONObject;
import com.gjy.zxks.po.User;

import java.util.List;

public interface UserServiceInter {
    User getUserById(String id);

    JSONObject checkLogin(String account, String pwd) throws Exception;

    User checkToken(String token);

    void logout(String token);

    List<User> getUsersByTeacherId(String id);
}
