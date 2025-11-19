package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.AlgorithmDataPlace;
import com.zengyanyu.system.mapper.AlgorithmDataPlaceMapper;
import com.zengyanyu.system.service.IAlgorithmDataPlaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法平台 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class AlgorithmDataPlaceServiceImpl extends ServiceImpl<AlgorithmDataPlaceMapper, AlgorithmDataPlace> implements IAlgorithmDataPlaceService {

    /**
     * 保存或更新算法平台
     *
     * @param algorithmDataPlace 算法平台
     * @return
     */
    @Override
    public ResponseData saveOrUpdateAlgorithmDataPlace(AlgorithmDataPlace algorithmDataPlace) {
        this.saveOrUpdate(algorithmDataPlace);
        return new ResponseData("保存或更新成功");
    }

}
