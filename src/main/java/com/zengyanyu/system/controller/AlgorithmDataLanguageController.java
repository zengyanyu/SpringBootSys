package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.AlgorithmDataLanguage;
import com.zengyanyu.system.query.AlgorithmDataLanguageQueryObject;
import com.zengyanyu.system.service.IAlgorithmDataLanguageService;
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
@Api(tags = "算法编程语言控制器")
@RequestMapping("/algorithm-data-language")
public class AlgorithmDataLanguageController extends BaseController {

    @Resource
    private IAlgorithmDataLanguageService algorithmDataLanguageService;

    @LogRecord("保存或更新算法编程语言")
    @ApiOperation("保存或更新算法编程语言")
    @PostMapping("/save")
    public ResponseData save(@RequestBody AlgorithmDataLanguage algorithmDataLanguage) {
        return algorithmDataLanguageService.saveOrUpdateAlgorithmDataLanguage(algorithmDataLanguage);
    }

    @LogRecord("删除算法编程语言")
    @ApiOperation("删除算法编程语言")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        algorithmDataLanguageService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除算法编程语言")
    @ApiOperation("批量删除算法编程语言")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        algorithmDataLanguageService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<AlgorithmDataLanguage>> findAll() {
        return new ResponseData("查询所有数据", algorithmDataLanguageService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<AlgorithmDataLanguage> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", algorithmDataLanguageService.getById(id));
    }

    @LogRecord("算法编程语言分页查询数据")
    @ApiOperation("算法编程语言分页查询数据")
    @GetMapping("/page")
    public Page<AlgorithmDataLanguage> page(AlgorithmDataLanguageQueryObject queryObject) {
        QueryWrapper<AlgorithmDataLanguage> wrapper = new QueryWrapper<>();
        return algorithmDataLanguageService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

