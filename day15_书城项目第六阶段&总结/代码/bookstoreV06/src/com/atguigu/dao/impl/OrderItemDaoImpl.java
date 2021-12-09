package com.atguigu.dao.impl;

import com.atguigu.bean.OrderItem;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderItemDao;

/**
 * 包名:com.atguigu.dao.impl
 *
 * @author Leevi
 * 日期2021-05-19  10:13
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public void insertOrderItemArr(Object[][] insertOrderItemParamArr) throws Exception{
        String sql = "insert into t_order_item (book_name,price,img_path,item_count,item_amount,order_id) values (?,?,?,?,?,?)";
        batchUpdate(sql,insertOrderItemParamArr);
    }
}
