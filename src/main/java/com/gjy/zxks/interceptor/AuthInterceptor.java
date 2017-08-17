package com.gjy.zxks.interceptor;

import com.gjy.zxks.service.inter.UserServiceInter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiayao gao
 * @create 2017-04-04 16:05
 **/
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    UserServiceInter userServiceInter;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getRequestURI().contains("userCtr/public/")){
            return true;
        }
        //得到头部令牌
        String token = httpServletRequest.getParameter("token");
        if (token == null) {
            httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        else if (token.equals("") || token.equals("false")) {
            httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest,httpServletResponse);
            return false;
        }else {
            if(userServiceInter.checkToken(token) != null){
                return true;
            }else {
                httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest,httpServletResponse);
                return false;
            }
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
