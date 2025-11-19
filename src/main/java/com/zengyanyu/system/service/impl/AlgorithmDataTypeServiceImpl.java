package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.AlgorithmDataType;
import com.zengyanyu.system.mapper.AlgorithmDataTypeMapper;
import com.zengyanyu.system.service.IAlgorithmDataTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法类型 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class AlgorithmDataTypeServiceImpl extends ServiceImpl<AlgorithmDataTypeMapper, AlgorithmDataType> implements IAlgorithmDataTypeService {

    /**
     * 保存或更新算法类型
     *
     * @param algorithmDataType 算法类型
     * @return
     */
    @Override
    public ResponseData saveOrUpdateAlgorithmDataType(AlgorithmDataType algorithmDataType) {
        this.saveOrUpdate(algorithmDataType);
        return new ResponseData("保存或更新成功");
    }

}
