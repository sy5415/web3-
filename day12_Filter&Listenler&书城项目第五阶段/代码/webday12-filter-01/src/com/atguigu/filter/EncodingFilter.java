package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Leevi
 * 日期2021-05-18  08:56
 * 编写过滤器的步骤:
 * 1. 写一个类实现Filter接口，并且重写方法
 * 2. 在web.xml中配置该过滤器的拦截路径
 *
 * 过滤器的生命周期和生命周期方法:
 * 1. 过滤器在服务器启动（项目部署）的时候创建，在服务器关闭的时候销毁
 *
 * 生命周期方法有仨:
 * 1. init()它会在过滤器对象创建出来之后执行，在它里面可以做一些初始化操作
 * 2. doFilter()它会在过滤器每次过滤请求的时候执行
 * 3. destroy()它会在过滤器对象销毁之前执行
 */
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("EncodingFilter对象销毁了...");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //解决请求参数的乱码
        HttpServletRequest request = (HttpServletRequest) req;
        request.setCharacterEncoding("UTF-8");

        //每次有请求被当前filter接收到的时候，就会执行doFilter进行过滤处理
        System.out.println("EncodingFilter接收到了一个请求...");

        //这句代码表示放行
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("EncodingFilter对象创建了。。。");
    }

}
