package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.AlgorithmDataType;
import com.zengyanyu.system.query.AlgorithmDataTypeQueryObject;
import com.zengyanyu.system.service.IAlgorithmDataTypeService;
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
@Api(tags = "算法类型控制器")
@RequestMapping("/algorithm-data-type")
public class AlgorithmDataTypeController extends BaseController {

    @Resource
    private IAlgorithmDataTypeService algorithmDataTypeService;

    @LogRecord("保存或更新算法类型")
    @ApiOperation("保存或更新算法类型")
    @PostMapping("/save")
    public ResponseData save(@RequestBody AlgorithmDataType algorithmDataType) {
        return algorithmDataTypeService.saveOrUpdateAlgorithmDataType(algorithmDataType);
    }

    @LogRecord("删除算法类型")
    @ApiOperation("删除算法类型")
    @GetMapping("/del/{id}")
    public ResponseData delete(@PathVariable String id) {
        algorithmDataTypeService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除算法类型")
    @ApiOperation("批量删除算法类型")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        algorithmDataTypeService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<AlgorithmDataType>> findAll() {
        return new ResponseData("查询所有数据", algorithmDataTypeService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<AlgorithmDataType> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", algorithmDataTypeService.getById(id));
    }

    @LogRecord("算法类型分页查询数据")
    @ApiOperation("算法类型分页查询数据")
    @GetMapping("/page")
    public Page<AlgorithmDataType> page(AlgorithmDataTypeQueryObject queryObject) {
        QueryWrapper<AlgorithmDataType> wrapper = new QueryWrapper<>();
        return algorithmDataTypeService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

