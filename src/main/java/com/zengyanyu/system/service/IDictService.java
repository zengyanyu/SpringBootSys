package com.zengyanyu.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.entity.Dict;

import java.util.List;

/**
 * 数据字典 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IDictService extends IService<Dict>, IImportExcelService {

    /**
     * 保存或更新数据字典
     *
     * @param dict 数据字典
     * @return
     */
    ResponseData saveOrUpdateDict(Dict dict);

    /**
     * 批量保存
     *
     * @param dictList
     */
    void batchSave(List<Dict> dictList);

    /**
     * @param code
     * @return
     */
    Boolean selectDataByCondition(String code);
}
