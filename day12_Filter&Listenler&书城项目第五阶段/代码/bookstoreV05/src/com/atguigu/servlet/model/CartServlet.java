package com.atguigu.servlet.model;

import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.servlet.base.ModelBaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public void addCartItem(HttpServletRequest request, HttpServletResponse response){
        try {
            //1. 获取请求参数id的值
            Integer id = Integer.valueOf(request.getParameter("id"));
            //2. 调用bookService的方法根据id查询book信息
            Book book = bookService.getBookById(id);
            //3. 尝试从会话域中获取购物车
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            //4. 判断之前是否已经添加过购物车了
            if (cart == null) {
                //说明这是第一次添加购物车
                //那么就要新创建一个购物车对象
                cart = new Cart();
                //然后将当前book加入到这个购物车
                cart.addBookToCart(book);
               //然后将cart存入到session
               session.setAttribute("cart",cart);
            }else {
                //说明不是第一次添加购物车
                //那么就直接用之前的购物车，添加当前book就行
                cart.addBookToCart(book);
            }

            //跳转到首页
            response.sendRedirect(request.getContextPath()+"/index.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        request.getSession().removeAttribute("cart");
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
        //1. 获取到要-1的书的id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2. 从session中获取购物车信息
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3. 调用购物车的-1方法
        cart.itemCountDecrease(id);
        //4. 跳转回到购物车页面
        processTemplate("cart/cart",request,response);
    }

    /**
     * 购物车中某个一个购物项的数量+1
     * @param request
     * @param response
     * @throws IOException
     */
    public void countIncrease(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1. 获取到要-+1的书的id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2. 从session中获取购物车信息
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3. 调用购物车的+1方法
        cart.itemCountIncrease(id);
        //4. 跳转回到购物车页面
        processTemplate("cart/cart",request,response);
    }

    /**
     * 删除购物项
     * @param request
     * @param response
     * @throws IOException
     */
    public void removeCartItem(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1. 获取要删除的购物项的书的id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //2.从session中获取购物车
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3. 调用cart的删除购物项的方法
        cart.removeCartItem(id);
        //4. 跳转回到购物车页面
        processTemplate("cart/cart",request,response);
    }

    /**
     * 修改购物项的数量
     * @param request
     * @param response
     * @throws IOException
     */
    public void updateCartItemCount(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1. 获取请求参数:id,newCount
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer newCount = Integer.valueOf(request.getParameter("newCount"));
        //2. 从session中获取购物车信息
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3. 调用cart中跟新数量的方法
        cart.updateItemCount(id,newCount);

        //4. 跳转到购物车页面
        processTemplate("cart/cart",request,response);
    }
}
