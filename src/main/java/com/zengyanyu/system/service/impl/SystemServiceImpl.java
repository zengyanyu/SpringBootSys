package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.System;
import com.zengyanyu.system.mapper.SystemMapper;
import com.zengyanyu.system.service.ISystemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 系统 服务实现类
 *
 * @author zengyanyu
 * @since 2026-03-13
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements ISystemService {

    /**
     * 保存或更新系统
     *
     * @param system 系统
     * @return
     */
    @Override
    public ResponseData saveOrUpdateSystem(System system) {
        this.saveOrUpdate(system);
        return new ResponseData("保存或更新成功");
    }

}
