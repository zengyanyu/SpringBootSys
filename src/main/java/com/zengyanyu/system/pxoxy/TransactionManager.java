package com.zengyanyu.system.pxoxy;

/**
 * 自定义事务管理对象
 *
 * @author zengyanyu
 */
public class TransactionManager {

    public void begin() {
        System.out.println("自定义事务-开始事务");
    }

    public void commit() {
        System.out.println("自定义事务-提交事务");
    }

    public void rollback() {
        System.out.println("自定义事务-回滚事务");
    }

}
