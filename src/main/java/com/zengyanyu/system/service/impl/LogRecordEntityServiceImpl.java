package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.zengyanyu.system.mapper.LogRecordMapper;
import com.zengyanyu.system.service.ILogRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author zengyanyu
 * @since 2025-07-16
 */
@Service
public class LogRecordEntityServiceImpl extends ServiceImpl<LogRecordMapper, LogRecordEntity> implements ILogRecordService {

    /**
     * 保存或更新
     *
     * @param logRecordEntity
     * @return
     */
    @Override
    public ResponseData saveOrUpdateLogRecord(LogRecordEntity logRecordEntity) {
        this.saveOrUpdate(logRecordEntity);
        return new ResponseData("保存或更新成功");
    }

}
