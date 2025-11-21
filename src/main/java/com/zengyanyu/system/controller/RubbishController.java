package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.Rubbish;
import com.zengyanyu.system.query.RubbishQueryObject;
import com.zengyanyu.system.service.IRubbishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-11-21
 */
@Slf4j
@RestController
@Api(tags = "垃圾控制器")
@RequestMapping("/rubbish")
public class RubbishController extends BaseController {

    @Resource
    private IRubbishService rubbishService;

    @LogRecord("保存或更新垃圾")
    @ApiOperation("保存或更新垃圾")
    @PostMapping("/save")
    public ResponseData save(@RequestBody Rubbish rubbish) {
        return rubbishService.saveOrUpdateRubbish(rubbish);
    }

    @LogRecord("删除垃圾")
    @ApiOperation("删除垃圾")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        rubbishService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除垃圾")
    @ApiOperation("批量删除垃圾")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        rubbishService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<Rubbish>> findAll() {
        return new ResponseData("查询所有数据", rubbishService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<Rubbish> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", rubbishService.getById(id));
    }

    @LogRecord("垃圾分页查询数据")
    @ApiOperation("垃圾分页查询数据")
    @GetMapping("/page")
    public Page<Rubbish> page(RubbishQueryObject queryObject) {
        QueryWrapper<Rubbish> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryObject.getName())) {
            wrapper.like("name", queryObject.getName());
        }
        return rubbishService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

