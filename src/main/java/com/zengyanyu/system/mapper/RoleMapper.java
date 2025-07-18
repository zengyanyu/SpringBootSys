package com.zengyanyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zengyanyu.system.entity.Role;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.FifoCache;

/**
 * 角色 Mapper 接口
 *
 * @author zengyanyu
 * @since 2024-11-19
 */
@Mapper
@CacheNamespace(flushInterval = 60000, size = 512, eviction = FifoCache.class)
public interface RoleMapper extends BaseMapper<Role> {

}
