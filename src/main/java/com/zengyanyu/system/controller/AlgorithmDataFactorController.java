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

import com.zengyanyu.system.service.IAlgorithmDataFactorService;
import com.zengyanyu.system.entity.AlgorithmDataFactor;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.zengyanyu.system.controller.BaseController;

/**
 * @author zengyanyu
 * @since 2025-11-19
 */
@RestController
@Api(tags = "算法要素控制器")
@RequestMapping("/algorithm-data-factor")
@Slf4j
public class AlgorithmDataFactorController extends BaseController {

    @Resource
    private IAlgorithmDataFactorService algorithmDataFactorService;

    @LogRecord("保存或更新算法要素")
    @ApiOperation("保存或更新算法要素")
    @PostMapping("/save")
    public ResponseData save(@RequestBody AlgorithmDataFactor algorithmDataFactor) {
        return algorithmDataFactorService.saveOrUpdateAlgorithmDataFactor(algorithmDataFactor);
    }

    @LogRecord("删除算法要素")
    @ApiOperation("删除算法要素")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        algorithmDataFactorService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除算法要素")
    @ApiOperation("批量删除算法要素")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        algorithmDataFactorService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<AlgorithmDataFactor>> findAll() {
        return new ResponseData("查询所有数据", algorithmDataFactorService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<AlgorithmDataFactor> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", algorithmDataFactorService.getById(id));
    }

    @LogRecord("算法要素分页查询数据")
    @ApiOperation("算法要素分页查询数据")
    @GetMapping("/page")
    public Page<AlgorithmDataFactor> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<AlgorithmDataFactor> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return algorithmDataFactorService.page(new Page<>(pageNum, pageSize), wrapper);
    }
}

