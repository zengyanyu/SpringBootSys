package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.RubbishBeach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务类
 *
 * @author zengyanyu
 * @since 2025-11-21
 */
public interface IRubbishBeachService extends IService<RubbishBeach> {

    /**
     * 保存或更新
     *
     * @param rubbishBeach 
     * @return
     */
    ResponseData saveOrUpdateRubbishBeach(RubbishBeach rubbishBeach);

}
