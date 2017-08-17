package com.gjy.zxks.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gjy.zxks.dao.LoginTokenMapper;
import com.gjy.zxks.dao.UserMapper;
import com.gjy.zxks.po.LoginToken;
import com.gjy.zxks.po.User;
import com.gjy.zxks.service.inter.UserServiceInter;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author jiayao gao
 * @create 2017-04-03 11:51
 **/
@Service
public class UserServiceImpl implements UserServiceInter {

    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginTokenMapper loginTokenMapper;

    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public JSONObject checkLogin(String account, String pwd) throws Exception{
        JSONObject result = new JSONObject();
        User user = userMapper.selectByPrimaryKey(account);
        if(user == null){
            result.put("msg", "用户不存在");
            return result;
        }
        else if (!user.getPassword().equals(pwd)){
            result.put("msg", "密码错误");
            return result;
        }
        else if (user.getPassword().equals(pwd)){
            result.put("msg", "登录成功");
            result.put("userinfo", user);
            LoginToken loginToken = new LoginToken();
            loginToken.setUsed("1");
            Date date = new Date();
            loginToken.setOuttime(new Timestamp(date.getTime() + 60*2*60*1000));
            loginToken.setUserid(user.getUserId());
            loginToken.setToken(UUID.randomUUID().toString());
            loginTokenMapper.insert(loginToken);
            result.put("token", loginToken.getToken());
            return result;
        }else{
            result.put("msg", "系统异常，请重试！");
        }
        return result;
    }

    public User checkToken(String token) {
        LoginToken loginToken = loginTokenMapper.getByToken(token);
        if (loginToken == null) {
            return null;
        }else if (new Timestamp(new Date().getTime()).after(loginToken.getOuttime())){
            return null;
        }else {
            //loginToken.setOuttime(new Timestamp(loginToken.getOuttime().getTime() + 60*2*60*1000));
            //loginTokenMapper.update(loginToken);
            return userMapper.selectByPrimaryKey(loginToken.getUserid());
        }
    }

    public void logout(String token) {
        LoginToken loginToken = loginTokenMapper.getByToken(token);
        loginToken.setOuttime(new Timestamp(new Date().getTime()-1));
        loginTokenMapper.update(loginToken);
    }

    public List<User> getUsersByTeacherId(String id) {
        return userMapper.getAll();
    }
}
