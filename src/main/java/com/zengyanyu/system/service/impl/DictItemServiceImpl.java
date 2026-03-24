/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.DictItem;
import com.zengyanyu.system.mapper.DictItemMapper;
import com.zengyanyu.system.service.IDictItemService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典项 服务实现类
 *
 * @author zengyanyu
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements IDictItemService {

    /**
     * 保存或更新数据字典项
     *
     * @param dictItem 数据字典项
     * @return
     */
    @Override
    public ResponseData saveOrUpdateDictItem(DictItem dictItem) {
        this.saveOrUpdate(dictItem);
        return new ResponseData("保存或更新成功");
    }

    /**
     * 根据字典ID查询字典项数据
     *
     * @param dictId
     * @return
     */
    @Override
    public ResponseData getDictItemByDictId(String dictId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("dict_id", dictId);
        List<DictItem> dictItems = this.listByMap(columnMap);
        return new ResponseData(ResponseData.SUCCEED, "根据字典ID查询字典项数据", dictItems);
    }

}
