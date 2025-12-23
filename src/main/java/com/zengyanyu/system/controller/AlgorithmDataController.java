package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.AlgorithmData;
import com.zengyanyu.system.query.AlgorithmDataQueryObject;
import com.zengyanyu.system.service.IAlgorithmDataService;
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
 * @since 2025-11-19
 */
@RestController
@Api(tags = "算法数据控制器")
@RequestMapping("/algorithm-data")
@Slf4j
public class AlgorithmDataController extends BaseController {

    @Resource
    private IAlgorithmDataService algorithmDataService;

    @LogRecord("保存或更新算法数据")
    @ApiOperation("保存或更新算法数据")
    @PostMapping("/save")
    public ResponseData save(@RequestBody AlgorithmData algorithmData) {
        return algorithmDataService.saveOrUpdateAlgorithmData(algorithmData);
    }

    @LogRecord("删除算法数据")
    @ApiOperation("删除算法数据")
    @GetMapping("/del/{id}")
    public ResponseData delete(@PathVariable String id) {
        algorithmDataService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除算法数据")
    @ApiOperation("批量删除算法数据")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        algorithmDataService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<AlgorithmData>> findAll() {
        return new ResponseData("查询所有数据", algorithmDataService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<AlgorithmData> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", algorithmDataService.getById(id));
    }

    @LogRecord("算法数据分页查询数据")
    @ApiOperation("算法数据分页查询数据")
    @GetMapping("/page")
    public Page<AlgorithmData> page(AlgorithmDataQueryObject queryObject) {
        QueryWrapper<AlgorithmData> wrapper = new QueryWrapper<>();
        return algorithmDataService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

