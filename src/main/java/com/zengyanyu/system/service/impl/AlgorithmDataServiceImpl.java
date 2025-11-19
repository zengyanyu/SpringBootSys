package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.AlgorithmData;
import com.zengyanyu.system.mapper.AlgorithmDataMapper;
import com.zengyanyu.system.service.IAlgorithmDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法数据 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class AlgorithmDataServiceImpl extends ServiceImpl<AlgorithmDataMapper, AlgorithmData> implements IAlgorithmDataService {

    /**
     * 保存或更新算法数据
     *
     * @param algorithmData 算法数据
     * @return
     */
    @Override
    public ResponseData saveOrUpdateAlgorithmData(AlgorithmData algorithmData) {
        this.saveOrUpdate(algorithmData);
        return new ResponseData("保存或更新成功");
    }

}
