package com.zengyanyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zengyanyu.system.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典 Mapper 接口
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {

}
