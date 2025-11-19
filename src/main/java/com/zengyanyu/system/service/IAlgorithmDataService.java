package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.AlgorithmData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法数据 服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface IAlgorithmDataService extends IService<AlgorithmData> {

    /**
     * 保存或更新算法数据
     *
     * @param algorithmData 算法数据
     * @return
     */
    ResponseData saveOrUpdateAlgorithmData(AlgorithmData algorithmData);

}
