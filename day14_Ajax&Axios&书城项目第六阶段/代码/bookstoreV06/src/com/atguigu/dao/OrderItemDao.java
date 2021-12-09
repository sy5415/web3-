package com.atguigu.dao;

/**
 * 包名:com.atguigu.dao
 *
 * @author Leevi
 * 日期2021-05-19  10:11
 */
public interface OrderItemDao {
    /**
     * 批量添加订单项
     * @param insertOrderItemParamArr
     */
    void insertOrderItemArr(Object[][] insertOrderItemParamArr) throws Exception;
}
