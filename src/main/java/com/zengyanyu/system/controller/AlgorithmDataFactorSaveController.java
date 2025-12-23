package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.AlgorithmDataFactorSave;
import com.zengyanyu.system.query.AlgorithmDataFactorSaveQueryObject;
import com.zengyanyu.system.service.IAlgorithmDataFactorSaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-11-19
 */
@Slf4j
@RestController
@Api(tags = "算法要素对应关系表控制器")
@RequestMapping("/algorithm-data-factor-save")
public class AlgorithmDataFactorSaveController extends BaseController {

    @Resource
    private IAlgorithmDataFactorSaveService algorithmDataFactorSaveService;

    @LogRecord("保存或更新算法要素对应关系表")
    @ApiOperation("保存或更新算法要素对应关系表")
    @PostMapping("/save")
    public ResponseData save(@RequestBody AlgorithmDataFactorSave algorithmDataFactorSave) {
        return algorithmDataFactorSaveService.saveOrUpdateAlgorithmDataFactorSave(algorithmDataFactorSave);
    }

    @LogRecord("删除算法要素对应关系表")
    @ApiOperation("删除算法要素对应关系表")
    @GetMapping("/del/{id}")
    public ResponseData delete(@PathVariable String id) {
        algorithmDataFactorSaveService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除算法要素对应关系表")
    @ApiOperation("批量删除算法要素对应关系表")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        algorithmDataFactorSaveService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<AlgorithmDataFactorSave>> findAll() {
        return new ResponseData("查询所有数据", algorithmDataFactorSaveService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<AlgorithmDataFactorSave> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", algorithmDataFactorSaveService.getById(id));
    }

    @LogRecord("算法要素对应关系表分页查询数据")
    @ApiOperation("算法要素对应关系表分页查询数据")
    @GetMapping("/page")
    public Page<AlgorithmDataFactorSave> page(AlgorithmDataFactorSaveQueryObject queryObject) {
        QueryWrapper<AlgorithmDataFactorSave> wrapper = new QueryWrapper<>();
        return algorithmDataFactorSaveService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

