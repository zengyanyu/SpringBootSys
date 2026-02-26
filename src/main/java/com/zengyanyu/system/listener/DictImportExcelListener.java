package com.zengyanyu.system.listener;

import com.zengyanyu.system.dto.DictImportExcelDto;
import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.service.IDictService;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典导入Excel监听器
 *
 * @author zengyanyu
 */
public class DictImportExcelListener extends BaseImportExcelListener<DictImportExcelDto> {

    private final IDictService dictService;

    public DictImportExcelListener(IDictService dictService) {
        this.dictService = dictService;
    }

    @Override
    protected boolean isDataExist(DictImportExcelDto data) {
        return dictService.selectDataByCondition(data.getCode());
    }

    @Override
    protected void saveData() {
        logger.info("执行批量处理,数据量: {}", cachedDataList.size());

        List<Dict> dictList = new ArrayList<>();
        for (DictImportExcelDto dto : cachedDataList) {
            Dict dict = new Dict();
            BeanUtils.copyProperties(dto, dict);
            dictList.add(dict);
        }

        dictService.batchSave(dictList);
    }
}
