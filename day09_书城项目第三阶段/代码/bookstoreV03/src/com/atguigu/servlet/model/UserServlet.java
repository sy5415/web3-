package com.atguigu.servlet.model;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Leevi
 * 日期2021-05-14  09:07
 */
public class UserServlet extends ModelBaseServlet {
    private UserService userService = new UserServiceImpl();
    /**
     * 跳转到登录页面
     * @param request
     * @param response
     */
    public void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/login",request,response);
    }

    /**
     * 处理登录校验
     * @param request
     * @param response
     * @throws IOException
     */
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //还是做原来的登录校验
        //1. 获取客户端传入的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        //2. 将username和password封装到User对象
        User user = new User();

        //3. 调用业务层的方法处理登录
        try {
            BeanUtils.populate(user,parameterMap);

            User loginUser = userService.doLogin(user);
            //没有出现异常，说明登录成功，那么跳转到登录成功页面
            processTemplate("user/login_success",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常表示登录失败，则往域对象中存储登录失败的信息
            request.setAttribute("errorMessage","登录失败,"+e.getMessage());
            //跳转到登录页面，显示登录失败的信息
            processTemplate("user/login",request,response);
        }
    }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @throws IOException
     */
    public void toRegisterPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/regist",request,response);
    }

    /**
     * 处理注册请求
     * @param request
     * @param response
     * @throws IOException
     */
    public void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1. 获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2. 使用BeanUtils将parameterMap中的数据封装到User对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
            //3. 调用业务层的方法处理注册业务
            userService.doRegister(user);

            //没有异常，就是注册成功
            //跳转到注册成功页面
            processTemplate("user/regist_success",request,response);
        } catch (Exception e) {
            e.printStackTrace();
            //有异常就注册失败,往域对象中存入失败信息
            request.setAttribute("errorMessage","注册失败,"+e.getMessage());
            //跳转回到注册页面
            processTemplate("user/regist",request,response);
        }
    }
}
