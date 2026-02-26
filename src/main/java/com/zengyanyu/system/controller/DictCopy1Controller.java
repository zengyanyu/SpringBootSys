package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.DictCopy1;
import com.zengyanyu.system.service.IDictCopy1Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2026-02-26
 */
@RestController
@Api(tags = "控制器")
@RequestMapping("/system/dict-copy1")
@Slf4j
public class DictCopy1Controller extends BaseController {

    @Resource
    private IDictCopy1Service dictCopy1Service;

    @LogRecord("保存或更新")
    @ApiOperation("保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody DictCopy1 dictCopy1) {
        return dictCopy1Service.saveOrUpdateDictCopy1(dictCopy1);
    }

    @LogRecord("删除")
    @ApiOperation("删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        dictCopy1Service.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        dictCopy1Service.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<DictCopy1>> findAll() {
        return new ResponseData("查询所有数据", dictCopy1Service.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<DictCopy1> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", dictCopy1Service.getById(id));
    }

    @LogRecord("分页查询数据")
    @ApiOperation("分页查询数据")
    @GetMapping("/page")
    public Page<DictCopy1> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<DictCopy1> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return dictCopy1Service.page(new Page<>(pageNum, pageSize), wrapper);
    }
}

