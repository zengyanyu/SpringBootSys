package com.zengyanyu.system.service;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 服务类
 *
 * @author zengyanyu
 * @since 2025-07-16
 */
public interface ILogRecordService extends IService<LogRecordEntity> {

    /**
     * 保存或更新
     *
     * @param logRecordEntity
     * @return
     */
    ResponseData saveOrUpdateLogRecord(LogRecordEntity logRecordEntity);

    /**
     * 定时删除日志记录
     */
    void removeLogRecordByConditions();
}
