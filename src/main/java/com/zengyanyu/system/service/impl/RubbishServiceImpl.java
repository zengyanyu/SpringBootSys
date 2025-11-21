package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.Rubbish;
import com.zengyanyu.system.mapper.RubbishMapper;
import com.zengyanyu.system.service.IRubbishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 垃圾 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-21
 */
@Service
public class RubbishServiceImpl extends ServiceImpl<RubbishMapper, Rubbish> implements IRubbishService {

    /**
     * 保存或更新垃圾
     *
     * @param rubbish 垃圾
     * @return
     */
    @Override
    public ResponseData saveOrUpdateRubbish(Rubbish rubbish) {
        this.saveOrUpdate(rubbish);
        return new ResponseData("保存或更新成功");
    }

}
