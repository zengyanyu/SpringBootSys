package com.zengyanyu.system.mapper;

import com.zengyanyu.system.entity.LogRecordEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper 接口
 *
 * @author zengyanyu
 * @since 2025-07-16
 */
@Mapper
public interface LogRecordMapper extends BaseMapper<LogRecordEntity> {

    /**
     * 根据条件获取日志记录
     * @param requestTime
     * @return
     */
    List<LogRecordEntity> getLogRecordByCondition(LocalDateTime requestTime);

}
