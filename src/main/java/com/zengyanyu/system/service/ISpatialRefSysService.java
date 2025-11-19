package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.SpatialRefSys;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface ISpatialRefSysService extends IService<SpatialRefSys> {

    /**
     * 保存或更新
     *
     * @param spatialRefSys 
     * @return
     */
    ResponseData saveOrUpdateSpatialRefSys(SpatialRefSys spatialRefSys);

}
