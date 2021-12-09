package com.atguigu.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Leevi
 * 日期2021-05-09  13:57
 */
public class ServletDemo04 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext对象
        //第一种方法获取ServletContext
        ServletContext servletContext = getServletContext();

        //第二种方法是:ServletConfig对象调用getServletContext()方法
        //ServletContext servletContext = getServletConfig().getServletContext();

        //ServletContext的作用一: 获取全局的初始化参数
        //获取全局的初始化参数
        String username = servletContext.getInitParameter("username");
        System.out.println("在ServletDemo04中获取全局的初始化参数username=" + username);


        //ServletContext的作用二: 作为全局的域对象在各个Servlet中进行数据共享
        String str = "周杰棍";
        //将str存储到ServletContext里边
        servletContext.setAttribute("str",str);

        //ServletContext的作用三: 获取资源文件的真实路径其实就是动态获取web文件夹里面的资源的物理路径
        //目标: 将img/mm.jpg转成字节输入流
        String realPath = servletContext.getRealPath("img/mm.jpg");

        //使用类加载器将资源文件转成字节输入流,不建议使用:因为文件没有在类路径下
        /*InputStream is = ServletDemo04.class.getClassLoader().getResourceAsStream("../../img/mm.jpg");
        System.out.println(is);*/

        System.out.println(realPath);
        FileInputStream is = new FileInputStream(realPath);
        System.out.println(is);
    }
}
