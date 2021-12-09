package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Leevi
 * 日期2021-05-09  10:36
 * 我们通常会在doGet()中调用doPost()或者在doPost()中调用doGet()
 * 原因是因为无论客户端发送的是什么方式的请求，只要你是访问我这个Servlet，那么我的处理方式都是一样的
 */
public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //动态获取项目部署名的方法
        String path = request.getContextPath()+"/pages/a.html";

        System.out.println(path);

        //获取全局的初始化参数
        String username = getServletContext().getInitParameter("username");
        System.out.println("在ServletDemo03中获取全局的初始化参数username=" + username);

        //获取域对象ServletContext里面存放的数据
        String str = (String) getServletContext().getAttribute("str");
        System.out.println("在ServletDemo03中获取域对象中的str=" + str);
    }
}
