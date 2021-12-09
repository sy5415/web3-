package com.atguigu.servlet;

import com.atguigu.bean.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leevi
 * 日期2021-05-13  09:15
 */
public class TestThymeleafServlet extends ViewBaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //往请求域中存储键值对
        request.setAttribute("username","奥巴马");
        request.setAttribute("helloRequestAttr","helloRequestAttr-VALUE");

        request.setAttribute("aNotEmptyList", Arrays.asList("a","b","c","d"));
        request.setAttribute("anEmptyList",new ArrayList<>());

        //往请求域中存入Student对象
        Map<String, Teacher> teacherMap = new HashMap<>();
        teacherMap.put("tKey",new Teacher("Leevi"));
        request.setAttribute("student",new Student("张三",new Subject("Java"),Arrays.asList(new School("尚硅谷"),new School("清华大学")),teacherMap));

        //往请求域中存储一个老师的集合
        request.setAttribute("teacherList",Arrays.asList(new Teacher("Leevi"),new Teacher("饭老师"),new Teacher("苍老师"),new Teacher("小泽老师"),new Teacher("波老师")));

        //往请求域中再存储一个数据:user对象
        request.setAttribute("user",new User("level-1"));
        //往会话域中存入值
        request.getSession().setAttribute("helloSessionAttr","helloSessionAttr-VALUE");

        //往全局域中村入值
        getServletContext().setAttribute("helloAppAttr","helloAppAttr-VALUE");

        //请求转发跳转到/WEB-INF/view/target.html
        processTemplate("target",request,response);
    }
}
