package com.zengyanyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.PermissionRecord;

/**
 * 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IPermissionRecordService extends IService<PermissionRecord> {

    /**
     * 保存或更新
     *
     * @param permissionRecord
     * @return
     */
    ResponseData saveOrUpdatePermissionRecord(PermissionRecord permissionRecord);

    /**
     * 加载权限记录
     *
     * @return
     */
    ResponseData loadPermissionRecord();

}
