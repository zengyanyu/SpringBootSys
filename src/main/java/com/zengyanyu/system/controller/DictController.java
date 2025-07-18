package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-18
 */
@RestController
@Api(tags = "数据字典控制器")
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Resource
    private IDictService dictService;

    @LogRecord("保存或更新数据字典")
    @ApiOperation("保存或更新数据字典")
    @PostMapping("/save")
    public ResponseData save(@RequestBody Dict dict) {
        return dictService.saveOrUpdateDict(dict);
    }

    @LogRecord("删除数据字典")
    @ApiOperation("删除数据字典")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        dictService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除数据字典")
    @ApiOperation("批量删除数据字典")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        dictService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<Dict>> findAll() {
        return new ResponseData("根据ID查询指定数据", dictService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<Dict> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", dictService.getById(id));
    }

    @LogRecord("数据字典分页查询数据")
    @ApiOperation("数据字典分页查询数据")
    @GetMapping("/page")
    public Page<Dict> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        return dictService.page(new Page<>(pageNum, pageSize), wrapper);
    }
}

