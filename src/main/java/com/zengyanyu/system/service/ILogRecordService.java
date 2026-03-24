/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 日志记录 服务类
 *
 * @author zengyanyu
 */
public interface ILogRecordService extends IService<LogRecordEntity> {

    /**
     * 定时删除日志记录
     */
    void removeLogRecordByConditions();
}
