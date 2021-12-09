package com.atguigu.dao;

import com.atguigu.bean.Order;

/**
 * 包名:com.atguigu.dao
 *
 * @author Leevi
 * 日期2021-05-19  09:12
 */
public interface OrderDao {
    /**
     * 保存订单信息
     * @param order
     */
    void insertOrder(Order order) throws Exception;
}
