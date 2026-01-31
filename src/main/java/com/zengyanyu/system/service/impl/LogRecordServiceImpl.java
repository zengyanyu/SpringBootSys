package com.zengyanyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.zengyanyu.system.mapper.LogRecordMapper;
import com.zengyanyu.system.service.ILogRecordService;
import com.zengyanyu.system.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 服务实现类
 *
 * @author zengyanyu
 * @since 2025-07-16
 */
@Service
public class LogRecordServiceImpl extends ServiceImpl<LogRecordMapper, LogRecordEntity> implements ILogRecordService {

    @Override
    public void removeLogRecordByConditions() {
        // 获取当前时间
        LocalDateTime now = DateUtils.getCurrentTime();
        // 当前时间减去5天
        LocalDateTime fiveDaysAgo = now.minus(5, ChronoUnit.DAYS);
        List<LogRecordEntity> logRecordEntityList = this.baseMapper.getLogRecordByCondition(fiveDaysAgo);
        if (null != logRecordEntityList && logRecordEntityList.size() > 0) {
//            this.baseMapper.removeBatchByIds(logRecordEntityList);
        }
    }

}
