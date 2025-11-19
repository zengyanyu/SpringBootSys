package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.AlgorithmDataType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法类型 服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface IAlgorithmDataTypeService extends IService<AlgorithmDataType> {

    /**
     * 保存或更新算法类型
     *
     * @param algorithmDataType 算法类型
     * @return
     */
    ResponseData saveOrUpdateAlgorithmDataType(AlgorithmDataType algorithmDataType);

}
