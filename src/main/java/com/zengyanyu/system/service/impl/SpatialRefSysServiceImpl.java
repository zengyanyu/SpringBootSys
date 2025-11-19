package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.SpatialRefSys;
import com.zengyanyu.system.mapper.SpatialRefSysMapper;
import com.zengyanyu.system.service.ISpatialRefSysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class SpatialRefSysServiceImpl extends ServiceImpl<SpatialRefSysMapper, SpatialRefSys> implements ISpatialRefSysService {

    /**
     * 保存或更新
     *
     * @param spatialRefSys 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateSpatialRefSys(SpatialRefSys spatialRefSys) {
        this.saveOrUpdate(spatialRefSys);
        return new ResponseData("保存或更新成功");
    }

}
