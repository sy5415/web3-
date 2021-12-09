package com.atguigu.servlet;

import com.atguigu.bean.Soldier;
import com.atguigu.service.SoldierService;
import com.atguigu.service.impl.SoldierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Leevi
 * 日期2021-05-13  14:14
 */
public class ShowAllServlet extends ViewBaseServlet {
    private SoldierService soldierService = new SoldierServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //调用业务层的方法查询士兵列表
            List<Soldier> soldierList = soldierService.findAllSoldier();
            //将soldierList存储到域对象
            request.setAttribute("soldierList",soldierList);
            //跳转到展示页面进行展示
            processTemplate("list",request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
