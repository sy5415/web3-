package com.atguigu.dao.impl;

import com.atguigu.bean.Order;
import com.atguigu.dao.BaseDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 包名:com.atguigu.dao.impl
 *
 * @author Leevi
 * 日期2021-05-19  09:12
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public void insertOrder(Order order) throws Exception{
        ResultSet resultSet = null;
        Connection conn = null;
        try {
            //往t_order表中插入一条订单信息
            //使用DBUtils没法获取自增长的主键值，所以我们只能使用原始的JDBC执行SQL语句，获取自增长的主键
            String sql = "insert into t_order (order_sequence,create_time,total_count,total_amount,order_status,user_id) values (?,?,?,?,?,?)";
            conn = JDBCUtil.getConnection();

            //预编译，并且指定获取自增长主键
            PreparedStatement preparedStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //设置参数
            preparedStatement.setObject(1, order.getOrderSequence());
            preparedStatement.setObject(2, order.getCreateTime());
            preparedStatement.setObject(3, order.getTotalCount());
            preparedStatement.setObject(4, order.getTotalAmount());
            preparedStatement.setObject(5, order.getOrderStatus());
            preparedStatement.setObject(6, order.getUserId());

            //执行sql语句
            preparedStatement.executeUpdate();

            //获取自增长主键值
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int orderId = resultSet.getInt(1);
                //将orderId存入到order对象中
                order.setOrderId(orderId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
