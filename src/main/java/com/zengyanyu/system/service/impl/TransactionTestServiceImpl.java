/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * @author zengyanyu
 */
package com.zengyanyu.system.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class TransactionTestServiceImpl {

    // 注入自己
    @Lazy
    @Resource
    private TransactionTestServiceImpl transactionTestService;

    // private 方法事务不会生效
//    @Transactional
//    private void testTransactional1() {
//        int i = 1 / 0;
//    }

    // 正确示例：public 方法事务有效
    @Transactional
    public void testTransactional2() {

    }

    // ============================
    // 自调用事务不生效
    public void submitOrder() {
        // 这里调用了同类中的方法，事务不生效！
        createOrder();
    }

    @Transactional
    public void createOrder() {
        // 失败不会回滚insert！
        throw new RuntimeException("模拟失败");
    }
    // ============================

    // ***********************
    // 解决自调用事务生效的问题：

    public void submitOrder1() {
        // 解决方案：注入自己，或者用ApplicationContext.getBean()获取代理对象。
        // 通过注入的self调用，事务生效！
        transactionTestService.createOrder();
    }
    // ***********************

}


