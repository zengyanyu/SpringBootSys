/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.System;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 系统 服务类
 *
 * @author zengyanyu
 * @since 2026-03-13
 */
public interface ISystemService extends IService<System> {

    /**
     * 保存或更新系统
     *
     * @param system 系统
     * @return
     */
    ResponseData saveOrUpdateSystem(System system);

}
