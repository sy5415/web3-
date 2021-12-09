package com.atguigu.servlet;

import com.atguigu.bean.Soldier;
import com.atguigu.service.SoldierService;
import com.atguigu.service.impl.SoldierServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Leevi
 * 日期2021-05-13  14:37
 */
public class AddSoldierServlet extends HttpServlet {
    private SoldierService soldierService = new SoldierServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //1. 获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2. 将请求参数封装到Soldier对象
        Soldier soldier = new Soldier();
        try {
            BeanUtils.populate(soldier,parameterMap);
            //3. 调用业务层的方法处理添加士兵的功能
            soldierService.addSoldier(soldier);

            //4. 跳转到查看所有士兵信息列表
            response.sendRedirect(request.getContextPath()+"/showAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
