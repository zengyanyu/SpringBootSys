package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

import java.io.InputStream;
import java.util.List;

/**
 * 数据字典 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IDictService extends IService<Dict> {

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
     * 导入Excel文件
     *
     * @param inputStream 文件输入流
     */
    void importExcel(InputStream inputStream);

    /**
     * @param code
     * @return
     */
    Boolean selectDataByCondition(String code);
}
