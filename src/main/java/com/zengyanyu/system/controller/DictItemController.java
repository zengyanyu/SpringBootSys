package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.DictItem;
import com.zengyanyu.system.query.DictItemQueryObject;
import com.zengyanyu.system.service.IDictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-18
 */
@RestController
@Api(tags = "数据字典项控制器")
@RequestMapping("/dict-item")
@Slf4j
public class DictItemController extends BaseController {

    @Resource
    private IDictItemService dictItemService;

    @LogRecord("保存或更新数据字典项")
    @ApiOperation("保存或更新数据字典项")
    @PostMapping("/save")
    public ResponseData save(@RequestBody DictItem dictItem) {
        return dictItemService.saveOrUpdateDictItem(dictItem);
    }

    @LogRecord("删除数据字典项")
    @ApiOperation("删除数据字典项")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        dictItemService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除数据字典项")
    @ApiOperation("批量删除数据字典项")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        dictItemService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<DictItem>> findAll() {
        return new ResponseData("根据ID查询指定数据", dictItemService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<DictItem> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", dictItemService.getById(id));
    }

    @LogRecord("数据字典项分页查询数据")
    @ApiOperation("数据字典项分页查询数据")
    @GetMapping("/page")
    public Page<DictItem> page(DictItemQueryObject queryObject) {
        QueryWrapper<DictItem> wrapper = new QueryWrapper<>();
        return dictItemService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

}

