package com.atguigu.servlet;

import com.atguigu.bean.Soldier;
import com.atguigu.service.SoldierService;
import com.atguigu.service.impl.SoldierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Leevi
 * 日期2021-05-13  14:39
 */
public class ToUpdatePageServlet extends ViewBaseServlet {
    private SoldierService soldierService = new SoldierServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要修改的士兵的id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //查询出当前士兵的信息
        try {
            Soldier soldier = soldierService.findSoldierById(id);
            //将soldier存储到请求域中
            request.setAttribute("soldier",soldier);

            //跳转到修改页面
            processTemplate("update",request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
