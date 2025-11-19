package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.AlgorithmDataFactor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法要素 服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface IAlgorithmDataFactorService extends IService<AlgorithmDataFactor> {

    /**
     * 保存或更新算法要素
     *
     * @param algorithmDataFactor 算法要素
     * @return
     */
    ResponseData saveOrUpdateAlgorithmDataFactor(AlgorithmDataFactor algorithmDataFactor);

}
