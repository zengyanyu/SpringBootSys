package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.AlgorithmDataPlace;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法平台 服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface IAlgorithmDataPlaceService extends IService<AlgorithmDataPlace> {

    /**
     * 保存或更新算法平台
     *
     * @param algorithmDataPlace 算法平台
     * @return
     */
    ResponseData saveOrUpdateAlgorithmDataPlace(AlgorithmDataPlace algorithmDataPlace);

}
