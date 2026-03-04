/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.DictCopy1;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务类
 *
 * @author zengyanyu
 * @since 2026-02-26
 */
public interface IDictCopy1Service extends IService<DictCopy1> {

    /**
     * 保存或更新
     *
     * @param dictCopy1 
     * @return
     */
    ResponseData saveOrUpdateDictCopy1(DictCopy1 dictCopy1);

}
