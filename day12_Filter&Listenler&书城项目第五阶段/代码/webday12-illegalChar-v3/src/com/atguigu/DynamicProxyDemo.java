package com.atguigu;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 包名:com.atguigu
 *
 * @author Leevi
 * 日期2021-05-18  11:18
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        //演示动态代理

        //names就是要被代理的对象
        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("李四");
        names.add("王五");

        Class<? extends List> clazz = names.getClass();
        //使用动态代理改变List的toString方法
        //proxyInstance 代理对象
        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //method就是代理对象要调用的方法
                if (method.getName().equals("toString")) {
                    return "赵六";
                }

                if (method.getName().equals("add")){
                    return method.invoke(names,"你好");
                }
                return method.invoke(names,args);
            }
        });
        proxyInstance.add("你大爷");
        //目标:让这里打印出来是"赵六"
        System.out.println(proxyInstance.toString());
    }
}
