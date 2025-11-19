package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.AlgorithmDataFactorSave;
import com.zengyanyu.system.mapper.AlgorithmDataFactorSaveMapper;
import com.zengyanyu.system.service.IAlgorithmDataFactorSaveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法要素对应关系表 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class AlgorithmDataFactorSaveServiceImpl extends ServiceImpl<AlgorithmDataFactorSaveMapper, AlgorithmDataFactorSave> implements IAlgorithmDataFactorSaveService {

    /**
     * 保存或更新算法要素对应关系表
     *
     * @param algorithmDataFactorSave 算法要素对应关系表
     * @return
     */
    @Override
    public ResponseData saveOrUpdateAlgorithmDataFactorSave(AlgorithmDataFactorSave algorithmDataFactorSave) {
        this.saveOrUpdate(algorithmDataFactorSave);
        return new ResponseData("保存或更新成功");
    }

}
