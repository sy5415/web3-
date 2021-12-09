package com.atguigu.servlet.model;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.bean.CommonResult;
import com.atguigu.constant.BookStoreConstants;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;
import com.atguigu.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @author Leevi
 * 日期2021-05-17  10:17
 */
public class CartServlet extends ModelBaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 添加商品进购物车
     * @param request
     * @param response
     */
    public void addCartItem(HttpServletRequest request, HttpServletResponse response) {
        CommonResult commonResult = null;
        try {
            //1. 获取请求参数id的值
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 调用bookService的方法根据id查询book信息
            Book book = bookService.getBookById(id);
            //3. 尝试从会话域中获取购物车
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(BookStoreConstants.CARTSESSIONKEY);
            //4. 判断之前是否已经添加过购物车了
            if (cart == null) {
                //说明这是第一次添加购物车
                //那么就要新创建一个购物车对象
                cart = new Cart();
                //然后将当前book加入到这个购物车
                cart.addBookToCart(book);
                //然后将cart存入到session
                session.setAttribute(BookStoreConstants.CARTSESSIONKEY, cart);
            } else {
                //说明不是第一次添加购物车
                //那么就直接用之前的购物车，添加当前book就行
                cart.addBookToCart(book);
            }

            //添加购物车成功
            //获取购物车中的商品数量cart.getTotalCount()

            commonResult = CommonResult.ok().setResultData(cart.getTotalCount());
        } catch (Exception e) {
            e.printStackTrace();
            //添加购物车失败
            commonResult = CommonResult.error().setMessage("添加购物车失败");
        }
        JsonUtils.writeResult(response, commonResult);
    }

    /**
     * 跳转到显示购物车列表的页面
     * @param request
     * @param response
     */
    public void toCartPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        processTemplate("cart/cart",request,response);
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws IOException
     */
    public void cleanCart(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //就是移除掉session中的cart就行了
        request.getSession().removeAttribute(BookStoreConstants.CARTSESSIONKEY);
        //跳转到购物车展示页面
        processTemplate("cart/cart",request,response);
    }

    /**
     * 购物车中某个一个购物项的数量-1
     * @param request
     * @param response
     * @throws IOException
     */
    public void countDecrease(HttpServletRequest request,HttpServletResponse response) throws IOException {
        CommonResult commonResult = null;
        try {
            //1. 获取到要-1的书的id
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 从session中获取购物车信息
            Cart cart = (Cart) request.getSession().getAttribute(BookStoreConstants.CARTSESSIONKEY);
            //3. 调用购物车的-1方法
            cart.itemCountDecrease(id);

            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = getResponseMap(id, cart);

            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }
        JsonUtils.writeResult(response, commonResult);
    }

    /**
     * 购物车中某个一个购物项的数量+1
     * @param request
     * @param response
     * @throws IOException
     */
    public void countIncrease(HttpServletRequest request,HttpServletResponse response) throws IOException {
        CommonResult commonResult = null;
        try {
            //1. 获取到要-+1的书的id
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 从session中获取购物车信息
            Cart cart = (Cart) request.getSession().getAttribute(BookStoreConstants.CARTSESSIONKEY);
            //3. 调用购物车的+1方法
            cart.itemCountIncrease(id);
            Map<String, Object> responseMap = getResponseMap(id, cart);

            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }

        JsonUtils.writeResult(response,commonResult);
    }

    /**
     * 封装响应的Map数据
     * @param id
     * @param cart
     * @return
     */
    private Map<String, Object> getResponseMap(Integer id, Cart cart) {
        //将服务器端最新的购物车totalCount和totalAmount响应给客户端
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("totalCount", cart.getTotalCount());
        responseMap.put("totalAmount", cart.getTotalAmount());

        if (cart.getCartItemMap().get(id) != null) {
            //直接将当前购物项的总金额也封装到map中
            responseMap.put("amount", cart.getCartItemMap().get(id).getAmount());
        }
        return responseMap;
    }

    /**
     * 删除购物项
     * @param request
     * @param response
     * @throws IOException
     */
    public void removeCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException {
        CommonResult commonResult = null;
        try {
            //1. 获取要删除的购物项的书的id
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2.从session中获取购物车
            Cart cart = (Cart) request.getSession().getAttribute(BookStoreConstants.CARTSESSIONKEY);
            //3. 调用cart的删除购物项的方法
            cart.removeCartItem(id);

            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = getResponseMap(id, cart);

            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }
        JsonUtils.writeResult(response,commonResult);
    }

    /**
     * 修改购物项的数量
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateCartItemCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
        CommonResult commonResult = null;
        try {
            //1. 获取请求参数:id,newCount
            Integer id = Integer.valueOf(request.getParameter("id"));
            Integer newCount = Integer.valueOf(request.getParameter("newCount"));
            //2. 从session中获取购物车信息
            Cart cart = (Cart) request.getSession().getAttribute(BookStoreConstants.CARTSESSIONKEY);
            //3. 调用cart中跟新数量的方法
            cart.updateItemCount(id,newCount);

            //将服务器端最新的购物车totalCount和totalAmount响应给客户端
            Map<String, Object> responseMap = getResponseMap(id, cart);
            commonResult = CommonResult.ok().setResultData(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error();
        }

        JsonUtils.writeResult(response,commonResult);
    }

    /**
     * 获取购物车的json数据
     * @param request
     * @param response
     */
    public void getCartJSON(HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> responseMap = null;
        CommonResult commonResult = null;
        try {
            //1. 获取购物车信息
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute(BookStoreConstants.CARTSESSIONKEY);

            //2. 我们要响应给客户端的是{"totalCount":总条数,"totalAmount":总金额,"cartItemList":购物项的集合}
            responseMap = new HashMap<>();
            responseMap.put("totalCount", cart.getTotalCount());
            responseMap.put("totalAmount", cart.getTotalAmount());

            //获取购物项列表
            Collection<CartItem> cartItemCollection = cart.getCartItemMap().values();
            List<CartItem> cartItemList = new ArrayList<>(cartItemCollection);

            responseMap.put("cartItemList", cartItemList);

            //查询成功
            commonResult = CommonResult.ok().setResultData(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            commonResult = CommonResult.error().setMessage("查询购物车信息失败");
        }

        //3. 将responseMap转成json并且响应给客户端
        JsonUtils.writeResult(response, commonResult);
    }
}
