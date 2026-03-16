/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zengyanyu.system.config.UserContextHolder;
import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.util.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * 系统元数据对象处理对象，实现字段值自动填充
 *
 * @author zengyanyu
 */
@Component
public class SysMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        UserInfo userContext = UserContextHolder.getUserContext();
        if (null != userContext) {
            metaObject.setValue("createBy", userContext.getUsername());
        }
        metaObject.setValue("createTime", DateUtils.getCurrentTime());
    }

    /**
     * 修改填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        UserInfo userContext = UserContextHolder.getUserContext();
        if (null != userContext) {
            metaObject.setValue("updateBy", userContext.getUsername());
        }
        metaObject.setValue("updateTime", DateUtils.getCurrentTime());
    }
}
