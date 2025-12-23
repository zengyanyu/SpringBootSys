package com.zengyanyu.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.DictImportExcelDto;
import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.listener.DictImportExcelListener;
import com.zengyanyu.system.mapper.DictMapper;
import com.zengyanyu.system.service.IDictService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

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

    /**
     * 批量保存
     *
     * @param dictList
     */
    @Override
    public void batchSave(List<Dict> dictList) {
        // 调用service接口中的方法(框架封装)
        this.saveBatch(dictList);
    }

    /**
     * 导入Excel文件
     *
     * @param inputStream 文件输入流
     */
    @Override
    public void importExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, DictImportExcelDto.class, new DictImportExcelListener(this))
                .charset(Charset.forName("UTF-8"))// 设置中文编码
                .sheet()// 读取第一个sheet(默认索引0,可指定sheetName .sheet("字典表"))
                .doRead();// 执行读取
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Boolean selectDataByCondition(String code) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code);
        return null != this.getOne(wrapper);
    }

}
