package com.atguigu.service;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;

/**
 * 包名:com.atguigu.service
 *
 * @author Leevi
 * 日期2021-05-19  09:11
 */
public interface OrderService {
    /**
     * 结算业务
     * @param user
     * @param cart
     * @return
     */
    String checkout(User user, Cart cart) throws Exception;
}
