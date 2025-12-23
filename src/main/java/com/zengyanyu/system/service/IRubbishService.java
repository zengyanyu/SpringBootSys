package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Rubbish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 垃圾 服务类
 *
 * @author zengyanyu
 * @since 2025-11-21
 */
public interface IRubbishService extends IService<Rubbish> {

    /**
     * 保存或更新垃圾
     *
     * @param rubbish 垃圾
     * @return
     */
    ResponseData saveOrUpdateRubbish(Rubbish rubbish);
}
