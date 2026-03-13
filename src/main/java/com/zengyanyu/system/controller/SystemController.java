/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.System;
import com.zengyanyu.system.query.SystemQueryObject;
import com.zengyanyu.system.service.ISystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2026-03-13
 */
@Slf4j
@RestController
@Api(tags = "系统控制器")
@RequestMapping("/system")
public class SystemController extends BaseController {

    @Resource
    private ISystemService systemService;

    @LogRecord("保存或更新系统")
    @ApiOperation("保存或更新系统")
    @PostMapping("/save")
    public ResponseData save(@RequestBody System system) {
        return systemService.saveOrUpdateSystem(system);
    }

    @LogRecord("删除系统")
    @ApiOperation("删除系统")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        systemService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除系统")
    @ApiOperation("批量删除系统")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        systemService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<System>> findAll() {
        return new ResponseData("查询所有数据", systemService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<System> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", systemService.getById(id));
    }

    @LogRecord("系统分页查询数据")
    @ApiOperation("系统分页查询数据")
    @GetMapping("/page")
    public Page<System> page(SystemQueryObject queryObject) {
        QueryWrapper<System> wrapper = new QueryWrapper<>();
        return systemService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}
