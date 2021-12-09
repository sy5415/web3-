package com.atguigu.servlet;

import com.atguigu.bean.Soldier;
import com.atguigu.service.SoldierService;
import com.atguigu.service.impl.SoldierServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Leevi
 * 日期2021-05-13  16:01
 */
public class SoldierServlet extends ModelBaseServlet {
    private SoldierService soldierService = new SoldierServiceImpl();
    /**
     * 处理查询所有士兵信息的请求
     * @param request
     * @param response
     */
    public void showAll(HttpServletRequest request,HttpServletResponse response){
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

    /**
     * 跳转到添加页面
     * @param request
     * @param response
     */
    public void toAddPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        processTemplate("add",request,response);
    }

    /**
     * 添加士兵信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void addSoldier(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1. 获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2. 将请求参数封装到Soldier对象
        Soldier soldier = new Soldier();
        try {
            BeanUtils.populate(soldier,parameterMap);
            //3. 调用业务层的方法处理添加士兵的功能
            soldierService.addSoldier(soldier);

            //4. 跳转到查看所有士兵信息列表
            response.sendRedirect(request.getContextPath()+"/soldier?method=showAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除士兵信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void deleteSoldier(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1. 获取请求参数：id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2. 调用业务层的方法，根据id删除士兵
        try {
            soldierService.deleteSoldierById(id);
            //3. 删除成功,重新查询所有
            response.sendRedirect(request.getContextPath()+"/soldier?method=showAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转到修改页面
     * @param request
     * @param response
     * @throws IOException
     */
    public void toUpdatePage(HttpServletRequest request,HttpServletResponse response) throws IOException {
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

    /**
     * 修改士兵信息
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateSoldier(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1. 获取请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2. 将请求参数封装到Soldier对象
        Soldier soldier = new Soldier();
        try {
            BeanUtils.populate(soldier,parameterMap);
            //3. 调用业务层的方法执行修改
            soldierService.updateSoldier(soldier);
            //4. 修改成功之后重新查询所有
            response.sendRedirect(request.getContextPath()+"/soldier?method=showAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
