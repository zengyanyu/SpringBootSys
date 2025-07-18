package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.zengyanyu.system.service.ILogRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-16
 */
@RestController
@Api(tags = "控制器")
@RequestMapping("/log-record-entity")
public class LogRecordController extends BaseController {

    @Resource
    private ILogRecordService logRecordEntityService;

    @LogRecord("保存或更新")
    @ApiOperation("保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody LogRecordEntity logRecordEntity) {
        return logRecordEntityService.saveOrUpdateLogRecord(logRecordEntity);
    }

    @LogRecord("删除")
    @ApiOperation("删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        logRecordEntityService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        logRecordEntityService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<LogRecordEntity>> findAll() {
        return new ResponseData("根据ID查询指定数据", logRecordEntityService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<LogRecordEntity> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", logRecordEntityService.getById(id));
    }

    @LogRecord("分页查询数据")
    @ApiOperation("分页查询数据")
    @GetMapping("/page")
    public Page<LogRecordEntity> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<LogRecordEntity> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return logRecordEntityService.page(new Page<>(pageNum, pageSize), wrapper);
    }

}

