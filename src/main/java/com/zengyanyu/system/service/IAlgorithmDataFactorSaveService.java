package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.AlgorithmDataFactorSave;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法要素对应关系表 服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface IAlgorithmDataFactorSaveService extends IService<AlgorithmDataFactorSave> {

    /**
     * 保存或更新算法要素对应关系表
     *
     * @param algorithmDataFactorSave 算法要素对应关系表
     * @return
     */
    ResponseData saveOrUpdateAlgorithmDataFactorSave(AlgorithmDataFactorSave algorithmDataFactorSave);

}
