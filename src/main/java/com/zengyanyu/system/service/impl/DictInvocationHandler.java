package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.pxoxy.TransactionManager;
import com.zengyanyu.system.service.IDictService;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 *
 * @author zengyanyu
 */
public class DictInvocationHandler implements InvocationHandler {

    @Resource
    private IDictService dictService;
    @Resource
    private TransactionManager transactionManager;

    @Resource
    private Object target;

    /**
     * 如何做增强的方法
     *
     * @param proxy
     * @param method
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            // 开始事务
            transactionManager.begin();
            // 原封不动调用真实对象的的方法
            method.invoke(dictService, args);

            // 提交事务
            transactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取代理对象
     *
     * @param <T>
     * @return
     */
    public <T> T getProxyObject() {
        // this.getClass().getClassLoader(): 类加载器
        // target.getClass().getInterfaces(): 为哪些接口做代码
        // this: 如何做增强对象InvocationHandler
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }
}
