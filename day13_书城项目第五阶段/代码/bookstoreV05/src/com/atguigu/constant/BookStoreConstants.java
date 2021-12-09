package com.atguigu.constant;

/**
 * 包名:com.atguigu.constant
 *
 * @author Leevi
 * 日期2021-05-18  14:23
 */
public class BookStoreConstants {
    /**
     * 将user存储到session时候的key
     */
    public static final String USERSESSIONKEY = "loginUser";

    /**
     * 将cart存储到session时候的key
     */
    public static final String CARTSESSIONKEY = "cart";

    /**
     * 未发货订单
     */
    public static final Integer UNFILLEDORDER = 0;

    /**
     * 已发货订单
     */
    public static final Integer FILLEDORDER = 1;

    /**
     * 已确认收货订单
     */
    public static final Integer RECEIVEDORDER = 2;
}
