package com.atguigu.servlet;

import com.atguigu.service.SoldierService;
import com.atguigu.service.impl.SoldierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Leevi
 * 日期2021-05-13  15:05
 */
public class DeleteSoldierServlet extends ViewBaseServlet {
    private SoldierService soldierService = new SoldierServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数：id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2. 调用业务层的方法，根据id删除士兵
        try {
            soldierService.deleteSoldierById(id);
            //3. 删除成功,重新查询所有
            response.sendRedirect(request.getContextPath()+"/showAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
