package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.RubbishBeach;
import com.zengyanyu.system.query.RubbishBeachQueryObject;
import com.zengyanyu.system.service.IRubbishBeachService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-11-21
 */
@Slf4j
@RestController
@Api(tags = "控制器")
@RequestMapping("/rubbish-beach")
public class RubbishBeachController extends BaseController {

    @Resource
    private IRubbishBeachService rubbishBeachService;

    @LogRecord("保存或更新")
    @ApiOperation("保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody RubbishBeach rubbishBeach) {
        return rubbishBeachService.saveOrUpdateRubbishBeach(rubbishBeach);
    }

    @LogRecord("删除")
    @ApiOperation("删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        rubbishBeachService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        rubbishBeachService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<RubbishBeach>> findAll() {
        return new ResponseData("查询所有数据", rubbishBeachService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<RubbishBeach> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", rubbishBeachService.getById(id));
    }

    @LogRecord("分页查询数据")
    @ApiOperation("分页查询数据")
    @GetMapping("/page")
    public Page<RubbishBeach> page(RubbishBeachQueryObject queryObject) {
        QueryWrapper<RubbishBeach> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return rubbishBeachService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

