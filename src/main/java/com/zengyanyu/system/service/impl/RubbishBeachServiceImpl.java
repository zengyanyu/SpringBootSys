package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.RubbishBeach;
import com.zengyanyu.system.mapper.RubbishBeachMapper;
import com.zengyanyu.system.service.IRubbishBeachService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-21
 */
@Service
public class RubbishBeachServiceImpl extends ServiceImpl<RubbishBeachMapper, RubbishBeach> implements IRubbishBeachService {

    /**
     * 保存或更新
     *
     * @param rubbishBeach 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateRubbishBeach(RubbishBeach rubbishBeach) {
        this.saveOrUpdate(rubbishBeach);
        return new ResponseData("保存或更新成功");
    }

}
