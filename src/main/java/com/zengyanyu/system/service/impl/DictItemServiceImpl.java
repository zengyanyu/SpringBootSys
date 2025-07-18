package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.DictItem;
import com.zengyanyu.system.mapper.DictItemMapper;
import com.zengyanyu.system.service.IDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 数据字典项 服务实现类
 *
 * @author zengyanyu
 * @since 2025-07-18
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

}
