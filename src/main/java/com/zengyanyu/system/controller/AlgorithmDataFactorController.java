package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.AlgorithmDataFactor;
import com.zengyanyu.system.query.AlgorithmDataFactorQueryObject;
import com.zengyanyu.system.service.IAlgorithmDataFactorService;
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
@Api(tags = "算法要素控制器")
@RequestMapping("/algorithm-data-factor")
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
    @GetMapping("/del/{id}")
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
    public Page<AlgorithmDataFactor> page(AlgorithmDataFactorQueryObject queryObject) {
        QueryWrapper<AlgorithmDataFactor> wrapper = new QueryWrapper<>();
        return algorithmDataFactorService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

