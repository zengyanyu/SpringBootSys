package com.zengyanyu.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zengyanyu.system.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息 Mapper 接口
 *
 * @author zengyanyu
 * @since 2024-11-18
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * @param username
     * @return
     */
    UserInfo getUserInfo(@Param("username") String username);

    /**
     * @param token
     * @return
     */
    UserInfo userInfo(@Param("token") String token);

}
