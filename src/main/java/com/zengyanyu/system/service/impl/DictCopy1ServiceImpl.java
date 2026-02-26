package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.DictCopy1;
import com.zengyanyu.system.mapper.DictCopy1Mapper;
import com.zengyanyu.system.service.IDictCopy1Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 *  服务实现类
 *
 * @author zengyanyu
 * @since 2026-02-26
 */
@Service
public class DictCopy1ServiceImpl extends ServiceImpl<DictCopy1Mapper, DictCopy1> implements IDictCopy1Service {

    /**
     * 保存或更新
     *
     * @param dictCopy1 
     * @return
     */
    @Override
    public ResponseData saveOrUpdateDictCopy1(DictCopy1 dictCopy1) {
        this.saveOrUpdate(dictCopy1);
        return new ResponseData("保存或更新成功");
    }

}
