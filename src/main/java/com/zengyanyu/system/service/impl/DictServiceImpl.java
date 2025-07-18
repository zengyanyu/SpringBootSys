package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.mapper.DictMapper;
import com.zengyanyu.system.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 数据字典 服务实现类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    /**
     * 保存或更新数据字典
     *
     * @param dict 数据字典
     * @return
     */
    @Override
    public ResponseData saveOrUpdateDict(Dict dict) {
        this.saveOrUpdate(dict);
        return new ResponseData("保存或更新成功");
    }

}
