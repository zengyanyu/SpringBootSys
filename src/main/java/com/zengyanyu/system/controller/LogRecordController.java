package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.zengyanyu.system.query.LogRecordQueryObject;
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
@Api(tags = "日志记录控制器")
@RequestMapping("/log-record-entity")
public class LogRecordController extends BaseController {

    @Resource
    private ILogRecordService logRecordEntityService;

    @LogRecord("日志记录删除")
    @ApiOperation("日志记录删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        logRecordEntityService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("日志记录批量删除")
    @ApiOperation("日志记录批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        logRecordEntityService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("日志记录分页查询数据")
    @ApiOperation("日志记录分页查询数据")
    @GetMapping("/page")
    public Page<LogRecordEntity> page(LogRecordQueryObject queryObject) {
        QueryWrapper<LogRecordEntity> wrapper = new QueryWrapper<>();
        return logRecordEntityService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

}

