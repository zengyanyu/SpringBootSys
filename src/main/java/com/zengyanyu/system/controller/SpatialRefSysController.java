package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.zengyanyu.system.service.ISpatialRefSysService;
import com.zengyanyu.system.entity.SpatialRefSys;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.zengyanyu.system.controller.BaseController;

/**
 * @author zengyanyu
 * @since 2025-11-19
 */
@RestController
@Api(tags = "控制器")
@RequestMapping("/spatial-ref-sys")
@Slf4j
public class SpatialRefSysController extends BaseController {

    @Resource
    private ISpatialRefSysService spatialRefSysService;

    @LogRecord("保存或更新")
    @ApiOperation("保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody SpatialRefSys spatialRefSys) {
        return spatialRefSysService.saveOrUpdateSpatialRefSys(spatialRefSys);
    }

    @LogRecord("删除")
    @ApiOperation("删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        spatialRefSysService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        spatialRefSysService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<SpatialRefSys>> findAll() {
        return new ResponseData("查询所有数据", spatialRefSysService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<SpatialRefSys> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", spatialRefSysService.getById(id));
    }

    @LogRecord("分页查询数据")
    @ApiOperation("分页查询数据")
    @GetMapping("/page")
    public Page<SpatialRefSys> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<SpatialRefSys> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return spatialRefSysService.page(new Page<>(pageNum, pageSize), wrapper);
    }
}

