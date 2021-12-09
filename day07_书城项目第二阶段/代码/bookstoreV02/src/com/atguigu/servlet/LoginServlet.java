package com.atguigu.servlet;

import com.atguigu.bean.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Leevi
 * 日期2021-05-12  09:19
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //1. 获取客户端传入的请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2. 将username和password封装到User对象
        User user = new User(null,username,password,null);

        //3. 调用业务层的方法处理登录
        try {
            User loginUser = userService.doLogin(user);
            //没有出现异常，说明登录成功，那么跳转到登录成功页面
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.html");
        } catch (Exception e) {
            e.printStackTrace();
            //出现异常表示登录失败，那么则响应失败信息
            response.getWriter().write("登录失败,"+e.getMessage());
        }
    }
}
