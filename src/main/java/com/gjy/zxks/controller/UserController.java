package com.gjy.zxks.controller;

import com.alibaba.fastjson.JSONObject;
import com.gjy.zxks.po.User;
import com.gjy.zxks.service.inter.UserServiceInter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author jiayao gao
 * @create 2017-04-03 19:45
 **/
@Controller
@RequestMapping("/userCtr")
public class UserController {

    @Resource
    UserServiceInter userServiceInter;

    @RequestMapping("/public/login")
    @ResponseBody
    public Object verifyUser(String account, String pwd){
        JSONObject result = new JSONObject();
        try {
            result = userServiceInter.checkLogin(account, pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/public/logout")
    @ResponseBody
    public Object logout(String token){
        JSONObject result = new JSONObject();
        try {
            if(token != null){
                userServiceInter.logout(token);
            }else{
                result.put("msg", "token不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("msg", "token验证失败");
        }
        result.put("msg", "退出成功");
        return result;
    }

    @RequestMapping("/public/check_token")
    @ResponseBody
    public Object checkToken(String token){
        JSONObject result = new JSONObject();
        try {
            if(token.equals("false")){
                return result;
            }
            User user = userServiceInter.checkToken(token);
            if(user != null){
                result.put("userinfo", user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
