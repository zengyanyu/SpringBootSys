package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.AlgorithmDataFactor;
import com.zengyanyu.system.mapper.AlgorithmDataFactorMapper;
import com.zengyanyu.system.service.IAlgorithmDataFactorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法要素 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class AlgorithmDataFactorServiceImpl extends ServiceImpl<AlgorithmDataFactorMapper, AlgorithmDataFactor> implements IAlgorithmDataFactorService {

    /**
     * 保存或更新算法要素
     *
     * @param algorithmDataFactor 算法要素
     * @return
     */
    @Override
    public ResponseData saveOrUpdateAlgorithmDataFactor(AlgorithmDataFactor algorithmDataFactor) {
        this.saveOrUpdate(algorithmDataFactor);
        return new ResponseData("保存或更新成功");
    }

}
