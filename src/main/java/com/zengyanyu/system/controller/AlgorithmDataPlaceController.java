package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.AlgorithmDataPlace;
import com.zengyanyu.system.query.AlgorithmDataPlaceQueryObject;
import com.zengyanyu.system.service.IAlgorithmDataPlaceService;
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
@Api(tags = "算法平台控制器")
@RequestMapping("/algorithm-data-place")
public class AlgorithmDataPlaceController extends BaseController {

    @Resource
    private IAlgorithmDataPlaceService algorithmDataPlaceService;

    @LogRecord("保存或更新算法平台")
    @ApiOperation("保存或更新算法平台")
    @PostMapping("/save")
    public ResponseData save(@RequestBody AlgorithmDataPlace algorithmDataPlace) {
        return algorithmDataPlaceService.saveOrUpdateAlgorithmDataPlace(algorithmDataPlace);
    }

    @LogRecord("删除算法平台")
    @ApiOperation("删除算法平台")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        algorithmDataPlaceService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除算法平台")
    @ApiOperation("批量删除算法平台")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        algorithmDataPlaceService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<AlgorithmDataPlace>> findAll() {
        return new ResponseData("查询所有数据", algorithmDataPlaceService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<AlgorithmDataPlace> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", algorithmDataPlaceService.getById(id));
    }

    @LogRecord("算法平台分页查询数据")
    @ApiOperation("算法平台分页查询数据")
    @GetMapping("/page")
    public Page<AlgorithmDataPlace> page(AlgorithmDataPlaceQueryObject queryObject) {
        QueryWrapper<AlgorithmDataPlace> wrapper = new QueryWrapper<>();
        return algorithmDataPlaceService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

