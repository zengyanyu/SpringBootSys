package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.DictCopy1;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 字典Copy对象 服务类
 *
 * @author zengyanyu
 * @since 2026-02-05
 */
public interface IDictCopy1Service extends IService<DictCopy1> {

    /**
     * 保存或更新字典Copy对象
     *
     * @param dictCopy1 字典Copy对象
     * @return
     */
    ResponseData saveOrUpdateDictCopy1(DictCopy1 dictCopy1);

}
