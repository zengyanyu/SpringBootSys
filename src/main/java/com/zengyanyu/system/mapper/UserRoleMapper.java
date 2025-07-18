package com.zengyanyu.system.mapper;

import com.zengyanyu.system.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户关联的角色 Mapper 接口
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
