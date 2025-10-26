package com.zengyanyu.system.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.util.DateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @author zengyanyu
 */
@Component
public class SysMetaObjectHandler implements MetaObjectHandler {

    // 新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        UserInfo userContext = UserContextHolder.getUserContext();
        if (null != userContext) {
            metaObject.setValue("createBy", userContext.getUsername());
        }
        metaObject.setValue("createTime", DateUtils.getCurrentTime());
    }

    // 修改填充
    @Override
    public void updateFill(MetaObject metaObject) {
        UserInfo userContext = UserContextHolder.getUserContext();
        if (null != userContext) {
            metaObject.setValue("updateBy", userContext.getUsername());
        }
        metaObject.setValue("updateTime", DateUtils.getCurrentTime());
    }
}
