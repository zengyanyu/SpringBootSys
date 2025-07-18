package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.DictItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 数据字典项 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IDictItemService extends IService<DictItem> {

    /**
     * 保存或更新数据字典项
     *
     * @param dictItem 数据字典项
     * @return
     */
    ResponseData saveOrUpdateDictItem(DictItem dictItem);

}
